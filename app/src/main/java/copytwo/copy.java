package copytwo;


import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class copy {
    /**
     * SQLife
     */
    public  void  jdbc() {

        try{
            Class.forName("org.sqlite.JDBC");
           String url="jdbc:sqlite:test.db";
         Connection con = DriverManager.getConnection(url);
         String  sql="select * from 表.test";
          Statement sta=con.createStatement();
          ResultSet rs = sta.executeQuery(sql);



          while (rs.next()) {
                int id = rs.getInt("id");
                String word= rs.getString("word");
                String detail = rs.getString("detail");
                Log.e("gong",id+word+detail);



            }


        } catch (Exception e) {
            e.printStackTrace();
          }
       }

    }








