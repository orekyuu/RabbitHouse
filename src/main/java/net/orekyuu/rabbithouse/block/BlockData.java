package net.orekyuu.rabbithouse.block;

import java.util.Arrays;

class BlockData {
    private String name;
    private String resource;
    private String item;
    private HarvestLevel[] harvestLevel;
    private float resistance;
    private float lightLevel;
    private float lightOpacity;
    private float hardness;

    public String getName() {
        return name;
    }

    public String getResource() {
        return resource;
    }

    public String getItem() {
        return item;
    }

    public HarvestLevel[] getHarvestLevel() {
        return harvestLevel;
    }

    public float getResistance() {
        return resistance;
    }

    public float getLightLevel() {
        return lightLevel;
    }

    public float getLightOpacity() {
        return lightOpacity;
    }

    public float getHardness() {
        return hardness;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BlockData{");
        sb.append("name='").append(name).append('\'');
        sb.append(", resource='").append(resource).append('\'');
        sb.append(", item='").append(item).append('\'');
        sb.append(", harvestLevel=").append(Arrays.toString(harvestLevel));
        sb.append(", resistance=").append(resistance);
        sb.append(", lightLevel=").append(lightLevel);
        sb.append(", lightOpacity=").append(lightOpacity);
        sb.append(", hardness=").append(hardness);
        sb.append('}');
        return sb.toString();
    }
}
