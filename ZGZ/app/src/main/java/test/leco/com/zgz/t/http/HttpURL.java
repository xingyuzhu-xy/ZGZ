package test.leco.com.zgz.t.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/12/26.
 */
public class HttpURL {

    public static void sendRequest(final String address,
                                   final HttpCallbackListener listener){
        new Thread(){
            @Override
            public void run() {
                HttpURLConnection httpURLConnection = null;
                try {
                    URL url = new URL(address);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(5000);
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                    StringBuilder stringBuilder = new StringBuilder();
                    String data;
                    while ((data = bufferedReader.readLine()) != null){
                        stringBuilder.append(data);
                    }
                    if (listener != null){
                        listener.onFinish(stringBuilder.toString());
                    }
                } catch (Exception e) {
                    if (listener != null){
                        listener.onError(e);
                    }
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
