package test.leco.com.zgz.zxy;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

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

        unLineListView.setAdapter(new UnlineFaceAdpater(unlineList,this));
        onLineListView.setAdapter(new OnlineFaceAdapter(onlineList,this));

        new Thread(new Runnable() {
            @Override
            public void run() {
                getinterview();
            }
        }).start();
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

    int user_id;
    int status;
    int id;
    int enterprise_id; //企业id
    String type;  //需求岗位
    String enterprise_name; //企业名称
    int isonline; //是否在线（1在线）
    String price; //薪水
    public void getinterview(){
        String httpurl = "http://192.168.7.6/index.php/home/index/getinterview?"+"user_id="+1;
        try {
            URL url = new URL(httpurl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                StringBuilder stringBuilder = new StringBuilder();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String s;
                while ((s = bufferedReader.readLine()) != null){
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
                    enterprise_id = object.getInt("enterprise_id");
                    type = object.getString("type");
                    enterprise_name = object.getString("enterprise_name");
                    isonline = object.getInt("isonline");
                    price = object.getString("price");
                    if(isonline== 1){
                        Log.i("online",""+enterprise_name+type+price);
                        HashMap<String,Object> map =new HashMap<>();
                        map.put("onCpimage",R.mipmap.cpimage);
                        map.put("onCpname",enterprise_name);
                        map.put("onPosition",type);
                        map.put("onPay",price);
                        onlineList.add(map);
                    }else {
                        Log.i("isonline",""+enterprise_name+type+price);
                        HashMap<String,Object> map =new HashMap<>();
                        map.put("unCpimage",R.mipmap.cpimage);
                        map.put("unCpname",enterprise_name);
                        map.put("unPosition",type);
                        map.put("unPay",price);
                        unlineList.add(map);
                    }
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
        }
    };
}
