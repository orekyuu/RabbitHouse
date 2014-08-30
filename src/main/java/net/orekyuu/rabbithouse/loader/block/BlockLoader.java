package net.orekyuu.rabbithouse.loader.block;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.orekyuu.rabbithouse.loader.Initializable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

/**
 * Blockのロードを行うクラスです。
 */
public class BlockLoader {

    private Object container;
    private String modId;

    /**
     * Block.jsonのロードを行います<br>
     * 引数のインスタンスの@Blockアノテーションが付加されているフィールドにDIを行います。
     *
     * @param container DIを行うインスタンス
     * @param modId     modId
     * @see net.orekyuu.rabbithouse.loader.block.BlockField
     */
    public BlockLoader(Object container, String modId) {
        this.container = container;
        this.modId = modId;
    }

    /**
     * Block.jsonからデータをロードし、完成したインスタンスをコンストラクタで与えられたインスタンスにDIします。<br>
     * 不正な値を渡していた場合や、使用していないブロックデータがある場合は例外を発生させてプログラムを終了します。
     */
    public void load() {
        JsonBlocks blocks = loadJsonBlocks();
        List<BlockData> dataList = new LinkedList<>();
        for (BlockData blockData : blocks.getBlocks()) {
            //重複チェックして重複があれば例外を発生
            for (BlockData data : dataList) {
                if (data.getName().equals(blockData.getName()))
                    throw new BlockDataFormatException(data.getName() + "ブロックは定義済みです");
            }
            dataList.add(blockData);
        }

        for (Field field : container.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            for (Annotation annotation : field.getDeclaredAnnotations()) {
                if (annotation instanceof BlockField) {
                    BlockField blockField = (BlockField) annotation;
                    setValue(blockField.name(), field, dataList);
                }
            }
        }

        //使用していないブロックデータがあれば例外を吐いて終了させる
        if (dataList.size() != 0) {
            StringBuilder sb = new StringBuilder();
            for (BlockData blockData : dataList) {
                sb.append("\"").append(blockData.getName()).append("\"は使用されていません。 ");
            }
            throw new BlockDataFormatException(sb.toString());
        }
    }

    private void setValue(String name, Field field, List<BlockData> dataList) {
        BlockData data = null;
        for (BlockData blockData : dataList) {
            if (blockData.getName().equals(name)) {
                Block block = getBlock(blockData);
                if (!field.getType().isInstance(block)) {
                    throw new BlockDataFormatException(block.getClass().getName() + "を" + field.getType().getName() + "にキャストできません。\nブロック名: " + blockData.getName());
                }

                try {
                    field.set(container, block);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                data = blockData;
                break;
            }
        }
        if (data != null)
            dataList.remove(data);
    }

    private JsonBlocks loadJsonBlocks() {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream("Blocks.json")))
        );
        return gson.fromJson(reader, JsonBlocks.class);
    }

    private Block getBlock(BlockData blockData) {
        Block block = null;
        if (blockData.getClassName() != null) {
            try {
                block = (Block) Class.forName(blockData.getClassName()).getConstructor(Material.class).newInstance(Material.rock);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                System.exit(-1);//正常に作成できなければ失敗
            } catch (NoSuchMethodException e) {
                throw new BlockDataFormatException("Materialを引数に取るコンストラクタがありません。");
            } catch (ClassNotFoundException e) {
                throw new BlockDataFormatException(blockData.getClassName() + "クラスが見つかりません");
            }
        } else {
            block = new SimpleBlock(Material.rock);
        }
        if (blockData.getName() == null)
            throw new BlockDataFormatException(blockData.getName() + "のname要素を指定してください。");
        if (blockData.getResource() == null)
            throw new BlockDataFormatException(blockData.getName() + "のresource要素を指定してください。");

        block.setBlockName(blockData.getName());
        block.setBlockTextureName(blockData.getResource());
        if (blockData.getHarvestLevel() != null)
            for (HarvestLevel harvestLevel : blockData.getHarvestLevel()) {
                block.setHarvestLevel(harvestLevel.getKey(), harvestLevel.getValue());
            }
        block.setResistance(blockData.getResistance());
        block.setHardness(blockData.getHardness());
        block.setLightLevel(blockData.getLightLevel());
        block.setLightOpacity(blockData.getLightOpacity());

        Class<? extends ItemBlock> item;
        try {
            if (blockData.getItem() != null)
                item = (Class<? extends ItemBlock>) Class.forName(blockData.getItem());
            else
                item = ItemBlock.class;
        } catch (ClassNotFoundException e) {
            //ここに来たらおしまい。
            throw new BlockDataFormatException(blockData.getName() + "のitem要素の値が不正です。");
        }

        if (!blockData.getArgs().isEmpty()) {
            if (!(block instanceof Initializable))
                throw new BlockDataFormatException(blockData.getClassName()
                        + "は引数を受け取るため、Initializableを実装する必要があります。");
            else {
                ((Initializable) block).initialize(blockData.getArgs());
            }

        }
        GameRegistry.registerBlock(block, item, blockData.getName(), modId);
        return block;
    }

    class SimpleBlock extends Block {

        protected SimpleBlock(Material material) {
            super(material);
        }
    }
}