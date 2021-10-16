package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public  class TableController implements Initializable{
    @FXML
    private TableView<ModelTable> table;
    @FXML
    private TableColumn<ModelTable,String> food_id;
    @FXML
    private TableColumn<ModelTable,String> fname;
    @FXML
    private TableColumn<ModelTable,String> cal;
    @FXML
    private TableColumn<ModelTable,String> serSize;
    @FXML
    private TableColumn<ModelTable,String> group_id;
    @FXML
    private TableColumn<ModelTable,String> brand_id;
    @FXML
    private Label sucesslbl;
    @FXML
    private Button showbtn;
    @FXML
    private ImageView img;
    @FXML
    private TabPane tabs;
    @FXML
    private TableView<groupTable> gTable;
    @FXML
    private TableColumn<groupTable,String> gid;
    @FXML
    private TableColumn<groupTable,String> gname;
    @FXML
    private TableColumn<groupTable,String> fats;
    @FXML
    private TableColumn<groupTable,String> carbs;
    @FXML
    private TableColumn<groupTable,String> vitamins;
    @FXML
    private TableView<brandTable> bTable;
    @FXML
    private TableColumn<brandTable,String> bid;
    @FXML
    private TableColumn<brandTable,String> bname;
    @FXML
    private TableColumn<brandTable,String> org;
    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
    ObservableList<groupTable> groups = FXCollections.observableArrayList();
    ObservableList<brandTable> brands = FXCollections.observableArrayList();
    private String command;
    Connection con;
    public void setCommand(String c){
        command=c;
    }

    public void showTable(){
        sucesslbl.setVisible(false);
        showbtn.setVisible(false);
        table.setVisible(true);
        img.setVisible(false);
        tabs.setVisible(true);
        String query="SELECT * FROM pantry.food";
        switch(command){
            case "Starch":
                query="SELECT * FROM pantry.food WHERE group_id=1111";
                break;
            case "Proteins and Fish":
                query="SELECT * FROM pantry.food WHERE group_id=2222";
                break;
            case "Fruits and Vegetables":
                query="SELECT * FROM pantry.food WHERE group_id=3333";
                break;
            case "Dairy":
                query="SELECT * FROM pantry.food WHERE group_id=4444";
                break;
            case "Snacks":
                query="SELECT * FROM pantry.food WHERE group_id=5555";
                break;
            case "less than 100":
                query="SELECT * FROM pantry.food WHERE cal < 100";
                break;
            case "less than 200":
                query="SELECT * FROM pantry.food WHERE cal < 200";
                break;
               case "less than 300":
                query="SELECT * FROM pantry.food WHERE cal < 300";
                break;
            case "300 and above":
                query="SELECT * FROM pantry.food WHERE cal > 300";
                break;
            default:
                break;
        }
        try {
            ResultSet rs=con.createStatement().executeQuery(query);
            while(rs.next()){
                oblist.add(new ModelTable(
                        rs.getString("food_id"),
                        rs.getString("fname"),
                        rs.getString("cal"),
                        rs.getString("serSize"),
                        rs.getString("group_id"),
                        rs.getString("brand_id")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        food_id.setCellValueFactory(new PropertyValueFactory<>("fid"));
        fname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        cal.setCellValueFactory(new PropertyValueFactory<>("cal"));
        serSize.setCellValueFactory(new PropertyValueFactory<>("ser"));
        group_id.setCellValueFactory(new PropertyValueFactory<>("gid"));
        brand_id.setCellValueFactory(new PropertyValueFactory<>("bid"));
        if(oblist == null)
            System.out.println("fail");

        table.setItems(oblist);
    }
   @Override
    public void initialize(URL location, ResourceBundle resources){
        con= DatabaseCon.getConnection();
        table.setVisible(false);
        tabs.setVisible(false);
        /*

        try {
            ResultSet rs=con.createStatement().executeQuery("SELECT * FROM pantry.food WHERE group_id=1111");
            while(rs.next()){
                oblist.add(new ModelTable(
                        rs.getString("food_id"),
                        rs.getString("fname"),
                        rs.getString("cal"),
                        rs.getString("serSize(grams)"),
                        rs.getString("group_id"),
                        rs.getString("brand_id")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        food_id.setCellValueFactory(new PropertyValueFactory<>("fid"));
        fname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        cal.setCellValueFactory(new PropertyValueFactory<>("cal"));
        serSize.setCellValueFactory(new PropertyValueFactory<>("ser"));
        group_id.setCellValueFactory(new PropertyValueFactory<>("gid"));
        brand_id.setCellValueFactory(new PropertyValueFactory<>("bid"));
        if(oblist == null)
            System.out.println("fail");

        table.setItems(oblist); */
       try {
           ResultSet rs=con.createStatement().executeQuery("SELECT * FROM pantry.food_group");
           while(rs.next()){
               groups.add(new groupTable(
                       rs.getString("group_id"),
                       rs.getString("gname"),
                       rs.getString("highInFats"),
                       rs.getString("highInCarbs"),
                       rs.getString("highInVitamins")
               ));
           }
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
       gid.setCellValueFactory(new PropertyValueFactory<>("gid"));
       gname.setCellValueFactory(new PropertyValueFactory<>("gname"));
       fats.setCellValueFactory(new PropertyValueFactory<>("fats"));
       carbs.setCellValueFactory(new PropertyValueFactory<>("carbs"));
       vitamins.setCellValueFactory(new PropertyValueFactory<>("vitamins"));

       gTable.setItems(groups);
       try {
           ResultSet rs=con.createStatement().executeQuery("SELECT * FROM pantry.brand");
           while(rs.next()){
               brands.add(new brandTable(
                       rs.getString("brand_id"),
                       rs.getString("bname"),
                       rs.getString("isOrganic")
               ));
           }
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
       bid.setCellValueFactory(new PropertyValueFactory<>("bid"));
       bname.setCellValueFactory(new PropertyValueFactory<>("bname"));
       org.setCellValueFactory(new PropertyValueFactory<>("org"));


       bTable.setItems(brands);

    }
    public void back(ActionEvent e) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("quickSearchMenu.fxml"));
        Scene s1=new Scene (root);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setTitle("Main Menu > Quick Search Menu");

        window.setScene(s1);
        window.show();
    }


}
