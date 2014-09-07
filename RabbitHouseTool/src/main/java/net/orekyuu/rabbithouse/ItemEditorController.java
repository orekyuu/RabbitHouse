package net.orekyuu.rabbithouse;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import net.orekyuu.rabbithouse.block.BlockData;
import net.orekyuu.rabbithouse.item.ItemData;
import net.orekyuu.rabbithouse.setting.ItemSetting;
import net.orekyuu.rabbithouse.setting.SettingJson;
import net.orekyuu.rabbithouse.util.Log;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

/**
 * ItemEditorのコントローラー
 */
public class ItemEditorController implements Initializable  {
    //Editor
    public TextField itemName;
    public TextField iconName;
    public ComboBox<String> baseClass;
    public Label stackText;
    public Slider stack;
    public Label damageText;
    public Slider damage;
    public TextArea args;
    public CheckBox is3D;
    //ListView
    public ListView<BindingItemData> listView;
    public Button addButton;
    public Button removeButton;
    public Button copyButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //DisableBindings
        ApplicationModel app = ApplicationModel.getInstance();
        BooleanProperty isOpen = app.getIsOpenProject();
        BooleanBinding binding = Bindings.not(isOpen)
                .or(Bindings.isNull(listView.getSelectionModel().selectedItemProperty()));
        itemName.disableProperty().bind(binding);
        iconName.disableProperty().bind(binding);
        baseClass.disableProperty().bind(binding);
        stack.disableProperty().bind(binding);
        damage.disableProperty().bind(binding);
        args.disableProperty().bind(binding);
        is3D.disableProperty().bind(binding);
        BooleanBinding binding1 = Bindings.not(isOpen);
        listView.disableProperty().bind(binding1);
        addButton.disableProperty().bind(binding1);
        removeButton.disableProperty().bind(binding1);
        copyButton.disableProperty().bind(binding1);
        Log.print(this, "initialize", "ItemEditor");

        //Bindings
        itemName.styleProperty().bind(
                Bindings.when(Bindings.equal("", itemName.textProperty()))
                        .then("-fx-border-color: red; -fx-border-width: 2px; -fx-focus-color: red;").otherwise("")
        );
        iconName.styleProperty().bind(
                Bindings.when(Bindings.equal("", iconName.textProperty()))
                        .then("-fx-border-color: red; -fx-border-width: 2px; -fx-focus-color: red;").otherwise("")
        );

        stack.setMax(64);
        stack.setBlockIncrement(1.0);
        stackText.textProperty().bind(
                Bindings.concat("スタック数: ")
                        .concat(Bindings.createIntegerBinding(() -> (int) stack.getValue(),
                                stack.valueProperty())));
        damage.setMax(5000);
        damage.setBlockIncrement(1.0);
        damageText.textProperty().bind(
                Bindings.concat("最大ダメージ: ")
                        .concat(Bindings.createIntegerBinding(() -> (int)damage.getValue(),
                                damage.valueProperty())));

        listView.getSelectionModel().selectedItemProperty().addListener(this::selectItem);

        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        listView.setCellFactory(new Callback<ListView<BindingItemData>, ListCell<BindingItemData>>() {
            @Override
            public ListCell<BindingItemData> call(ListView<BindingItemData> bindingItemDataListView) {
                return new ListCell<BindingItemData>() {
                    @Override
                    protected void updateItem(BindingItemData item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null)
                            setText(item.getName());
                        else
                            setText("");
                    }
                };
            }
        });
    }

    private void selectItem(ObservableValue observable, BindingItemData oldvalue, BindingItemData newValue) {
        //バインドを解除
        if (oldvalue != null) {
            oldvalue.nameProperty().unbind();
            oldvalue.iconProperty().unbind();
            oldvalue.baseClassProperty().unbind();
            oldvalue.stackProperty().unbind();
            oldvalue.maxDamageProperty().unbind();
            oldvalue.is3DProperty().unbind();
            oldvalue.argsProperty().unbind();
        }
        if (newValue != null) {
            //値をGUIに反映
            itemName.setText(newValue.getName());
            iconName.setText(newValue.getIcon());
            baseClass.getSelectionModel().select(newValue.getBaseClass());
            stack.setValue(newValue.getStack());
            damage.setValue(newValue.getMaxDamage());
            args.setText(newValue.getArgs());
            is3D.setSelected(newValue.getIs3D());
            Log.print(this, "SelectItem", newValue.getName());
            //バインドを行う
            newValue.nameProperty().bind(itemName.textProperty());
            newValue.iconProperty().bind(iconName.textProperty());
            newValue.baseClassProperty().bind(baseClass.getSelectionModel().selectedIndexProperty());
            newValue.stackProperty().bind(stack.valueProperty());
            newValue.maxDamageProperty().bind(damage.valueProperty());
            newValue.is3DProperty().bind(is3D.selectedProperty());
            newValue.argsProperty().bind(args.textProperty());
        } else {
            //nullの場合はテキストをクリアする
            itemName.clear();
            iconName.clear();
        }
    }

    /**
     * プロジェクトを開いた時
     */
    public void openProject() {
        ApplicationModel app = ApplicationModel.getInstance();
        SettingJson settings = app.getProject().getSettings();
        ItemSetting itemSetting = settings.getItemSetting();

        baseClass.getItems().clear();

        baseClass.getItems().add("指定なし");
        for (String s : itemSetting.getItemClass())
            baseClass.getItems().add(s);


        listView.getItems().clear();
        for (ItemData itemData : app.getProject().getItems().getItems())
            listView.getItems().add(new BindingItemData(itemData));
    }

    /**
     * プロジェクトのセーブ
     */
    public void saveProject() {
        ApplicationModel app = ApplicationModel.getInstance();
        List<ItemData> items = app.getProject().getItems().getItems();
        items.clear();
        items.addAll(listView.getItems()
                .stream()
                .map(BindingItemData::toItemData)
                .collect(Collectors.toList()));
    }

    @FXML
    private void addItem() {
        ItemData itemData = new ItemData();
        itemData.setName("Unnamed Item");
        listView.getItems().add(new BindingItemData(itemData));
    }

    @FXML
    private void removeItem() {
        if (listView.getSelectionModel().isEmpty())
            return;
        int index = listView.getSelectionModel().getSelectedIndex();
        listView.getItems().remove(index);
    }

    @FXML
    private void copyItem() {
        if (listView.getSelectionModel().isEmpty())
            return;
        ItemData itemData = listView.getSelectionModel().getSelectedItem().toItemData();
        listView.getItems().add(new BindingItemData(itemData));
    }
}
