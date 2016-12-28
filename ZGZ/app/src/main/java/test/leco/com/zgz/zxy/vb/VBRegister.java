package test.leco.com.zgz.zxy.vb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;

/**
 * 微博授权
 * Created by Administrator on 2016/11/0030.
 */

public class VBRegister {
    AuthInfo authInfo;
    SsoHandler mSsoHandler;

    Activity context;
    public VBRegister(Activity context){
        this.context = context;
        //创建微博授权类对象
        authInfo = new AuthInfo(context,Constants.APP_KEY,Constants.REDIRECT_URL,Constants.SCOPE);
        //认证授权
        mSsoHandler = new SsoHandler(context, authInfo);
        mSsoHandler.authorize(new AuthListener());
    }

    /**
     * 微博认证授权回调类。
     * 1.SSO 授权时，需要在 {@link onActivityResult#} 中调用 {@link SsoHandler#authorizeCallBack}
     *   后，该回调才会被执行。
     * 2. 非 SSO 授权时，当授权结束后，该回调就会被执行。
     * 当授权成功后，请保存该 access_token、expires_in、uid 等信息到 SharedPreferences 中。
     */
    class AuthListener  implements WeiboAuthListener {

        @Override
        public void onComplete(Bundle values) {
            Log.i("onComplete==>","授权成功");
            // 从 Bundle 中解析 Token
            Oauth2AccessToken mAccessToken = Oauth2AccessToken.parseAccessToken(values);
            if (mAccessToken.isSessionValid()) {
                // 保存 Token 到 SharedPreferences
                AccessTokenKeeper.writeAccessToken(context, mAccessToken);

            } else {
                // 当您注册的应用程序签名不正确时，就会收到 Code，请确保签名正确
                String code = values.getString("code", "");
            }
        }
        @Override
        public void onCancel() {
            Log.i("onCancel==>","取消授权");
        }
        @Override
        public void onWeiboException(WeiboException e) {
            e.printStackTrace();
            Log.i("onWeiboException==>","授权异常");
        }

    }

    //授权是必须在onActivityResult中执行
    public void callBack(int requestCode, int resultCode, Intent data){
        if (mSsoHandler != null){
            mSsoHandler.authorizeCallBack(requestCode,resultCode,data);
        }
    }

}
