package test.leco.com.zgz.t;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
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

import test.leco.com.zgz.R;
import test.leco.com.zgz.zxy.ReferCompanyActivity;

/**
 * Created by Administrator on 2016/12/0014.
 */

public class PositionDetailsActivity extends Activity{
    ImageView back; //返回上级页面
    TextView enterprise; //企业咨询
    Intent intent;
    RelativeLayout tel; //打电话
    int en_id; //企业的id
    TextView phone1; //电话
    TextView position;//职位名称
    TextView money;//薪水
    TextView jinyan;//经验
    TextView education;//学历
    TextView people_numb;//招收人数
    TextView age;//年龄
    TextView sex;//性别
    TextView graduate;//招收标准
    TextView site;//地址
    TextView site_xiang;//详细地址
    TextView enterprise_name;//公司名字
    ImageView enterprise_image;//公司标志
    TextView industry;//经营范围

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_position_details);
        back = (ImageView) findViewById(R.id.back_icon);
        enterprise = (TextView) findViewById(R.id.enterprise_consultation);
        phone1 = (TextView) findViewById(R.id.phone_call);
        tel = (RelativeLayout) findViewById(R.id.tel);
        position = (TextView) findViewById(R.id.position);
        money = (TextView) findViewById(R.id.money);
        jinyan = (TextView) findViewById(R.id.jinyan);
        education = (TextView) findViewById(R.id.education);
        people_numb = (TextView) findViewById(R.id.people_numb);
        age = (TextView) findViewById(R.id.age);
        sex = (TextView) findViewById(R.id.sex);
        graduate = (TextView) findViewById(R.id.graduate);
        site = (TextView) findViewById(R.id.site);
        site_xiang = (TextView) findViewById(R.id.site_xiang);
        enterprise_name = (TextView) findViewById(R.id.enterprise_name);
        industry = (TextView) findViewById(R.id.industry);
        enterprise_image = (ImageView) findViewById(R.id.enterprise_image);
        //结束当前页面，返回上级页面
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //跳转到企业咨询页面
        enterprise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PositionDetailsActivity.this, ReferCompanyActivity.class);
                startActivity(intent);
            }
        });
        final String phone = "15823903420";
        //打电话
        tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phone));
                startActivity(intent);
            }
        });
        Intent inte = getIntent();
        en_id = inte.getIntExtra("en",-1);
        Log.i("en_id===>",""+en_id);
        //打电话

        phone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + "");
                intent.setData(data);
                startActivity(intent);
            }
        });

        checkPermisson();
        new Thread(new Runnable() {
            @Override
            public void run() {
                getseekpostdetails();
            }
        }).start();
    }

    int postid;
    int status;
    String message;
    public void getseekpostdetails(){
        try {
            URL url = new URL("http://localhost/index.php/home/index/seekpostdetails?"+"postid"+postid);
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
                while((s=bufferedReader.readLine())!=null){
                    stringBuilder.append(s);
                }
                String date = stringBuilder.toString();
                JSONObject jsonObject = new JSONObject(date);
                status =jsonObject.getInt("status");
                message = jsonObject.getString("message");
                JSONArray jsonArray = jsonObject.getJSONArray("postdetailsdata");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    String post_type = object.getString("post_type");
                    String experience = object.getString("experience");
                    int person_num =object.getInt("person_num");
                    String pay = object.getString("pay");
                    String base_pay = object.getString("base_pay");
                    String obligation = object.getString("obligation");
                    String take_office_status = object.getString("take_office_status");
                    String sex_type = object.getString("sex_type");
                    String enducation_type = object.getString("enducation_type");
                    String enterprise_name = object.getString("enterprise_name");
                    String enterprise_image = object.getString("enterprise_image");
                    int isapprove = object.getInt("isapprove");
                    String industry = object.getString("industry");
                    int enterprise_id = object.getInt("enterprise_id");
                    String direct_camp = object.getString("direct_camp");
                    String district = object.getString("district");
                    int recuit_person_id = object.getInt("recuit_person_id");//招聘人的ID
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
    /**
     * 动态权限的请求
     */
    public void checkPermisson(){
        if(Build.VERSION.SDK_INT>=23){
            ActivityCompat.requestPermissions(this,//上下文
                    new String[]{
                            Manifest.permission.CALL_PHONE},//权限数组
                            1001);
        }
    }

    /**
     * 动态权限的回调函数
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
