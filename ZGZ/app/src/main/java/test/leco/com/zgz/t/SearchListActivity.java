package test.leco.com.zgz.t;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import test.leco.com.zgz.R;
import test.leco.com.zgz.t.adapter.SearchListAdapter;
import test.leco.com.zgz.t.data.SearchListItem;

/**
 * Created by Administrator on 2016/12/0014.
 */

public class SearchListActivity extends Activity {
    ImageView back; //返回上级页面
    ListView listView;
    List<SearchListItem> list;
    Spinner position_type;
    Spinner regio;
    Spinner salary;
    String[] zhiye_sp = {"职位类别","经理0","经理1","经理2"};
    String[] regio_sp = {"地区","经理0","经理1","经理2"};
    String[] salary_sp = {"薪资","1000-3000","3000-5000","5000-8000","8000-10000","10000-15000","15000以上"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_advanced_search_list);

        back = (ImageView) findViewById(R.id.back_icon);
        position_type = (Spinner) findViewById(R.id.position_type1);
        regio = (Spinner) findViewById(R.id.regio1);
        salary = (Spinner) findViewById(R.id.salary1);
        listView = (ListView) findViewById(R.id.listView);

        getData();
        listView.setAdapter(new SearchListAdapter(this,list));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchListActivity.this,PositionDetailsActivity.class);
                startActivity(intent);
            }
        });


        //点击结束当前页面，返回上级页面
        back.setOnClickListener(onClickListener);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SearchListActivity.this,android.R.layout.simple_spinner_dropdown_item,zhiye_sp);
        position_type.setAdapter(arrayAdapter);
        position_type.setOnItemSelectedListener(onItemSelectedListener);
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(SearchListActivity.this,android.R.layout.simple_spinner_dropdown_item,regio_sp);
        regio.setAdapter(arrayAdapter1);
        regio.setOnItemSelectedListener(onItemSelectedListener);
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(SearchListActivity.this,android.R.layout.simple_spinner_dropdown_item,salary_sp);
        salary.setAdapter(arrayAdapter2);
        salary.setOnItemSelectedListener(onItemSelectedListener);
        //点击结束当前页面，返回上级页面
        back.setOnClickListener(onClickListener);
//        position_type.setOnClickListener(onClickListener);
    }
    String shijian_time;
    String[] time = {"一个月前","一周前","今天","不限"};
    public void createDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(SearchListActivity.this);
        dialog.setTitle("选择发布时间");
        dialog.setSingleChoiceItems(time, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(AdvancedSearchActivity.this,"你选择了"+time[which],Toast.LENGTH_LONG).show();
                shijian_time = time[which];
            }
        });
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(AdvancedSearchActivity.this,"你选择了"+which,Toast.LENGTH_LONG).show();
            }
        });
        dialog.create();
        dialog.show();
    }


    public void getData(){
        list = new ArrayList<SearchListItem>();
        for (int i = 0; i < 10; i++){
            SearchListItem searchListItem = new SearchListItem();
            searchListItem.setCompanyName("飞成机密磨具");
            searchListItem.setTiem("今天");
            searchListItem.setPositionName("注塑磨具师傅");
            searchListItem.setAddress("渝中区");
            searchListItem.setEducation("大专/2年及以上");
            searchListItem.setSalary("3500-6500/月");
            list.add(searchListItem);
        }
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.salary:
                    break;
                case R.id.back_icon:
                    finish();
                    break;
            }
        }
    };
    AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //parent.getItemAtPosition(position)
            Log.i("======",""+parent.getItemAtPosition(position));
            switch (view.getId()){
                case R.id.position_type1:
                    Toast.makeText(SearchListActivity.this,"您选择了"+zhiye_sp[position],Toast.LENGTH_SHORT).show();
                    break;
                case R.id.regio1:
                    break;
                case R.id.salary1:
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    
}
