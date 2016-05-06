package SeaTransport.view.Control;


import SeaTransport.Ships.Vessel;
import SeaTransport.Tooklits.Windows;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import static SeaTransport.Tooklits.ValueChecker.checkIntPositiveValue;


public class VesselManagerController extends Controller implements VesselController {

    @FXML
    public GridPane gridPane;
    @FXML
    public TextField textFieldLength,textFieldCapacity,textFieldWidth,textFieldDisplacement;

    @Override
    protected void initialize() {

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
}
