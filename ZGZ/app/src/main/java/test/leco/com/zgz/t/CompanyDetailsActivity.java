package test.leco.com.zgz.t;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import test.leco.com.zgz.R;

/**
 * Created by Administrator on 2016/12/0014.
 */

public class CompanyDetailsActivity extends Activity {
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_part_time_company_details);
        findView();
    }
    
    public void findView(){
        back = (ImageView) findViewById(R.id.back_icon);
        back.setOnClickListener(onClickListener);


    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back_icon:
                    finish();
                    break;
            }
        }
    };
}
