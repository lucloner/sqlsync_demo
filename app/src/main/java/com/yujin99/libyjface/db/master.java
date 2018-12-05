package com.yujin99.libyjface.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Random;

public class master {
 SQLiteOpenHelper db;


    /**
     * 获得sqlite_master
     * 的 name,sql字段信息
     * @param ct
     * @return
     */

    public  SQLiteDatabase getDatabase(Context ct){
        SQLiteOpenHelper db=null;

        SQLiteDatabase n ;


            if (db == null) {
                db = new SQLiteOpenHelper(ct, "Tm",/*数据库*/ null, 1) {


                    @Override
                    public void onCreate(SQLiteDatabase db) {
                        db.execSQL("create table test(id integer primary key autoincrement,word varchar(255),detail varchar(255))");/*数据库sql创建表*/
                    }


                    @Override
                    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

                    }
                };
            }

              //获得数据库n
              n = db.getReadableDatabase();


            return n;



    }
}
