package SeaTransport.view.Control;

import SeaTransport.ShipAdapter.VesselOrDeviceAdapter;
import SeaTransport.ShipAdapter.YachtAdapter;
import SeaTransport.Tooklits.Windows;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class YachtManagerController extends Controller implements VesselController,DeviceManagerController{

    @FXML
    public BorderPane borderPane;
    public Button buttonSetSail;


    private Object sail = null;
    private BoatManagerController boatManagerController;

    @Override
    protected void initialize() {
        try {
            FXMLLoader loader=new FXMLLoader(getMain().getClass().getResource("view/BoatManager.fxml"));
            borderPane.setCenter(loader.load());
            boatManagerController =loader.getController();
            boatManagerController.setMain(getMain());
        }catch (IOException ioe){
            Windows.showAlert(ioe+"  ");
        }
    }

    @Override
    public Object[] getFields() {
        if (checkFields()) {
            Object[] objects = boatManagerController.getFields();
                Object[] result = new Object[1+objects.length];
                System.arraycopy(objects, 0, result, 0, objects.length);
                result[result.length - 1] = sail;
                return result;
        } else
            return null;
    }

    @Override
    public boolean checkFields() {

        return (boatManagerController.checkFields()&& sail !=null);
    }

    @FXML
    public void handleButtonSetSail(){
        try {
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getMain().getClass().getResource("view/BaseManager.fxml"));
            loader.load();
            BaseManagerController baseManagerController =loader.getController();
            baseManagerController.setCheckedVessel("Sail");
            baseManagerController.setMain(getMain());
            Windows.showWindowWithModality(loader, "Sail", getMain());
            if (baseManagerController.getModalResult()==1){
                sail =baseManagerController.getObject();
            }else {
                sail =null;
            }
        } catch (Exception e) {
            Windows.showAlert(e + "");
        }
    }

    @Override
    public VesselOrDeviceAdapter createAdapter() {
        return (checkFields())?new YachtAdapter(getFields()):null;
    }
}
