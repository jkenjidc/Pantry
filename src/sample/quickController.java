package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class quickController{
    @FXML
    private ComboBox gcombo;
    @FXML
    private ComboBox ccombo;
    @FXML
    private Button searchG;
    @FXML
    private Button searchC;

    private String command;
    private Parent root;

    ObservableList<String> groups = FXCollections.
            observableArrayList("Starch","Proteins and Fish","Fruits and Vegetables","Dairy","Snacks");
    ObservableList<String> calories = FXCollections.
            observableArrayList("less than 100","less than 200","less than 300","300 and above");
    @FXML
    private void initialize(){
        gcombo.setValue("Select Here");
        gcombo.setItems(groups);
        ccombo.setValue("Select Here");
        ccombo.setItems(calories);
        gcombo.setVisible(false);
        ccombo.setVisible(false);
        searchC.setVisible(false);
        searchG.setVisible(false);


    }
    public void back(ActionEvent e) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene s1=new Scene (root);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

        window.setScene(s1);
        window.show();
        window.setTitle("Main Menu");
    }
    public void clickG(ActionEvent e){
        gcombo.setVisible(true);
        searchG.setVisible(true);
        ccombo.setVisible(false);
        searchC.setVisible(false);



    }
    public void clickC(ActionEvent e){
        ccombo.setVisible(true);
        searchC.setVisible(true);
        gcombo.setVisible(false);
        searchG.setVisible(false);


    }
    public void tableView(ActionEvent e) throws IOException {
        if(ccombo.isVisible()){
            command=(String)ccombo.getValue();
        }else{
            command=(String)gcombo.getValue();
        }
        FXMLLoader load=new FXMLLoader(getClass().getResource("tableview.fxml"));
        root =load.load();
        TableController tc= load.getController();

        tc.setCommand(command);


        //Parent root = FXMLLoader.load(getClass().getResource("tableview.fxml"));
        Scene s2=(new Scene(root, 650, 700));


        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setTitle("Main Menu > Quick Search Menu > Results Screen");

        window.setScene(s2);
        window.show();

    }
}
