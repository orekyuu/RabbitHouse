package net.orekyuu.rabbithouse.block;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

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
     * @see net.orekyuu.rabbithouse.block.BlockField
     */
    public BlockLoader(Object container, String modId) {
        this.container = container;
        this.modId = modId;
    }

    /**
     * Block.jsonからデータをロードし、完成したインスタンスをコンストラクタで与えられたインスタンスにDIします。
     */
    public void load() {
        JsonBlocks blocks = loadJsonBlocks();

        for (Field field : container.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            for (Annotation annotation : field.getDeclaredAnnotations()) {
                if (annotation instanceof BlockField) {
                    BlockField blockField = (BlockField) annotation;
                    setValue(blockField.name(), field, blocks);
                }
            }
        }
    }

    private void setValue(String name, Field field, JsonBlocks blocks) {
        for (BlockData blockData : blocks.getBlocks()) {
            if (blockData.getName().equals(name)) {
                Block block = getBlock(blockData);
                try {
                    field.set(container, block);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private JsonBlocks loadJsonBlocks() {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream("Blocks.json")))
        );
        return gson.fromJson(reader, JsonBlocks.class);
    }

    private Block getBlock(BlockData blockData) {
        Block block = new SimpleBlock(Material.sand);
        if (blockData.getName() == null)
            throw new UnsupportedOperationException(blockData.getName() + "のname要素を指定してください。");
        if (blockData.getResource() == null)
            throw new UnsupportedOperationException(blockData.getName() + "のresource要素を指定してください。");

        block.setBlockName(blockData.getName());
        block.setBlockTextureName(blockData.getResource());
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
            throw new UnsupportedOperationException(blockData.getName() + "のitem要素の値が不正です。");
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