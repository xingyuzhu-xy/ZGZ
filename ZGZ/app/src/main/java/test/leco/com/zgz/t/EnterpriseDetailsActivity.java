package test.leco.com.zgz.t;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import java.util.List;

import test.leco.com.zgz.R;
import test.leco.com.zgz.t.adapter.WhoSeeMeAdapter;
import test.leco.com.zgz.t.data.WhoSeeMeItem;

/**
 * Created by Administrator on 2016/12/0014.
 */

public class EnterpriseDetailsActivity extends Activity {
    ImageView back; //返回上级页面
    RelativeLayout consultation,care; // 咨询企业、关注企业
    TextView companyName,companyname,pepole_number,jinying,hangye,wuxian,shuangxui,daixin,jieri,area,context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_enterprise_details);
        findView();
        new Thread(new Runnable() {
            @Override
            public void run() {
                gethomeenterprise();
            }
        }).start();
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
                    break;
                case R.id.care_enterprise:
                    break;
            }
        }
    };
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
    String pay_treatmenr_name;//福利名称
    public void gethomeenterprise(){
        String httpurl = "http://192.168.7.6/index.php/home/index/gethomeenterprise?"+"user_id="+1+"&enterprise_id="+2;
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
                    user_id = object.getInt("user_id");
                    enterprise_id = object.getString("enterprise_id");
                    enterprise_name = object.getString("enterprise_name");
                    isattention = object.getInt("isattention");
                    nature = object.getString("nature");
                    scale = object.getString("scale");
                    industry = object.getString("industry");
                    site = object.getString("site");
                    introduce = object.getString("introduce");
                    pay_treatmenr_name = object.getString("pay_treatmenr_name");
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
