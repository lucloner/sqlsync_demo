package com.com.yujin99.libyjface.synchronize2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Random;

public class master {
    private static SQLiteOpenHelper db;
    private static Random r = new Random();

    /**
     * 获得sqlite_master
     * 的 name,sql字段信息
     * @param ct
     * @return
     */

    public Cursor getchart(Context ct,String database){

        Cursor c = null;
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

            c = db.getReadableDatabase().query("sqlite_master", new String[]{   "name","sql"}, null, null, null, null, null);




        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;






    }
}
