package SeaTransport.view.Control.Managers.ComponentControllers;

import SeaTransport.ShipAdapter.DeviceAdapter.DeviceAdapter;
import SeaTransport.ShipAdapter.DeviceAdapter.SailAdapter;
import SeaTransport.Ships.VesselComponent.Sail;
import SeaTransport.view.Control.Controller;
import SeaTransport.view.Control.Managers.Abstract.DeviceManagerController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import static SeaTransport.Toolkits.ValueChecker.checkIntPositiveValue;

public class SailManagerController extends Controller implements DeviceManagerController {

    @FXML
    public GridPane gridPane;
    @FXML
    public TextField textFieldHeight;
    @FXML
    public TextField textFieldWidth;

    private Object downStreamObject;

    @Override
    protected void initialize(){
        if (downStreamObject!=null) {
            Sail sail = (Sail) downStreamObject;
            textFieldHeight.setText(sail.getHeight() + "");
            textFieldWidth.setText(sail.getWidth() + "");
        }
    }

    @Override
    public boolean checkFields() {
        return (checkIntPositiveValue(textFieldWidth.getText(),"Width")&&
                checkIntPositiveValue(textFieldHeight.getText(),"Height"));
    }

    @Override
    public DeviceAdapter createAdapter() {
        return (checkFields())?new SailAdapter(getFields()):null;
    }

    @Override
    public Object[] getFields() {
        if (checkFields())
            return new Object[]{
                    textFieldHeight.getText(),
                    textFieldWidth.getText(),
            };
        else
            return null;
    }

    @Override
    public void setDownStreamObject(Object downStreamObject) {
        this.downStreamObject = downStreamObject;
    }
}
