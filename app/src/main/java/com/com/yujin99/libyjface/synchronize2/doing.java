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


    List<String> chart;


    public void method(Context ct) {
        //自动创建数据表
        new docreatechart().method(ct);
       try{
            //获得所有表名

            Cursor c = new chart().getchart(ct);
            //遍历所有表名
           while (c.moveToNext()) {

                String b = c.getString(0);
               //插入每一个表信息
               new insert().doinsert(ct,b);

               }

        }catch(Exception e){
            e.printStackTrace();

        }

    }
}


