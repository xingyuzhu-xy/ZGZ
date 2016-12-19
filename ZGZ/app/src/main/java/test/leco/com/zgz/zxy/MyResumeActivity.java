package test.leco.com.zgz.zxy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import test.leco.com.zgz.R;
import test.leco.com.zgz.t.Z.AlterNameActivity;
import test.leco.com.zgz.t.Z.AlterPlaceActivity;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MyResumeActivity extends Activity {
    RelativeLayout name;
    RelativeLayout place;
    ImageView resumeArrow;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_resume_layout);
        findView();
        resumeArrow= (ImageView) findViewById(R.id.resume_arrow);
        resumeArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void findView(){
        name = (RelativeLayout) findViewById(R.id.resume_name);
        place = (RelativeLayout) findViewById(R.id.resume_hukou);
        name.setOnClickListener(clickListener);
        place.setOnClickListener(clickListener);
    }
    Intent intent;
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.resume_name:
                    intent = new Intent(MyResumeActivity.this, AlterNameActivity.class);
                    startActivity(intent);
                    break;
                case R.id.resume_hukou:
                    intent = new Intent(MyResumeActivity.this, AlterPlaceActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}
