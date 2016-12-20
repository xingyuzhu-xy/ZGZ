package test.leco.com.zgz.zxy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import test.leco.com.zgz.t.HomePageActivity;

/**
 * Created by Administrator on 2016/12/20.
 */

public class LoginActivity extends Activity {
          //账号   密码
    EditText accountEdit,passwordEdit;
    Button login;//登录
       //   忘记密码     立即注册
    TextView forgetPassword,regist;
    String telephone,password;
    int status;
    String message;
    int id;
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login_layout);
        findViewById();
        super.onCreate(savedInstanceState);
        login.setOnClickListener(listener);
    }
    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.login:
                    new Thread(){
                        @Override
                        public void run() {
                            Looper.prepare();
                            login();
                            Looper.loop();
                        }
                    }.start();
                    break;
            }
        }
    };
    public void login(){
        telephone=accountEdit.getText().toString().trim();
        password=passwordEdit.getText().toString().trim();
        String httpUrl="http://10.0.2.2/index.php/home/index/login?"+"telephone="+telephone+"&password="+password;
        try {
            URL url=new URL(httpUrl);
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
                String data =stringBuilder.toString();
                JSONObject jsonObject=new JSONObject(data);
                status=jsonObject.getInt("status");
                message=jsonObject.getString("message");
                if(status==200){
                   JSONObject jsonObject1=jsonObject.getJSONObject("result");
                    id =jsonObject1.getInt("user_id");
                    Intent intent=new Intent(LoginActivity.this, HomePageActivity.class);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this,"登录成功!",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this,""+message,Toast.LENGTH_SHORT).show();
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
        accountEdit= (EditText) findViewById(R.id.account_edittext);
        passwordEdit= (EditText) findViewById(R.id.password_edittext);
        login= (Button) findViewById(R.id.login);
        forgetPassword= (TextView) findViewById(R.id.forget_password);
        regist= (TextView) findViewById(R.id.regist);
    }
}
