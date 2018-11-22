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
    List<Student> li;

    public void four() {
        li = new ArrayList<Student>();
        try {
            String sql = "select * from guest.Student";
            String connectionUrl = "jdbc:jtds:sqlserver://192.168.165.180;databaseName=PZG;user=TechCent_PZG;password=12345678";
            try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {

                ResultSet rs = stmt.executeQuery(sql);


                rs.insertRow();
                rs.moveToInsertRow();
                rs.updateInt(0,li.get(0).getAge());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("出现异常");
        }
    }
}
