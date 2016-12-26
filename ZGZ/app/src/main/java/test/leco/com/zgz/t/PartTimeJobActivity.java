package test.leco.com.zgz.t;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

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
    Spinner work_area;
    Spinner zhiwei_choose;
    Spinner push_time;
    String[] work_area_sp = {"工作地点","经理0","经理1","经理2"};
    String[] zhiwei_choose_sp = {"职位选择","经理0","经理1","经理2"};
    String[] push_time_sp = {"刷新时间","10秒","30秒","1分钟","2分钟","10分钟"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_part_time_job);

        back = (ImageView) findViewById(R.id.back_icon);
        listView = (ListView) findViewById(R.id.listView);
        work_area = (Spinner) findViewById(R.id.work_area);
        zhiwei_choose = (Spinner) findViewById(R.id.zhiwei_choose);
        push_time = (Spinner) findViewById(R.id.push_time);
        //返回上级页面
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getData();
        listView.setAdapter(new PartTimeJobAdapter(PartTimeJobActivity.this,list));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PartTimeJobActivity.this,CompanyDetailsActivity.class);
                startActivity(intent);
            }
        });
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(PartTimeJobActivity.this,android.R.layout.simple_spinner_dropdown_item,work_area_sp);
        work_area.setAdapter(arrayAdapter);
        work_area.setOnItemSelectedListener(onItemSelectedListener);
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(PartTimeJobActivity.this,android.R.layout.simple_spinner_dropdown_item,zhiwei_choose_sp);
        zhiwei_choose.setAdapter(arrayAdapter1);
        zhiwei_choose.setOnItemSelectedListener(onItemSelectedListener);
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(PartTimeJobActivity.this,android.R.layout.simple_spinner_dropdown_item,push_time_sp);
        push_time.setAdapter(arrayAdapter2);
        push_time.setOnItemSelectedListener(onItemSelectedListener);
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
    AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
}
