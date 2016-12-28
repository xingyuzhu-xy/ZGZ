package test.leco.com.zgz.zxy.Utils;

import android.content.Context;
import android.provider.Settings;
import android.widget.Toast;

import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * Created by Administrator on 2016/12/26.
 */
public class ShareWeiXin {
    private IWXAPI api;
    private Context context;
    private static final String APP_ID="wxb00245927e010dcf";

    public ShareWeiXin(Context context,String text) {
        this.context = context;
        resiter();
        share(text);
    }

    public IWXAPI getApi() {
        return api;
    }

    private void resiter(){
        api= WXAPIFactory.createWXAPI(context,APP_ID,true);
        api.registerApp(APP_ID);
    }

   private void share(String text){

        if(!api.isWXAppInstalled()){
            Toast.makeText(context,"请安装微信客服端",Toast.LENGTH_SHORT).show();
            return;
        }
        WXTextObject object=new WXTextObject();
        object.text=text;

        WXMediaMessage msg=new WXMediaMessage();
        msg.mediaObject=object;
        msg.description=text;

        SendMessageToWX.Req req=new SendMessageToWX.Req();
        req.transaction= System.currentTimeMillis()+"";
        req.message=msg;
        req.scene=SendMessageToWX.Req.WXSceneTimeline;
        api.sendReq(req);

    }



}
