package test.leco.com.zgz.t;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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
import test.leco.com.zgz.zxy.Utils.DownUrl;

/**
 * Created by Administrator on 2016/12/0014.
 */

public class PositionDetailsActivity extends Activity {
    ImageView back; //返回上级页面
    TextView enterprise; //企业咨询
    Intent intent;
    RelativeLayout tel; //打电话
    RelativeLayout uplome; //投简历
    TextView uptext;
    int en_id; //企业的id
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
    TextView yaoqui;
    TextView zhize;
    ImageView persomImage;
    TextView pesonPost;
    TextView efficiency;
    RelativeLayout resume;
    TextView resumeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_position_details);
        back = (ImageView) findViewById(R.id.back_icon);
        enterprise = (TextView) findViewById(R.id.enterprise_consultation);
        tel = (RelativeLayout) findViewById(R.id.tel);
        position = (TextView) findViewById(R.id.position);
        money = (TextView) findViewById(R.id.money);
        jinyan = (TextView) findViewById(R.id.jinyan);
        education = (TextView) findViewById(R.id.education);
        people_numb = (TextView) findViewById(R.id.people_numbs);
        age = (TextView) findViewById(R.id.age);
        sex = (TextView) findViewById(R.id.sex);
        graduate = (TextView) findViewById(R.id.graduate);
        site = (TextView) findViewById(R.id.site);
        site_xiang = (TextView) findViewById(R.id.site_xiang);
        enterprise_name = (TextView) findViewById(R.id.enterprise_name);
        industry = (TextView) findViewById(R.id.industry);
        enterprise_image = (ImageView) findViewById(R.id.enterprise_image);

        uplome = (RelativeLayout) findViewById(R.id.resume);
        uptext = (TextView) findViewById(R.id.resume_text);
        uplome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uptext.setText("已投递");
            }
        });
        zhize = (TextView) findViewById(R.id.zhize);
        yaoqui = (TextView) findViewById(R.id.yaoqui);
        persomImage = (ImageView) findViewById(R.id.person_image);
        pesonPost = (TextView) findViewById(R.id.person_post);
        efficiency = (TextView) findViewById(R.id.efficiency);
        resume = (RelativeLayout) findViewById(R.id.resume);
        resumeText = (TextView) findViewById(R.id.resume_text);

        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resumeText.setText("已投递");
            }
        });

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
                intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                startActivity(intent);
            }
        });
//        Intent inte = getIntent();
//        en_id = inte.getIntExtra("en",-1);
//        Log.i("en_id===>",""+en_id);
//        //打电话
//
//        tel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                Uri data = Uri.parse("tel:" + phone);
//                intent.setData(data);
//                startActivity(intent);
//            }
//        });

        //拿到传递过来的ID
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            postid = bundle.getInt("positionid");
            recuitPersonID = bundle.getInt("recuitPersonId");
            Log.i("postid===>", "" + postid);
            Log.i("recuitPersonID/*/*/*/>", "" + recuitPersonID);
        }
        checkPermisson();


        new Thread(new Runnable() {
            @Override
            public void run() {
                getseekpostdetails();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                bringData();
            }
        }).start();


    }

    int recuitPersonID;
    int postid;
    int status;
    String message;
    String pay;
    String base_pay;
    String obligation;
    String take_office_status;
    String sex_type;
    String enducation_type;
    String enterprise_nama;
    String enterprise_imag;
    int isapprove;
    String industr;
    int enterprise_id;
    String direct_camp;
    String district;
    int recuit_person_id;//招聘人的ID
    String post_type;
    String experience;
    int person_num;
    String aged;

    public void getseekpostdetails() {
        try {
            URL url = new URL("http://10.0.2.2/index.php/home/index/seekpostdetails?" + "postid=" + postid);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                StringBuilder stringBuilder = new StringBuilder();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String s;
                while ((s = bufferedReader.readLine()) != null) {
                    stringBuilder.append(s);
                }
                String date = stringBuilder.toString();
//                Log.i("*-*-*-*-*-*-",""+date);
                JSONObject jsonObject = new JSONObject(date);
                status = jsonObject.getInt("status");
                message = jsonObject.getString("message");
                JSONArray jsonArray = jsonObject.getJSONArray("postdetailsdata");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    post_type = object.getString("post_type");
                    experience = object.getString("experience");
                    person_num = object.getInt("person_num");
                    aged = object.getString("age");
                    pay = object.getString("pay");
                    base_pay = object.getString("base_pay");
                    obligation = object.getString("obligation");
                    take_office_status = object.getString("take_office_status");
                    sex_type = object.getString("sex_type");
                    enducation_type = object.getString("enducation_type");
                    enterprise_nama = object.getString("enterprise_name");
                    enterprise_imag = object.getString("enterprise_image");
                    isapprove = object.getInt("isapprove");
                    industr = object.getString("industry");
                    enterprise_id = object.getInt("enterprise_id");
                    direct_camp = object.getString("direct_camp");
                    district = object.getString("district");
                    recuit_person_id = object.getInt("recuit_person_id");//招聘人的ID
                    Log.i("recuit_person_id", "" + recuit_person_id);
                }
                handler.sendEmptyMessage(0);
            } else {
                Log.i("getResponseCode()", "" + httpURLConnection.getResponseCode());
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
    public void checkPermisson() {
        if (Build.VERSION.SDK_INT >= 23) {
            ActivityCompat.requestPermissions(this,//上下文
                    new String[]{
                            Manifest.permission.CALL_PHONE},//权限数组
                    1001);
        }
    }

    String recuitNamePost;
    String recuitratio;

    public void bringData() {
        String personUrl = "http://10.0.2.2/index.php/home/index/bringperson?recuitpersonid="
                + recuitPersonID;
        Log.i("personUrl", "" + personUrl);
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(personUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) {
                StringBuilder stringBuilder = new StringBuilder();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String bingPerson;
                while ((bingPerson = bufferedReader.readLine()) != null) {
                    stringBuilder.append(bingPerson);
                    JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                    int status = jsonObject.getInt("status");
                    String message = jsonObject.getString("message");
                    Log.i("*-9*-*-*-*-*", "" + stringBuilder.toString());
                    if (status == 200) {
                        JSONArray jsonArray = jsonObject.getJSONArray("recuitpersondata");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);

                            String recuitPersonImage = object.getString("recuit_person_image");
                            personIamgUrl = recuitPersonImage;
                            DownUrl.down(persomImage,personIamgUrl);
                            String recuitPersonName = object.getString("recuit_person_name");
                            String recuitPersonPost = object.getString("recuit_person_post");
                            recuitNamePost = recuitPersonName + "/" + recuitPersonPost;
                            double sendsum = object.getInt("sum(feedback.sendsum)");
                            double feedbacksum = object.getInt("sum(feedback.feedbacksum)");
                            int ratio = (int) ((feedbacksum / sendsum )* 100);
                            Log.i("ratio====>",""+sendsum+" "+feedbacksum+" "+ratio);
                            recuitratio = ratio + "%";
                        }
                        handlerPerson.sendEmptyMessage(0);
                    }
                }
            } else {
                Log.i("getResponseCode()", "" + httpURLConnection.getResponseCode());
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
     * 动态权限的回调函数
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            position.setText(post_type);//职位名称
            money.setText(pay);//薪水
            jinyan.setText(experience);//经验
            education.setText(enducation_type);//学历
            people_numb.setText("" + person_num);//招收人数
            age.setText(aged);//年龄
            sex.setText(sex_type);//性别
//            graduate.setText();//招收标准
//            site.setText();//地址
            site_xiang.setText(district);//详细地址
            enterprise_name.setText(enterprise_nama);//公司名字
//            enterprise_image.setImageResource();//公司标志
            industry.setText(industr);//经营范围
            zhize.setText(obligation);
            yaoqui.setText(take_office_status);

        }
    };

    Handler handlerPerson = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            pesonPost.setText(recuitNamePost);
            efficiency.setText(recuitratio);

        }
    };

    String personIamgUrl;

    //activity中加载图片

}
