package test.leco.com.zgz.t;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import test.leco.com.zgz.R;
import test.leco.com.zgz.zxy.Utils.ShareWeiXin;

/**
 * Created by Administrator on 2016/12/0014.
 */

public class CompanyDetailsActivity extends Activity {
    ImageView back;
    TextView share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_part_time_company_details);
        findView();
    }
    
    public void findView(){
        back = (ImageView) findViewById(R.id.back_icon);
        share = (TextView) findViewById(R.id.share);
        back.setOnClickListener(onClickListener);
        share.setOnClickListener(onClickListener);

    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back_icon:
                    finish();
                    break;
                case R.id.share:
                    ShareWeiXin weiXin = new ShareWeiXin(CompanyDetailsActivity.this,"hello");
                    break;
            }
        }
    };
}
