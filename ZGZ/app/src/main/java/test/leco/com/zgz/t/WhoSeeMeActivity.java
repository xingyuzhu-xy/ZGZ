package test.leco.com.zgz.t;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import test.leco.com.zgz.R;
import test.leco.com.zgz.t.adapter.WhoSeeMeAdapter;
import test.leco.com.zgz.t.data.WhoSeeMeItem;

/**
 * Created by Administrator on 2016/12/0014.
 */

public class WhoSeeMeActivity extends Activity {
    ImageView back;//返回上级页面
    ListView listView; //谁看过我的列表显示
    List<WhoSeeMeItem> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_who_see_me);
        back = (ImageView) findViewById(R.id.back_icon);
        listView = (ListView) findViewById(R.id.listView);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                integerList.get(position);
               // Toast.makeText(WhoSeeMeActivity.this,""+integerList.get(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(WhoSeeMeActivity.this,EnterpriseDetailsActivity.class);
                intent.putExtra("enter",integerList.get(position));
                startActivity(intent);
            }
        });


        new Thread(new Runnable() {
            @Override
            public void run() {
                gethomewhoselook();
            }
        }).start();
    }

    int user_id;
    int status;
    int id;
    String enterprise_id; //企业id
    String enterprise_name; //企业名称
    String time;
    List<String> integerList = new ArrayList<>();
    public void gethomewhoselook(){
        String httpurl = "http://192.168.7.6/index.php/home/index/gethomewhoselook?"+"user_id="+1;
        try {
            URL url = new URL(httpurl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            if(httpURLConnection.getResponseCode()==HttpURLConnection.HTTP_OK){
                StringBuilder stringBuilder = new StringBuilder();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String s;
                while((s =bufferedReader.readLine()) != null){
                    stringBuilder.append(s);
                }
                String data = stringBuilder.toString();

                JSONObject jsonObject = new JSONObject(data);
                status = jsonObject.getInt("status");
                JSONArray jsonArray = jsonObject.getJSONArray("message");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    id = object.getInt("id");
                    user_id = object.getInt("user_id");
                    enterprise_id = object.getString("enterprise_id");
                    enterprise_name = object.getString("enterprise_name");
                    time = object.getString("time");
                    WhoSeeMeItem whoSeeMeItem = new WhoSeeMeItem();
                    whoSeeMeItem.setPd(enterprise_id);
                    whoSeeMeItem.setCompanyName(enterprise_name);
                    whoSeeMeItem.setTime(time);
                    list.add(whoSeeMeItem);
                    integerList.add(enterprise_id);
                    Log.i("integerList","=========="+integerList);
                }
                handler.sendEmptyMessage(0);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            WhoSeeMeAdapter whoSeeMeAdapter = new WhoSeeMeAdapter(WhoSeeMeActivity.this,list);
            listView.setAdapter(whoSeeMeAdapter);
        }
    };
}
