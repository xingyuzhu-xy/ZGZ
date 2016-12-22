package test.leco.com.zgz.t;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_involved_item_list);

        back = (ImageView) findViewById(R.id.back_icon);
        listView = (ListView) findViewById(R.id.listView);
        save = (TextView) findViewById(R.id.save);

        save.setOnClickListener(onClickListener);
        back.setOnClickListener(onClickListener);

        getData();
        InvolvedItemAdapter involvedItemAdapter=new InvolvedItemAdapter(this,list);
        involvedItemAdapter.setOnItemClickListener(new InvolvedItemAdapter.ItemClickListener() {
            @Override
            public void click(int poistion,String text) {
                //Toast.makeText(InvolvedItemActivity.this,""+poistion+text,Toast.LENGTH_SHORT).show();
                zhiye = text;
            }
        });
        listView.setAdapter(involvedItemAdapter);
    }
    String[] data = {"互联网/游戏/软件","互联网/移动互联/电子商务","游戏","软件","IT服务/系统集成"};
    IndustryInvolvedItem industryInvolvedItem;
    public void getData(){
        list = new ArrayList<IndustryInvolvedItem>();
        for (int i = 0; i < data.length; i++){
            industryInvolvedItem = new IndustryInvolvedItem();
            industryInvolvedItem.setType(data[i]);
            industryInvolvedItem.setCheckBox(false);
            list.add(industryInvolvedItem);
        }
    }
    String zhiye;
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back_icon:
                    finish();
                    break;
                case R.id.save:
                    if(zhiye!=null){
                        Intent saveIntentBtn = new Intent(InvolvedItemActivity.this,AdvancedSearchActivity.class);
                        startActivity(saveIntentBtn);
                    }else {
                        Toast.makeText(InvolvedItemActivity.this, "请填写完整", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
}
