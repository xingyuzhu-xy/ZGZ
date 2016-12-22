package test.leco.com.zgz.zxy.Utils;

import android.content.Context;

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
    public CaptchaUtils(Context context,EventHandler eh){
        this.context = context;
        init(eh);
    }


    public void init(EventHandler eh){
        SMSSDK.initSDK(context,APPKEY,APPSECRET);

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
