package net.orekyuu.rabbithouse.block;

import java.util.LinkedList;
import java.util.List;

public class JsonBlocks {
    private List<BlockData> blocks = new LinkedList<>();

    public List<BlockData> getBlocks() {
        return blocks;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("JsonBlocks{");
        sb.append("blocks=").append(blocks);
        sb.append('}');
        return sb.toString();
    }
}
