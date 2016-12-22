package test.leco.com.zgz.t.other;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import test.leco.com.zgz.R;

/**
 * Created by Administrator on 2016/12/0020.
 */

public class AlterTimeActivity extends Activity {
    TextView save;
    ImageView back;
    EditText year,month,data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_alter_time);

        back = (ImageView) findViewById(R.id.back_icon);
        save = (TextView) findViewById(R.id.save);
        year = (EditText) findViewById(R.id.year);
        month = (EditText) findViewById(R.id.month);
        data = (EditText) findViewById(R.id.data);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mTime = year.getText().toString().trim()+month.getText().toString().trim()+data.getText().toString().trim();
                Intent intent = AlterTimeActivity.this.getIntent();
                Bundle bundle = intent.getExtras();
                bundle.putString("mTime",mTime);
                intent.putExtras(bundle);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });
    }
}
