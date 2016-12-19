package test.leco.com.zgz.zxy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import test.leco.com.zgz.R;

/**
 * Created by Administrator on 2016/12/0016.
 */

public class ReferCompanyActivity extends Activity {
    ImageView referArrow;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        referArrow= (ImageView) findViewById(R.id.refer_arrow);
        setContentView(R.layout.activity_refer_company_layout);
        referArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
