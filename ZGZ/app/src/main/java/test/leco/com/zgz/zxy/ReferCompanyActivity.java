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
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer_company_layout);
        back = (ImageView) findViewById(R.id.resume_arrow);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
