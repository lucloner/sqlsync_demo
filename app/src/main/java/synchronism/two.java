package synchronism;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import copytwo.Student;

public class two {


    public void four() {

        try {
            String sql = "select * from guest.Student";
            String connectionUrl = "jdbc:jtds:sqlserver://192.168.165.180;databaseName=PZG;user=TechCent_PZG;password=12345678";
            try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {

                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()){
                    int age = rs.getInt("age");
                    String name = rs.getString("name");
                    String sql2="insert into guest.Teacher values(" +age+",'"+ name +"')";
                    int a= stmt.executeUpdate(sql2);



                }



            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("出现异常");
        }
    }
}
