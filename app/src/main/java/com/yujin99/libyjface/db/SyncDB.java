package com.yujin99.libyjface.db;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public  class SyncDB extends Service {

    private SQLiteDatabase[] SrcDBs;
    private Connection DstDB;
    private Context context;
    private String header; //数据表名格式为header.SrcDBs编号_表名
    private PreparedStatement stmt;

    /**
     * 本方法用于初始化
     *
     * @param SrcDBs     需要同步的所有数据库
     * @param SQLconnStr sql连接字串
     * @param context    父引用标记
     * @param ID         唯一标识
     */
   public SyncDB(SQLiteDatabase[] SrcDBs, String SQLconnStr, Context context, String ID) {
       try {
           this.DstDB=DriverManager.getConnection(SQLconnStr);
                          } catch (SQLException e) {
                              e.printStackTrace();
                              }
               this.SrcDBs = SrcDBs;
               this.context = context;
               this.header = ID;



    }




    /**
     * 本方法为将sqlite行转换到sqlserver预处理格式
     *
     * @param c
     * @return
     */
    private synchronized PreparedStatement CurConv(Cursor c,String SQLcmd) {
        int number1 = c.getColumnCount();
        String  placeholder=null;
        placeholder="?";
        for (int i = 1; i < number1; i++) {
            placeholder+=",";
            placeholder+="?";
        }

      String   SQL=SQLcmd+"("+placeholder+")";

        try {

              stmt = DstDB.prepareStatement(SQL);
        } catch (SQLException e) {
            e.printStackTrace();


        }

        return stmt;
    }
    /**
     * 本程序用来判断是否已经包含该行
     *
     * @param src
     * @return
     */
  /* private synchronized boolean RowIsEquals(PreparedStatement src) {



        return false;
    }*/
    /**
     * 本程序用于最终执行同步
     *
     * @param c
     */
    private synchronized void DoSync(Cursor c, String SQLcmd) {
        int number1 = c.getColumnCount();
        PreparedStatement p = CurConv(c,SQLcmd);
        while (c.moveToNext()) {

           /* if (RowIsEquals(p)) {
                continue;
            }*/
            //此处插入同步代码

            for (int i = 0; i < number1; i++) {
                Object o=null;
                switch (c.getType(i)) {
                    case Cursor.FIELD_TYPE_NULL:
                        o=null;
                        break;
                    case Cursor.FIELD_TYPE_INTEGER:
                        o=c.getInt(i);
                        // stmt.setInt( i,d.getInt(i));
                        break;
                    case Cursor.FIELD_TYPE_FLOAT:
                        o=c.getFloat(i);
                        break;
                    case Cursor.FIELD_TYPE_STRING:
                        o=c.getString(i);
                        break;
                    case Cursor.FIELD_TYPE_BLOB:
                        o=c.getBlob(i);
                        break;
                    default:
                        break;
                }


                try {
                    p.setObject(i+1,o);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }



            try {
                p.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    /**
     * 本方法用于获取数据库内的表数量
     *
     * @param db
     * @return
     */


    public final String[] getTableNames(SQLiteDatabase db) {



        final String sql = "SELECT name FROM sqlite_master WHERE type='table'";

        Cursor c= db.rawQuery(sql,null);
          int num=c.getCount();
        String[] TableNames=new String[num];
             TableNames=new String[num];
                while (c.moveToNext()){
                String tablename =c.getString(0);
                    Log.e("gong", "" + tablename );
                   int i=0;
                    TableNames[i]=tablename;
                    i++;
        }


        return  TableNames;
    }
    /**
     * 本方法为服务接口，服务程序入口，留空
     *
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
