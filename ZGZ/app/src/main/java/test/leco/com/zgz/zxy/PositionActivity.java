package test.leco.com.zgz.zxy;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import test.leco.com.zgz.R;
import test.leco.com.zgz.zxy.Myadapter.PositionAdapter;

/**
 * Created by Administrator on 2016/12/15.
 */

public class PositionActivity extends Activity {
    ListView listView;
    List<HashMap<String,Object>> list;
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_position_layout);
        listView= (ListView) findViewById(R.id.position_listview);
        list=new ArrayList<>();
        getDate();
        listView.setAdapter(new PositionAdapter(list,this));
        super.onCreate(savedInstanceState);
    }
    public void getDate(){
        for (int i=0;i<5;i++){
            HashMap<String,Object> map =new HashMap<>();
            map.put("cpimage",R.mipmap.cpimage);
            map.put("position",getResources().getString(R.string.position));
            map.put("cpname",getResources().getString(R.string.cpname));
            map.put("city",getResources().getString(R.string.city));
            map.put("someday",getResources().getString(R.string.someday));
            map.put("pay",getResources().getString(R.string.money));
            list.add(map);
        }
    }
}
