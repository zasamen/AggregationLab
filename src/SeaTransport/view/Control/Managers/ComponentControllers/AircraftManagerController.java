package SeaTransport.view.Control.Managers.ComponentControllers;

import SeaTransport.ShipAdapter.DeviceAdapter.AircraftAdapter;
import SeaTransport.ShipAdapter.DeviceAdapter.DeviceAdapter;
import SeaTransport.view.Control.Controller;
import SeaTransport.view.Control.Managers.Abstract.DeviceManagerController;
import SeaTransport.view.Control.Managers.Abstract.VesselController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import static SeaTransport.Tooklits.ValueChecker.checkIntPositiveValue;
import static SeaTransport.Tooklits.ValueChecker.checkStringFullValue;

public class AircraftManagerController extends Controller implements VesselController,DeviceManagerController {


    @FXML
    public GridPane gridPane;
    public TextField textFieldWeight;
    public TextField textFieldModel;

    @Override
    protected void initialize(){

    }

    @Override
    public boolean checkFields() {
        return (checkIntPositiveValue(textFieldWeight.getText(),"Weight")&&
                checkStringFullValue(textFieldModel.getText(),"Model"));
    }

    @Override
    public DeviceAdapter createAdapter() {
        return (checkFields())?new AircraftAdapter(getFields()):null;
    }

    @Override
    public Object[] getFields() {
        if (checkFields())
            return new Object[]{
                    textFieldWeight.getText(),
                    textFieldModel.getText(),
            };
        else
            return null;
    }

}
