package net.orekyuu.rabbithouse.block;

import java.util.Arrays;

class JsonBlocks {
    private BlockData[] blocks;

    public BlockData[] getBlocks() {
        return blocks;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("JsonBlocks{");
        sb.append("blocks=").append(Arrays.toString(blocks));
        sb.append('}');
        return sb.toString();
    }
}
