package net.orekyuu.rabbithouse.loader.item;

import java.util.LinkedList;
import java.util.List;

/**
 * Itemのデータのまとまり
 */
public class JsonItems {
    private List<ItemData> items = new LinkedList<>();

    @Override
    public String toString() {
        return new StringBuilder().append("JsonItems{").append("items=").append(items).append('}').toString();
    }

    public List<ItemData> getItems() {
        return items;
    }
}
