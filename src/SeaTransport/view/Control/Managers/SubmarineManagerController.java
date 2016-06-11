package SeaTransport.view.Control.Managers;


import SeaTransport.ShipAdapter.SubmarineAdapter;
import SeaTransport.ShipAdapter.VesselOrDeviceAdapter;
import SeaTransport.Ships.Submarine;
import SeaTransport.Toolkits.Windows;
import SeaTransport.view.Control.Controller;
import SeaTransport.view.Control.Managers.Abstract.DeviceManagerController;
import SeaTransport.view.Control.Managers.Abstract.ShipManagerController;
import SeaTransport.view.Control.Managers.Abstract.VesselController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

import static SeaTransport.Toolkits.ValueChecker.checkIntPositiveValue;
import static SeaTransport.Toolkits.ValueChecker.checkStringFullValue;

public class SubmarineManagerController extends Controller implements VesselController,DeviceManagerController {

    @FXML
    public BorderPane borderPane;
    @FXML
    public TextField textFieldTorpedoType;
    @FXML
    public TextField textFieldCount;


    private ShipManagerController shipManagerController;
    private Object downStreamObject;


    @Override
    protected void initialize() {
        try {
            FXMLLoader loader=new FXMLLoader(getMain().getClass().getResource("view/ShipManager.fxml"));
            borderPane.setCenter(loader.load());
            shipManagerController =loader.getController();
            if (downStreamObject!=null) {
                shipManagerController.setDownStreamObject(downStreamObject);
                textFieldTorpedoType.setText(((Submarine)downStreamObject).getTorpedoType());
                textFieldCount.setText(((Submarine)downStreamObject).getCount()+"");
            }
            shipManagerController.setMain(getMain());
        }catch (IOException ioe){
            Windows.showAlert(ioe+"   ");
        }
    }

    @Override
    public Object[] getFields() {
        if (checkFields()) {
            Object[] objects = shipManagerController.getFields();
            Object[] result = new Object[2+objects.length];
            System.arraycopy(objects, 0, result, 0, objects.length);
            result[result.length - 1] = textFieldCount.getText();
            result[result.length - 2] = textFieldTorpedoType.getText();
            return result;
        } else
            return null;
    }

    @Override
    public boolean checkFields() {

        return (shipManagerController.checkFields()&&
                checkStringFullValue(textFieldTorpedoType.getText(),"Torpedo Type")&&
                checkIntPositiveValue(textFieldCount.getText(),"count"));
    }

    @Override
    public VesselOrDeviceAdapter createAdapter() {
        return (checkFields())?new SubmarineAdapter(getFields()):null;
    }

    @Override
    public void setDownStreamObject(Object downStreamObject) {
        this.downStreamObject = downStreamObject;
    }
}
