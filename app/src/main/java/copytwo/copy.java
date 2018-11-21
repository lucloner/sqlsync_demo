package copytwo;

import android.support.v4.widget.TextViewCompat;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class copy {


    public  void  jdbc(){




        try {
            String sql="select * from guest.Student" ;
            //String connectDB = "jdbc:jtds:sqlserver://192.168.165.180;DatabaseName=PZG";
            // Create a variable for the connection string.
            String connectionUrl = "jdbc:jtds:sqlserver://192.168.165.180;databaseName=PZG;user=TechCent_PZG;password=12345678";
            try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
                //String SQL = "SELECT TOP 10 * FROM Person.Contact";
                ResultSet rs = stmt.executeQuery(sql);

                // Iterate through the data in the result set and display it.
                while (rs.next()) {
                    Log.v("11111",rs.getString("age") + " " + rs.getString("name"));
                }
            }
            // Handle any errors that may have occurred.
            catch (SQLException e) {
                e.printStackTrace();
            }

//
//            Class cl=   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            Connection con=DriverManager.getConnection(connectDB,"Tech_PZG","12345678");
//
//            Statement statement=con.createStatement();
//            ResultSet resultSet=statement.executeQuery(sql);
//
//            while (resultSet.next()){
//
//                Log.e("inging","i"+ resultSet.toString());
//
//
//
//
//
//            }
//            resultSet.close();
//            statement.close();




        }catch (Exception e){

           e.printStackTrace();
           System.out.print("出现异常");



        }




    }

}
