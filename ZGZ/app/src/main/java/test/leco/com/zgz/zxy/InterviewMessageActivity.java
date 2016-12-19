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
import test.leco.com.zgz.zxy.Myadapter.InterviewAdapter;

/**
 * Created by Administrator on 2016/12/15.
 */

public class InterviewMessageActivity extends Activity{
    List<HashMap<String,Object>> list;
    ListView listView;
    ImageView messageArrow;//返回箭头
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_interview_message_layout);
        listView= (ListView) findViewById(R.id.message_listview);
        messageArrow= (ImageView) findViewById(R.id.message_arrow);
        list=new ArrayList<>();
        getDate();
        listView.setAdapter(new InterviewAdapter(list,this));
        super.onCreate(savedInstanceState);
        messageArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public  void getDate(){
        for (int i=0;i<5;i++){
            HashMap<String,Object> map=new HashMap<>();
            map.put("cpimage",R.mipmap.cpimage);
            map.put("cpname",getResources().getString(R.string.cpname));
            map.put("time",getResources().getString(R.string.date));
            list.add(map);
       }
   }
}
