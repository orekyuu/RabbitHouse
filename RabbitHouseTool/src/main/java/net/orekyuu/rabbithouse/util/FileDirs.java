package net.orekyuu.rabbithouse.util;

import java.io.File;

public class FileDirs {
    private FileDirs() {
        throw new AssertionError("インスタンス化不可");
    }

    public static File createBlockDir(File root) {
        return new File(root, "/net/orekyuu/rabbithouse/loader/block/");
    }

    public static File createItemDir(File root) {
        return new File(root, "/net/orekyuu/rabbithouse/loader/item/");
    }

    public static File createBlockFile(File root) {
        return new File(root, "/net/orekyuu/rabbithouse/loader/block/Blocks.json");
    }

    public static File createItemFile(File root) {
        return new File(root, "/net/orekyuu/rabbithouse/loader/item/Items.json");
    }

    public static File createSettingsFile(File root) {
        return new File(root, "settings.json");
    }
}
