package test.leco.com.zgz.t;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import test.leco.com.zgz.R;
import test.leco.com.zgz.zxy.ReferCompanyActivity;

/**
 * Created by Administrator on 2016/12/0014.
 */

public class PositionDetailsActivity extends Activity{
    ImageView back; //返回上级页面
    TextView enterprise; //企业咨询
    int en_id; //企业的id
    TextView phone; //电话
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_position_details);
        back = (ImageView) findViewById(R.id.back_icon);
        enterprise = (TextView) findViewById(R.id.enterprise_consultation);
        phone = (TextView) findViewById(R.id.phone);
        //结束当前页面，返回上级页面
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //跳转到企业咨询页面
        enterprise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PositionDetailsActivity.this, ReferCompanyActivity.class);
                startActivity(intent);
            }
        });
        Intent inte = getIntent();
        en_id = inte.getIntExtra("en",-1);
        Log.i("en_id===>",""+en_id);
        //打电话
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + "");
                intent.setData(data);
                startActivity(intent);
            }
        });
    }
}
