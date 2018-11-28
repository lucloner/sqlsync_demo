package copytwo;


import android.util.Log;

import com.yujin99libyjface.entity.test;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *同步类
 *
 */
public class copy2 {
    public  void tesss(List<test> tes)  {

        Connection con = null;
        String connectionUrl = "jdbc:jtds:sqlserver://192.168.165.180;databaseName=PZG;user=TechCent_PZG;password=12345678";


        try {
            con = DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        for (test u:tes
                ) {
            int a=u.getId();
            String b=u.getWord();
            String c=u.getDetail();





            try {
                String sql="insert into guest.test values(?,?,?)";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt( 1,a);
                stmt.setString(2,b);
                stmt.setString(3,c);



                stmt.executeUpdate();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }




    }
}
