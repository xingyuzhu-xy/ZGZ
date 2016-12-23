package test.leco.com.zgz;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Administrator on 2016/12/23.
 */
public class TextListview extends Activity {
    ListView listView1;
    ListView listView2;
    ArrayAdapter<String> arrayAdapterListView1;
    ArrayAdapter<String> arrayAdapterListView2;
    String listView1Array[] = {"1","2","3"};
    int pos =0;
    private String[] city = new String[]{"北京","上海","天津","重庆"};
    String[][] pandc = new String[][]{{"北京","上海","天津","重庆"},{"香港","澳门"},
            {"哈尔滨","齐齐哈尔","牡丹江","大庆","伊春","双鸭山","鹤岗","鸡西","佳木斯",
                    "七台河","黑河","绥化","大兴安岭"}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_expert_seek_position);





        listView1 = (ListView) findViewById(R.id.listview1);
        listView2 = (ListView) findViewById(R.id.listview2);

       arrayAdapterListView1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1,listView1Array );

        listView1.setAdapter(arrayAdapterListView1);
        listView1.setOnItemClickListener(onItemClickListener);


        Log.i("pos=======>", ""+pos );
        Log.i("pandc+++++++>", ""+pandc[pos] );
       //listView2.setAdapter(arrayAdapterListView2);

    }



    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            pos = i;
            Log.i("pandc=======>", ""+pandc[pos] );
            arrayAdapterListView2 = new ArrayAdapter<String>(TextListview.this,
                    android.R.layout.simple_expandable_list_item_1,pandc[pos]);
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    Message message = new Message();
                    message.what=1111;
                    handler.sendMessage(message);
                }
            }.start();

        }
    };
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1111) {
                Log.i("pos=======>", ""+pos );
                Log.i("pandc+++++++>", ""+pandc[pos] );
               // arrayAdapterListView2.notifyDataSetChanged();
                listView2.setAdapter(arrayAdapterListView2);
            }
        }
    };




}
