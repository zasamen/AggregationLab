package SeaTransport.view.Control.Managers.Abstract;


import SeaTransport.Ships.Vessel;
import SeaTransport.view.Control.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import static SeaTransport.Tooklits.ValueChecker.checkIntPositiveValue;


public class VesselManagerController extends Controller implements VesselController {

    @FXML
    public GridPane gridPane;
    @FXML
    public TextField textFieldLength,textFieldCapacity,textFieldWidth,textFieldDisplacement;

    private Object downStreamObject;

    @Override
    protected void initialize() {
        if (downStreamObject!=null) {
            Vessel vessel = (Vessel) downStreamObject;
            textFieldLength.setText(vessel.getLength() + "");
            textFieldWidth.setText(vessel.getWidth() + "");
            textFieldCapacity.setText(vessel.getCapacity() + "");
            textFieldDisplacement.setText(vessel.getDisplacement() + "");
        }
    }

    @Override
    public boolean checkFields() {
        return (checkIntPositiveValue(textFieldLength.getText(),"Length")&&
                checkIntPositiveValue(textFieldWidth.getText(),"Width")&&
                checkIntPositiveValue(textFieldLength.getText(),"Capacity")&&
                checkIntPositiveValue(textFieldLength.getText(),"Displacement"));

    }

    @Override
    public Object[] getFields() {
        if (checkFields())
            return new Object[]{
                    textFieldLength.getText(),
                    textFieldWidth.getText(),
                    textFieldCapacity.getText(),
                    textFieldDisplacement.getText()
            };
        else
            return null;
    }

    @Override
    public void setDownStreamObject(Object downStreamObject) {
        this.downStreamObject = downStreamObject;
    }
}
