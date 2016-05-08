package SeaTransport.view.Control;

import SeaTransport.Ships.Vessel;
import SeaTransport.Tooklits.*;
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
import java.util.ArrayList;
import java.util.List;

public class MainController extends Controller {

    @FXML
    public Menu menu;
    @FXML
    public ListView<Vessel> listView;
    @FXML
    public MenuItem remove;

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
        try {
            FileOutputStream fos=new FileOutputStream(fileName);
            serializer.serialize(new BufferedOutputStream(fos),new ArrayList<>(listView.getItems()));
        }catch (IOException ioe){
            Windows.showAlert(ioe+"");
        }
    }

    @SuppressWarnings("unchecked")
    private void loadFromFile(Serializer serializer,String fileName){
        try {
            FileInputStream fis=new FileInputStream(fileName);
            List<Vessel> list = (List<Vessel>)serializer.deserialize(fis);
            listView.getItems().clear();
            listView.getItems().addAll(list);
        }catch (IOException|ClassNotFoundException e){
            Windows.showAlert(e+"");
        }
    }
}
