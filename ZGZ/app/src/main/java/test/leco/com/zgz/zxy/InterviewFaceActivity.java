package test.leco.com.zgz.zxy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

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

    TextView online,unline;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview_face_layout);
        onLineListView= (ListView) findViewById(R.id.online_listview);
        unLineListView= (ListView) findViewById(R.id.unline_listview);
        online = (TextView) findViewById(R.id.online);
        unline = (TextView) findViewById(R.id.unline);

        online.setOnClickListener(clickListener);
        unline.setOnClickListener(clickListener);

        unlineList=new ArrayList<HashMap<String,Object>>();
        onlineList=new ArrayList<HashMap<String,Object>>();

        getunlineDate();
        getOnlineDate();
        unLineListView.setAdapter(new UnlineFaceAdpater(unlineList,this));
        onLineListView.setAdapter(new OnlineFaceAdapter(onlineList,this));

    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.online:
                    if (unLineListView.getVisibility() == View.VISIBLE){
                        unLineListView.setVisibility(View.GONE);
                    }
                    if (onLineListView.getVisibility() == View.VISIBLE){
                        onLineListView.setVisibility(View.GONE);
                    }else {
                        onLineListView.setVisibility(View.VISIBLE);
                    }

                    break;
                case R.id.unline:
                    if (onLineListView.getVisibility() == View.VISIBLE){
                        onLineListView.setVisibility(View.GONE);
                    }
                    if (unLineListView.getVisibility() == View.VISIBLE){
                        unLineListView.setVisibility(View.GONE);
                    }else {
                        unLineListView.setVisibility(View.VISIBLE);
                    }
                    break;
            }
        }
    };

    public void getOnlineDate(){
        for (int i=0;i<10;i++){
            HashMap<String,Object> map =new HashMap<>();
            map.put("onCpimage",R.mipmap.cpimage);
            map.put("onCpname",getResources().getString(R.string.cpname));
            map.put("onPosition",getResources().getString(R.string.position));
            map.put("onPay",getResources().getString(R.string.paymoney));
            onlineList.add(map);
        }
    }
    public void getunlineDate(){
        for (int i=0;i<8;i++){
            HashMap<String,Object> map =new HashMap<>();
            map.put("unCpimage",R.mipmap.cpimage);
            map.put("unCpname",getResources().getString(R.string.cpname));
            map.put("unPosition",getResources().getString(R.string.position));
            map.put("unPay",getResources().getString(R.string.paymoney));
            unlineList.add(map);
        }
    }
}
