package test.leco.com.zgz.t;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import test.leco.com.zgz.R;

/**
 * Created by Administrator on 2016/12/0014.
 */

public class AdvancedSearchActivity extends Activity{
    Button search;//搜索
    ImageView back; //返回上级页面
    LinearLayout positionLinear;//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_advanced_search);

        search = (Button) findViewById(R.id.search_btn);
        back = (ImageView) findViewById(R.id.back_icon);


        search.setOnClickListener(clickListener);
        back.setOnClickListener(clickListener);
    }
    Intent intent;
    //点击事件
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.search_btn:
                    intent = new Intent(AdvancedSearchActivity.this,SearchListActivity.class);
                    startActivity(intent);
                    break;
                case R.id.back_icon:
                    finish();
                    break;
            }
        }
    };
    
}
