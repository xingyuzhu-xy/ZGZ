package test.leco.com.zgz.t.other;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import test.leco.com.zgz.R;

/**
 * Created by Administrator on 2016/12/0019.
 */

public class AlterPlaceActivity extends Activity {
    ImageView back;
    TextView save;  //保存
    EditText area_city;  //市级输入框
    EditText area_sheng; //省级输入框
    String city;
    String sheng;
    private static final int SIGNATURE_REQUESTCODE = 1010;
    //123123
    //21312312
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_alter_place);
        back = (ImageView) findViewById(R.id.back_icon);
        save = (TextView) findViewById(R.id.save);
        area_sheng = (EditText) findViewById(R.id.area_sheng);
        area_city = (EditText) findViewById(R.id.area_city);

        back.setOnClickListener(onClickListener);
        save.setOnClickListener(onClickListener);

        area_sheng.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sheng = s.toString();
            }
        });
        area_city.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                city = s.toString();
            }
        });
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.back_icon:
                    finish();
                    break;
                case R.id.save:
                    if(sheng != null && city != null){
                        Intent saveIntentBtn = getIntent();
                        saveIntentBtn.putExtra("ischeck",""+sheng+city);
                        setResult(SIGNATURE_REQUESTCODE,saveIntentBtn);
                        Toast.makeText(AlterPlaceActivity.this, "传递成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(AlterPlaceActivity.this, "请填写完整", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };


}
