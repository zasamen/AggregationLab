package SeaTransport.view.Control.Managers.Abstract;

import SeaTransport.Toolkits.Windows;
import SeaTransport.view.Control.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class BaseManagerController extends Controller {

    private String checkedVessel;

    @FXML
    public BorderPane borderPane;
    @FXML
    public Button buttonAccept;

    private int modalResult = 0;
    private Object object;
    private Object downStreamObject;

    private DeviceManagerController objectManagerController;

    public void setCheckedVessel(String checkedVessel) {
        this.checkedVessel = checkedVessel;
    }

    @Override
    protected void initialize() {
        try {
            FXMLLoader loader = new FXMLLoader(getMain().getClass().getResource("view/" + checkedVessel + "Manager.fxml"));
            borderPane.setCenter(loader.load());
            objectManagerController = loader.getController();
            objectManagerController.setDownStreamObject(downStreamObject);
            ((Controller) objectManagerController).setMain(getMain());
        } catch (IOException ioe) {
            Windows.showAlert(ioe + "");
        }
    }

    @FXML
    public void handleButtonAccept() {
        if ((objectManagerController).checkFields()) {
            object = objectManagerController.createAdapter().getObject();
            modalResult = 1;
            getStage().close();
        }
    }

    @FXML
    public void handleButtonDecline() {
        object = null;
        modalResult = 2;
        getStage().close();
    }

    public int getModalResult() {
        return modalResult;
    }

    public Object getObject() {
        return object;
    }

    public void setDownStreamObject(Object downStreamObject) {
        this.downStreamObject = downStreamObject;
    }

}
