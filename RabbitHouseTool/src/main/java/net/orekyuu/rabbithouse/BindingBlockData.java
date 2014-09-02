package net.orekyuu.rabbithouse;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import net.orekyuu.rabbithouse.block.BlockData;

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
    private SimpleListProperty<String> args;
    private SimpleListProperty<String> harvestLevel;

    BindingBlockData(BlockData data) {
        name = new SimpleStringProperty(data.getName());
        icon = new SimpleStringProperty(data.getResource());
        itemClass = new SimpleStringProperty(data.getItem());
        baseClass = new SimpleStringProperty(data.getClassName());
        registance = new SimpleFloatProperty(data.getResistance());
        lightLevel = new SimpleFloatProperty(data.getLightLevel());
        lightOpacity = new SimpleIntegerProperty(data.getLightOpacity());
        hardness = new SimpleFloatProperty(data.getHardness());
        args = new SimpleListProperty<>();
        harvestLevel = new SimpleListProperty<>();
    }

    public BlockData toBlockData() {
        BlockData data = new BlockData();
        data.setClassName(baseClass.get());
        data.setHardness(hardness.get());
        //TODO あとでやる
        //data.setHarvestLevel(harvestLevel);
        data.setItem(itemClass.get());
        data.setLightLevel(lightLevel.get());
        data.setLightOpacity(lightOpacity.get());
        data.setName(name.get());
        data.setResistance(registance.get());
        data.setResource(icon.get());
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

    public ObservableList<String> getArgs() {
        return args.get();
    }

    public SimpleListProperty<String> argsProperty() {
        return args;
    }

    public ObservableList<String> getHarvestLevel() {
        return harvestLevel.get();
    }

    public SimpleListProperty<String> harvestLevelProperty() {
        return harvestLevel;
    }
}
