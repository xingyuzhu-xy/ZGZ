package test.leco.com.zgz.t;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import test.leco.com.zgz.R;

/**
 * Created by Administrator on 2016/12/0014.
 */

public class EnterpriseDetailsActivity extends Activity {
    ImageView back; //返回上级页面
    RelativeLayout consultation,care; // 咨询企业、关注企业

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_enterprise_details);
        findView();
    }

    public void findView(){
        back = (ImageView) findViewById(R.id.back_icon);
        consultation = (RelativeLayout) findViewById(R.id.enterprise_consultation); //咨询企业
        care = (RelativeLayout) findViewById(R.id.care_enterprise); //关注企业
        //添加点击事件
        back.setOnClickListener(clickListener);
        consultation.setOnClickListener(clickListener);
        care.setOnClickListener(clickListener);
    }
    //点击事件
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back_icon:
                    finish();
                    break;
                case R.id.enterprise_consultation:
                    break;
                case R.id.care_enterprise:
                    break;
            }
        }
    };
}
