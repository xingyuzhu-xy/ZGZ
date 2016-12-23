package test.leco.com.zgz.t;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_advanced_search_list);

        back = (ImageView) findViewById(R.id.back_icon);
        listView = (ListView) findViewById(R.id.listView);
        //点击结束当前页面，返回上级页面
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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
                case R.id.back_icon:
                    finish();
                    break;
            }
        }
    };


}
