package sqlsync.tools.biggee.cn.sqlsync;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


import com.com.yujin99.libyjface.synchronize2.doing;
import com.yujin99.libyjface.db.SyncDB;
import com.yujin99.libyjface.db.master;


import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;



public class MainActivity extends AppCompatActivity {
    private TextView mT;


    private Handler handler=new Handler(){
        private void write_mT(String toWrite){
            if(mT ==null){
                return;
            }
            if(mT.getText().toString().length()>1400){
                mT.setText("Cleared");
            }
            mT.append(" \t ");
            mT.append(toWrite);
        }

        @Override
        public void handleMessage(Message msg) {
            try {
                super.handleMessage(msg);
                write_mT(" \n" + msg.what);
                write_mT(msg.obj.toString());
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //getPermission();

        mT=(TextView)findViewById(R.id.mytext);
        final Context c=this;
         int a=1213;
         a+=2;

        final int[] finalA=new int[1];
        finalA[0]=a;
        finalA[0]++;
        (new ScheduledThreadPoolExecutor(1)).scheduleAtFixedRate(new Runnable() {
                                                                     @Override
                                                                     public void run() {
                                                                         try {
                                                                             Message msg = new Message();
                                                                             msg.what = (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
                                                                             msg.obj="hh"; /*TestSQL.testSQLite(c);*/

                                                                             handler.handleMessage(msg);
                                                                             msg = new Message();
                                                                             msg.what = 1234;
                                                                             msg.obj = "just hello\n";
                                                                             handler.handleMessage(msg);
                                                                             //增加每秒执行的代码：
                                                                             mT.append("555");



                                                                         }
                                                                         catch (Exception e){
                                                                             Message msg = new Message();
                                                                             msg.what = 5555;
                                                                             msg.obj = e.getMessage();
                                                                             handler.handleMessage(msg);
                                                                             e.printStackTrace();
                                                                         }
                                                                     }
                                                                 }
                , 1, 1, TimeUnit.SECONDS);

        new Thread(new Runnable() {

            Object Connection =null ;

            @Override
            public void run() {
                String v=null;
                String sql = "insert into guest.test values";
                String connectionUrl = "jdbc:jtds:sqlserver://192.168.165.180;databaseName=PZG;user=TechCent_PZG;password=12345678";

                SyncDB b=  new SyncDB( connectionUrl,c);
                SQLiteDatabase c=   b.getDatabase();
               String[]d= b.getTableNames(c);
               for(int i=0;i<d.length;i++) v = d[i];









            }
        }).start();










    }

   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

           //* int id = item.getItemId();*//*

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long

            // as you specify a parent activity in AndroidManifest.xml.
            //noinspection SimplifiableIfStatement
//            if (id == R.id.action_settings) {            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
