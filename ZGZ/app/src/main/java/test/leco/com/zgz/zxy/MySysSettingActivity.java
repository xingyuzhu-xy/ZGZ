package test.leco.com.zgz.zxy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import test.leco.com.zgz.R;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MySysSettingActivity extends Activity {
    ImageView sysArrow;
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_systemset_layout);
        sysArrow= (ImageView) findViewById(R.id.sys_arrow);
        sysArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        super.onCreate(savedInstanceState);
    }
}
