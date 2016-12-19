package test.leco.com.zgz.zxy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import test.leco.com.zgz.R;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MyResumeActivity extends Activity {

    ImageView resumeArrow;
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_resume_layout);
        resumeArrow= (ImageView) findViewById(R.id.resume_arrow);
        resumeArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        super.onCreate(savedInstanceState);
    }
}
