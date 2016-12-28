package test.leco.com.zgz.zxy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import test.leco.com.zgz.t.data.MyAppLication;
import test.leco.com.zgz.zxy.vb.VBRegister;

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
    //返回箭头
    ImageView loginArrow;
    //清除账号内容
    ImageView clearAccount;
    ImageView weiboLogin;
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login_layout);
        findViewById();
        isLogin = false;
        super.onCreate(savedInstanceState);
        login.setOnClickListener(listener);
        loginArrow.setOnClickListener(listener);
        clearAccount.setOnClickListener(listener);
        forgetPassword.setOnClickListener(listener);
        regist.setOnClickListener(listener);
        weiboLogin.setOnClickListener(listener);
    }
    VBRegister vbRegister;
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
                case R.id.login_arrow:
                    finish();
                    break;
                case R.id.clear_account:
                    accountEdit.setText("");
                    break;
                case R.id.forget_password:
                    Intent intent=new Intent(LoginActivity.this, LoginForgetPassword.class);
                    startActivity(intent);
                    break;
                case R.id.regist:
                    Intent intent1=new Intent(LoginActivity.this,RegistActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.login_weibo:
                   vbRegister= new VBRegister(LoginActivity.this);
                    break;
            }
        }
    };
    boolean isLogin;
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
                    isLogin = true;
                    JSONObject jsonObject1=jsonObject.getJSONObject("result");
                    id =jsonObject1.getInt("user_id");
                    sharePreferences();
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

    public void sharePreferences(){
        SharedPreferences sharedPreferences=getSharedPreferences("ZGZ", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("user_id",id);
        editor.putBoolean("isLogin",isLogin);
        editor.commit();

        MyAppLication myAppLication = (MyAppLication) getApplication();
        myAppLication.setLogin(isLogin);
        myAppLication.setId(id);
    }

    public void findViewById(){
        accountEdit= (EditText) findViewById(R.id.account_edittext);
        passwordEdit= (EditText) findViewById(R.id.password_edittext);
        login= (Button) findViewById(R.id.login);
        forgetPassword= (TextView) findViewById(R.id.forget_password);
        regist= (TextView) findViewById(R.id.regist);
        loginArrow= (ImageView) findViewById(R.id.login_arrow);
        clearAccount= (ImageView) findViewById(R.id.clear_account);
        weiboLogin= (ImageView) findViewById(R.id.login_weibo);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        vbRegister.callBack(requestCode,resultCode,data);
    }
}
