package test.leco.com.zgz.zxy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import test.leco.com.zgz.R;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MyAdviceActivity extends Activity {
    ImageView adviceArrow;
    protected void onCreate(Bundle savedInstanceState) {
         setContentView(R.layout.acitivity_my_advice_layout);
        adviceArrow= (ImageView) findViewById(R.id.advice_arrow);
        super.onCreate(savedInstanceState);
        adviceArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
