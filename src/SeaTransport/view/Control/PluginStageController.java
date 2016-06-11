package SeaTransport.view.Control;


import PluginPackage.Plugin;
import SeaTransport.Toolkits.PluginLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.File;

public class PluginStageController extends Controller {

    @FXML
    public Button acceptButton,declineButton;
    @FXML
    public ListView<Plugin> listView;

    private ObservableList<Plugin> pluginObservableList= FXCollections.observableArrayList();

    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public MainController getMainController() {
        return mainController;
    }

    @Override
    protected void initialize() {
        File file=new File("pluginsDir");
        if (file.exists()){
            File[] files=file.listFiles();
            for (File someFile:files){
                PluginLoader loader=new PluginLoader(someFile);
                String pluginName=someFile.getName().split("\\.")[0];
                Class<Plugin> pluginClass=null;
                try {
                    pluginClass=loader.loadPlugin(pluginName);
                }catch (Exception e){
                    e.printStackTrace();
                }
                Plugin plugin=null;
                if (pluginClass!=null){
                    try {
                        plugin=pluginClass.newInstance();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                if (plugin!=null){
                    pluginObservableList.add(plugin);
                }
            }
            listView.setItems(pluginObservableList);
        }
    }


    public void handleDecline(){
        getMainController().setPlugin(null);
        getStage().close();
    }

    public void handleAccept(){
        if (listView.getSelectionModel().getSelectedItem()!=null){
            getMainController().setPlugin(listView.getSelectionModel().getSelectedItem());}
        getStage().close();
    }
}
