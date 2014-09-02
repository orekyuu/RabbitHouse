package net.orekyuu.rabbithouse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;
import javafx.fxml.FXML;
import javafx.stage.DirectoryChooser;
import net.orekyuu.rabbithouse.setting.SettingJson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * MainWindowのコントローラー
 */
public class MainWindowController {
    @FXML
    private void openProject() {
        DirectoryChooser fc = new DirectoryChooser();
        fc.setTitle("プロジェクトを開く");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        File f = fc.showDialog(Main.getPrimaryStage());
        //TODO あとで実装
    }

    @FXML
    private void createProject() {
        try {
            DirectoryChooser fc = new DirectoryChooser();
            fc.setTitle("プロジェクトを作成");
            fc.setInitialDirectory(new File(System.getProperty("user.home")));
            File root = fc.showDialog(Main.getPrimaryStage());
            //Block
            File block = new File(root, "/net/orekyuu/rabbithouse/block");
            block.mkdirs();
            File blockFile = new File(block, "/blocks.json");
            blockFile.createNewFile();
            //Item
            File item = new File(root, "/net/orekyuu/rabbithouse/item");
            item.mkdirs();
            File itemFile = new File(item, "/items.json");
            itemFile.createNewFile();
            //Settings
            File settings = new File(root, "settings.json");
            if (!settings.exists()) {
                settings.createNewFile();
                Gson gson = new GsonBuilder().create();
                JsonWriter writer = new JsonWriter(new FileWriter(settings));
                writer.setIndent("    ");
                gson.toJson(new SettingJson(), SettingJson.class, writer);
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
