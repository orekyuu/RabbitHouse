package net.orekyuu.rabbithouse;

import net.orekyuu.rabbithouse.block.JsonBlocks;
import net.orekyuu.rabbithouse.item.JsonItems;
import net.orekyuu.rabbithouse.setting.SettingJson;

import java.io.File;

/**
 * プロジェクトの情報
 */
class ProjectModel {
    private String projectName;
    private File projectDir;
    private JsonBlocks blocks;
    private JsonItems items;
    private SettingJson settings;

    public ProjectModel(String projectName, File projectDir, JsonBlocks blocks, JsonItems items) {
        this.projectName = projectName;
        this.projectDir = projectDir;
        this.blocks = blocks;
        this.items = items;
    }


    public File getProjectDir() {
        return projectDir;
    }

    public JsonBlocks getBlocks() {
        return blocks;
    }

    public JsonItems getItems() {
        return items;
    }

    public SettingJson getSettings() {
        return settings;
    }

    public void setSettings(SettingJson settings) {
        this.settings = settings;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProjectModel{");
        sb.append("projectName='").append(projectName).append('\'');
        sb.append(", projectDir=").append(projectDir.getAbsolutePath());
        sb.append(", blocks=").append(blocks);
        sb.append(", items=").append(items);
        sb.append('}');
        return sb.toString();
    }
}
