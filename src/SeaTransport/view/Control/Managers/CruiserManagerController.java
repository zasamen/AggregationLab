package SeaTransport.view.Control.Managers;

import SeaTransport.ShipAdapter.CruiserAdapter;
import SeaTransport.ShipAdapter.VesselOrDeviceAdapter;
import SeaTransport.Ships.Cruiser;
import SeaTransport.Toolkits.Windows;
import SeaTransport.view.Control.*;
import SeaTransport.view.Control.Managers.Abstract.BaseManagerController;
import SeaTransport.view.Control.Managers.Abstract.DeviceManagerController;
import SeaTransport.view.Control.Managers.Abstract.ShipManagerController;
import SeaTransport.view.Control.Managers.Abstract.VesselController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

import static SeaTransport.Toolkits.ValueChecker.checkIntPositiveValue;

public class CruiserManagerController extends Controller implements VesselController,DeviceManagerController {

    @FXML
    public BorderPane borderPane;
    @FXML
    public Button buttonSetArmament;
    @FXML
    public TextField textFieldCount;


    private Object armament = null;
    private ShipManagerController shipManagerController;
    private Object downStreamObject;

    @Override
    protected void initialize() {
        try {
            FXMLLoader loader=new FXMLLoader(getMain().getClass().getResource("view/ShipManager.fxml"));
            borderPane.setCenter(loader.load());
            shipManagerController =loader.getController();
            shipManagerController.setDownStreamObject(downStreamObject);
            if (downStreamObject!=null)
                textFieldCount.setText(((Cruiser)downStreamObject).getCount()+"");
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
            baseManagerController.setDownStreamObject((downStreamObject!=null)?((Cruiser)downStreamObject).getArmament():null);
            baseManagerController.setMain(getMain());
            Windows.showWindowWithModality(loader, "Armament", getMain());
            if (baseManagerController.getModalResult()==1){
                armament =baseManagerController.getObject();
            }else {
                armament =(downStreamObject!=null)?((Cruiser)downStreamObject).getArmament():null;
            }
        } catch (Exception e) {
            Windows.showAlert(e + "");
        }
    }

    @Override
    public VesselOrDeviceAdapter createAdapter() {
        return (checkFields())?new CruiserAdapter(getFields()):null;
    }

    @Override
    public void setDownStreamObject(Object downStreamObject) {
        this.downStreamObject = downStreamObject;
    }
}
