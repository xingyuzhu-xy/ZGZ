package test.leco.com.zgz.zxy;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
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

import agora.openvcall.AGApplication;
import test.leco.com.zgz.R;
import test.leco.com.zgz.t.data.MyAppLication;
import test.leco.com.zgz.t.http.GetRecordHttp;
import test.leco.com.zgz.zxy.Myadapter.MyDeliverAdapter;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MyDeliverActivity extends Activity {
    List<HashMap<String,Object>> list;
    ListView listView;
    ImageView deliverArrow;
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_deliver_layout);
        listView= (ListView) findViewById(R.id.my_deliver_listview);
        list=new ArrayList<>();
        deliverArrow= (ImageView) findViewById(R.id.deliver_arrow);

        new Thread(){
            @Override
            public void run() {
                getHttpData();
            }
        }.start();

        listView.setAdapter(new MyDeliverAdapter(list,this));
        super.onCreate(savedInstanceState);
        deliverArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    AGApplication myAppLication;
    int useId;
    //网络请求的数据
    public void getHttpData(){
        myAppLication = (AGApplication) getApplication();
        useId = myAppLication.getId();
        Log.i("useId====>",""+useId);
        try {
            GetRecordHttp getRecordHttp = new GetRecordHttp(useId);
            String data = getRecordHttp.getStringBuilder().toString();
            Log.i("data===>",data);
            JSONObject jsonObject = new JSONObject(data);
            JSONArray message = jsonObject.getJSONArray("message");
           for (int i = 0; i < message.length(); i++){
               JSONObject each = message.getJSONObject(i);
               HashMap<String,Object> map=new HashMap<>();
               map.put("cpname",each.getString("enterprise_name"));
               map.put("position",each.getString("post_type"));
               map.put("time",each.getString("time"));
               list.add(map);
           }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
