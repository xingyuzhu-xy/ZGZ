package test.leco.com.zgz.zxy;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import test.leco.com.zgz.R;
import test.leco.com.zgz.zxy.Myadapter.MyMessageAdapter;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MyMessageActivity extends Activity {
    List<HashMap<String,Object>> list;
    ListView listView;
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_message_layout);
        listView= (ListView) findViewById(R.id.my_message_listview);
        list=new ArrayList<>();
        getDate();
        listView.setAdapter(new MyMessageAdapter(list,this));
        super.onCreate(savedInstanceState);
    }
    public void getDate(){
        for(int i=0;i<5;i++){
            HashMap<String,Object>  map=new HashMap<>();
            map.put("cpname",getResources().getString(R.string.cpname));
            map.put("time",getResources().getString(R.string.time));
            map.put("site",getResources().getString(R.string.site));
            list.add(map);
        }
    }
}
