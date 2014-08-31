package net.orekyuu.rabbithouse.block;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BlockData {
    private String name;
    private String resource;
    private String item;
    private List<HarvestLevel> harvestLevel;
    private List<String> args;
    private float resistance;
    private float lightLevel;
    private int lightOpacity;
    private float hardness;
    @SerializedName("class")
    private String className;

    public String getName() {
        return name;
    }

    public String getResource() {
        return resource;
    }

    public String getItem() {
        return item;
    }

    public List<HarvestLevel> getHarvestLevel() {
        return harvestLevel;
    }

    public float getResistance() {
        return resistance;
    }

    public float getLightLevel() {
        return lightLevel;
    }

    public int getLightOpacity() {
        return lightOpacity;
    }

    public float getHardness() {
        return hardness;
    }

    public String getClassName() {
        return className;
    }

    public List<String> getArgs() {
        return args;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BlockData{");
        sb.append("name='").append(name).append('\'');
        sb.append(", resource='").append(resource).append('\'');
        sb.append(", item='").append(item).append('\'');
        sb.append(", harvestLevel=").append(harvestLevel == null ? "[]" : harvestLevel.toString());
        sb.append(", resistance=").append(resistance);
        sb.append(", lightLevel=").append(lightLevel);
        sb.append(", lightOpacity=").append(lightOpacity);
        sb.append(", hardness=").append(hardness);
        sb.append(", class=").append(className);
        sb.append(", args=").append(args == null ? "[]" : args.toString());
        sb.append('}');
        return sb.toString();
    }
}
