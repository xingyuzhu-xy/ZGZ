package test.leco.com.zgz.t;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import test.leco.com.zgz.R;

/**
 * Created by Administrator on 2016/12/0014.
 */

public class PositionDetailsActivity extends Activity{
    ImageView back; //返回上级页面
    TextView enterprise; //企业咨询
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_position_details);

        back = (ImageView) findViewById(R.id.back_icon);
        enterprise = (TextView) findViewById(R.id.enterprise_consultation);
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

            }
        });
    }
}
