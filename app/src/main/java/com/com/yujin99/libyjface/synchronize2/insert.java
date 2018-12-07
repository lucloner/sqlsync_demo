package com.com.yujin99.libyjface.synchronize2;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class insert {
     /**
     *获取表字段信息
     * 插入表中
     * @param ct
     * @param chartname 表名
     */
    public  void doinsert(Context ct,   String chartname ,String database){

        Cursor d = new chartdata().getdata(ct, chartname, database);

        Connection con = null;
        String connectionUrl = "jdbc:jtds:sqlserver://192.168.165.180;databaseName=PZG;user=TechCent_PZG;password=12345678";



        try {
            con = DriverManager.getConnection(connectionUrl);
            while (d.moveToNext()) {

                int number1 = d.getColumnCount();
              String  placeholder=null;
                placeholder="?";
                for (int i = 1; i < number1; i++) {
                    placeholder+=",";
                    placeholder+="?";
                    }


                String sql = "insert into guest.test values("+placeholder+")";
                PreparedStatement stmt = con.prepareStatement(sql);

                for (int i = 0; i < number1; i++) {
                    Object o=null;
                    switch (d.getType(i)) {
                        case Cursor.FIELD_TYPE_NULL:
                            o=null;
                            break;
                        case Cursor.FIELD_TYPE_INTEGER:
                            o=d.getInt(i);
                            // stmt.setInt( i,d.getInt(i));
                            break;
                        case Cursor.FIELD_TYPE_FLOAT:
                            o=d.getFloat(i);
                            break;
                        case Cursor.FIELD_TYPE_STRING:
                            o=d.getString(i);
                            break;
                        case Cursor.FIELD_TYPE_BLOB:
                            o=d.getBlob(i);
                            break;
                        default:
                            break;
                }


                    stmt.setObject(i+1,o);

                }

                stmt.executeUpdate();
                Log.e("gong", "" + "又增加了一条数据");



            }



        } catch (SQLException e) {
            e.printStackTrace();
        }









    }
}
