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
 * Created by Administrator on 2016/12/0019.
 */

public class AlterNameActivity extends Activity {
    ImageView back;
    EditText userName;
    TextView save;
    //userBean bean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_alter_name);
        //bean = getIntent().getSerializableExtra("name");
        back = (ImageView) findViewById(R.id.back_icon);
        save = (TextView) findViewById(R.id.save);
        userName = (EditText) findViewById(R.id.userName);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mName = userName.getText().toString().trim();
                if(mName != null && mName !="") {
                    Intent intent = AlterNameActivity.this.getIntent();
                    Bundle bundle = intent.getExtras();
                    bundle.putString("userName",mName);
                    intent.putExtras(bundle);
                    setResult(Activity.RESULT_OK,intent);
                    finish();
                }
            }
        });

    }
}
