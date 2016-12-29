package test.leco.com.zgz.zxy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import test.leco.com.zgz.R;

/**
 * Created by Administrator on 2016/12/0016.
 */

public class ReferCompanyActivity extends Activity {
    ImageView referArrow;
    LinearLayout qingkong; //清空输入的内容
    EditText edt; //咨询企业的具体问题
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer_company_layout);
        referArrow= (ImageView) findViewById(R.id.refer_arrow);
        qingkong = (LinearLayout) findViewById(R.id.qingkong);
        edt = (EditText) findViewById(R.id.edt);
        referArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        qingkong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.setText("");
            }
        });
    }
}
