package SeaTransport.view.Control.Managers.ComponentControllers;

import SeaTransport.ShipAdapter.DeviceAdapter.DeviceAdapter;
import SeaTransport.ShipAdapter.DeviceAdapter.EngineAdapter;
import SeaTransport.Ships.VesselComponent.Engine;
import SeaTransport.view.Control.Controller;
import SeaTransport.view.Control.Managers.Abstract.DeviceManagerController;
import SeaTransport.view.Control.Managers.Abstract.VesselController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import static SeaTransport.Toolkits.ValueChecker.checkIntPositiveValue;

public class EngineManagerController extends Controller implements VesselController,DeviceManagerController {


    @FXML
    public GridPane gridPane;
    @FXML
    public TextField textFieldVolume;
    @FXML
    public TextField textFieldPower;
    @FXML
    public TextField textFieldConsumption;

    private Object downStreamObject;

    @Override
    protected void initialize(){
        if (downStreamObject!=null) {
            Engine engine = (Engine) downStreamObject;
            textFieldVolume.setText(engine.getVolume() + "");
            textFieldConsumption.setText(engine.getConsumption() + "");
            textFieldPower.setText(engine.getPower() + "");
        }
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

    @Override
    public void setDownStreamObject(Object downStreamObject) {
        this.downStreamObject = downStreamObject;
    }
}
