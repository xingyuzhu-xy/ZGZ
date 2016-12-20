package test.leco.com.zgz.zxy;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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

/**
 * Created by Administrator on 2016/12/19.
 */

public class SetNewPasswordActivity extends Activity {
           // 旧密码      新密码     重复密码
    EditText oldPassword,newPassword,newRePassword;
    Button complete;//完成
    ImageView arrow;//返回箭头
    int status;
    String message;
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_set_new_password);
        findViewById();
        complete.setOnClickListener(listener);
        super.onCreate(savedInstanceState);
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.complete:
                 new Thread(){
                     @Override
                     public void run() {
                         Looper.prepare();
                         setNewPassword();
                         Looper.loop();
                     }
                 }.start();
                    break;
                case R.id.arrow:
                    finish();
                    break;
            }
        }
    };
    public void setNewPassword(){
        SharedPreferences sharedPreferences=getSharedPreferences("ZGZ",Activity.MODE_PRIVATE);
        Integer userId=sharedPreferences.getInt("user_id",0);
        Log.i("userId====="+userId,"");
      String oldpassword= oldPassword.getText().toString();
      String newpassword= newPassword.getText().toString();
        String newrepassword=newRePassword.getText().toString();
        newRePassword.getText().toString().trim();
        String httpURL="http://10.0.2.2/index.php/home/index/changpassword?"+"user_id="+userId+"&password="+oldpassword+
                "&newpassword="+newpassword+"&newrepassword="+newrepassword;
        Log.i("http===="+httpURL,"");
        try {
            URL url=new URL(httpURL);
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
                status=jsonObject.getInt("status");
                message=jsonObject.getString("message");
                if(status==201){
                    finish();
                    Toast.makeText(SetNewPasswordActivity.this,"修改成功！",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SetNewPasswordActivity.this,""+message,Toast.LENGTH_SHORT).show();
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
    public void findViewById(){
        oldPassword= (EditText) findViewById(R.id.old_password);
        newPassword= (EditText) findViewById(R.id.new_password);
        newRePassword= (EditText) findViewById(R.id.new_re_password);
        complete= (Button) findViewById(R.id.complete);
        arrow= (ImageView) findViewById(R.id.arrow);
    }
}
