package net.orekyuu.rabbithouse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import net.orekyuu.rabbithouse.block.JsonBlocks;
import net.orekyuu.rabbithouse.item.JsonItems;
import net.orekyuu.rabbithouse.setting.SettingJson;

import java.io.File;
import java.io.FileReader;
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
        File root = fc.showDialog(Main.getPrimaryStage());
        ApplicationModel app = ApplicationModel.getInstance();

        File blockFile = new File(root, "/net/orekyuu/rabbithouse/block/blocks.json");
        File itemFile = new File(root, "/net/orekyuu/rabbithouse/item/items.json");
        File settingsFile = new File(root, "settings.json");
        if (!blockFile.exists() || !itemFile.exists() || !settingsFile.exists()) {
            Stage dialog = new Stage();
            Scene scene = new Scene(new Group(new Text(25, 25, "プロジェクトに必要なファイルがありません")));
            dialog.setScene(scene);
            dialog.centerOnScreen();
            dialog.show();
            return;
        }
        try {
            JsonBlocks blocks = readObject(blockFile, JsonBlocks.class);
            JsonItems items = readObject(itemFile, JsonItems.class);
            SettingJson setting = readObject(settingsFile, SettingJson.class);

            ProjectModel project = new ProjectModel(root.getName(), root, blocks, items);
            project.setSettings(setting);
            app.setProject(project);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            writeObject(blockFile, new JsonItems());
            //Item
            File item = new File(root, "/net/orekyuu/rabbithouse/item");
            item.mkdirs();
            File itemFile = new File(item, "/items.json");
            itemFile.createNewFile();
            writeObject(itemFile, new JsonItems());
            //Settings
            File settings = new File(root, "settings.json");
            if (!settings.exists()) {
                settings.createNewFile();
                writeObject(settings, new SettingJson());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeObject(File file, Object obj) throws IOException {
        Gson gson = new GsonBuilder().create();
        JsonWriter writer = new JsonWriter(new FileWriter(file));
        writer.setIndent("    ");
        gson.toJson(obj, obj.getClass(), writer);
        writer.close();
    }

    private <T> T readObject(File file, Class<T> clazz) throws IOException {
        T res;
        Gson gson = new GsonBuilder().create();
        JsonReader reader = new JsonReader(new FileReader(file));
        res = gson.fromJson(reader, clazz);
        reader.close();
        return res;
    }
}
