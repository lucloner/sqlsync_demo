package com.com.yujin99.libyjface.synchronize2;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class createchart {


//创建或删除表单
    public   void  dochart(String sql,  String connectionUrl){

        Connection con = null;
        Statement stmt = null;


        try {

            con = DriverManager.getConnection(connectionUrl);
            stmt = con.createStatement();



            stmt.executeUpdate(sql);
            Log.e("gong", "ng" + "表已创建或删除");

            stmt.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }




    }
}
