package com.yujin99libyjface.entity;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class createchart {



    public   void  dochart(String sql){

        Connection con = null;
        Statement stmt = null;
        String connectionUrl = "jdbc:jtds:sqlserver://192.168.165.180;databaseName=PZG;user=TechCent_PZG;password=12345678";

        try {

            con = DriverManager.getConnection(connectionUrl);
            stmt = con.createStatement();



            stmt.executeUpdate(sql);
            Log.e("gong", "ng" + "表已创建");

            stmt.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }




    }
}
