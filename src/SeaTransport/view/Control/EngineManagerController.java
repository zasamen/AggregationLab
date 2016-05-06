package SeaTransport.view.Control;


import SeaTransport.ShipAdapter.DeviceAdapter.DeviceAdapter;
import SeaTransport.ShipAdapter.DeviceAdapter.EngineAdapter;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import static SeaTransport.Tooklits.ValueChecker.checkIntPositiveValue;

public class EngineManagerController extends Controller implements VesselController,DeviceManagerController {


    @FXML
    public GridPane gridPane;
    public TextField textFieldVolume;
    public TextField textFieldPower;
    public TextField textFieldConsumption;

    @Override
    protected void initialize(){

    }

    @Override
    public boolean checkFields() {
        return (checkIntPositiveValue(textFieldVolume.getText(),"Volume")&&
                checkIntPositiveValue(textFieldPower.getText(),"Power")&&
                checkIntPositiveValue(textFieldConsumption.getText(),"Consumption"));
    }

    @Override
    public DeviceAdapter createAdapter() {
        return (checkFields())?new EngineAdapter(getFields()):null;
    }

    @Override
    public Object[] getFields() {
        if (checkFields())
            return new Object[]{
                    textFieldVolume.getText(),
                    textFieldPower.getText(),
                    textFieldConsumption.getText()
            };
        else
            return null;
    }
}
