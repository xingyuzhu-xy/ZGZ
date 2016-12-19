package test.leco.com.zgz.zxy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import test.leco.com.zgz.R;

/**
 * Created by Administrator on 2016/12/13.
 */

public class PositionDetailActivity extends Activity {
    TextView positionDetail,companyDetail;
    LinearLayout positionLinearlayout,companyLinearlayout;
    ImageView pdArrow;//返回箭头
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_position_detail_layout);
        positionDetail= (TextView) findViewById(R.id.position_detail);
        companyDetail= (TextView) findViewById(R.id.company_detail);
        positionLinearlayout= (LinearLayout) findViewById(R.id.position_linearlayout);
        companyLinearlayout= (LinearLayout) findViewById(R.id.company_linearlayout);
        pdArrow= (ImageView) findViewById(R.id.position_detail_arrow);
        positionDetail.setOnClickListener(listener);
        companyDetail.setOnClickListener(listener);
        super.onCreate(savedInstanceState);
        pdArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           switch (v.getId()){
               case R.id.position_detail:
                    positionLinearlayout.setVisibility(View.VISIBLE);
                   companyLinearlayout.setVisibility(View.GONE);
                   positionDetail.setBackgroundResource(R.drawable.position_detail_solid);
                   companyDetail.setBackgroundResource(R.drawable.position_detail);
                   positionDetail.setTextColor(getResources().getColor(R.color.my_setting_blue1));
                   companyDetail.setTextColor(getResources().getColor(R.color.white));
                   break;
               case R.id.company_detail:
                   positionLinearlayout.setVisibility(View.GONE);
                   companyLinearlayout.setVisibility(View.VISIBLE);
                   positionDetail.setBackgroundResource(R.drawable.position_detail);
                   companyDetail.setBackgroundResource(R.drawable.position_detail_solid);
                   positionDetail.setTextColor(getResources().getColor(R.color.white));
                   companyDetail.setTextColor(getResources().getColor(R.color.my_setting_blue1));
                   break;
           }
        }
    };
}
