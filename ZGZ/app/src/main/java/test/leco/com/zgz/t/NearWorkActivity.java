package test.leco.com.zgz.t;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import test.leco.com.zgz.R;

/**
 * Created by Administrator on 2016/12/0015.
 */

public class NearWorkActivity extends Activity {
    LinearLayout vocationType; //行业种类
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_near_job);

        vocationType = (LinearLayout) findViewById(R.id.vocationType);
        back = (ImageView) findViewById(R.id.back_icon);

        back.setOnClickListener(onClickListener);
        vocationType.setOnClickListener(onClickListener);
    }

    Intent intent;
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.vocationType: //行业类别
                    intent = new Intent(NearWorkActivity.this,IndustryInvolvedActivity.class);
                    startActivity(intent);
                    break;
                case R.id.back_icon:
                    finish();
                    break;
            }
        }
    };
}
