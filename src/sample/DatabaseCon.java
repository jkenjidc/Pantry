package sample;
import java.sql.*;
public class DatabaseCon {
    private static Connection databaseLink;

    public static Connection getConnection(){
        String dUser="root";
        String dPass="I-dontknow123";
        String url;
        url = "jdbc:mysql://localhost/pantry";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink= DriverManager.getConnection(url,dUser,dPass);

        }catch (Exception e){
            System.out.println(e.getMessage());
            //System.out.println("failed");
        }




        return databaseLink;
    }
}
