package test.leco.com.zgz.t;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import test.leco.com.zgz.R;
import test.leco.com.zgz.t.adapter.InvolvedItemAdapter;
import test.leco.com.zgz.t.data.IndustryInvolvedItem;

/**
 * Created by Administrator on 2016/12/0015.
 */

public class InvolvedItemActivity extends Activity {
    ImageView back;
    ListView listView;
    List<IndustryInvolvedItem> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_involved_item_list);

        back = (ImageView) findViewById(R.id.back_icon);
        listView = (ListView) findViewById(R.id.listView);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getData();
        listView.setAdapter(new InvolvedItemAdapter(this,list));

    }

    public void getData(){
        list = new ArrayList<IndustryInvolvedItem>();
        String[] data = {"互联网/游戏/软件","互联网/移动互联/电子商务","游戏","软件","IT服务/系统集成"};
        for (int i = 0; i < data.length; i++){
            IndustryInvolvedItem industryInvolvedItem = new IndustryInvolvedItem();
            industryInvolvedItem.setType(data[i]);
            list.add(industryInvolvedItem);
        }
    }
}
