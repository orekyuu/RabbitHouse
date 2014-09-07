package net.orekyuu.rabbithouse;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import net.orekyuu.rabbithouse.item.ItemData;
import net.orekyuu.rabbithouse.setting.BlockSetting;
import net.orekyuu.rabbithouse.setting.ItemSetting;
import net.orekyuu.rabbithouse.setting.SettingJson;
import net.orekyuu.rabbithouse.util.Log;

import java.util.Arrays;

class BindingItemData {
    private SimpleStringProperty name;
    private SimpleStringProperty icon;
    private SimpleIntegerProperty baseClass;
    private SimpleIntegerProperty stack;
    private SimpleIntegerProperty maxDamage;
    private SimpleBooleanProperty is3D;
    private SimpleStringProperty args;

    BindingItemData(ItemData data) {
        name = new SimpleStringProperty(data.getName());
        icon = new SimpleStringProperty(data.getIcon());
        stack = new SimpleIntegerProperty(data.getMaxStackSize());
        maxDamage = new SimpleIntegerProperty(data.getMaxDamage());
        is3D = new SimpleBooleanProperty(data.isIs3D());
        {
            StringBuilder sb = new StringBuilder();
            for (String s : data.getArgs()) {
                sb.append(s).append('\n');
            }
            args = new SimpleStringProperty(sb.toString());
        }
        ApplicationModel app = ApplicationModel.getInstance();
        if (app.getProject() == null) {
            return;
        }
        SettingJson settings = app.getProject().getSettings();
        ItemSetting itemSetting = settings.getItemSetting();
        //ItemClass
        {
            int index = 0;
            if (data.getClassName() != null) {
                for (String s : itemSetting.getItemClass()) {
                    index++;
                    if (s.equals(data.getClassName())) {
                        break;
                    }
                }
            }
            baseClass = new SimpleIntegerProperty(index);
        }
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

    public int getBaseClass() {
        return baseClass.get();
    }

    public SimpleIntegerProperty baseClassProperty() {
        return baseClass;
    }

    public int getStack() {
        return stack.get();
    }

    public SimpleIntegerProperty stackProperty() {
        return stack;
    }

    public int getMaxDamage() {
        return maxDamage.get();
    }

    public SimpleIntegerProperty maxDamageProperty() {
        return maxDamage;
    }

    public boolean getIs3D() {
        return is3D.get();
    }

    public SimpleBooleanProperty is3DProperty() {
        return is3D;
    }

    public String getArgs() {
        return args.get();
    }

    public SimpleStringProperty argsProperty() {
        return args;
    }

    public ItemData toItemData() {
        ItemData data = new ItemData();
        data.setName(getName());
        data.setIcon(getIcon());
        data.setMaxStackSize(getStack());
        data.setMaxDamage(getMaxDamage());
        data.setIs3D(getIs3D());
        //args複製
        {
            String[] args = getArgs().split("\n");
            for (int i = 0; i < args.length; i++) {
                args[i] = args[i].trim();
            }
            data.setArgs(Arrays.asList(args));
        }

        ApplicationModel app = ApplicationModel.getInstance();
        if (app.getProject() == null) {
            data.setClassName(null);
            Log.print(this, "Warning", "ここでtoItemDataはおかしい");
            return data;
        }
        SettingJson settings = app.getProject().getSettings();
        BlockSetting blockSetting = settings.getBlockSetting();

        data.setClassName(baseClass.get() == 0 ? null : blockSetting.getBlockClass().get(baseClass.get() - 1));
        Log.print(this, "toItemData", data);
        return data;
    }
}
