package net.orekyuu.rabbithouse.loader.item;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;
import java.util.List;

/**
 * アイテムを表すデータ
 */
public class ItemData {
    private String name;
    private String icon;
    @SerializedName("class")
    private String className;
    private int maxStackSize = 64;
    private int maxDamage;
    private boolean is3D;
    private List<String> args = new LinkedList<>();

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ItemData{");
        sb.append("is3D=").append(is3D);
        sb.append(", name='").append(name).append('\'');
        sb.append(", icon='").append(icon).append('\'');
        sb.append(", class='").append(className).append('\'');
        sb.append(", args='").append(args == null ? "[]" : args.toString()).append('\'');
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

    public void setName(String name) {
        this.name = name;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setMaxStackSize(int maxStackSize) {
        this.maxStackSize = maxStackSize;
    }

    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }

    public void setIs3D(boolean is3D) {
        this.is3D = is3D;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }
}