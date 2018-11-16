package sqlsync.tools.biggee.cn.sqlsync;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

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
            super.handleMessage(msg);
            write_mT(" \n"+msg.what);
            write_mT(msg.obj.toString());
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

        mT=(TextView)findViewById(R.id.mytext);

        (new ScheduledThreadPoolExecutor(1)).scheduleAtFixedRate(new Runnable() {
                                                                     @Override
                                                                     public void run() {
                                                                         Message msg=new Message();
                                                                         msg.what=(int)(System.currentTimeMillis()%Integer.MAX_VALUE);
                                                                         msg.obj=TestSQL.testSQLite();
                                                                     }
                                                                 }
                , 1, 1, TimeUnit.SECONDS);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
