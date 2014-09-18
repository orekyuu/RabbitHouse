package net.orekyuu.rabbithouse.loader.block;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;
import java.util.List;

public class BlockData {
    private String name;
    private String resource;
    private String item;
    private List<HarvestLevel> harvestLevel = new LinkedList<>();
    private List<String> args = new LinkedList<>();
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

    public void setHarvestLevel(List<HarvestLevel> harvestLevel) {
        this.harvestLevel = harvestLevel;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public void setResistance(float resistance) {
        this.resistance = resistance;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setHardness(float hardness) {
        this.hardness = hardness;
    }

    public void setLightOpacity(int lightOpacity) {
        this.lightOpacity = lightOpacity;
    }

    public void setLightLevel(float lightLevel) {
        this.lightLevel = lightLevel;
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
