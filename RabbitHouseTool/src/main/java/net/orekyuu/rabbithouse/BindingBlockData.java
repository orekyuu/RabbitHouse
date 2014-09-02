package net.orekyuu.rabbithouse;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import net.orekyuu.rabbithouse.block.BlockData;
import net.orekyuu.rabbithouse.block.HarvestLevel;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * バインド用
 */
class BindingBlockData {
    private SimpleStringProperty name;
    private SimpleStringProperty icon;
    private SimpleStringProperty itemClass;
    private SimpleStringProperty baseClass;
    private SimpleFloatProperty registance;
    private SimpleFloatProperty lightLevel;
    private SimpleIntegerProperty lightOpacity;
    private SimpleFloatProperty hardness;
    private SimpleStringProperty args;
    private SimpleStringProperty harvestLevel;

    BindingBlockData(BlockData data) {
        name = new SimpleStringProperty(data.getName());
        icon = new SimpleStringProperty(data.getResource());
        itemClass = new SimpleStringProperty(data.getItem());
        baseClass = new SimpleStringProperty(data.getClassName());
        registance = new SimpleFloatProperty(data.getResistance());
        lightLevel = new SimpleFloatProperty(data.getLightLevel());
        lightOpacity = new SimpleIntegerProperty(data.getLightOpacity());
        hardness = new SimpleFloatProperty(data.getHardness());

        //args
        {
            StringBuilder sb = new StringBuilder();
            for (String s : data.getArgs()) {
                sb.append(s).append('\n');
            }
            args = new SimpleStringProperty(sb.toString());
        }

        //HarvestLevel
        {
            StringBuilder sb = new StringBuilder();
            for (HarvestLevel h : data.getHarvestLevel()) {
                sb.append(h.getKey()).append(" : ").append(h.getValue()).append('\n');
            }
            harvestLevel = new SimpleStringProperty(sb.toString());
        }
    }

    public BlockData toBlockData() {
        BlockData data = new BlockData();
        data.setClassName(baseClass.get());
        data.setHardness(hardness.get());
        data.setItem(itemClass.get());
        data.setLightLevel(lightLevel.get());
        data.setLightOpacity(lightOpacity.get());
        data.setName(name.get());
        data.setResistance(registance.get());
        data.setResource(icon.get());

        //args複製
        {
            String[] args = getArgs().get().split("\n");
            for (int i = 0; i < args.length; i++) {
                args[i] = args[i].trim();
            }
            data.setArgs(Arrays.asList(args));
        }
        //HarvestLevel複製
        {
            //
            String[] lines = getHarvestLevel().get().split("\n");
            for (int i = 0; i < lines.length; i++) {
                lines[i] = lines[i].trim();
            }
            List<HarvestLevel> res = new LinkedList<>();
            for (String line : lines) {
                String[] split = line.split(":");
                if (split.length == 2)
                    res.add(new HarvestLevel(split[0].trim(), Integer.parseInt(split[1].trim())));
            }
            data.setHarvestLevel(res);
        }
        return data;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getIcon() {
        return icon.get();
    }

    public SimpleStringProperty iconProperty() {
        return icon;
    }

    public String getItemClass() {
        return itemClass.get();
    }

    public SimpleStringProperty itemClassProperty() {
        return itemClass;
    }

    public String getBaseClass() {
        return baseClass.get();
    }

    public SimpleStringProperty baseClassProperty() {
        return baseClass;
    }

    public float getRegistance() {
        return registance.get();
    }

    public SimpleFloatProperty registanceProperty() {
        return registance;
    }

    public float getLightLevel() {
        return lightLevel.get();
    }

    public SimpleFloatProperty lightLevelProperty() {
        return lightLevel;
    }

    public int getLightOpacity() {
        return lightOpacity.get();
    }

    public SimpleIntegerProperty lightOpacityProperty() {
        return lightOpacity;
    }

    public float getHardness() {
        return hardness.get();
    }

    public SimpleFloatProperty hardnessProperty() {
        return hardness;
    }

    public SimpleStringProperty getArgs() {
        return args;
    }

    public SimpleStringProperty argsProperty() {
        return args;
    }

    public SimpleStringProperty getHarvestLevel() {
        return harvestLevel;
    }

    public SimpleStringProperty harvestLevelProperty() {
        return harvestLevel;
    }
}
