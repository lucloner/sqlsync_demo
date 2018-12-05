package com.com.yujin99.libyjface.synchronize2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Random;



 public  class chartdata {
    private static SQLiteOpenHelper db;
    private static Random r = new Random();
     private static Cursor d;
     /**
      * 根据表名获取
      * 表字段信息
      *
      */
     public static Cursor getdata(Context ct, String chartname,String database) {

         Cursor c = null;
         Cursor cursor = null;
         try {
             if (db == null) {
                 db = new SQLiteOpenHelper(ct, database,/*数据库*/ null, 1) {
                     @Override
                     public void onCreate(SQLiteDatabase db) {
                         db.execSQL("create table test(id integer primary key autoincrement,word varchar(255),detail varchar(255))");/*数据库sql创建表*/
                     }


                     @Override
                     public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

                     }
                 };
             }
            for (int i = 0; i < 2; i++) {
                 ContentValues cv = new ContentValues();
                 cv.put("word", "w" + r.nextLong() + "e");
                 cv.put("detail", System.currentTimeMillis());
                 db.getWritableDatabase().insert(chartname, "", cv);
             }

             String sql = "SELECT * FROM " + chartname + ";";


             cursor = db.getReadableDatabase().rawQuery(sql, null);


         } catch (Exception e) {
             e.printStackTrace();
         }
         return cursor;

     }

}
