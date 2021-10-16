package sample;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class searchController implements Initializable {
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
    private TextField searchBar;
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

    ObservableList<groupTable> groups = FXCollections.observableArrayList();
    ObservableList<brandTable> brands = FXCollections.observableArrayList();

    private final ObservableList<ModelTable> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb){
        Connection con= DatabaseCon.getConnection();
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
        try {
            ResultSet rs=con.createStatement().executeQuery("SELECT * FROM pantry.food");
            while(rs.next()){
                data.add(new ModelTable(
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

        table.setItems(data);

        FilteredList<ModelTable> filteredData = new FilteredList<>(data,b->true);
            searchBar.textProperty().addListener((observable,oldValue,newValue)->{
                filteredData.setPredicate(ModelTable->{
                    if(newValue ==  null || newValue.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if(ModelTable.getFid().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                        return true;
                    }else if(ModelTable.getFname().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                        return true;
                    }else if(ModelTable.getBid().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                        return true;
                    }else{
                        return false;
                    }
                });
            } );

        SortedList<ModelTable> sortedData=new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(table.comparatorProperty());

        table.setItems(sortedData);

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
