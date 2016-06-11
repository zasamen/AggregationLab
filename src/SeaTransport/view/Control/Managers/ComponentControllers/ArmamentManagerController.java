package SeaTransport.view.Control.Managers.ComponentControllers;

import SeaTransport.ShipAdapter.DeviceAdapter.ArmamentAdapter;
import SeaTransport.ShipAdapter.DeviceAdapter.DeviceAdapter;
import SeaTransport.Ships.VesselComponent.Armament;
import SeaTransport.view.Control.Controller;
import SeaTransport.view.Control.Managers.Abstract.DeviceManagerController;
import SeaTransport.view.Control.Managers.Abstract.VesselController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import static SeaTransport.Toolkits.ValueChecker.checkIntPositiveValue;
import static SeaTransport.Toolkits.ValueChecker.checkStringFullValue;

public class ArmamentManagerController extends Controller implements DeviceManagerController,VesselController {


    @FXML
    public GridPane gridPane;
    @FXML
    public TextField textFieldModel;
    @FXML
    public TextField textFieldCaliber;

    private Object downStreamObject;

    @Override
    protected void initialize(){
        if (downStreamObject!=null) {
            Armament armament = (Armament) downStreamObject;
            textFieldModel.setText(armament.getModel());
            textFieldCaliber.setText(armament.getCaliber() + "");
        }
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

    @Override
    public void setDownStreamObject(Object downStreamObject) {
        this.downStreamObject = downStreamObject;
    }
}
