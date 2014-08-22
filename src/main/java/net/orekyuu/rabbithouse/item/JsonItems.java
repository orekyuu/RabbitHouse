package net.orekyuu.rabbithouse.item;

import java.util.Arrays;

/**
 * Itemのデータのまとまり
 */
class JsonItems {
    private ItemData[] items;

    @Override
    public String toString() {
        return new StringBuilder().append("JsonItems{").append("items=").append(Arrays.toString(items)).append('}').toString();
    }

    public ItemData[] getItems() {
        return items;
    }
}
