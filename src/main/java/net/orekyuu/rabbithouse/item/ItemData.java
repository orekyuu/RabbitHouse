package net.orekyuu.rabbithouse.item;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * アイテムを表すデータ
 */
class ItemData {
    private String name;
    private String icon;
    @SerializedName("class")
    private String className;
    private int maxStackSize = 64;
    private int maxDamage;
    private boolean is3D;
    private List<String> args;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ItemData{");
        sb.append("is3D=").append(is3D);
        sb.append(", name='").append(name).append('\'');
        sb.append(", icon='").append(icon).append('\'');
        sb.append(", class='").append(className).append('\'');
        sb.append(", args='").append(args.toString()).append('\'');
        sb.append(", maxStackSize=").append(maxStackSize);
        sb.append(", maxDamage=").append(maxDamage);
        sb.append('}');
        return sb.toString();
    }

    public List<String> getArgs() {
        return args;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public String getClassName() {
        return className;
    }

    public int getMaxStackSize() {
        return maxStackSize;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public boolean isIs3D() {
        return is3D;
    }
}