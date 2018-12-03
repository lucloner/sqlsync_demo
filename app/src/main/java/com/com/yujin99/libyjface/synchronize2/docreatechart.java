package com.com.yujin99.libyjface.synchronize2;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.yujin99libyjface.entity.createchart;

import java.util.List;

public class docreatechart {
    List<String> chart;

    /**
     * 创建所有表
     * @param ct
     */

    public void method(Context ct) {


        Cursor c=new master().getchart(ct);
        try{
            while (c.moveToNext()) {
                int number1 = c.getColumnCount();



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

                    //删除表格
                    if(i==0){
                        String  pppp="drop table "+"guest."+o+";";
                        Log.e("gong", "" + pppp);
                       new deletechart().dogeletechart(pppp);

                    }

                    if(i==1){
                        /**
                         * sql语句字符串拼接
                         */
                        String ing=(String)o;
                        String[] ong=ing.split("CREATE TABLE ");
                        String ppp =ong[1];
                        String pppp="CREATE TABLE guest."+ppp+";";
                        String ppppp=pppp.replace(" primary key autoincrement","");
                       /* Log.e("gong", "" + ppppp);*/


                        new createchart().dochart(ppppp);

                    }



                }




            }
        }catch (Exception e){


            e.printStackTrace();}









    }
}
