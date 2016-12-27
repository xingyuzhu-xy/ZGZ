package test.leco.com.zgz.zxy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import test.leco.com.zgz.R;
import test.leco.com.zgz.t.other.AlterPhoneActivity;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MySysSettingActivity extends Activity {
    ImageView sysArrow;
    //修改密码
    LinearLayout setPassword;
    LinearLayout phoneNumb; //绑定手机
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_systemset_layout);
        sysArrow= (ImageView) findViewById(R.id.sys_arrow);
        setPassword= (LinearLayout) findViewById(R.id.set_password);
        phoneNumb = (LinearLayout) findViewById(R.id.phoneNumb);
        sysArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MySysSettingActivity.this,SetNewPasswordActivity.class);
                startActivity(intent);
            }
        });
        phoneNumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MySysSettingActivity.this,AlterPhoneActivity.class);
                startActivity(intent);
            }
        });
        super.onCreate(savedInstanceState);
    }
}
