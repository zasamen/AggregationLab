package SeaTransport.view.Control;

import SeaTransport.ShipAdapter.ShallopAdapter;
import SeaTransport.ShipAdapter.VesselAdapter;
import SeaTransport.Tooklits.Windows;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

import static SeaTransport.Tooklits.ValueChecker.checkStringFullValue;

public class ShallopManagerController extends Controller implements VesselController,DeviceManagerController {

    @FXML
    public BorderPane borderPane;
    public TextField textFieldType;

    private VesselManagerController vesselManagerController;

    @Override
    protected void initialize(){
        try {
            FXMLLoader loader=new FXMLLoader(getMain().getClass().getResource("view/VesselManager.fxml"));
            borderPane.setCenter(loader.load());
            vesselManagerController=loader.getController();
        }catch (IOException ioe){
            Windows.showAlert(ioe+"");
        }
    }

    @Override
    public boolean checkFields() {
        return (vesselManagerController.checkFields()&&checkStringFullValue(textFieldType.getText(),"Type"));
    }

    @Override
    public VesselAdapter createAdapter() {
        return (checkFields())?new ShallopAdapter(getFields()):null;
    }

    @Override
    public Object[] getFields() {
        if (checkFields()) {
            Object[] objects = vesselManagerController.getFields();
            if (objects != null) {
                Object[] result = new Object[objects.length + 1];
                System.arraycopy(objects, 0, result, 0, objects.length);
                result[result.length - 1] = textFieldType.getText();
                return result;
            } else
                return null;
        } else
            return null;
    }
}
