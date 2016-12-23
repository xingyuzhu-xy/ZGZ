package test.leco.com.zgz.zxy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import java.text.DecimalFormat;

import test.leco.com.zgz.R;

/**
 * Created by Administrator on 2016/12/13.
 */

public class PositionDetailActivity extends Activity {
             //职位详情     公司详情
    TextView positionDetail,companyDetail;
    LinearLayout positionLinearlayout,companyLinearlayout;
    ImageView pdArrow;//返回箭头
             //职位  公司名字 位置 工资
    TextView positionText,nameText,siteText,payText,expText,eduText,numText,ageText,sexText,isfreshText,deSiteText,respNumb;

    //公司详情
    TextView cpname,cpIndustry,cpUrl,cpIntroduce,cpScale,cpSite,cpNature;
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_position_detail_layout);
        positionDetail= (TextView) findViewById(R.id.position_detail);
        companyDetail= (TextView) findViewById(R.id.company_detail);
        positionLinearlayout= (LinearLayout) findViewById(R.id.position_linearlayout);
        companyLinearlayout= (LinearLayout) findViewById(R.id.company_linearlayout);
        pdArrow= (ImageView) findViewById(R.id.position_detail_arrow);
        positionDetail.setOnClickListener(listener);
        companyDetail.setOnClickListener(listener);
        findViewById();
        super.onCreate(savedInstanceState);
        pdArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
       new Thread(){
           @Override
           public void run() {
               getDate();
               enterPrise();
           }
       }.start();
    }
    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           switch (v.getId()){
               case R.id.position_detail:
                    positionLinearlayout.setVisibility(View.VISIBLE);
                   companyLinearlayout.setVisibility(View.GONE);
                   positionDetail.setBackgroundResource(R.drawable.position_detail_solid);
                   companyDetail.setBackgroundResource(R.drawable.position_detail);
                   positionDetail.setTextColor(getResources().getColor(R.color.my_setting_blue1));
                   companyDetail.setTextColor(getResources().getColor(R.color.white));
                   break;
               case R.id.company_detail:
                   positionLinearlayout.setVisibility(View.GONE);
                   companyLinearlayout.setVisibility(View.VISIBLE);
                   positionDetail.setBackgroundResource(R.drawable.position_detail);
                   companyDetail.setBackgroundResource(R.drawable.position_detail_solid);
                   positionDetail.setTextColor(getResources().getColor(R.color.white));
                   companyDetail.setTextColor(getResources().getColor(R.color.my_setting_blue1));
                   break;
           }
        }
    };
    public void findViewById(){
        positionText= (TextView) findViewById(R.id.position);
        nameText= (TextView) findViewById(R.id.cpname);
        siteText= (TextView) findViewById(R.id.site);
        payText= (TextView) findViewById(R.id.pay);
        expText= (TextView) findViewById(R.id.exp);
        eduText= (TextView) findViewById(R.id.edu);
        numText= (TextView) findViewById(R.id.num);
        ageText= (TextView) findViewById(R.id.age);
        sexText= (TextView) findViewById(R.id.sex);
        isfreshText= (TextView) findViewById(R.id.isfresh);
        deSiteText= (TextView) findViewById(R.id.desite);
        respNumb= (TextView) findViewById(R.id.resp_num);

        cpname= (TextView) findViewById(R.id.cpname_cp);
        cpIndustry= (TextView) findViewById(R.id.industry_cp);
        cpNature= (TextView) findViewById(R.id.nature_cp);
        cpUrl= (TextView) findViewById(R.id.url_cp);
        cpSite= (TextView) findViewById(R.id.site_cp);
        cpScale= (TextView) findViewById(R.id.scale_cp);

    }
    public void getDate(){
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        int postDetailId=bundle.getInt("postDetailId");
        String httpUrl="http://10.0.2.2/index.php/home/index/postdetails?postid="+postDetailId;
        Log.i("================",""+postDetailId);
        try {
            URL url =new URL(httpUrl);
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
                JSONArray jsonArray=jsonObject.getJSONArray("postdateilsdata");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                    String cpposition=jsonObject1.getString("post_type");
                    String cpname=jsonObject1.getString("enterprise_name");
                    String site =jsonObject1.getString("district");
                    String pay=jsonObject1.getString("pay");
                    String exp=jsonObject1.getString("experience");
                    String edu=jsonObject1.getString("enducation_type");
                    String num=jsonObject1.getString("person_num");
                    String age=jsonObject1.getString("age");
                    String sex=jsonObject1.getString("sex_type");
                    String[] isfreshgraduate={"","应届生亦可","不招聘应届生"};
                    int isFresh=jsonObject1.getInt("isfreshgraduate");
                    String deSite=jsonObject1.getString("site");
                    int seedsum=jsonObject1.getInt("sendsum");
                    int feedbacksum=jsonObject1.getInt("feedbacksum");

                    float a=((float)feedbacksum/seedsum)*100;
                    DecimalFormat df = new DecimalFormat("0");//格式化小数
                    String sb = df.format(a);//返回的是String类型

                   respNumb.setText(sb+"%");
                    positionText.setText(cpposition);
                    nameText.setText(cpname);
                    siteText.setText(site);
                    payText.setText(pay);
                    expText.setText(exp);
                    eduText.setText(edu);
                    numText.setText(num);
                    ageText.setText(age);
                    sexText.setText(sex);
                    deSiteText.setText(deSite);
                    isfreshText.setText(isfreshgraduate[isFresh]);
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
    public void enterPrise(){
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        int enterPriseId=bundle.getInt("enterPriseId");
        String httpURL="http://10.0.2.2/index.php/home/index/enterprisedateils?enterpriseid="+enterPriseId;
            try {
                URL url =new URL(httpURL);
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
                    JSONArray jsonArray=jsonObject.getJSONArray("enterprisedata");
                    JSONObject jsonObject1=jsonArray.getJSONObject(0);
                    String name=jsonObject1.getString("enterprise_name");
                    String Url=jsonObject1.getString("url");
                    String industry=jsonObject1.getString("industry");
                    String introduce=jsonObject1.getString("introduce");
                    String nature=jsonObject1.getString("nature");
                    String scale=jsonObject1.getString("scale");
                    String site=jsonObject1.getString("site");
                    Log.i("name==========",""+name);

                    cpname.setText(name);
                    cpUrl.setText(Url);
                    cpIndustry.setText(industry);
                    cpNature.setText(nature);
                    cpScale.setText(scale);
                    cpSite.setText(site);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();


        }
    }
}
