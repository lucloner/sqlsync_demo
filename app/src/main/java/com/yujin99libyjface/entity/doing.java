package com.yujin99libyjface.entity;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.List;

public class doing {


    List<String> chart;


    public void method(Context ct) {

        Cursor c= new com.yujin99libyjface.entity.chart().getchart( ct);
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

                    if(i==1){
                        /**
                         * sql语句字符串拼接
                         */
                        String ing=(String)o;
                        String[] ong=ing.split("CREATE TABLE ");
                        String ppp =ong[1];
                        String pppp="CREATE TABLE dbo."+ppp+";";
                        String ppppp=pppp.replace(" primary key autoincrement","");
                        Log.e("gong", "" + ppppp);




                    }



                }




            }
        }catch (Exception e){


            e.printStackTrace();}









        }
}




