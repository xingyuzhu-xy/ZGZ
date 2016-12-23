package test.leco.com.zgz.t.other;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import test.leco.com.zgz.R;
import test.leco.com.zgz.zxy.Utils.CaptchaUtils;
import test.leco.com.zgz.zxy.Utils.TimerUtils;

/**
 * Created by Administrator on 2016/12/0020.
 */

public class AlterPhoneActivity extends Activity {
    ImageView back;
    TextView save;
    EditText phone,code;
    Button button;
    CaptchaUtils captchaUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_alter_phone);

        back = (ImageView) findViewById(R.id.back_icon);
        save = (TextView) findViewById(R.id.save);
        phone = (EditText) findViewById(R.id.phone);
        code = (EditText) findViewById(R.id.code);
        button = (Button) findViewById(R.id.send_yanzhengma);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captchaUtils.commint(code.getText().toString());
            }
        });
        captchaUtils = new CaptchaUtils(this,new EventHandler(){

            public void afterEvent(int event, int result, Object data) {

                switch (event) {
                    case SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE:
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            Log.i("验证码===>","验证成功");
                            Intent intent = AlterPhoneActivity.this.getIntent();
                            Bundle bundle = intent.getExtras();
                            bundle.putString("mPhone",phone.getText().toString());
                            intent.putExtras(bundle);
                            setResult(Activity.RESULT_OK,intent);
                            finish();
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
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (phone.getText().toString() != null && !phone.getText().toString().equals("")){
                    new TimerUtils(button,60*1000,1000).start();
                    captchaUtils.sendCaptcha(phone.getText().toString());
                }else {
                    Toast.makeText(AlterPhoneActivity.this,"手机号不能为空",Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
