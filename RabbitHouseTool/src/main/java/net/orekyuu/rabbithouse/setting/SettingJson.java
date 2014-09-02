package net.orekyuu.rabbithouse.setting;

/**
 * 設定ファイル
 */
public class SettingJson {
    private ItemSetting itemSetting = new ItemSetting();
    private BlockSetting blockSetting = new BlockSetting();

    public ItemSetting getItemSetting() {
        return itemSetting;
    }

    public BlockSetting getBlockSetting() {
        return blockSetting;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SettingJson{");
        sb.append("itemSetting=").append(itemSetting);
        sb.append(", blockSetting=").append(blockSetting);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SettingJson)) return false;

        SettingJson that = (SettingJson) o;

        if (blockSetting != null ? !blockSetting.equals(that.blockSetting) : that.blockSetting != null) return false;
        if (itemSetting != null ? !itemSetting.equals(that.itemSetting) : that.itemSetting != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = itemSetting != null ? itemSetting.hashCode() : 0;
        result = 31 * result + (blockSetting != null ? blockSetting.hashCode() : 0);
        return result;
    }
}
