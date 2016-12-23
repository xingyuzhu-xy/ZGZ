package test.leco.com.zgz.zxy.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/12/19.
 */

public class RegisterHttp {
    String STR_URL="http://10.0.2.2/index.php/home/index/register?";
    String telephone;
    String password;
    private StringBuilder stringBuilder;
    public  StringBuilder getStringBuilder(){return stringBuilder;}
    URL url;
    public RegisterHttp(String telephone,String password) throws MalformedURLException {
        this.telephone=telephone;
        this.password=password;
        url=new URL(STR_URL+"telephone="+telephone+"&password="+password);
        stringBuilder = new StringBuilder();
        register();
    }
    public void register(){
        try {
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(2000);
            httpURLConnection.connect();
            if(httpURLConnection.getResponseCode()==httpURLConnection.HTTP_OK){
                InputStream inputStream=httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"utf-8");
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String s;
                while ((s=bufferedReader.readLine())!=null){
                    stringBuilder.append(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
