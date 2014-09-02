package net.orekyuu.rabbithouse.setting;

import java.util.LinkedList;
import java.util.List;

/**
 * アイテムに関する設定
 */
public class ItemSetting {
    private List<String> itemClass = new LinkedList<>();

    public List<String> getItemClass() {
        return itemClass;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ItemSetting{");
        sb.append("itemClass=").append(itemClass);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemSetting)) return false;

        ItemSetting that = (ItemSetting) o;

        if (itemClass != null ? !itemClass.equals(that.itemClass) : that.itemClass != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return itemClass != null ? itemClass.hashCode() : 0;
    }
}
