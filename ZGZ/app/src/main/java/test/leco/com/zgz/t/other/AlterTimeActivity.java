package test.leco.com.zgz.t.other;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import test.leco.com.zgz.R;

/**
 * Created by Administrator on 2016/12/0020.
 */

public class AlterTimeActivity extends Activity {

    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_alter_time);

        back = (ImageView) findViewById(R.id.back_icon);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
