package com.yujin99.libyjface.db;

import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.com.yujin99.libyjface.synchronize2.createchart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public  class SyncDB extends Service {

    public   SQLiteDatabase[] SrcDBs;
    public    Connection DstDB;
    public  Context context;
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



    public SyncDB( SQLiteDatabase[] SrcDBs, String SQLconnStr, Context context,String ID) {
       try {
           this.DstDB=DriverManager.getConnection(SQLconnStr);
                          } catch (SQLException e) {
                              e.printStackTrace();
                              }
        this.SrcDBs=SrcDBs;
       this.context = context;
       this.header=ID;


    }
    public void createchart(){
        Object chartname=null;

        for (int i=0;i<SrcDBs.length;i++){

            Cursor  d=   SrcDBs[i].query("sqlite_master", new String[]{   "name","sql"}, null, null, null, null, null);

                while(d.moveToNext()){

                    int number1 = d.getColumnCount();

                    for ( int j=0;j<number1;j++){
                        Object o=null;
                        switch (d.getType(j)) {
                            case Cursor.FIELD_TYPE_NULL:
                                o=null;
                                break;
                            case Cursor.FIELD_TYPE_INTEGER:
                                o=d.getInt(j);
                                // stmt.setInt( i,d.getInt(i));
                                break;
                            case Cursor.FIELD_TYPE_FLOAT:
                                o=d.getFloat(j);
                                break;
                            case Cursor.FIELD_TYPE_STRING:
                                o=d.getString(j);
                                break;
                            case Cursor.FIELD_TYPE_BLOB:
                                o=d.getBlob(j);
                                break;
                            default:
                                break;
                        }

                        if(j==0){

                          String  pppp="drop table "+"guest."+header+"SrcDBs"+i+"_"+o+";";
                            dochart(pppp);

                         chartname=o;

                        }


                        if(j==1){
                       //创建表
                           String ing=(String)o;
                            String[] ong=ing.split("CREATE TABLE ");
                            String ppp =ong[1];
                            String pppp="CREATE TABLE guest."+header+"SrcDBs"+i+"_"+ppp+";";
                            String ppppp=pppp.replace(" primary key autoincrement","");
                            Log.e("gong", "创建名" +ppppp );
                            dochart(ppppp);
                            //查询所有表信息
                            String sql = "SELECT * FROM " + chartname + ";";

                            Cursor  c=    SrcDBs[i].rawQuery(sql,null);
                            //插入数据
                            String SQLcmd = "insert into guest."+header+"SrcDBs"+i+"_"+ chartname+" values";

                            DoSync( c,  SQLcmd);



                            }



                    }















                }


         /*   String[] a=getTableNames(SrcDBs[i]);

            for (int j=0;j<a.length;j++){


                String     Sql="CREATE TABLE guest.ii;
                Log.e("gong", "ng" + Sql);
                dochart(Sql);






            }*/



        }



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
        Log.e("gong", "插入" +SQL );

        try {

              stmt = DstDB.prepareStatement(SQL);
        } catch (SQLException e) {


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



            e.printStackTrace();
        return false;
    }*/
    /**
     * 本程序用于最终执行同步
     *
     * @param c
     */
    public synchronized void DoSync(Cursor c, String SQLcmd) {
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
                Log.e("gong", "" + "增加了一条新数据");
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


    public  String[] getTableNames(SQLiteDatabase db) {



        final String sql = "SELECT name FROM sqlite_master WHERE type='table'";

        Cursor c= db.rawQuery(sql,null);
          int num=c.getCount();
        String[] TableNames=new String[num];
             TableNames=new String[num];
                int i=0;
                while (c.moveToNext()){
                String tablename =c.getString(0);


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



    /**
     * 创建或删除表
     *
     * @param sql
     *
     */
    public void  dochart(String sql){






        try {

            Statement   stmt =  DstDB.createStatement();

            stmt.executeUpdate(sql);




        } catch (SQLException e) {
            e.printStackTrace();
        }




    }

    /**
     * 获取数据库
     *
     *
     *
     * @return
     */
    public  SQLiteDatabase getDatabase(){
        SQLiteOpenHelper db=null;

        SQLiteDatabase n ;


        if (db == null) {
            db = new SQLiteOpenHelper(context,"Tm", null, 1) {


                @Override
                public void onCreate(SQLiteDatabase db) {
                    db.execSQL("create table test(id integer primary key autoincrement,word varchar(255),detail varchar(255))");/*数据库sql创建表*/
                }


                @Override
                public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

                }
            };
        }



        n = db.getReadableDatabase();


        return n;



    }

}


