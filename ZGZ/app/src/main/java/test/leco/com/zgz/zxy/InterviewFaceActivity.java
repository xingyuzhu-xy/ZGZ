package test.leco.com.zgz.zxy;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import test.leco.com.zgz.R;
import test.leco.com.zgz.zxy.Myadapter.OnlineFaceAdapter;
import test.leco.com.zgz.zxy.Myadapter.UnlineFaceAdpater;

/**
 * Created by Administrator on 2016/12/15.
 */

public class InterviewFaceActivity extends Activity {
    ListView onLineListView,unLineListView;
    List<HashMap<String,Object>> onlineList,unlineList;
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_interview_face_layout);
        onLineListView= (ListView) findViewById(R.id.online_listview);
        unLineListView= (ListView) findViewById(R.id.unline_listview);
        unlineList=new ArrayList<>();
        onlineList=new ArrayList<>();
        getunlineDate();
        getOnlineDate();
        unLineListView.setAdapter(new UnlineFaceAdpater(unlineList,this));
        onLineListView.setAdapter(new OnlineFaceAdapter(onlineList,this));
        super.onCreate(savedInstanceState);
    }
    public void getOnlineDate(){
        for (int i=0;i<3;i++){
            HashMap<String,Object> map =new HashMap<>();
            map.put("onCpimage",R.mipmap.cpimage);
            map.put("onCpname",getResources().getString(R.string.cpname));
            map.put("onPosition",getResources().getString(R.string.position));
            map.put("onPay",getResources().getString(R.string.paymoney));
            onlineList.add(map);
        }
    }
    public void getunlineDate(){
        for (int i=0;i<3;i++){
            HashMap<String,Object> map =new HashMap<>();
            map.put("unCpimage",R.mipmap.cpimage);
            map.put("unCpname",getResources().getString(R.string.cpname));
            map.put("unPosition",getResources().getString(R.string.position));
            map.put("unPay",getResources().getString(R.string.paymoney));
            unlineList.add(map);
        }
    }
}
