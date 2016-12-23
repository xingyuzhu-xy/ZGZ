package test.leco.com.zgz.t.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import test.leco.com.zgz.R;
import test.leco.com.zgz.zxy.InterviewMessageActivity;
import test.leco.com.zgz.zxy.Myadapter.PositionAdapter;
import test.leco.com.zgz.zxy.PositionDetailActivity;

/**
 * Created by Administrator on 2016/12/14.
 */

public class PositionFragment extends Fragment {
    ListView listView;
    Intent intent;
    List<HashMap<String,Object>> list;
    List<Integer> enterPriseId;
    TextView message;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.activity_position_layout,null);
        listView= (ListView) view.findViewById(R.id.position_listview);
        //消息
        enterPriseId=new ArrayList<>();
        message = (TextView) view.findViewById(R.id.message_textview);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), InterviewMessageActivity.class);
                startActivity(intent);
            }
        });
        new Thread(){
            @Override
            public void run() {
             getPosition();
            }
        }.start();
        //职位列表
        list=new ArrayList<>();
        listView.setAdapter(new PositionAdapter(list,getActivity()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent=new Intent(getActivity(), PositionDetailActivity.class);
                HashMap<String,Object> map =new HashMap<>();
                map = (HashMap<String, Object>) parent.getItemAtPosition(position);
                int idd = (int) map.get("id");
                intent.putExtra("postDetailId",idd);
                intent.putExtra("enterPriseId",enterPriseId.get(position));
                Log.i("==============",""+enterPriseId.get(position));
                startActivity(intent);
            }
        });
        return view;
    }

   /* public void getDate(){
        for (int i=0;i<5;i++){
            HashMap<String,Object> map =new HashMap<>();
            map.put("cpimage",R.mipmap.cpimage);
            map.put("position",getResources().getString(R.string.design_director));
            map.put("cpname",getResources().getString(R.string.cpname));
            map.put("city",getResources().getString(R.string.city));
            map.put("someday",getResources().getString(R.string.someday));
            map.put("pay",getResources().getString(R.string.money));
            list.add(map);
        }
    }*/
    public void getPosition(){
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd");
        String dt=simpleDateFormat.format(date).toString();

        String httpUrl="http://10.0.2.2/index.php/home/index/postintro?showtime="+dt;
        try {
            URL url=new URL(httpUrl);
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
                JSONArray jsonArray=jsonObject.getJSONArray("paytreatmenrtypedata");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                    int pdId=jsonObject1.getInt("post_details_id");
                    int enterId=jsonObject1.getInt("enterprise_id");
                    HashMap<String,Object> map =new HashMap<>();
                    map.put("cpimage",R.mipmap.cpimage);
                    map.put("position",jsonObject1.getString("post_type"));
                    map.put("cpname",jsonObject1.getString("enterprise_name"));
                    map.put("city",jsonObject1.getString("district"));
                    map.put("someday",getResources().getString(R.string.someday));
                    map.put("pay",jsonObject1.getString("pay"));
                    map.put("id",pdId);
                    list.add(map);
                    enterPriseId.add(enterId);
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
