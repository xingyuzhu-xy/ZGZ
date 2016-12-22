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

public class AlterExperienceActivity extends Activity {
    ImageView back;
    TextView save;
    EditText experience;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_alter_experience);

        back = (ImageView) findViewById(R.id.back_icon);
        save = (TextView) findViewById(R.id.save);
        experience = (EditText) findViewById(R.id.content);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = AlterExperienceActivity.this.getIntent();
                Bundle bundle = intent.getExtras();
                bundle.putString("mExperience",experience.getText().toString());
                intent.putExtras(bundle);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });
    }
}
