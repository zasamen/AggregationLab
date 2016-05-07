package SeaTransport.Tooklits;

import SeaTransport.Main;
import SeaTransport.view.Control.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Windows {

    public static void showAlert(String message) {
        new Alert(Alert.AlertType.WARNING, message, ButtonType.CANCEL).show();
    }

    public static void showWindowWithModality(FXMLLoader loader, String title, Main main) throws Exception {
        prepareWindow(loader, title, main).showAndWait();
    }

    private static Stage prepareWindow(FXMLLoader loader, String title, Main main) throws Exception {
        Stage stage = new Stage();
        if (loader.getRoot() == null)
            stage.setScene(new Scene(loader.load()));
        else
            stage.setScene(new Scene(loader.getRoot()));
        ((Controller) loader.getController()).setStage(stage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(main.getStage());
        stage.setTitle(title);
        return stage;
    }

}
