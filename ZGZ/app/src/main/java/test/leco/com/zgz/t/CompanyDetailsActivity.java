package test.leco.com.zgz.t;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import test.leco.com.zgz.R;

/**
 * Created by Administrator on 2016/12/0014.
 */

public class CompanyDetailsActivity extends Activity {
    ImageView back;
    GridView gridView; //星期下的选择项
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_part_time_company_details);
        findView();
    }
    
    public void findView(){
        back = (ImageView) findViewById(R.id.back_icon);
        gridView = (GridView) findViewById(R.id.gr);
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
