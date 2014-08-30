package net.orekyuu.rabbithouse.loader.item;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.orekyuu.rabbithouse.loader.Initializable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

/**
 * Itemの情報を読み込むクラス
 */
public class ItemLoader {
    private Object container;
    private String modId;

    public ItemLoader(Object container, String modId) {
        this.container = container;
        this.modId = modId;
    }

    public void load() {
        JsonItems items = loadJsonItems();
        List<ItemData> dataList = new LinkedList<>();
        for (ItemData itemData : items.getItems()) {
            //重複チェックして重複があれば例外を発生
            for (ItemData data : dataList) {
                if (data.getName().equals(itemData.getName()))
                    throw new ItemDataFormatException(data.getName() + "は定義済みです");
            }
            dataList.add(itemData);
        }

        for (Field field : container.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            for (Annotation annotation : field.getDeclaredAnnotations()) {
                if (annotation instanceof ItemField) {
                    ItemField itemField = (ItemField) annotation;
                    setValue(itemField.name(), field, dataList);
                }
            }
        }

        //使用していないデータがあれば例外をはいて終了
        if (dataList.size() != 0) {
            StringBuilder sb = new StringBuilder();
            for (ItemData blockData : dataList) {
                sb.append("\"").append(blockData.getName()).append("\"は使用されていません。 ");
            }
            throw new ItemDataFormatException(sb.toString());
        }
    }

    private void setValue(String name, Field field, List<ItemData> dataList) {
        ItemData data = null;
        for (ItemData itemData : dataList) {
            if (itemData.getName().equals(name)) {
                Item item = getItem(itemData);
                if(!field.getType().isInstance(item)) {
                    throw new ItemDataFormatException(item.getClass().getName() + "を" + field.getType().getName() + "にキャストできません。\nアイテム名: " + itemData.getName());
                }

                try {
                    field.set(container, item);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                data = itemData;
            }
        }
        if(data != null)
            dataList.remove(data);
    }

    private Item getItem(ItemData itemData) {
        Item item = null;
        if (itemData.getClassName() != null) {
            try {
                item = (Item) Class.forName(itemData.getClassName()).getConstructor().newInstance();
            } catch (ClassNotFoundException e) {
                throw new ItemDataFormatException(itemData.getClassName() + "クラスが見つかりません");
            } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
                System.exit(-1);//例外発生時は終了させる
            }
        } else {
            item = new Item();
        }
        if (itemData.getName() == null) {
            throw new ItemDataFormatException("name要素を指定してください");
        }
        if (itemData.getIcon() == null) {
            throw new ItemDataFormatException(itemData.getName() + "のicon要素を指定してください");
        }
        if(itemData.getMaxStackSize() < 1) {
            throw new ItemDataFormatException(itemData.getName() + "のmaxStackSizeには1以上の値を指定してください");
        }

        item.setUnlocalizedName(itemData.getName());
        item.setTextureName(itemData.getIcon());
        item.setMaxStackSize(itemData.getMaxStackSize());
        item.setMaxDamage(itemData.getMaxDamage());
        if (itemData.isIs3D()) {
            item.setFull3D();
        }

        if (itemData.getArgs() != null) {
            if (!(item instanceof Initializable))
                throw new ItemDataFormatException(itemData.getClassName()
                        + "は引数を受け取るため、Initializableを実装する必要があります。");
            else {
                ((Initializable) item).initialize(itemData.getArgs());
            }

        }
        GameRegistry.registerItem(item, itemData.getName(), modId);
        return item;
    }

    private JsonItems loadJsonItems() {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream("Items.json")))
        );
        return gson.fromJson(reader, JsonItems.class);
    }
}
