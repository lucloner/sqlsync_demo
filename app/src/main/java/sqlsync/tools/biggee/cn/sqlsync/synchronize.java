package sqlsync.tools.biggee.cn.sqlsync;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.yujin99libyjface.entity.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import copytwo.copy2;


public class synchronize {
    List<test> tes;


    public void dosynchronize( Context c ){
        tes=new ArrayList<test>();


        Cursor cd = TestSQL.testSQLite(c);
        while (cd.moveToNext()){
            int   a=cd.getInt(0) ;
            String b= cd.getString(1);
            String d=  cd.getString(2);

           test te=new test();

            te.setId(a);
            te.setWord(b);
            te.setDetail(d);
            tes.add(te);

    }

        new copy2().tesss(tes);
        tes.clear();
}













}
