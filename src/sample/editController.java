package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class editController {
    @FXML
    private ComboBox groups;
    @FXML
    private ComboBox brands;
    ObservableList<String> group = FXCollections.
            observableArrayList("Starch", "Proteins and Fish", "Fruits and Vegetables", "Dairy", "Snacks");
    ObservableList<String> brand = FXCollections.
            observableArrayList("Walmart","Aldis","Kraft","Publix","Whole Foods","Nestle");
    @FXML
    private TextField tfname;
    @FXML
    private TextField tcal;
    @FXML
    private TextField tser;
    @FXML
    private TextField deleted;
    @FXML
    private Label errlbl;
    @FXML
    private Label succlbl;
    @FXML
    private Label idlbl;
    @FXML
    private Label succdel;


    @FXML
    private void initialize() {
        groups.setValue("Select groups Here");
        groups.setItems(group);
        brands.setValue("Select brands Here");
        brands.setItems(brand);
        errlbl.setVisible(false);
        succlbl.setVisible(false);
        idlbl.setVisible(false);
        succdel.setVisible(false);



    }


    public void deleteFoodNow(ActionEvent e) throws Exception {
        String del = deleted.getText();
        DatabaseCon connectNow = new DatabaseCon();
        Connection connectDB = connectNow.getConnection();
        Statement stmnt = connectDB.createStatement();
        boolean valid=true;
        if(del.isEmpty()){
            valid=false;
        }
        if(valid) {
            try {
                String q = "SELECT * FROM `pantry`.`food` WHERE food_id =" + del;
                ResultSet rs = connectDB.createStatement().executeQuery(q);
                if (!rs.next() || del.isEmpty()) {
                    valid = false;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if(valid) {
            try {

                String connectQuery = "DELETE FROM `pantry`.`food` WHERE food_id = " + del;
                stmnt.execute(connectQuery);
                idlbl.setVisible(false);
                succdel.setVisible(true);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else{
            idlbl.setVisible(true);
            succdel.setVisible(false);
        }
    }
    public void addFoodNow(ActionEvent e) throws Exception{
        String name=tfname.getText();

        String ser=tser.getText();
        String gs= (String)groups.getValue();
        String bs=(String)brands.getValue();
        int gid;
        int bid;
        if((name.isBlank()) || (ser.isBlank()) || (tcal.getText().isBlank())){
            errlbl.setVisible(true);
            succlbl.setVisible(false);
        }else {
            int cal=Integer.valueOf(tcal.getText());
            succlbl.setVisible(true);
            errlbl.setVisible(false);
            switch (gs) {
                case "Starch":
                    gid = 1111;
                    break;
                case "Proteins and Fish":
                    gid = 2222;
                    break;
                case "Fruits and Vegetables":
                    gid = 3333;
                    break;
                case "Dairy":
                    gid = 4444;
                    break;
                case "Snacks":
                    gid = 5555;
                    break;
                default:
                    gid = 0;
                    break;
            }
            switch (bs) {
                case "Publix":
                    bid = 1;
                    break;
                case "Walmart":
                    bid = 2;
                    break;
                case "Aldis":
                    bid = 3;
                    break;
                case "Kraft":
                    bid = 44;
                    break;
                case "Whole Foods":
                    bid = 5;
                    break;
                case "Nestle":
                    bid = 6;
                    break;
                default:
                    bid = 0 ;
                    break;
            }
            DatabaseCon connectNow = new DatabaseCon();
            Connection connectDB = connectNow.getConnection();
            try {
                Statement stmnt = connectDB.createStatement();
                String connectQuery = "INSERT INTO `pantry`.`food` (`fname`, `cal`, `serSize`, `group_id`, `brand_id`) VALUES ('" + name + "', '" + cal + "', '" + ser + "', '" + gid + "', '" + bid + "')";
                stmnt.execute(connectQuery);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }
    public void back(ActionEvent e) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene s1=new Scene (root);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setTitle("Main Menu");

        window.setScene(s1);
        window.show();
    }
}
