package SeaTransport.view.Control.Managers.ComponentControllers;

import SeaTransport.ShipAdapter.DeviceAdapter.ArmamentAdapter;
import SeaTransport.ShipAdapter.DeviceAdapter.DeviceAdapter;
import SeaTransport.view.Control.Controller;
import SeaTransport.view.Control.Managers.Abstract.DeviceManagerController;
import SeaTransport.view.Control.Managers.Abstract.VesselController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import static SeaTransport.Tooklits.ValueChecker.checkIntPositiveValue;
import static SeaTransport.Tooklits.ValueChecker.checkStringFullValue;

public class ArmamentManagerController extends Controller implements DeviceManagerController,VesselController {


    @FXML
    public GridPane gridPane;
    public TextField textFieldModel;
    public TextField textFieldCaliber;

    @Override
    protected void initialize(){

    }

    @Override
    public boolean checkFields() {
        return (checkIntPositiveValue(textFieldCaliber.getText(),"Caliber")&&
                checkStringFullValue(textFieldModel.getText(),"Model"));
    }

    @Override
    public DeviceAdapter createAdapter() {
        return (checkFields())?new ArmamentAdapter(getFields()):null;
    }

    @Override
    public Object[] getFields() {
        if (checkFields())
            return new Object[]{
                    textFieldCaliber.getText(),
                    textFieldModel.getText(),
            };
        else
            return null;
    }

}
