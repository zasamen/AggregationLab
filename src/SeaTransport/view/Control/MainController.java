package SeaTransport.view.Control;

import SeaTransport.Ships.Vessel;
import SeaTransport.Tooklits.Windows;
import SeaTransport.view.Control.Managers.Abstract.BaseManagerController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class MainController extends Controller {

    @FXML
    public Menu menu;
    @FXML
    public ListView<Vessel> listView;
    @FXML
    public MenuItem remove;

    @Override
    protected void initialize() {
        for (MenuItem menuItem : menu.getItems()) {
            menuItem.setOnAction(event -> manage(menuItem.getText(), null));
        }
    }

    public void handleRemove() {
        listView.getItems().remove(listView.getSelectionModel().getSelectedItem());
    }

    public void handleEdit() {
        if (listView.getItems().size()!=0) {
            Vessel vessel = listView.getSelectionModel().getSelectedItem();
            manage(vessel.getClass().getSimpleName(), vessel);
        }
    }

    private void manage(String vesselName, Vessel vessel) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getMain().getClass().getResource("view/BaseManager.fxml"));
            loader.load();
            BaseManagerController baseManagerController = loader.getController();
            baseManagerController.setCheckedVessel(vesselName);
            baseManagerController.setDownStreamObject(vessel);
            baseManagerController.setMain(getMain());
            Windows.showWindowWithModality(loader, vesselName, getMain());
            if (baseManagerController.getModalResult() == 1) {
                if (vessel != null)
                    listView.getItems().remove(listView.getSelectionModel().getSelectedItem());
                listView.getItems().add(0, (Vessel) baseManagerController.getObject());
            }
        } catch (Exception e) {
            Windows.showAlert(e + "");
        }
    }

}
