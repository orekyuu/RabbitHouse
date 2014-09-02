package net.orekyuu.rabbithouse;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import net.orekyuu.rabbithouse.block.BlockData;
import net.orekyuu.rabbithouse.setting.BlockSetting;
import net.orekyuu.rabbithouse.setting.SettingJson;
import net.orekyuu.rabbithouse.util.Log;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * BlockEditorのコントローラー
 */
public class BlockEditorController implements Initializable {
    //Editor
    public TextField blockName;
    public TextField iconName;
    public ComboBox<String> baseClass;
    public ComboBox<String> itemClass;
    public Label registanceTest;
    public Slider registance;
    public Label lightLevelText;
    public Slider lightLevel;
    public Label lightOpacityText;
    public Slider lightOpacity;
    public TextField hardnessField;
    public Slider hardness;
    public TextArea harvestLevel;
    public TextArea args;
    //ListView
    public Button copyButton;
    public ListView<BindingBlockData> listView;
    public Button addButton;
    public Button removeButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //DisableBindings
        ApplicationModel app = ApplicationModel.getInstance();
        BooleanProperty isOpen = app.getIsOpenProject();
        BooleanBinding binding = Bindings.not(isOpen)
                .or(Bindings.isNull(listView.getSelectionModel().selectedItemProperty()));
        blockName.disableProperty().bind(binding);
        iconName.disableProperty().bind(binding);
        baseClass.disableProperty().bind(binding);
        itemClass.disableProperty().bind(binding);
        registance.disableProperty().bind(binding);
        lightLevel.disableProperty().bind(binding);
        lightOpacity.disableProperty().bind(binding);
        hardness.disableProperty().bind(binding);
        hardnessField.disableProperty().bind(binding);
        harvestLevel.disableProperty().bind(binding);
        args.disableProperty().bind(binding);
        BooleanBinding binding1 = Bindings.not(isOpen);
        listView.disableProperty().bind(binding1);
        addButton.disableProperty().bind(binding1);
        removeButton.disableProperty().bind(binding1);
        copyButton.disableProperty().bind(binding1);
        Log.print(this, "initialize", "BlockEditor");

        //Bindings
        blockName.styleProperty().bind(
                Bindings.when(Bindings.equal("", blockName.textProperty()))
                        .then("-fx-border-color: red; -fx-border-width: 2px; -fx-focus-color: red;").otherwise("")
        );
        iconName.styleProperty().bind(
                Bindings.when(Bindings.equal("", iconName.textProperty()))
                        .then("-fx-border-color: red; -fx-border-width: 2px; -fx-focus-color: red;").otherwise("")
        );

        lightLevelText.textProperty().bind(
                Bindings.convert(Bindings.divide(lightLevel.valueProperty(), 100))
        );
        lightOpacityText.textProperty().bind(
                Bindings.convert(Bindings.divide(lightOpacity.valueProperty(), 100))
        );
        registanceTest.textProperty().bind(
                Bindings.convert(registance.valueProperty())
        );
        hardnessField.textProperty().bindBidirectional(
                hardness.valueProperty(), new StringConverter<Number>() {
                    @Override
                    public String toString(Number object) {
                        return object.toString();
                    }

                    @Override
                    public Number fromString(String string) {
                        if (string.matches("/^[-]?¥d+(¥.¥d+)?$/")) {
                            return Double.valueOf(string);
                        }
                        return 0;
                    }
                }
        );
        listView.getSelectionModel().selectedItemProperty().addListener(this::selectItem);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    //ListViewのアイテムがセレクトされた時
    private void selectItem(ObservableValue observable, BindingBlockData oldvalue, BindingBlockData newValue) {
        //バインドを解除
        if (oldvalue != null) {
            oldvalue.nameProperty().unbind();
            oldvalue.iconProperty().unbind();
            oldvalue.baseClassProperty().unbind();
            oldvalue.itemClassProperty().unbind();
            oldvalue.registanceProperty().unbind();
            oldvalue.lightLevelProperty().unbind();
            oldvalue.lightOpacityProperty().unbind();
            oldvalue.hardnessProperty().unbind();
            oldvalue.harvestLevelProperty().unbind();
            oldvalue.argsProperty().unbind();
        }
        if (newValue != null) {
            //値をGUIに反映
            blockName.setText(newValue.getName());
            iconName.setText(newValue.getIcon());
            registance.setValue(newValue.getRegistance());
            hardness.setValue(newValue.getHardness());
            lightLevel.setValue(newValue.getLightLevel());
            lightOpacity.setValue(newValue.getLightOpacity());
            args.setText(newValue.getArgs().get());
            harvestLevel.setText(newValue.getHarvestLevel().get());
            baseClass.getSelectionModel().select(newValue.getBaseClass());
            itemClass.getSelectionModel().select(newValue.getItemClass());
            Log.print(this, "SelectItem", newValue.getName());
            //バインドを行う
            newValue.nameProperty().bind(blockName.textProperty());
            newValue.iconProperty().bind(iconName.textProperty());
            newValue.baseClassProperty().bind(baseClass.getSelectionModel().selectedIndexProperty());
            newValue.itemClassProperty().bind(itemClass.getSelectionModel().selectedIndexProperty());
            newValue.registanceProperty().bind(registance.valueProperty());
            newValue.hardnessProperty().bind(hardness.valueProperty());
            newValue.lightLevelProperty().bind(lightLevel.valueProperty());
            newValue.lightOpacityProperty().bind(lightOpacity.valueProperty());
            newValue.harvestLevelProperty().bind(harvestLevel.textProperty());
            newValue.argsProperty().bind(args.textProperty());
        } else {
            //nullの場合はテキストをクリアする
            blockName.clear();
            iconName.clear();
        }
    }

    /**
     * プロジェクトを開いた時
     */
    public void openProject() {
        ApplicationModel app = ApplicationModel.getInstance();
        SettingJson settings = app.getProject().getSettings();
        BlockSetting blockSetting = settings.getBlockSetting();

        baseClass.getItems().clear();
        itemClass.getItems().clear();

        baseClass.getItems().add("指定なし");
        itemClass.getItems().add("指定なし");
        for (String s : blockSetting.getBlockClass())
            baseClass.getItems().add(s);
        for (String s : blockSetting.getBlockItemClass())
            itemClass.getItems().add(s);


        listView.getItems().clear();
        for (BlockData blockData : app.getProject().getBlocks().getBlocks())
            listView.getItems().add(new BindingBlockData(blockData));
    }

    /**
     * プロジェクトのセーブ
     */
    public void saveProject() {
        ApplicationModel app = ApplicationModel.getInstance();
        List<BlockData> blocks = app.getProject().getBlocks().getBlocks();
        blocks.clear();
        blocks.addAll(listView.getItems().stream().map(BindingBlockData::toBlockData).collect(Collectors.toList()));
    }

    @FXML
    private void addBlock() {
        BlockData data = new BlockData();
        data.setName("Unnamed Block");
        listView.getItems().add(new BindingBlockData(data));
    }

    @FXML
    private void removeBlock() {
        if (listView.getSelectionModel().isEmpty())
            return;
        int index = listView.getSelectionModel().getSelectedIndex();
        listView.getItems().remove(index);
    }

    @FXML
    private void copyBlock() {
        if (listView.getSelectionModel().isEmpty())
            return;
        BlockData blockData = listView.getSelectionModel().getSelectedItem().toBlockData();
        listView.getItems().add(new BindingBlockData(blockData));
    }
}
