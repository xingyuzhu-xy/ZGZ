package test.leco.com.zgz.zxy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import test.leco.com.zgz.R;
import test.leco.com.zgz.t.HomePageActivity;
import test.leco.com.zgz.zxy.Utils.CaptchaUtils;
import test.leco.com.zgz.zxy.Utils.TimerUtils;
import test.leco.com.zgz.zxy.http.RegisterHttp;

/**
 * Created by Administrator on 2016/12/19.
 */

public class RegistActivity extends Activity {
    EditText phoneNumb;//手机号
    EditText password;//密码
    EditText yanzhengma;//验证码
    Button sendCode;//获取验证码
    Button register;//注册
    CaptchaUtils captchaUtils;//短信验证
    RegisterHttp registerHttp;//注册接口
    Intent intent;
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_regist_layout);
        findViewById();
        captchaUtils=new CaptchaUtils(this,new EventHandler(){
            public void afterEvent(int event, int result, Object data) {
                switch (event) {
                    case SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE:
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            Log.i("验证码===>","验证成功");
                            new Thread() {
                                @Override
                                public void run() {
                                    getData();
                                }
                            }.start();
                        } else {
                            Log.i("验证码===>","验证失败");
                        }
                        break;
                    case SMSSDK.EVENT_GET_VERIFICATION_CODE:
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            Log.i("验证码===>","获取验证码成功");
                        } else {
                            Log.i("验证码===>","获取验证码失败");
                        }
                        break;
                }
            }
        });
        super.onCreate(savedInstanceState);
        register.setOnClickListener(listener);
        sendCode.setOnClickListener(listener);
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.get_yanzhengma://获取验证码
                    new TimerUtils(sendCode,1000*60,1000).start();//开始倒计时
                    captchaUtils.sendCaptcha(phoneNumb.getText().toString().trim());
                    break;
                case R.id.regist://立即注册
                    captchaUtils.commint(yanzhengma.getText().toString().trim());
                    captchaUtils.cancellation();
                    break;
            }
        }
    };
    public void findViewById(){
        phoneNumb= (EditText) findViewById(R.id.phonenumb);
        password= (EditText) findViewById(R.id.password);
        yanzhengma= (EditText) findViewById(R.id.yanzhengma);
        sendCode= (Button) findViewById(R.id.get_yanzhengma);
        register= (Button) findViewById(R.id.regist);
    }
    public void getData(){
        Message message=new Message();
        try {
            registerHttp=new RegisterHttp(phoneNumb.getText().toString().trim(),password.getText().toString().trim());
            String data= registerHttp.getStringBuilder().toString().trim();//json数据
            JSONObject jsonObject=new JSONObject(data);
            int status=jsonObject.getInt("status");
            if(status==200){//注册成功
                Log.i("123123","123");
                message.arg1=200;
                handler.sendMessage(message);
                intent=new Intent(RegistActivity.this, HomePageActivity.class);
                startActivity(intent);
            }else if(status==403){
                message.arg1=403;
                handler.sendMessage(message);
            }else if(status==405){
                message.arg1=405;
                handler.sendMessage(message);
            }else if(status==406){
                message.arg1=406;
                handler.sendMessage(message);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.arg1==200){
                Toast.makeText(RegistActivity.this,"注册成功!",Toast.LENGTH_SHORT).show();
            }else if(msg.arg1==403){
                Toast.makeText(RegistActivity.this,"手机号已存在",Toast.LENGTH_SHORT).show();
            }else if(msg.arg1==405){
                Toast.makeText(RegistActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
            }else if(msg.arg1==406){
                Toast.makeText(RegistActivity.this,"手机号不能为空",Toast.LENGTH_SHORT).show();
            }
        }
    };
}
