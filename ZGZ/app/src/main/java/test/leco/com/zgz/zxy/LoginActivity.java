package test.leco.com.zgz.zxy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import agora.openvcall.AGApplication;
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
    ImageView login_qq;
    ImageView login_weibo;
    //返回箭头
    ImageView loginArrow;
    //清除账号内容
    ImageView clearAccount;
    // Tencent类是SDK的主要实现类，开发者可通过Tencent类访问腾讯开放的OpenAPI。
    private static Tencent tencent;
    boolean isServerSideLogin;

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
        login_qq.setOnClickListener(listener);
        login_weibo.setOnClickListener(listener);
        weiboLogin.setOnClickListener(listener);
    }
    VBRegister vbRegister;
    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.login: //登录
                    new Thread(){
                        @Override
                        public void run() {
                            Looper.prepare();
                            login();
                            Looper.loop();
                        }
                    }.start();
                    break;
                case R.id.login_arrow:  //返回上级页面
                    finish();
                    break;
                case R.id.clear_account: //清除
                    accountEdit.setText("");
                    break;
                case R.id.forget_password: //忘记密码
                    Intent intent=new Intent(LoginActivity.this, LoginForgetPassword.class);
                    startActivity(intent);
                    break;
                case R.id.regist: //注册
                    Intent intent1=new Intent(LoginActivity.this,RegistActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.login_qq: //qq登录
                    tencent = Tencent.createInstance("1105780483",getApplicationContext());
                    intent = new Intent(LoginActivity.this,HomePageActivity.class);
                    startActivity(intent);
                    /** 判断是否登陆过 */
                    if(!tencent.isSessionValid()){
                        tencent.login(LoginActivity.this,"all",loginListener);
                        isServerSideLogin = false;
                        /** 登陆过注销之后在登录 */
                    }else {
                        tencent.logout(LoginActivity.this);
                    }
                    break;
                case R.id.login_weibo:  //微博登录
                   vbRegister= new VBRegister(LoginActivity.this);
                    break;
            }
        }
    };

    IUiListener loginListener = new BaseUiListener(){
        @Override
        protected void doComplete(JSONObject values) {
            initOpenidAndToken(values);
//            updateUserInfo();
        }
    };

    private class BaseUiListener implements IUiListener {
        public void onComplete(Object response) {
            if (null == response) {
//                Util.showResultDialog(ShunbeiLogin.this, "返回为空", "登录失败");
                return;
            }
            JSONObject jsonResponse = (JSONObject) response;
            if (null != jsonResponse && jsonResponse.length() == 0) {
//                Util.showResultDialog(ShunbeiLogin.this, "返回为空", "登录失败");
                return;
            }
            doComplete((JSONObject)response);
        }

        protected void doComplete(JSONObject values) {}

        public void onError(UiError e) {
//            Util.toastMessage(ShunbeiLogin.this, "onError: " + e.errorDetail);
//            Util.dismissDialog();
        }

        public void onCancel() {
//            Util.toastMessage(ShunbeiLogin.this, "onCancel: ");
//            Util.dismissDialog();
            if (isServerSideLogin) {
                isServerSideLogin = false;
            }
        }
    }

    /** QQ登录第二步：存储token和openid */
    public  void initOpenidAndToken(JSONObject jsonObject) {
        try {
            String token = jsonObject.getString(com.tencent.connect.common.Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(com.tencent.connect.common.Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.getString(com.tencent.connect.common.Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                tencent.setAccessToken(token, expires);
                tencent.setOpenId(openId);
            }
        } catch(Exception e) {
        }
    }
    boolean isLogin;
    public void login(){
        telephone=accountEdit.getText().toString().trim();
        password=passwordEdit.getText().toString().trim();
        String httpUrl="http://192.168.7.6/index.php/home/index/login?"+"telephone="+telephone+"&password="+password;
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

        AGApplication myAppLication = (AGApplication) getApplication();
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
        login_qq = (ImageView) findViewById(R.id.login_qq);
        login_weibo = (ImageView) findViewById(R.id.login_weibo);
        weiboLogin= (ImageView) findViewById(R.id.login_weibo);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        vbRegister.callBack(requestCode,resultCode,data);
    }
}
