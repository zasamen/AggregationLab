package SeaTransport.view.Control;

import PluginPackage.Plugin;
import SeaTransport.Ships.Vessel;
import SeaTransport.Toolkits.*;
import SeaTransport.view.Control.Managers.Abstract.BaseManagerController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class MainController extends Controller {

    @FXML
    public Menu menu;
    @FXML
    public MenuItem menuItemPluginCheck;
    @FXML
    public ListView<Vessel> listView;
    @FXML
    public MenuItem remove;

    private Plugin plugin;

    public Plugin getPlugin() {
        return plugin;
    }

    public void setPlugin(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    protected void initialize() {
        for (MenuItem menuItem : menu.getItems()) {
            menuItem.setOnAction(event -> manage(menuItem.getText(), null));
        }
    }

    public void handleRemove() {
        listView.getItems().remove(listView.getSelectionModel().getSelectedItem());
    }

    public void handleEdit() {
        if (listView.getItems().size()!=0) {
            Vessel vessel = listView.getSelectionModel().getSelectedItem();
            manage(vessel.getClass().getSimpleName(), vessel);
        }
    }

    private void manage(String vesselName, Vessel vessel) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getMain().getClass().getResource("view/BaseManager.fxml"));
            loader.load();
            BaseManagerController baseManagerController = loader.getController();
            baseManagerController.setCheckedVessel(vesselName);
            baseManagerController.setDownStreamObject(vessel);
            baseManagerController.setMain(getMain());
            Windows.showWindowWithModality(loader, vesselName, getMain());
            if (baseManagerController.getModalResult() == 1) {
                if (vessel != null)
                    listView.getItems().remove(listView.getSelectionModel().getSelectedItem());
                listView.getItems().add(0, (Vessel) baseManagerController.getObject());
            }
        } catch (Exception e) {
            Windows.showAlert(e + "");
        }
    }

    public void handleSaveXML(){
        saveToFile(new XMLSerializer(),"Transport.xml");
    }

    public void handleSaveBIN(){
        saveToFile(new BINSerializer(),"Transport.bin");
    }

    public void handleSaveTXT(){
        saveToFile(new TXTSerializer(),"Transport.txt");
    }

    public void handleOpenXML(){
        loadFromFile(new XMLSerializer(),"Transport.xml");
    }

    public void handleOpenBIN(){
        loadFromFile(new BINSerializer(),"Transport.bin");
    }

    public void handleOpenTXT(){
        loadFromFile(new TXTSerializer(),"Transport.txt");
    }

    private void saveToFile(Serializer serializer,String fileName){
        try (FileOutputStream fos=new FileOutputStream(fileName)){
            ProxySerializer proxySerializer=ProxySerializer.getInstance(getPlugin(),serializer);
            proxySerializer.serialize(fos,new LinkedList<>(listView.getItems()));
        }catch (IOException ioe){
            Windows.showAlert(ioe+"");
        }
    }

    @SuppressWarnings("unchecked")
    private void loadFromFile(Serializer serializer,String fileName){
        try (FileInputStream fis=new FileInputStream(fileName)){
            ProxySerializer proxySerializer=ProxySerializer.getInstance(getPlugin(),serializer);
            List<Vessel> list = (List<Vessel>)proxySerializer.deserialize(fis);
            listView.getItems().clear();
            listView.getItems().addAll(list);
        }catch (IOException|ClassNotFoundException e){
            Windows.showAlert(e+"");
        }
    }

    public void handleMenuItemPluginCheck(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getMain().getClass().getResource("view/PluginStage.fxml"));
            loader.load();
            Controller controller = loader.getController();
            controller.setMain(getMain());
            ((PluginStageController)controller).setMainController(this);
            Windows.showWindowWithModality(loader, "plugins", getMain());
        } catch (Exception e) {
            Windows.showAlert(e + "");
        }
    }
}
