package sqlsync.tools.biggee.cn.sqlsync;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Random;

public class TestSQL {
    private static SQLiteOpenHelper db;
    private static Random r = new Random();

    public final static String testSQLite(final Context ct) {
        try {
            if (db == null) {
                db = new SQLiteOpenHelper(ct, "mT", null, 1) {
                    @Override
                    public void onCreate(SQLiteDatabase db) {
                        db.execSQL("create table test(id integer primary key autoincrement,word varchar(255),detail varchar(255))");
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
            Cursor c = db.getReadableDatabase().query("test", new String[]{"id", "word", "detail"}, null, null, null, null, null);
            StringBuilder sb = new StringBuilder();
            c.moveToFirst();
            while (r.nextInt() % 3 > 1) {
                c.moveToNext();
            }
            sb.append(":");
            sb.append(c.getInt(0));
            sb.append(":");
            sb.append(c.getString(1));
            sb.append(":");
            sb.append(c.getString(2));
            sb.append(":");
            return sb.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}

