package test.leco.com.zgz.t;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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
import test.leco.com.zgz.t.adapter.SearchListAdapter;
import test.leco.com.zgz.t.data.SearchListItem;

/**
 * Created by Administrator on 2016/12/0014.
 */

public class SearchListActivity extends Activity {
    ImageView back; //返回上级页面
    static ListView listView;
    static List<SearchListItem> list = new ArrayList<SearchListItem>();
    ArrayList<Integer> arrayList = new ArrayList<Integer>();

    Spinner position_type;
    Spinner regio;
    Spinner salary;
    String[] zhiye_sp = {"职位类别", "经理0", "经理1", "经理2"};
    String[] regio_sp = {"地区", "经理0", "经理1", "经理2"};
    String[] salary_sp = {"薪资", "1000-3000", "3000-5000", "5000-8000", "8000-10000", "10000-15000", "15000以上"};
    String positionName;//行业
    String postName;//职位
    String site;//地区
    int minPay;//最低薪资
    int maxPay;//最高薪资
    int inssueTime;//时间
    private final int SEARCHLIST_POSITIONID_SATE = 11111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_advanced_search_list);

        back = (ImageView) findViewById(R.id.back_icon);
        position_type = (Spinner) findViewById(R.id.position_type1);
        regio = (Spinner) findViewById(R.id.regio1);
        salary = (Spinner) findViewById(R.id.salary1);
        listView = (ListView) findViewById(R.id.listView);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            positionName = bundle.getString("positionName");
            postName = bundle.getString("postName");
            site = bundle.getString("site");
            minPay = bundle.getInt("minPay");
            maxPay = bundle.getInt("maxPay");
            inssueTime = bundle.getInt("inssueTime");
            Log.i("data*-*-*-*-*-*", "" + positionName + "****" + postName + "****" + site + "****"
                    + minPay + "****" + maxPay + "****" + inssueTime);
        }
        experTseekData();
        new Thread() {
            @Override
            public void run() {
                experTseekData();
            }
        }.start();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SearchListItem seracItem = list.get(position);
                String positionstr = seracItem.getPostid();
                int positionid = Integer.parseInt(positionstr);
                Log.i("positionid", "" + positionid);
                Intent intent = new Intent(SearchListActivity.this, PositionDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("positionid", positionid);
                intent.putExtras(bundle);
                startActivityForResult(intent, SEARCHLIST_POSITIONID_SATE);

                Bundle bundle1 = new Bundle();
                bundle1.putInt("ischecked",arrayList.get(position));
                intent.putExtras(bundle1);
                Log.i("ischecked","-------------"+arrayList.get(position));
                startActivity(intent);

            }
        });


        //点击结束当前页面，返回上级页面
        back.setOnClickListener(onClickListener);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SearchListActivity.this, android.R.layout.simple_spinner_dropdown_item, zhiye_sp);
        position_type.setAdapter(arrayAdapter);
        position_type.setOnItemSelectedListener(onItemSelectedListener);
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(SearchListActivity.this, android.R.layout.simple_spinner_dropdown_item, regio_sp);
        regio.setAdapter(arrayAdapter1);
        regio.setOnItemSelectedListener(onItemSelectedListener1);
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(SearchListActivity.this, android.R.layout.simple_spinner_dropdown_item, salary_sp);
        salary.setAdapter(arrayAdapter2);
        salary.setOnItemSelectedListener(onItemSelectedListener2);
        //点击结束当前页面，返回上级页面
        back.setOnClickListener(onClickListener);
//        position_type.setOnClickListener(onClickListener);
    }

    String shijian_time;
    String[] time = {"一个月前", "一周前", "今天", "不限"};

    public void createDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(SearchListActivity.this);
        dialog.setTitle("选择发布时间");
        dialog.setSingleChoiceItems(time, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(AdvancedSearchActivity.this,"你选择了"+time[which],Toast.LENGTH_LONG).show();
                shijian_time = time[which];
            }
        });
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(AdvancedSearchActivity.this,"你选择了"+which,Toast.LENGTH_LONG).show();
            }
        });
        dialog.create();
        dialog.show();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.salary:
                    break;
                case R.id.back_icon:
                    finish();
                    break;
            }
        }
    };
    AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //parent.getItemAtPosition(position)
            Log.i("======", "" + parent.getItemAtPosition(position));
            Log.i("parent======", "" + parent);
            postName = parent.getItemAtPosition(position).toString();
            if (postName.equals("职位类别")) {
                postName = null;
                Log.i("postName======", "" + postName);
            }
            experTseekData();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    AdapterView.OnItemSelectedListener onItemSelectedListener1 = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //parent.getItemAtPosition(position)
            Log.i("======", "" + parent.getItemAtPosition(position));
            Log.i("parent======", "" + parent);
            site = parent.getItemAtPosition(position).toString();
            if (site.equals("地区")) {
                site = null;
            }
            experTseekData();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    AdapterView.OnItemSelectedListener onItemSelectedListener2 = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //parent.getItemAtPosition(position)
            Log.i("======", "" + parent.getItemAtPosition(position));
            Log.i("parent======", "" + parent);
            String pay = parent.getItemAtPosition(position).toString();

            if (pay.equals("薪资")) {
                pay = "0-999999999";
            } else if (pay.equals("15000以上")) {
                pay = "15000-999999999";
            }
            String payArray[] = pay.split("-", 2);
            Log.i("payArray======", "" + payArray);
            String minPayStr = payArray[0];
            String maxPayStr = payArray[1];
            Log.i("minPayStr======", "" + minPayStr);
            Log.i("maxPayStr======", "" + maxPayStr);
            minPay = Integer.parseInt(minPayStr);
            maxPay = Integer.parseInt(maxPayStr);

            experTseekData();

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };


    //高级搜索页面接口
    public void experTseekData() {
        list.clear();
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat
                ("yyyyMMdd");//可以方便地修改日期格式
        String today = dateFormat.format(now);

        if (positionName == null) {
            positionName = "";
        } else if (postName == null) {
            postName = "";
        } else if (site == null) {
            site = "";
        } else if (minPay == 0) {
            minPay = 0;
        }
        if (maxPay == 0) {
            maxPay = 999999999;
        } else if (inssueTime < 19700101) {
            maxPay = 19700101;
        }


        try {
            positionName = java.net.URLEncoder.encode(positionName,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String httpURL = "http://192.168.7.6/index.php/home/index/expertseek?positionname="
                + positionName + "&currenttime=" + today + "&postname=" + postName + "&site=" + site
                + "&minpay=" + minPay + "&maxpay=" + maxPay + "&time=" + inssueTime;

        SearchSeekLoad sear = new SearchSeekLoad();
        sear.execute(httpURL);

    }

    //异步任务的使用方法
    public class SearchSeekLoad extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }


        @Override
        protected String doInBackground(String... params) {
            SearchListItem searchListItem = null;
            HttpURLConnection httpURLConnection = null;
            try {
                httpURLConnection = (HttpURLConnection)
                        new URL(params[0]).openConnection();
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
                    String positionData = stringBuilder.toString();
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
            try {
                JSONObject jsonObject = new JSONObject(s);
                int status = jsonObject.getInt("status");
                String message = jsonObject.getString("message");
                if (status == 200) {
                    JSONArray postdetailsdata = jsonObject.getJSONArray("postdetailsdata");
                    for (int i = 0; i < postdetailsdata.length(); i++) {
                        SearchListItem searchListItem = new SearchListItem();
                        JSONObject object = postdetailsdata.getJSONObject(i);
                        int postDetailsID = object.getInt("post_details_id");
                        searchListItem.setPostid(postDetailsID + "");
                        String enterpriseName = object.getString("enterprise_name");
                        searchListItem.setCompanyName(enterpriseName);
                        String postType = object.getString("post_type");
                        searchListItem.setPositionName(postType);
                        String startTime = object.getString("start_time");
                        String startTimeStr = currerttime(startTime);
                        searchListItem.setTiem(startTimeStr);
                        String district = object.getString("district");
                        searchListItem.setAddress(district);
                        String enducationType = object.getString("enducation_type");
                        String experience = object.getString("experience");
                        String expEnduca = experience + "/" + enducationType;
                        searchListItem.setEducation(expEnduca);
                        String pay = object.getString("pay");
                        searchListItem.setSalary(pay);
                        list.add(searchListItem);
                        arrayList.add(postDetailsID);

                        Log.i("list/*/*/*/*/", "" + list);
                    }
                }
                listView.setAdapter(new SearchListAdapter(SearchListActivity.this, list));

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
