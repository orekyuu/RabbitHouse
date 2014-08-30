package net.orekyuu.rabbithouse.loader.block;

import com.google.gson.annotations.SerializedName;

import java.util.List;

class BlockData {
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

    String getName() {
        return name;
    }

    String getResource() {
        return resource;
    }

    String getItem() {
        return item;
    }

    List<HarvestLevel> getHarvestLevel() {
        return harvestLevel;
    }

    float getResistance() {
        return resistance;
    }

    float getLightLevel() {
        return lightLevel;
    }

    int getLightOpacity() {
        return lightOpacity;
    }

    float getHardness() {
        return hardness;
    }

    String getClassName() {
        return className;
    }

    List<String> getArgs() {
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
