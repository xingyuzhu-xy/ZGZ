package test.leco.com.zgz.zxy;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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
import java.util.HashMap;
import java.util.List;

import test.leco.com.zgz.R;
import test.leco.com.zgz.zxy.Myadapter.MyAttentionAdapter;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MyAttentionActivity extends Activity {
    ListView listView;
    List<HashMap<String,Object>> list;
    ImageView attentionArrow;
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_attention_layout);
        listView= (ListView) findViewById(R.id.attention_listview);
        attentionArrow= (ImageView) findViewById(R.id.attention_arrow);
        list=new ArrayList<>();
        listView.setAdapter(new MyAttentionAdapter(list,this));
        super.onCreate(savedInstanceState);
        attentionArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        new Thread(){
            @Override
            public void run() {
                attention();
            }
        }.start();
    }
  /*  public void getDate(){
        for (int i=0;i<5;i++){
            map.put("cpimage",R.mipmap.cpimage);
            map.put("cpname",getResources().getString(R.string.cpname));
            map.put("cpIntroduce",getResources().getString(R.string.cp_introduce));
            list.add(map);
        }
    }*/
    public void attention(){
        SharedPreferences sharedPreferences=getSharedPreferences("ZGZ",Activity.MODE_PRIVATE);
        Integer userId=sharedPreferences.getInt("user_id",0);
        String httpURL="http://10.0.2.2/index.php/home/index/getmyattention?"+"user_id="+userId;
        try {
            URL url=new URL(httpURL);
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(2000);
            httpURLConnection.connect();
            if(httpURLConnection.getResponseCode()==httpURLConnection.HTTP_OK){
                StringBuilder stringBuilder=new StringBuilder();
                InputStream inputStream=httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"utf-8");
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String s;
                while ((s=bufferedReader.readLine())!=null){
                    stringBuilder.append(s);
                }
                String data=stringBuilder.toString();
                JSONObject jsonObject=new JSONObject(data);
                JSONArray jsonArray=jsonObject.getJSONArray("message");
                for(int i=0;i<jsonArray.length();i++){
                    HashMap<String,Object> map=new HashMap<>();
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                    String cpname=jsonObject1.getString("enterprise_name");
                    String cpindustry=jsonObject1.getString("industry");
                    map.put("cpname",cpname);
                    map.put("cpimage",R.mipmap.cpimage);
                    map.put("cpIntroduce",cpindustry);
                    list.add(map);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
