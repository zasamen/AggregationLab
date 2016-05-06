package SeaTransport.view.Control;

import SeaTransport.Tooklits.Windows;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class BoatManagerController extends Controller implements VesselController{

    @FXML
    public BorderPane borderPane;
    public Button buttonSetShallop;


    private Object shallop=null;
    private VesselManagerController vesselManagerController;

    @Override
    protected void initialize() {
        try {
            FXMLLoader loader=new FXMLLoader(getMain().getClass().getResource("view/VesselManager.fxml"));
            borderPane.setCenter(loader.load());
            vesselManagerController=loader.getController();
            vesselManagerController.setMain(getMain());
        }catch (IOException ioe){
            Windows.showAlert(ioe+" ");
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

        return (vesselManagerController.checkFields()&&shallop!=null);
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
            if (baseManagerController.getModalResult()==1){
                shallop=baseManagerController.getObject();
            }else {
                shallop=null;
            }
        } catch (Exception e) {
            Windows.showAlert(e + "");
        }
    }
}
