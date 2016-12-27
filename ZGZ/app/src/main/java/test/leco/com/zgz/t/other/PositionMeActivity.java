package test.leco.com.zgz.t.other;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import test.leco.com.zgz.R;
import test.leco.com.zgz.t.PositionDetailsActivity;
import test.leco.com.zgz.t.adapter.MePositionAdapter;
import test.leco.com.zgz.t.data.MePositionItem;

/**
 * Created by Administrator on 2016/12/0019.
 */

public class PositionMeActivity extends Activity {
    ImageView back;
    ListView listView;
    List<MePositionItem> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_position_me);
        listView = (ListView) findViewById(R.id.listView);
        back = (ImageView) findViewById(R.id.back_icon);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getData();
        listView.setAdapter(new MePositionAdapter(this,list));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PositionMeActivity.this,PositionDetailsActivity.class);
                startActivity(intent);
            }
        });
    }
    public void getData(){
        list = new ArrayList<MePositionItem>();

        for (int i = 0; i< 10; i++){
            MePositionItem mePositionItem = new MePositionItem();
            mePositionItem.setCompanyName("微跑科技有限公司");
            mePositionItem.setAddress("渝中区-石油路");
            mePositionItem.setPositionName("安卓开发学徒");
            mePositionItem.setTime("2016-12-15");
            list.add(mePositionItem);
        }
    }
}