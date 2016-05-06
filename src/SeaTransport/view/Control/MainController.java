package SeaTransport.view.Control;

        import SeaTransport.Tooklits.Windows;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.control.Menu;
        import javafx.scene.control.MenuItem;

public class MainController extends Controller {

    @FXML
    private Menu menu;

    @Override
    protected void initialize() {
        for (MenuItem menuItem : menu.getItems()) {
            menuItem.setOnAction(event -> {
                try {
                    FXMLLoader loader=new FXMLLoader();
                    loader.setLocation(getMain().getClass().getResource("view/BaseManager.fxml"));
                    loader.load();
                    BaseManagerController baseManagerController =loader.getController();
                    baseManagerController.setCheckedVessel(menuItem.getText());
                    baseManagerController.setMain(getMain());
                    Windows.showWindowWithModality(loader, menuItem.getText(), getMain());
                    if (baseManagerController.getModalResult()==1){
                        Object object=baseManagerController.getObject();
                    }
                } catch (Exception e) {
                    Windows.showAlert(e + "");
                }
            });
        }
    }
}
