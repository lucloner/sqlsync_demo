package com.yujin99.libyjface.db;

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

    public  SQLiteDatabase getchart(Context ct,String database){

        SQLiteDatabase n ;


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


              n = db.getReadableDatabase();


            return n;



    }
}
