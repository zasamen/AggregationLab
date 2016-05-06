package SeaTransport.view.Control;

import SeaTransport.Main;
import javafx.stage.Stage;

public abstract class Controller {
    private Main main;

    public void setMain(Main main) {
        this.main = main;
        initialize();
    }

    public Main getMain() {
        return main;
    }

    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    protected abstract void initialize();
}
