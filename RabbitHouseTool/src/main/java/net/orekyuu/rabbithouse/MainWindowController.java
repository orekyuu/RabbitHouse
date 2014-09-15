package net.orekyuu.rabbithouse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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
import java.util.prefs.Preferences;

/**
 * MainWindowのコントローラー
 */
public class MainWindowController {


    public BorderPane blockEditor;
    public BorderPane itemEditor;
    @FXML
    private ItemEditorController itemEditorController;
    @FXML
    private BlockEditorController blockEditorController;

    private Preferences prefs;
    public static final String PATH = "PATH";
    public static final String DEFAULT_PATH = System.getProperty("user.home");

    public MainWindowController() {
        prefs = Preferences.userNodeForPackage(this.getClass());
    }

    //プロジェクトを開く
    @FXML
    private void openProject() {
        DirectoryChooser fc = new DirectoryChooser();
        fc.setTitle("プロジェクトを開く");
        fc.setInitialDirectory(new File(prefs.get(PATH, DEFAULT_PATH)));
        File root = fc.showDialog(Main.getPrimaryStage());
        open(root);
    }

    //新規プロジェクト
    @FXML
    private void createProject() {
        try {
            DirectoryChooser fc = new DirectoryChooser();
            fc.setTitle("プロジェクトを作成");
            fc.setInitialDirectory(new File(System.getProperty("user.home")));
            File root = fc.showDialog(Main.getPrimaryStage());
            create(root);
            open(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //別名保存
    @FXML
    private void saveAsProject() {
        Log.print(this, "saveAsProject", "別名保存");
        DirectoryChooser fc = new DirectoryChooser();
        fc.setTitle("プロジェクトを開く");
        File dir = new File(prefs.get(PATH, DEFAULT_PATH));
        if (!dir.exists()) {
            dir = new File(DEFAULT_PATH);
        }
        fc.setInitialDirectory(dir);
        File root = fc.showDialog(Main.getPrimaryStage());
        try {
            create(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        save(root);
    }

    //上書き保存
    @FXML
    private void saveProject() {
        Log.print(this, "saveProject", "上書き保存");
        ApplicationModel app = ApplicationModel.getInstance();
        ProjectModel project = app.getProject();
        if (project == null)
            return;
        File root = project.getProjectDir();
        save(root);
    }

    private void create(File root) throws IOException {
        if (root == null)
            return;
        //Block
        File block = FileDirs.createBlockDir(root);
        block.mkdirs();
        File blockFile = FileDirs.createBlockFile(root);
        blockFile.createNewFile();
        writeObject(blockFile, new JsonBlocks());
        //Item
        File item = FileDirs.createItemDir(root);
        item.mkdirs();
        File itemFile = FileDirs.createItemFile(root);
        itemFile.createNewFile();
        writeObject(itemFile, new JsonItems());
        //Settings
        File settings = FileDirs.createSettingsFile(root);
        if (!settings.exists()) {
            settings.createNewFile();
            writeObject(settings, new SettingJson());
        }
        Log.print(this, "CreateProject", root.getAbsolutePath());
    }

    private void open(File root) {
        if (root == null)
            return;
        ApplicationModel app = ApplicationModel.getInstance();

        File blockFile = FileDirs.createBlockFile(root);
        File itemFile = FileDirs.createItemFile(root);
        File settingsFile = FileDirs.createSettingsFile(root);
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
            blockEditorController.openProject();
            itemEditorController.openProject();
            Log.print(this, "OpenProject", project);
            prefs.put(PATH, project.getProjectDir().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void save(File root) {
        if (root == null)
            return;
        ApplicationModel app = ApplicationModel.getInstance();
        ProjectModel project = app.getProject();
        if (project == null)
            return;
        blockEditorController.saveProject();
        itemEditorController.saveProject();
        try {
            writeObject(FileDirs.createBlockFile(root), project.getBlocks());
            writeObject(FileDirs.createItemFile(root), project.getItems());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.print(this, "SaveProject", project);
        prefs.put(PATH, project.getProjectDir().getAbsolutePath());

    }

    private void writeObject(File file, Object obj) throws IOException {
        if (!file.exists())
            file.createNewFile();
        Gson gson = new GsonBuilder().create();
        JsonWriter writer = new JsonWriter(new FileWriter(file));
        writer.setIndent("    ");
        gson.toJson(obj, obj.getClass(), writer);
        writer.close();
        Log.print(this, "write object", obj);
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
