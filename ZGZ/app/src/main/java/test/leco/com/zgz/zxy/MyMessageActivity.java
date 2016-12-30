package test.leco.com.zgz.zxy;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
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
import test.leco.com.zgz.t.http.GetInformHttp;
import test.leco.com.zgz.zxy.Myadapter.MyMessageAdapter;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MyMessageActivity extends Activity {
    List<HashMap<String,Object>> list;
    ListView listView;
    ImageView interviewArrow;
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_message_layout);
        listView= (ListView) findViewById(R.id.my_message_listview);
        interviewArrow= (ImageView) findViewById(R.id.interview_arrow);
        list=new ArrayList<>();
        new Thread(){
            @Override
            public void run() {
                getData();
            }
        }.start();
        MyMessageAdapter myMessageAdapter = new MyMessageAdapter(list,this);
        myMessageAdapter.setTongyi(new MyMessageAdapter.Tongyi() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void click(int i, View view) {
                Button button = (Button) view.findViewById(R.id.message_agree);
                button.setText("已同意");
                button.setBackground(getResources().getDrawable(R.drawable.deliver_button2));
                button.setClickable(false);
            }
        });
        listView.setAdapter(myMessageAdapter);
        super.onCreate(savedInstanceState);
        interviewArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    AGApplication myAppLication;
    int useId;

    public void getData(){
        myAppLication = (AGApplication) getApplication();
        useId = myAppLication.getId();

        try {
            GetInformHttp getInform = new GetInformHttp(useId);
            String data = getInform.getStringBuilder().toString();
            JSONObject jsonObject = new JSONObject(data);
            JSONArray message = jsonObject.getJSONArray("message");
            for (int i = 0; i< message.length(); i++){
                JSONObject each = message.getJSONObject(i);
                HashMap<String,Object>  map=new HashMap<>();
                map.put("cpname",each.getString("enterprise_name"));
                map.put("time",each.getString("time"));
                map.put("site",each.getString("site"));
                list.add(map);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
