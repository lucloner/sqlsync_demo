package com.com.yujin99.libyjface.synchronize2;

import android.content.Context;
import android.database.Cursor;
import android.icu.text.StringSearch;
import android.util.Log;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class doing {


    /**
     * @param ct
     */
    public void method(Context ct) {
        String connectionUrl = "jdbc:jtds:sqlserver://192.168.165.180;databaseName=PZG;user=TechCent_PZG;password=12345678";
        String database="Tm";

        //自动创建数据表
        new docreatechart().method(ct,connectionUrl,database);
       try{
            //获得所有表名和SQL语句

            Cursor c = new master().getchart(ct,database);
            //遍历所有表名
           while (c.moveToNext()) {

               String chartname = c.getString(0);
               //插入每一个表信息
               new insert().doinsert(ct,chartname,database);

               }

        }catch(Exception e){
            e.printStackTrace();

        }

    }
}


