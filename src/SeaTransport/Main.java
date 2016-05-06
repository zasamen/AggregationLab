package SeaTransport;

import SeaTransport.Ships.Vessel;
import SeaTransport.view.Control.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage stage;
    private Vessel vessel;


    public Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage=primaryStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/MainStage.fxml"));

        primaryStage.setScene(new Scene(loader.load()));
        ((MainController)loader.getController()).setMain(this);

        primaryStage.show();
    }

    public void setVessel(Vessel vessel) {
        this.vessel = vessel;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
