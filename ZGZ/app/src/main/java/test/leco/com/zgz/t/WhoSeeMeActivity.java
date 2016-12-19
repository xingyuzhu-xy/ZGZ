package test.leco.com.zgz.t;

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
import test.leco.com.zgz.t.adapter.WhoSeeMeAdapter;
import test.leco.com.zgz.t.data.WhoSeeMeItem;

/**
 * Created by Administrator on 2016/12/0014.
 */

public class WhoSeeMeActivity extends Activity {
    ImageView back;//返回上级页面
    ListView listView; //谁看过我的列表显示
    List<WhoSeeMeItem> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_who_see_me);
        back = (ImageView) findViewById(R.id.back_icon);
        listView = (ListView) findViewById(R.id.listView);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //添加适配器
        getData();
        listView.setAdapter(new WhoSeeMeAdapter(this,list));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(WhoSeeMeActivity.this,EnterpriseDetailsActivity.class);
                startActivity(intent);
            }
        });
    }
    public void getData(){
        list = new ArrayList<WhoSeeMeItem>();

        WhoSeeMeItem whoSeeMeItem = new WhoSeeMeItem();
        whoSeeMeItem.setCompanyName("文林创意传媒");
        whoSeeMeItem.setTime("今天");
        list.add(whoSeeMeItem);

        WhoSeeMeItem whoSeeMeItem1 = new WhoSeeMeItem();
        whoSeeMeItem1.setCompanyName("飞成精密模具");
        whoSeeMeItem1.setTime("2016-10-18");
        list.add(whoSeeMeItem1);

        WhoSeeMeItem whoSeeMeItem2 = new WhoSeeMeItem();
        whoSeeMeItem2.setCompanyName("外滩摩配");
        whoSeeMeItem2.setTime("2016-9-10");
        list.add(whoSeeMeItem2);

        WhoSeeMeItem whoSeeMeItem3 = new WhoSeeMeItem();
        whoSeeMeItem3.setCompanyName("翰昌教育");
        whoSeeMeItem3.setTime("2016-9-10");
        list.add(whoSeeMeItem3);
    }
}
