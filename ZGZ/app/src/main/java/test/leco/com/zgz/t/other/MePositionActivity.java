package test.leco.com.zgz.t.other;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import test.leco.com.zgz.R;
import test.leco.com.zgz.t.PositionDetailsActivity;
import test.leco.com.zgz.t.adapter.MePositionAdapter;
import test.leco.com.zgz.t.data.MePositionItem;
import test.leco.com.zgz.t.data.MyAppLication;
import test.leco.com.zgz.t.http.GetLookHttp;

/**
 * Created by Administrator on 2016/12/0016.
 */

public class MePositionActivity extends Activity {
    ImageView back;
    ListView listView;
    List<MePositionItem> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_me_position);
        listView = (ListView) findViewById(R.id.listView);
        back = (ImageView) findViewById(R.id.back_icon);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        list = new ArrayList<MePositionItem>();
        enter_id = new ArrayList<Integer>();
        new Thread() {
            @Override
            public void run() {
                getData();
            }
        }.start();
        listView.setAdapter(new MePositionAdapter(this, list));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MePositionActivity.this, PositionDetailsActivity.class);
                int en = enter_id.get(position);
                intent.putExtra("en",en);
                startActivity(intent);
            }
        });
    }

    //    public void getData(){
//        list = new ArrayList<MePositionItem>();
//
//        for (int i = 0; i< 10; i++){
//            MePositionItem mePositionItem = new MePositionItem();
//            mePositionItem.setCompanyName("微跑科技有限公司");
//            mePositionItem.setAddress("渝中区-石油路");
//            mePositionItem.setPositionName("安卓开发学徒");
//            mePositionItem.setTime("昨天");
//            list.add(mePositionItem);
//        }
//    }
    MyAppLication myAppLication;
    int useId;

    int enterprise_id;
    List<Integer> enter_id;
    public void getData() {

        myAppLication = (MyAppLication) getApplication();
        useId = myAppLication.getId();


        try {
            GetLookHttp getLookHttp = new GetLookHttp(useId);
            String data = getLookHttp.getStringBuilder().toString();
            JSONObject jsonObject = new JSONObject(data);
            JSONArray message = jsonObject.getJSONArray("message");
            for (int i = 0; i < message.length(); i++) {
                JSONObject each = message.getJSONObject(i);
                MePositionItem mePositionItem = new MePositionItem();
                enterprise_id = each.getInt("enterprise_id");
                mePositionItem.setCompanyName(each.getString("enterprise_name"));
                mePositionItem.setAddress(each.getString("enterprise_name"));
                mePositionItem.setPositionName(each.getString("enterprise_name"));
                mePositionItem.setTime(each.getString("time"));
                list.add(mePositionItem);
                enter_id.add(enterprise_id);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
