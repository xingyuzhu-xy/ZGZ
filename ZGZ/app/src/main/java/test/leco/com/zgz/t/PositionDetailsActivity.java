package test.leco.com.zgz.t;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import test.leco.com.zgz.R;
import test.leco.com.zgz.zxy.ReferCompanyActivity;

/**
 * Created by Administrator on 2016/12/0014.
 */

public class PositionDetailsActivity extends Activity{
    ImageView back; //返回上级页面
    TextView enterprise; //企业咨询
    Intent intent;
    RelativeLayout tel; //打电话
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_position_details);
        back = (ImageView) findViewById(R.id.back_icon);
        enterprise = (TextView) findViewById(R.id.enterprise_consultation);
        tel = (RelativeLayout) findViewById(R.id.tel);
        //结束当前页面，返回上级页面
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        enterprise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(PositionDetailsActivity.this, ReferCompanyActivity.class);
                startActivity(intent);
            }
        });
        final String phone = "15823903420";
        //打电话
        tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phone));
                startActivity(intent);
            }
        });
    }
}
