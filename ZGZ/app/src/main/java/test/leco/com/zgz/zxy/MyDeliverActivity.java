package test.leco.com.zgz.zxy;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import test.leco.com.zgz.R;
import test.leco.com.zgz.zxy.Myadapter.MyDeliverAdapter;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MyDeliverActivity extends Activity {
    List<HashMap<String,Object>> list;
    ListView listView;
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_deliver_layout);
        listView= (ListView) findViewById(R.id.my_deliver_listview);
        list=new ArrayList<>();
        getDate();
        listView.setAdapter(new MyDeliverAdapter(list,this));
        super.onCreate(savedInstanceState);
    }
    public void getDate(){
        for (int i=0;i<5;i++){
            HashMap<String,Object> map=new HashMap<>();
            map.put("cpname",getResources().getString(R.string.cpname));
            map.put("position",getResources().getString(R.string.position));
            map.put("time",getResources().getString(R.string.time));
            list.add(map);
        }
    }
}
