package test.leco.com.zgz.t.other;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import test.leco.com.zgz.R;

/**
 * Created by Administrator on 2016/12/0019.
 */

public class AlterPlaceActivity extends Activity {
    ImageView back;
    //123123
    //21312312
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_alter_place);
        back = (ImageView) findViewById(R.id.back_icon);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
