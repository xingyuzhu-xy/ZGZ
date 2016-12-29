package test.leco.com.zgz.t;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
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
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import test.leco.com.zgz.R;
import test.leco.com.zgz.t.adapter.PartTimeJobAdapter;
import test.leco.com.zgz.t.adapter.SearchListAdapter;
import test.leco.com.zgz.t.data.PartTimeJobItem;
import test.leco.com.zgz.t.data.SearchListItem;

/**
 * Created by Administrator on 2016/12/0014.
 */

public class PartTimeJobActivity extends Activity {
    ImageView back; //返回上级页面
    ListView listView; //兼职列表
    List<PartTimeJobItem> list = new ArrayList<PartTimeJobItem>();
    Spinner work_area;
    Spinner push_time;
    String[] work_area_sp = {"重庆","上海","北京"};
    String[] push_time_sp = {"发布时间","一个月前", "一周前", "今天", "不限"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_part_time_job);

        back = (ImageView) findViewById(R.id.back_icon);
        listView = (ListView) findViewById(R.id.listView);
        work_area = (Spinner) findViewById(R.id.work_area);

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
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(PartTimeJobActivity.this,android.R.layout.simple_spinner_dropdown_item,push_time_sp);
        push_time.setAdapter(arrayAdapter2);
        push_time.setOnItemSelectedListener(onItemSelectedListener);

        startAsyncTask();
    }

    AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            parent.getItemAtPosition(position);
            switch (parent.getId()){
                case R.id.work_area:
                    partsite = parent.getItemAtPosition(position).toString();
                    Log.i("partsite","======"+partsite);
                    startAsyncTask();
                    break;
                case R.id.push_time:
                    part_time = parent.getItemAtPosition(position).toString();
                    startAsyncTask();
                //   Toast.makeText(PartTimeJobActivity.this,"你点击了"+push_time+parent.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
                    break;
            }

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
    String partsite;
    String parttime;
    int part_time_job_details_id;//兼职的ID
    String part_name; //兼职的名称
    String part_site;//兼职的地点
    String part_time;//兼职形式
    String part_money;//多少钱
    int isstick;//是否已经置顶 1、置顶 0、不置顶
    List<Integer> integerList = new ArrayList<>();
    String positionData;
//    public void part(){
//        gettime();
//        String httpurl;
//        if(partsite == null){
//            httpurl = "http://10.0.2.2/index.php/home/index/part?"+"currenttime="+string+"&partsite="+"&parttime=";
//        }else {
//            httpurl = "http://10.0.2.2/index.php/home/index/part?"+"currenttime="+string+"&partsite="+partsite+"&parttime=";
//        }
//        try {
//            URL url = new URL(httpurl);
//            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//            httpURLConnection.setRequestMethod("GET");
//            httpURLConnection.setConnectTimeout(5000);
//            httpURLConnection.connect();
//            if(httpURLConnection.getResponseCode()==HttpURLConnection.HTTP_OK){
//                StringBuilder stringBuilder = new StringBuilder();
//                InputStream inputStream = httpURLConnection.getInputStream();
//                InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
//                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//                String s;
//                while ((s=bufferedReader.readLine())!=null){
//                    stringBuilder.append(s);
//                }
//                date = stringBuilder.toString();
//                JSONObject jsonObject = new JSONObject(date);
//                status = jsonObject.getInt("status");
//                message = jsonObject.getString("message");
//                JSONArray jsonArray = jsonObject.getJSONArray("partjobdetailsdata");
//                for(int i =0;i<jsonArray.length();i++){
//                    JSONObject object = jsonArray.getJSONObject(i);
//                    part_time_job_details_id = object.getInt("part_time_job_details_id");
//                    part_name = object.getString("part_name");
//                    part_site = object.getString("part_site");
//                    part_time = object.getString("part_time");
//                    part_money = object.getString("part_money");
//                    isstick = object.getInt("isstick");
//                    Log.i("object","object==="+object);
//                    PartTimeJobItem partTimeJobItem = new PartTimeJobItem();
//                    partTimeJobItem.setPositionName(part_name);
//                    partTimeJobItem.setAddress(part_site);
//                    partTimeJobItem.setSalary(part_money);
//                    partTimeJobItem.setWorkTime(part_time);
//                    if(isstick == 0){
//                        partTimeJobItem.setTop("不置顶");
//                    }else {
//                        partTimeJobItem.setTop("置顶");
//                    }
//                    list.add(partTimeJobItem);
//                    integerList.add(part_time_job_details_id);
//                }
//                handler.sendEmptyMessage(0);
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            PartTimeJobAdapter partTimeJobAdapter = new PartTimeJobAdapter(PartTimeJobActivity.this,list);
            listView.setAdapter(partTimeJobAdapter);
        }
    };


    //异步任务的使用方法
    public class SearchSeekLoad extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }


        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection httpURLConnection = null;
            try {
                httpURLConnection = (HttpURLConnection)
                        new URL(params[0]).openConnection();
                Log.i("","111111111");
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode() == 200) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                    StringBuilder stringBuilder = new StringBuilder();
                    String data;
                    while ((data = bufferedReader.readLine()) != null) {
                        stringBuilder.append(data);
                    }
                    positionData = stringBuilder.toString();
                    return positionData;
                } else {
                    Log.i("haha", "错误信息" + httpURLConnection.getResponseCode());
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        //对发布时间进行判断
        public String currerttime(String time) {
            String newTime = time.replace("-", "");
            int positionTime = Integer.parseInt(newTime);
            Log.i("newTime++++++", "" + newTime);
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat
                    ("yyyyMMdd");//可以方便地修改日期格式
            String todayStr = dateFormat.format(now);
            int today = Integer.parseInt(todayStr);
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int date = calendar.get(Calendar.DATE);
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 7);//一周前今天的时间
            Date lastWeek1 = calendar.getTime();
            int lastWeek = Integer.parseInt(dateFormat.format(lastWeek1));

            calendar.add(Calendar.DATE, -1 + 7);    //得到前一天
            calendar.add(Calendar.MONTH, -1);    //得到前一个月
            int lastmonth1 = calendar.get(Calendar.MONTH) + 1;
            String lastmonthStr = year + "" + lastmonth1 + "" + date;
            int lastmonth = Integer.parseInt(lastmonthStr);
            Log.i("lastmonth++++++", "" + lastmonth);
            int lastdate1 = calendar.get(Calendar.DATE);
            String lastdateStr = year + "" + month + "" + lastdate1;
            int lastdate = Integer.parseInt(lastdateStr);
            Log.i("date++++++", "" + lastdate);
            calendar.add(Calendar.DATE, -1);
            int qiantian1 = calendar.get(Calendar.DATE);//得到前天
            String qiantianStr = year + "" + month + "" + qiantian1;
            int qiantian = Integer.parseInt(qiantianStr);
            Log.i("qiantian++++++", "" + qiantian);

            String timeStr;
            if (lastWeek <= positionTime && positionTime < qiantian) {
                timeStr = "一周以内";
            } else if (today == positionTime) {
                timeStr = "今天";
            } else if (lastmonth <= positionTime && positionTime < lastWeek) {
                timeStr = "一个月以内";
            } else if (lastdate == positionTime) {
                timeStr = "昨天";
            } else if (qiantian == positionTime) {
                timeStr = "前天";
            } else {
                timeStr = time;
            }
            return timeStr;
        }

        @Override
        protected void onPostExecute(String s) {
            if (positionData != null){
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(positionData);
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
                        if(isstick == 0){
                            partTimeJobItem.setTop("不置顶");
                        }else {
                            partTimeJobItem.setTop("置顶");
                        }
                        list.add(partTimeJobItem);
                        Log.i("======>",""+list);
                        integerList.add(part_time_job_details_id);
                    }
                    PartTimeJobAdapter partTimeJobAdapter = new PartTimeJobAdapter(PartTimeJobActivity.this,list);
                    listView.setAdapter(partTimeJobAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    public void startAsyncTask(){
        gettime();
        list.clear();
        String httpurl = "http://10.0.2.2/index.php/home/index/part?"+"currenttime="+string+"&partsite="+partsite+"&parttime=";
        Log.i("====>",httpurl);
        SearchSeekLoad searchSeekLoad = new SearchSeekLoad();
        searchSeekLoad.execute(httpurl);
    }
}
