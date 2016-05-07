package SeaTransport.view.Control.Managers;

import SeaTransport.ShipAdapter.AircraftCarrierAdapter;
import SeaTransport.ShipAdapter.VesselOrDeviceAdapter;
import SeaTransport.Ships.AircraftCarrier;
import SeaTransport.Tooklits.Windows;
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

import static SeaTransport.Tooklits.ValueChecker.checkIntPositiveValue;

public class AircraftCarrierManagerController extends Controller implements VesselController,DeviceManagerController {

    @FXML
    public BorderPane borderPane;
    @FXML
    public Button buttonSetAircraft;
    @FXML
    public TextField textFieldCount;


    private Object aircraft = null;
    private ShipManagerController shipManagerController;
    private Object downStreamObject;

    @Override
    protected void initialize() {
        try {
            FXMLLoader loader=new FXMLLoader(getMain().getClass().getResource("view/ShipManager.fxml"));
            borderPane.setCenter(loader.load());
            shipManagerController =loader.getController();
            shipManagerController.setDownStreamObject(downStreamObject);
            if (downStreamObject!=null) {
                AircraftCarrier aircraftCarrier = (AircraftCarrier) downStreamObject;
                textFieldCount.setText(aircraftCarrier.getCount() + "");
            }
            shipManagerController.setMain(getMain());
        }catch (IOException ioe){
            Windows.showAlert(ioe+" ");
        }
    }

    @Override
    public Object[] getFields() {
        if (checkFields()) {
            Object[] objects = shipManagerController.getFields();
            Object[] result = new Object[2+objects.length];
            System.arraycopy(objects, 0, result, 0, objects.length);
            result[result.length - 2] = aircraft;
            result[result.length - 1] = textFieldCount.getText();
            return result;
        } else
            return null;
    }

    @Override
    public boolean checkFields() {

        return (shipManagerController.checkFields()&& aircraft !=null&&checkIntPositiveValue(textFieldCount.getText(),"Count"));
    }

    @FXML
    public void handleButtonSetAircraft(){
        try {
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getMain().getClass().getResource("view/BaseManager.fxml"));
            loader.load();
            BaseManagerController baseManagerController =loader.getController();
            baseManagerController.setCheckedVessel("Aircraft");
            baseManagerController.setDownStreamObject((downStreamObject!=null)?((AircraftCarrier)downStreamObject).getAircraft():null);
            baseManagerController.setMain(getMain());
            Windows.showWindowWithModality(loader, "Aircraft", getMain());
            if (baseManagerController.getModalResult()==1){
                aircraft =baseManagerController.getObject();
            }else {
                aircraft =(downStreamObject!=null)?((AircraftCarrier)downStreamObject).getAircraft():null;
            }
        } catch (Exception e) {
            Windows.showAlert(e + "");
        }
    }

    @Override
    public VesselOrDeviceAdapter createAdapter() {
        return (checkFields())?new AircraftCarrierAdapter(getFields()):null;
    }

    @Override
    public void setDownStreamObject(Object downStreamObject) {
        this.downStreamObject = downStreamObject;
    }
}
