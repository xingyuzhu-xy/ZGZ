package test.leco.com.zgz.zxy;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import test.leco.com.zgz.R;
import test.leco.com.zgz.zxy.Myadapter.MyAttentionAdapter;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MyAttentionActivity extends Activity {
    ListView listView;
    List<HashMap<String,Object>> list;

    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_attention_layout);
        listView= (ListView) findViewById(R.id.attention_listview);
        list=new ArrayList<>();
        getDate();
        listView.setAdapter(new MyAttentionAdapter(list,this));
        super.onCreate(savedInstanceState);
    }
    public void getDate(){
        for (int i=0;i<5;i++){
            HashMap<String,Object> map=new HashMap<>();
            map.put("cpimage",R.mipmap.cpimage);
            map.put("cpname",getResources().getString(R.string.cpname));
            map.put("cpIntroduce",getResources().getString(R.string.cp_introduce));
            list.add(map);
        }
    }
}
