package test.leco.com.zgz.t.http;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/12/0021.
 */

public class MyresumeHttp {
    String STR_URL = "http://10.0.2.2/index.php/home/index/updatausermessage?";
    String name; //姓名
    String sex; //性别
    String jiguan;// 籍贯
    int education; //学历
    int burn; //出生年月
    String position; //应聘职位
    String experience; //工作经历
    String phone; //电话
    String email; //邮箱
    int id; //当前用户id

    URL url;
    public MyresumeHttp(int id,String name,String sex,String jiguan,int education,int burn,
                        String position,String experience,String phone,String email) throws MalformedURLException {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.jiguan = jiguan;
        this.education = education;
        this.burn = burn;
        this.position = position;
        this.experience = experience;
        this.phone = phone;
        this.email = email;
        url = new URL(STR_URL+"user_id="+id+"&user_name="+name+"&user_sex="+sex+"&user_hobby="+position+
                "&user_content="+experience+"&user_education="+education+"&uesr_brithday="+burn+
                "&user_email="+email+"&user_tel="+phone+"&native="+jiguan);
        stringBuilder = new StringBuilder();
        Http();
    }
    private StringBuilder stringBuilder;

    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }
    public void Http(){
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(2000);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() ==httpURLConnection.HTTP_OK){
                InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream(),"utf-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String s;
                while ((s = bufferedReader.readLine()) != null){
                    stringBuilder.append(s);
                }
                Log.i("stringBuilder==>",stringBuilder.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
