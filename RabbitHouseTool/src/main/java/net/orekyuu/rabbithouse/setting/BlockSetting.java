package net.orekyuu.rabbithouse.setting;

import java.util.LinkedList;
import java.util.List;

/**
 * ブロックに関する設定
 */
public class BlockSetting {
    //@Expose
    private List<String> blockClass = new LinkedList<>();
    //@Expose
    private List<String> blockItemClass = new LinkedList<>();

    public List<String> getBlockClass() {
        return blockClass;
    }

    public List<String> getBlockItemClass() {
        return blockItemClass;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BlockSetting{");
        sb.append("blockClass=").append(blockClass);
        sb.append(", blockItemClass=").append(blockItemClass);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockSetting)) return false;

        BlockSetting that = (BlockSetting) o;

        if (blockClass != null ? !blockClass.equals(that.blockClass) : that.blockClass != null) return false;
        if (blockItemClass != null ? !blockItemClass.equals(that.blockItemClass) : that.blockItemClass != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = blockClass != null ? blockClass.hashCode() : 0;
        result = 31 * result + (blockItemClass != null ? blockItemClass.hashCode() : 0);
        return result;
    }
}
