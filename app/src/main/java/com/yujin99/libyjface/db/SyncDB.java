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
import java.sql.PreparedStatement;
import java.sql.SQLException;

import sqlsync.tools.biggee.cn.sqlsync.TestSQL;

public  class SyncDB extends Service {
    private String[] SrcDB;
    private Connection DstDB;
    private Context context;
    private String header; //数据表名格式为header.SrcDBs编号_表名
    /**
     * 本方法用于初始化
     *
     * @param SrcDBs     需要同步的所有数据库
     * @param SQLconnStr sql连接字串
     * @param context    父引用标记
     * @param ID         唯一标识
     */
    public Cursor  Sync(String[] SrcDBs, String SQLconnStr, Context context, String ID) {



    String  sql=SQLconnStr;
    String SrcDB =SrcDBs[0];
        
    Cursor ing=TestSQL.testSQLite(context, SrcDB, sql);
        Log.e("gong","ing");


        return ing;

    }



    /**
     * 本方法为将sqlite行转换到sqlserver预处理格式
     *
     * @param c
     * @return
     */
    private synchronized PreparedStatement CurConv(Cursor c) {
        return null;
    }
    /**
     * 本程序用来判断是否已经包含该行
     *
     * @param src
     * @return
     */
    private synchronized boolean RowIsEquals(PreparedStatement src) {
        return false;
    }
    /**
     * 本程序用于最终执行同步
     *
     * @param c
     */
    private synchronized void DoSync(Cursor c, String SQLcmd) {
        PreparedStatement p = CurConv(c);
        while (c.moveToNext()) {
            if (RowIsEquals(p)) {
                continue;
            }
            //此处插入同步代码




            try {
                p.executeUpdate(SQLcmd);
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
    private final String[] getTableNames(SQLiteDatabase db) {
        final String sql = "SELECT * FROM sqlite_master WHERE type='table'";
        return null;
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
