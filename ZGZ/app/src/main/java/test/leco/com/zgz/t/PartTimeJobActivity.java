package test.leco.com.zgz.t;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import test.leco.com.zgz.R;

import static test.leco.com.zgz.R.styleable.View;

/**
 * Created by Administrator on 2016/12/0014.
 */

public class PartTimeJobActivity extends Activity {
    ImageView back; //返回上级页面
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_part_time_job);

        back = (ImageView) findViewById(R.id.back_icon);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
