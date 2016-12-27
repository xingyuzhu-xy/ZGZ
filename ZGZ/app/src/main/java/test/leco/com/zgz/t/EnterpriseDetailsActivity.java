package test.leco.com.zgz.t;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import java.util.ArrayList;
import java.util.List;

import test.leco.com.zgz.R;
import test.leco.com.zgz.t.data.MyAppLication;
import test.leco.com.zgz.zxy.ReferCompanyActivity;


/**
 * Created by Administrator on 2016/12/0014.
 */

public class EnterpriseDetailsActivity extends Activity {
    ImageView back; //返回上级页面
    RelativeLayout consultation,care; // 咨询企业、关注企业
    TextView companyName,companyname,pepole_number,jinying,hangye,wuxian,shuangxui,daixin,jieri,area,context;
    LinearLayout fuli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_enterprise_details);

        companyName = (TextView) findViewById(R.id.companyName);
        companyname = (TextView) findViewById(R.id.companyname);
        pepole_number = (TextView) findViewById(R.id.pepole_number);
        jinying = (TextView) findViewById(R.id.jinying);
        hangye = (TextView) findViewById(R.id.hangye);
        area = (TextView) findViewById(R.id.area);
        context = (TextView) findViewById(R.id.context);
        fuli = (LinearLayout) findViewById(R.id.fuli);
        findView();
        new Thread(new Runnable() {
            @Override
            public void run() {
                gethomeenterprise();
            }
        }).start();

        Intent intent = getIntent();
        String numb = intent.getStringExtra("enter");
        enterprise_id = numb;
    }

    public void findView(){
        back = (ImageView) findViewById(R.id.back_icon);
        consultation = (RelativeLayout) findViewById(R.id.enterprise_consultation); //咨询企业
        care = (RelativeLayout) findViewById(R.id.care_enterprise); //关注企业
        //添加点击事件
        back.setOnClickListener(clickListener);
        consultation.setOnClickListener(clickListener);
        care.setOnClickListener(clickListener);
    }
    //点击事件
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back_icon:
                    finish();
                    break;
                case R.id.enterprise_consultation:
                    Intent intent = new Intent(EnterpriseDetailsActivity.this, ReferCompanyActivity.class);
                    startActivity(intent);
                    break;
                case R.id.care_enterprise:
                    new Thread(){
                        @Override
                        public void run() {
                            setCare();
                        }
                    }.start();
                    break;
            }
        }
    };

    MyAppLication myAppLication;
    int user_id;
    int status;
    int id;
    String enterprise_id; //企业id
    String enterprise_name; //企业名称
    int isattention;//是否关注（1关注）
    String nature;//企业性质
    String scale;//企业规模
    String industry;//经营范围
    String site;//地址
    String introduce;//企业简介
    List<String> pay_treatmenr_name = new ArrayList<String>();//福利名称
    public void gethomeenterprise(){
        String httpurl = "http://192.168.7.6/index.php/home/index/gethomeenterprise?"+"user_id="+1+"&enterprise_id="+enterprise_id;
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
                    Log.i("JSONObject","======="+object);
                    id = object.getInt("id");
                    enterprise_id = object.getString("enterprise_id");
                    enterprise_name = object.getString("enterprise_name");
                    isattention = object.getInt("isattention");
                    nature = object.getString("nature");
                    scale = object.getString("scale");
                    industry = object.getString("industry");
                    site = object.getString("site");
                    introduce = object.getString("introduce");
                }
                JSONArray jsonArray1 = jsonObject.getJSONArray("treatmenr");
                for(int i=0;i<jsonArray1.length();i++){
                    JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                    pay_treatmenr_name.add(jsonObject1.getString("pay_treatmenr_name"));
                    Log.i("pay_treatmenr_name","======="+pay_treatmenr_name);

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
            companyName.setText(enterprise_name);
            Log.i("companyName","======="+companyName);
            pepole_number.setText(scale);
            jinying.setText(nature);
            hangye.setText(industry);
            area.setText(site);
            context.setText(introduce);
            for(int z=0;z<pay_treatmenr_name.size();z++){
                TextView textView = new TextView(EnterpriseDetailsActivity.this);
                textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                textView.setText(pay_treatmenr_name.get(z));
                textView.setTextSize(10);
                textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.t_benefits));
                textView.setPadding(5,3,5,4);
                fuli.addView(textView);
            }
        }
    };

    public void setCare(){
        myAppLication = (MyAppLication) getApplication();
        user_id = myAppLication.getId();
        String STR_URL = "http://192.168.7.6/index.php/home/index/attentionbtn?";

        try {
            URL url = new URL(STR_URL+"user_id="+user_id+"&enterprise_id="+enterprise_id);
            Log.i("======>",""+url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(2000);
            httpURLConnection.connect();
            Message ms = new Message();
            if (httpURLConnection.getResponseCode() ==httpURLConnection.HTTP_OK){
                ms.arg1 = 200;
                hand.sendMessage(ms);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    Handler hand = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 == 200){
                Toast.makeText(EnterpriseDetailsActivity.this,"关注成功",Toast.LENGTH_SHORT).show();
                care.setBackgroundDrawable(getResources().getDrawable(R.drawable.deliver_button2));
                care.setClickable(false);
            }else {
                Toast.makeText(EnterpriseDetailsActivity.this,"关注失败",Toast.LENGTH_SHORT).show();
            }
        }
    };

}
