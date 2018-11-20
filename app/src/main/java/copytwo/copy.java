package copytwo;

import android.support.v4.widget.TextViewCompat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class copy {


    public  void  jdbc(){
        try {
            String sql="select * from Student" ;
            String connectDB = "jdbc:jtds:sqlserver://192.168.165.180;DatabaseName=PZG";


            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con=DriverManager.getConnection(connectDB,"Tech_PZG","12345678");

            Statement statement=con.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            System.out.print("异常");
            while (resultSet.next()){

             System.out.print(resultSet.first());


            }
            resultSet.close();
            statement.close();




        }catch (Exception e){

           e.printStackTrace();
           System.out.print("出现异常");



        }




    }

}
