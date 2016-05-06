package SeaTransport.view.Control;


import SeaTransport.ShipAdapter.LaunchAdapter;
import SeaTransport.ShipAdapter.VesselOrDeviceAdapter;
import SeaTransport.Tooklits.Windows;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class LaunchManagerController extends Controller implements VesselController,DeviceManagerController{

    @FXML
    public BorderPane borderPane;
    public Button buttonSetEngine;


    private Object engine = null;
    private BoatManagerController boatManagerController;

    @Override
    protected void initialize() {
        try {
            FXMLLoader loader=new FXMLLoader(getMain().getClass().getResource("view/BoatManager.fxml"));
            borderPane.setCenter(loader.load());
            boatManagerController =loader.getController();
            boatManagerController.setMain(getMain());
        }catch (IOException ioe){
            Windows.showAlert(ioe+" ");
        }
    }

    @Override
    public Object[] getFields() {
        if (checkFields()) {
            Object[] objects = boatManagerController.getFields();
            if (objects != null) {
                Object[] result = new Object[1+objects.length];
                System.arraycopy(objects, 0, result, 0, objects.length);
                result[result.length - 1] = engine;
                return result;
            } else
                return null;
        } else
            return null;
    }

    @Override
    public boolean checkFields() {

        return (boatManagerController.checkFields()&& engine !=null);
    }

    @FXML
    public void handleButtonSetEngine(){
        try {
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getMain().getClass().getResource("view/BaseManager.fxml"));
            loader.load();
            BaseManagerController baseManagerController =loader.getController();
            baseManagerController.setCheckedVessel("Engine");
            baseManagerController.setMain(getMain());
            Windows.showWindowWithModality(loader, "Engine", getMain());
            if (baseManagerController.getModalResult()==1){
                engine =baseManagerController.getObject();
            }else {
                engine =null;
            }
        } catch (Exception e) {
            Windows.showAlert(e + "");
        }
    }

    @Override
    public VesselOrDeviceAdapter createAdapter() {
        return (checkFields())?new LaunchAdapter(getFields()):null;
    }
}
