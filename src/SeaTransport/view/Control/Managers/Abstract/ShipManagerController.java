package SeaTransport.view.Control.Managers.Abstract;

import SeaTransport.Tooklits.Windows;
import SeaTransport.view.Control.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

import static SeaTransport.Tooklits.ValueChecker.checkIntPositiveValue;

public class ShipManagerController extends Controller implements VesselController{

    @FXML
    public BorderPane borderPane;
    public Button buttonSetShallop;
    public TextField textFieldCrew;


    private Object sail =null;
    private VesselManagerController vesselManagerController;

    @Override
    protected void initialize() {
        try {
            FXMLLoader loader=new FXMLLoader(getMain().getClass().getResource("view/VesselManager.fxml"));
            borderPane.setCenter(loader.load());
            vesselManagerController=loader.getController();
            vesselManagerController.setMain(getMain());
        }catch (IOException ioe){
            Windows.showAlert(ioe+"  ");
        }
    }

    @Override
    public Object[] getFields() {
        if (checkFields()) {
            Object[] objects = vesselManagerController.getFields();
            Object[] result = new Object[objects.length + 2];
            System.arraycopy(objects, 0, result, 0, objects.length);
            result[result.length - 1] = sail;
            result[result.length - 2] = textFieldCrew.getText();
            return result;
        } else
            return null;
    }

    @Override
    public boolean checkFields() {
        return (vesselManagerController.checkFields() &&
                checkIntPositiveValue(textFieldCrew.getText(), "Crew") &&
                sail != null);
    }

    @FXML
    public void handleButtonSetShallop(){
        try {
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getMain().getClass().getResource("view/BaseManager.fxml"));
            loader.load();
            BaseManagerController baseManagerController =loader.getController();
            baseManagerController.setCheckedVessel("Shallop");
            baseManagerController.setMain(getMain());
            Windows.showWindowWithModality(loader, "Shallop", getMain());
            if (baseManagerController.getModalResult()!=1){
                sail =null;
            }else {
                sail =baseManagerController.getObject();
            }
        } catch (Exception e) {
            Windows.showAlert(e + " ");
        }
    }
}
