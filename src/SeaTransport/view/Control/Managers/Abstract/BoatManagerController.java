package SeaTransport.view.Control.Managers.Abstract;

import SeaTransport.Ships.Boat;
import SeaTransport.Toolkits.Windows;
import SeaTransport.view.Control.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class BoatManagerController extends Controller implements VesselController {

    @FXML
    public BorderPane borderPane;
    @FXML
    public Button buttonSetShallop;


    private Object shallop = null;
    private VesselManagerController vesselManagerController;
    private Object downStreamObject;

    @Override
    protected void initialize() {
        try {
            FXMLLoader loader = new FXMLLoader(getMain().getClass().getResource("view/VesselManager.fxml"));
            borderPane.setCenter(loader.load());
            vesselManagerController = loader.getController();
            vesselManagerController.setDownStreamObject(downStreamObject);
            vesselManagerController.setMain(getMain());
        } catch (IOException ioe) {
            Windows.showAlert(ioe + " ");
        }
    }

    @Override
    public Object[] getFields() {
        if (checkFields()) {
            Object[] objects = vesselManagerController.getFields();
            if (objects != null) {
                Object[] result = new Object[objects.length + 1];
                System.arraycopy(objects, 0, result, 0, objects.length);
                result[result.length - 1] = shallop;
                return result;
            } else
                return null;
        } else
            return null;
    }

    @Override
    public boolean checkFields() {
        return (vesselManagerController.checkFields() && shallop != null);
    }

    @FXML
    public void handleButtonSetShallop() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getMain().getClass().getResource("view/BaseManager.fxml"));
            loader.load();
            BaseManagerController baseManagerController = loader.getController();
            baseManagerController.setCheckedVessel("Shallop");
            baseManagerController.setDownStreamObject((downStreamObject != null) ? ((Boat) downStreamObject).getShallop() : null);
            baseManagerController.setMain(getMain());
            Windows.showWindowWithModality(loader, "Shallop", getMain());
            if (baseManagerController.getModalResult() == 1) {
                shallop = baseManagerController.getObject();
            } else {
                shallop = (downStreamObject != null) ? ((Boat) downStreamObject).getShallop() : null;
            }
        } catch (Exception e) {
            Windows.showAlert(e + "");
        }
    }

    @Override
    public void setDownStreamObject(Object downStreamObject) {
        this.downStreamObject = downStreamObject;
    }

}
