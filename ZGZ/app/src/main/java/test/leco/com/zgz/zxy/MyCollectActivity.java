package test.leco.com.zgz.zxy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import test.leco.com.zgz.R;
import test.leco.com.zgz.t.data.MyAppLication;
import test.leco.com.zgz.t.http.GetMineCollectHttp;
import test.leco.com.zgz.zxy.Myadapter.MyCollectAdapter;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MyCollectActivity extends Activity {
    ListView listView;
    List<HashMap<String,Object>> list;
    ImageView collectArrow;
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_collect_layout);
        listView= (ListView) findViewById(R.id.my_collect_listview);
        list=new ArrayList<>();
        collectArrow= (ImageView) findViewById(R.id.collect_arrow);
        new Thread(){
            @Override
            public void run() {
                getData();
            }
        }.start();
        listView.setAdapter(new MyCollectAdapter(list,this));
        super.onCreate(savedInstanceState);
        collectArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    MyAppLication myAppLication;

    int useId;
    public void getData(){
        myAppLication = (MyAppLication) getApplication();
        useId = myAppLication.getId();
        try {
            GetMineCollectHttp getMineCollectHttp = new GetMineCollectHttp(useId);
            String data = getMineCollectHttp.getStringBuilder().toString();
            JSONObject jsonObject = new JSONObject(data);
            JSONArray message = jsonObject.getJSONArray("message");
            for (int i =0; i<message.length(); i++){
                JSONObject each = message.getJSONObject(i);
                HashMap<String,Object> map =new HashMap<>();
                map.put("position",each.getString("post_type"));
                map.put("cpname",each.getString("enterprise_name"));
                map.put("location",each.getString("district"));
                map.put("exp",each.getString("enducation_type")+"/"+each.getString("experience"));
                map.put("pay",each.getString("pay")+"元/月");
                list.add(map);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
