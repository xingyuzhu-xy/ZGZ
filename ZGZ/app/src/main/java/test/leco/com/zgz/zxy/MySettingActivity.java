package test.leco.com.zgz.zxy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import test.leco.com.zgz.R;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MySettingActivity extends Activity {
                  //我的简历，投递记录，谁下载了，面试通知，我的收藏，我的关注，系统设置，反馈
    RelativeLayout resume,deliver,download,message,collect,attention,setting,advice;
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_setting_layout);
        super.onCreate(savedInstanceState);
        resume= (RelativeLayout) findViewById(R.id.my_resume);
        deliver= (RelativeLayout) findViewById(R.id.my_deliver);
        download= (RelativeLayout) findViewById(R.id.my_download);
        message= (RelativeLayout) findViewById(R.id.my_message);
        collect= (RelativeLayout) findViewById(R.id.my_collcet);
        attention= (RelativeLayout) findViewById(R.id.my_attention);
        setting= (RelativeLayout) findViewById(R.id.my_setting);
        advice= (RelativeLayout) findViewById(R.id.my_advice);
        resume.setOnClickListener(listener);
        deliver.setOnClickListener(listener);
        download.setOnClickListener(listener);
        message.setOnClickListener(listener);
        collect.setOnClickListener(listener);
        attention.setOnClickListener(listener);
        setting.setOnClickListener(listener);
        advice.setOnClickListener(listener);
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.my_resume:
                    intent=new Intent(MySettingActivity.this,MyResumeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_deliver:
                    intent=new Intent(MySettingActivity.this,MyDeliverActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_download:
                    intent=new Intent(MySettingActivity.this,MyDownloadActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_message:
                    intent=new Intent(MySettingActivity.this,MyMessageActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_collcet:
                    intent=new Intent(MySettingActivity.this,MyCollectActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_attention:
                    intent=new Intent(MySettingActivity.this,MyAttentionActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_setting:
                    intent=new Intent(MySettingActivity.this,MySysSettingActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_advice:
                    intent=new Intent(MySettingActivity.this,MyAdviceActivity.class);
                    startActivity(intent);
                    break;

            }
        }
    };
}
