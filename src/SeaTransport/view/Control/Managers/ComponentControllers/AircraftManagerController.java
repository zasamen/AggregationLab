package SeaTransport.view.Control.Managers.ComponentControllers;

import SeaTransport.ShipAdapter.DeviceAdapter.AircraftAdapter;
import SeaTransport.ShipAdapter.DeviceAdapter.DeviceAdapter;
import SeaTransport.Ships.VesselComponent.Aircraft;
import SeaTransport.view.Control.Controller;
import SeaTransport.view.Control.Managers.Abstract.DeviceManagerController;
import SeaTransport.view.Control.Managers.Abstract.VesselController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import static SeaTransport.Toolkits.ValueChecker.checkIntPositiveValue;
import static SeaTransport.Toolkits.ValueChecker.checkStringFullValue;

public class AircraftManagerController extends Controller implements VesselController,DeviceManagerController {


    @FXML
    public GridPane gridPane;
    @FXML
    public TextField textFieldWeight;
    @FXML
    public TextField textFieldModel;

    private Object downStreamObject;

    @Override
    protected void initialize() {
        if (downStreamObject!=null) {
            Aircraft aircraft = (Aircraft) downStreamObject;
            textFieldModel.setText(aircraft.getModel());
            textFieldWeight.setText(aircraft.getWeight() + "");
        }
    }

    @Override
    public boolean checkFields() {
        return (checkIntPositiveValue(textFieldWeight.getText(), "Weight") &&
                checkStringFullValue(textFieldModel.getText(), "Model"));
    }

    @Override
    public DeviceAdapter createAdapter() {
        return (checkFields()) ? new AircraftAdapter(getFields()) : null;
    }

    @Override
    public Object[] getFields() {
        return (checkFields()) ?
                new Object[]{
                textFieldWeight.getText(),
                textFieldModel.getText(),
        } : null;
    }

    @Override
    public void setDownStreamObject(Object downStreamObject) {
        this.downStreamObject = downStreamObject;
    }
}
