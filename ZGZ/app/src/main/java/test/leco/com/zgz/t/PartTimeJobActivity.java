package test.leco.com.zgz.t;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import test.leco.com.zgz.R;
import test.leco.com.zgz.t.adapter.PartTimeJobAdapter;
import test.leco.com.zgz.t.data.PartTimeJobItem;

/**
 * Created by Administrator on 2016/12/0014.
 */

public class PartTimeJobActivity extends Activity {
    ImageView back; //返回上级页面
    ListView listView; //兼职列表
    List<PartTimeJobItem> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_part_time_job);

        back = (ImageView) findViewById(R.id.back_icon);
        listView = (ListView) findViewById(R.id.listView);
        //返回上级页面
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getData();
        listView.setAdapter(new PartTimeJobAdapter(PartTimeJobActivity.this,list));
    }
    public void getData(){
        list = new ArrayList<PartTimeJobItem>();

        for (int i = 0; i < 10; i++){
            PartTimeJobItem partTimeJobItem = new PartTimeJobItem();
            partTimeJobItem.setPositionName("沁园工厂实习生");
            partTimeJobItem.setAddress("九龙坡区-白市驿");
            partTimeJobItem.setWorkTime("周末");
            partTimeJobItem.setSalary("3500元/月");
            list.add(partTimeJobItem);
        }
    }
}
