package test.leco.com.zgz.t.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/12/0027.
 */

public class GetEnterpriseHttp {
    String STR_URL = "http://10.0.2.2/index.php/home/index/gethomeenterprise?";
    int en_id;
    URL url;

    public GetEnterpriseHttp(int en_id) throws MalformedURLException {
        this.en_id = en_id;
        url = new URL(STR_URL+"user_id="+en_id);
        stringBuilder = new StringBuilder();
        getHttpInform();
    }

    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }

    private StringBuilder stringBuilder;

    public void getHttpInform(){
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(2000);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() ==httpURLConnection.HTTP_OK){
                InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream(),"utf-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String s;
                while ( (s = bufferedReader.readLine()) != null){
                    stringBuilder.append(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
