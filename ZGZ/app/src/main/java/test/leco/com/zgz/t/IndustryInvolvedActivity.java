package test.leco.com.zgz.t;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import test.leco.com.zgz.R;
import test.leco.com.zgz.t.adapter.IndustryInvolvedAdapter;
import test.leco.com.zgz.t.data.IndustryInvolvedItem;

/**
 * Created by Administrator on 2016/12/0015.
 */

public class IndustryInvolvedActivity extends Activity {
    ListView listView;
    ImageView back;
    List<IndustryInvolvedItem> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_industry_involved);

        listView = (ListView) findViewById(R.id.listView);
        back = (ImageView) findViewById(R.id.back_icon);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getData();
        listView.setAdapter(new IndustryInvolvedAdapter(this,list));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(IndustryInvolvedActivity.this,InvolvedItemActivity.class);
                startActivity(intent);
            }
        });
    }


    public void getData(){
        list = new ArrayList<IndustryInvolvedItem>();
        String[] data = {"互联网/游戏/软件","通信/计算机硬件","房地产/建筑/物业/装饰","汽车/摩托车","金融",
                "机械制造","消费品","教育/培训/专业服务","医疗/医药","广告/文化/传媒"};

        for (int i = 0; i < data.length; i++){
            IndustryInvolvedItem industryInvolvedItem = new IndustryInvolvedItem();
            industryInvolvedItem.setType(data[i]);
            list.add(industryInvolvedItem);
        }

    }
}
