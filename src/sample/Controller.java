package sample;
import java.io.IOException;
import java.sql.*;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import javafx.scene.Node;

public class Controller {
    @FXML
    private ImageView img;


    public void connectButton(ActionEvent e){

        DatabaseCon connectNow = new DatabaseCon();
        Connection connectDB=connectNow.getConnection();

        String connectQuery="SELECT food_id FROM pantry.foods;";

        try{
            Statement statement=connectDB.createStatement();
            ResultSet out=statement.executeQuery(connectQuery);
            while(out.next()){
                System.out.println(out.getString("food_id"));
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void searchMenu(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("searchMenu.fxml"));
        Scene s2=new Scene (root);

        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setTitle("Main Menu > Search Menu");

        window.setScene(s2);
        window.show();

    }
    public void back(ActionEvent e) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene s1=new Scene (root);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setTitle("Main Menu");
        window.setScene(s1);
        window.show();
    }
    public void tableView(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("tableview.fxml"));
        Scene s2=(new Scene(root, 650, 700));


        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

        window.setScene(s2);
        window.show();

    }
    public void quickMenu(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("quickSearchMenu.fxml"));
        Scene s2=(new Scene(root, 650, 700));


        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

        window.setScene(s2);
        window.setTitle("Main Menu > Quick Search Menu");
        window.show();

    }
    public void addFood(ActionEvent e) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("addFood.fxml"));
        Scene s1=new Scene (root);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setTitle("Main Menu > Edit Menu");

        window.setScene(s1);
        window.show();
    }
    //INSERT INTO `pantry`.`foods` (`food_id`, `cal`, `isHealthy`) VALUES ('3', '25', '1');

}

