package test.leco.com.zgz.zxy.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/12/30.
 */
public class DownUrl extends AsyncTask<String, Void, Bitmap> {
    //异步加载，参数自定
    private ImageView image;

    @Override
    protected void onPostExecute(Bitmap bitmap) {//网络请求后处理
        super.onPostExecute(bitmap);
        Log.i("=====s=", bitmap + "");
        if (bitmap != null) {
            image.setImageBitmap(bitmap);
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
        Bitmap bitmap = null;
        URL personIamgURL = null;
        try {
            personIamgURL = new URL(params[0]);
            HttpURLConnection conn = (HttpURLConnection) personIamgURL.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream input = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(input);
            input.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static void down(ImageView image, String url) {
        DownUrl loadImage = new DownUrl();
        loadImage.image = image;
        loadImage.execute(url);
    }
}
