package test.leco.com.zgz.zxy.Utils;

import android.content.Context;
import android.util.Log;

import cn.smssdk.EventHandler;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by Administrator on 2016/12/19.
 */

public class CaptchaUtils {
    //短信验证的key和secret(mob.com)
    private static final String APPKEY = "1a0432043a7c8";
    private static final String APPSECRET = "87ff7861a27754b9a2aa961127471957d";
    private static final String COUNTRY = "+86";
    Context context;
    String phoneNumb;
    public CaptchaUtils(Context context){
        this.context = context;

        init();
    }

    public boolean bool = false;

    public void init(){
        SMSSDK.initSDK(context,APPKEY,APPSECRET);
        EventHandler eh=new EventHandler(){

            public void afterEvent(int event, int result, Object data) {

                switch (event) {
                    case SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE:
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            Log.i("验证码===>","验证成功");
                            bool = true;
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
        };
        SMSSDK.registerEventHandler(eh); //注册短信回调
    }
    public void sendCaptcha( String phoneNumb){ //发送验证码
        this.phoneNumb=phoneNumb;
        SMSSDK.getVerificationCode(COUNTRY, phoneNumb, new OnSendMessageHandler() {

            public boolean onSendMessage(String country, String phone) {
                return false;
            }
        });

    }
    public void commint(String code){//从用户手机中上传验证码到服务器
        SMSSDK.submitVerificationCode(COUNTRY,phoneNumb,code);
    }

    public void cancellation(){//注销验证码请求
        SMSSDK.unregisterEventHandler(new EventHandler());
    }




}
