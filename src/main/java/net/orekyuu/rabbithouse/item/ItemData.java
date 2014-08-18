package net.orekyuu.rabbithouse.item;

/**
 * アイテムを表すデータ
 */
public class ItemData {
    private String name;
    private String icon;
    private String className;
    private int maxStackSize = 64;
    private int maxDamage;
    private boolean is3D;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ItemData{");
        sb.append("is3D=").append(is3D);
        sb.append(", name='").append(name).append('\'');
        sb.append(", icon='").append(icon).append('\'');
        sb.append(", className='").append(className).append('\'');
        sb.append(", maxStackSize=").append(maxStackSize);
        sb.append(", maxDamage=").append(maxDamage);
        sb.append('}');
        return sb.toString();
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