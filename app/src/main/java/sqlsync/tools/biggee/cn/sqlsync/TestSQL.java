package sqlsync.tools.biggee.cn.sqlsync;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Random;

public final  class TestSQL {
    private static SQLiteOpenHelper db;
    private static Random r = new Random();

    public static Cursor testSQLite(Context ct) {
        Cursor c = null;
        try {
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
            for (int i = 0; i < 2; i++) {
                ContentValues cv = new ContentValues();
                cv.put("word", "w" + r.nextLong() + "e");
                cv.put("detail", System.currentTimeMillis());
                db.getWritableDatabase().insert("test", "", cv);
            }
              c = db.getReadableDatabase().query("test", new String[]{"id", "word", "detail"}, null, null, null, null, null);




        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }


}

