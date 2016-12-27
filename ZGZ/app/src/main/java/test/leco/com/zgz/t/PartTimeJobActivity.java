package test.leco.com.zgz.t;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import test.leco.com.zgz.R;
import test.leco.com.zgz.t.adapter.PartTimeJobAdapter;
import test.leco.com.zgz.t.data.PartTimeJobItem;

/**
 * Created by Administrator on 2016/12/0014.
 */

public class PartTimeJobActivity extends Activity {
    ImageView back; //返回上级页面
    ListView listView; //兼职列表
    List<PartTimeJobItem> list = new ArrayList<PartTimeJobItem>();
    Spinner work_area;
    Spinner zhiwei_choose;
    Spinner push_time;
    String[] work_area_sp = {"工作地点","经理0","经理1","经理2"};
    String[] zhiwei_choose_sp = {"职位选择","经理0","经理1","经理2"};
    String[] push_time_sp = {"刷新时间","10秒","30秒","1分钟","2分钟","10分钟"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_part_time_job);

        back = (ImageView) findViewById(R.id.back_icon);
        listView = (ListView) findViewById(R.id.listView);
        work_area = (Spinner) findViewById(R.id.work_area);
        zhiwei_choose = (Spinner) findViewById(R.id.zhiwei_choose);
        push_time = (Spinner) findViewById(R.id.push_time);
        //返回上级页面
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
                Intent intent = new Intent(PartTimeJobActivity.this,CompanyDetailsActivity.class);
                intent.putExtra("gongsiid",integerList.get(position));
                startActivity(intent);
            }
        });
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(PartTimeJobActivity.this,android.R.layout.simple_spinner_dropdown_item,work_area_sp);
        work_area.setAdapter(arrayAdapter);
        work_area.setOnItemSelectedListener(onItemSelectedListener);
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(PartTimeJobActivity.this,android.R.layout.simple_spinner_dropdown_item,zhiwei_choose_sp);
        zhiwei_choose.setAdapter(arrayAdapter1);
        zhiwei_choose.setOnItemSelectedListener(onItemSelectedListener);
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(PartTimeJobActivity.this,android.R.layout.simple_spinner_dropdown_item,push_time_sp);
        push_time.setAdapter(arrayAdapter2);
        push_time.setOnItemSelectedListener(onItemSelectedListener);
        new Thread(new Runnable() {
            @Override
            public void run() {
                part();
            }
        }).start();

    }

    AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    //获得当前时间
    String string;
    public void gettime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(System.currentTimeMillis());
        string= simpleDateFormat.format(date);
        Log.i("currenttime","===="+string);
    }


    int status;
    String message;
    String currenttime ;
    int part_time_job_details_id;//兼职的ID
    String part_name; //兼职的名称
    String part_site;//兼职的地点
    String part_time;//兼职形式
    String part_money;//多少钱
    int isstick;//是否已经置顶 1、置顶 0、不置顶
    List<Integer> integerList = new ArrayList<>();
    public void part(){
        gettime();
        try {
            URL url = new URL("http://192.168.7.6/index.php/home/index/part?"+"currenttime="+string);
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
                while ((s=bufferedReader.readLine())!=null){
                    stringBuilder.append(s);
                }
                String date = stringBuilder.toString();
                JSONObject jsonObject = new JSONObject(date);
                status = jsonObject.getInt("status");
                message = jsonObject.getString("message");
                JSONArray jsonArray = jsonObject.getJSONArray("partjobdetailsdata");
                for(int i =0;i<jsonArray.length();i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    part_time_job_details_id = object.getInt("part_time_job_details_id");
                    part_name = object.getString("part_name");
                    part_site = object.getString("part_site");
                    part_time = object.getString("part_time");
                    part_money = object.getString("part_money");
                    isstick = object.getInt("isstick");
                    Log.i("object","object==="+object);
                    PartTimeJobItem partTimeJobItem = new PartTimeJobItem();
                    partTimeJobItem.setPositionName(part_name);
                    partTimeJobItem.setAddress(part_site);
                    partTimeJobItem.setSalary(part_money);
                    partTimeJobItem.setWorkTime(part_time);
                    list.add(partTimeJobItem);
                    integerList.add(part_time_job_details_id);
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
            PartTimeJobAdapter partTimeJobAdapter = new PartTimeJobAdapter(PartTimeJobActivity.this,list);
            listView.setAdapter(partTimeJobAdapter);
        }
    };
}
