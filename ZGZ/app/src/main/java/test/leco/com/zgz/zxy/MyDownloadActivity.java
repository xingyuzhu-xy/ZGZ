package test.leco.com.zgz.zxy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import test.leco.com.zgz.R;
import test.leco.com.zgz.zxy.Myadapter.MyDownloadAdapter;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MyDownloadActivity extends Activity {
    List<HashMap<String,Object>> list;
    ListView listView;
    ImageView downloadArrow;
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_download_layout);
        listView= (ListView) findViewById(R.id.my_download_listview);
        list=new ArrayList<>();
        downloadArrow= (ImageView) findViewById(R.id.download_arrow);
        getDate();
        listView.setAdapter(new MyDownloadAdapter(list,this));
        super.onCreate(savedInstanceState);
        downloadArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void getDate(){
        for (int i=0;i<5;i++){
            HashMap<String,Object> map =new HashMap<>();
            map.put("cpimage",R.mipmap.cpimage);
            map.put("cpname",getResources().getString(R.string.cpname));
            map.put("time",getResources().getString(R.string.time));
            list.add(map);
        }
    }
}
