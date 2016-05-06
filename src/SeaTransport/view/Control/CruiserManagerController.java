package SeaTransport.view.Control;

import SeaTransport.ShipAdapter.CruiserAdapter;
import SeaTransport.ShipAdapter.VesselOrDeviceAdapter;
import SeaTransport.Tooklits.Windows;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

import static SeaTransport.Tooklits.ValueChecker.checkIntPositiveValue;

public class CruiserManagerController extends Controller implements VesselController,DeviceManagerController{

    @FXML
    public BorderPane borderPane;
    public Button buttonSetArmament;
    public TextField textFieldCount;


    private Object armament = null;
    private ShipManagerController shipManagerController;

    @Override
    protected void initialize() {
        try {
            FXMLLoader loader=new FXMLLoader(getMain().getClass().getResource("view/ShipManager.fxml"));
            borderPane.setCenter(loader.load());
            shipManagerController =loader.getController();
            shipManagerController.setMain(getMain());
        }catch (IOException ioe){
            Windows.showAlert(ioe+"  ");
        }
    }

    @Override
    public Object[] getFields() {
        if (checkFields()) {
            Object[] objects = shipManagerController.getFields();
            Object[] result = new Object[2+objects.length];
            System.arraycopy(objects, 0, result, 0, objects.length);
            result[result.length - 1] = textFieldCount.getText();
            result[result.length - 2] = armament;
            return result;
        } else
            return null;
    }

    @Override
    public boolean checkFields() {

        return (shipManagerController.checkFields()&& armament !=null && checkIntPositiveValue(textFieldCount.getText(),"count"));
    }

    @FXML
    public void handleButtonSetArmament(){
        try {
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getMain().getClass().getResource("view/BaseManager.fxml"));
            loader.load();
            BaseManagerController baseManagerController =loader.getController();
            baseManagerController.setCheckedVessel("Armament");
            baseManagerController.setMain(getMain());
            Windows.showWindowWithModality(loader, "Armament", getMain());
            if (baseManagerController.getModalResult()==1){
                armament =baseManagerController.getObject();
            }else {
                armament =null;
            }
        } catch (Exception e) {
            Windows.showAlert(e + "");
        }
    }

    @Override
    public VesselOrDeviceAdapter createAdapter() {
        return (checkFields())?new CruiserAdapter(getFields()):null;
    }
}
