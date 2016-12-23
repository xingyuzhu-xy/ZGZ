package test.leco.com.zgz.zxy;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import test.leco.com.zgz.R;
import test.leco.com.zgz.zxy.Myadapter.MyCollectAdapter;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MyCollectActivity extends Activity {
    ListView listView;
    List<HashMap<String,Object>> list;
    LayoutInflater layoutInflater;
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_collect_layout);
        listView= (ListView) findViewById(R.id.my_collect_listview);
        list=new ArrayList<>();
        getDate();
        listView.setAdapter(new MyCollectAdapter(list,this));
        super.onCreate(savedInstanceState);
    }
    public void getDate(){
        for(int i =0;i<5;i++){
              HashMap<String,Object> map =new HashMap<>();
            map.put("position","总统");
            map.put("cpname","中华民国");
            map.put("location","亚洲");
            map.put("exp","不限");
            map.put("pay","$9999999");
            list.add(map);
        }
    }
}
