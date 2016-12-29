package test.leco.com.zgz.t;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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

import test.leco.com.zgz.R;
import test.leco.com.zgz.zxy.Utils.ShareWeiXin;

/**
 * Created by Administrator on 2016/12/0014.
 */

public class CompanyDetailsActivity extends Activity {
    ImageView back;
    TextView share;
    TextView part_nam;//职位名称
    TextView time;//工作时间
    TextView name;//公司名称
    TextView sit;//公司地点
    TextView money;//薪水
    TextView part_mone;//节薪水的方式
    TextView textView;//招聘的方式
    TextView zhitime;//招聘时间
    TextView xueli;//学历
    TextView xinbie;//性别
    TextView context;//公司简介
    TextView jeisutime;//结束时间

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_part_time_company_details);
        findView();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            partjobdetailsid = bundle.getInt("gongsiid");
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                getpartcompanydetails();
            }
        }).start();
    }
    
    public void findView(){
        back = (ImageView) findViewById(R.id.back_icon);
        share = (TextView) findViewById(R.id.share);
        back.setOnClickListener(onClickListener);
        share.setOnClickListener(onClickListener);
        part_nam = (TextView) findViewById(R.id.part_name);
        time = (TextView) findViewById(R.id.time);
        name = (TextView) findViewById(R.id.name);
        sit = (TextView) findViewById(R.id.site);
        money = (TextView) findViewById(R.id.money);
        part_mone = (TextView) findViewById(R.id.part_money);
        textView = (TextView) findViewById(R.id.textView);
        zhitime = (TextView) findViewById(R.id.zhitime);
        xueli = (TextView) findViewById(R.id.xueli);
        xinbie = (TextView) findViewById(R.id.xinbie);
        context = (TextView) findViewById(R.id.context);
        jeisutime = (TextView) findViewById(R.id.jeisutime);

    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back_icon:
                    finish();
                    break;
                case R.id.share:
                    ShareWeiXin weiXin = new ShareWeiXin(CompanyDetailsActivity.this,"hello");
                    break;
            }
        }
    };

    int partjobdetailsid;//兼职职位的id
    int status;
    String message;
    int part_time_job_details_id;//兼职的ID
    String part_name; //兼职的名称
    String part_money;//多少钱
    String part_clearing_form;//结算的方式
    String part_person;//招聘多少人
    int isinterview;//是否需要面试1、需要面试0、不需要面试
    String part_start_time;//招聘开始时间
    String part_end_time;//招聘结束时间
    String part_time;//性质模式
    String part_miute;//详细的信息
    String enducation_type;//学历要求
    String sex_type;//性别要求
    String site;//详细的地址
    int enterprise_id;//公司的id
    public void getpartcompanydetails(){
        try {
            URL url = new URL("http://192.168.7.6/index.php/home/index/partcompanydetails?"+"partjobdetailsid="+partjobdetailsid);
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
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    int part_time_job_details_id;//兼职的ID
                    part_name = object.getString("part_name"); //兼职的名称
                    part_money= object.getString("part_money");//多少钱
                    part_clearing_form= object.getString("part_clearing_form");//结算的方式
                    part_person= object.getString("part_person");//招聘多少人
                    isinterview= object.getInt("isinterview");//是否需要面试1、需要面试0、不需要面试
                    part_start_time= object.getString("part_start_time");//招聘开始时间
                    part_end_time= object.getString("part_end_time");//招聘结束时间
                    part_time= object.getString("part_time");//性质模式
                    part_miute= object.getString("part_miute");//详细的信息
                    enducation_type= object.getString("enducation_type");//学历要求
                    sex_type= object.getString("sex_type");//性别要求
                    site= object.getString("site");//详细的地址
                    enterprise_id= object.getInt("enterprise_id");

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
            part_nam.setText(part_name);//职位名称
            time.setText(part_clearing_form);//工作时间
//                    name.setText();//公司名称
            sit.setText(site);//公司地点
            money.setText(part_money);//薪水
            part_mone.setText(part_clearing_form);//节薪水的方式
            textView.setText(part_person);//招聘的方式
            zhitime.setText(part_start_time);//招聘时间
            xueli.setText(enducation_type);//学历
            xinbie.setText(sex_type);//性别
            context.setText(part_miute);//公司简介
            jeisutime.setText(part_end_time);//结束时间
        }
    };
}
