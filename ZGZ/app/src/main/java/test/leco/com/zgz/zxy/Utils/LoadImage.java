package test.leco.com.zgz.zxy.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.telecom.Connection;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import test.leco.com.zgz.t.http.HttpURL;

/**
 * Created by Administrator on 2016/12/28.
 */
public class LoadImage extends AsyncTask<String,Void,Bitmap>{//异步加载，参数自定

    private HeadImage image;



    @Override
    protected void onPostExecute(Bitmap bitmap) {//网络请求后处理
        super.onPostExecute(bitmap);
        Log.i("=====s=",bitmap+"");
        if(bitmap!=null){
            image.setBitmap(bitmap);
        }
    }

    @Override
    protected void onPreExecute() {//初始化
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Void... values) {//进度表更新
        super.onProgressUpdate(values);
    }

    @Override
    protected Bitmap doInBackground(String... params) {//耗时操作

        Bitmap bitmap=null;
        HttpURLConnection connection=null;

        try {
            connection= (HttpURLConnection) new URL(params[0]).openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.connect();

            if(connection.getResponseCode()==200){
                InputStream is=connection.getInputStream();
                bitmap=BitmapFactory.decodeStream(is);
                Log.i("=====s=",is+"");
                if(is!=null){
                    is.close();
                }
            }else {
                Log.i("=====s=","error"+"");
            }



        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(connection!=null)
            connection.disconnect();
        }


        return bitmap;
    }

    public static void load(HeadImage image,String url){
        LoadImage loadImage=new LoadImage();
        loadImage.image=image;
        loadImage.execute(url);
    }
}
