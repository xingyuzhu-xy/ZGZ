package test.leco.com.zgz.t;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
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
    CheckBox checkBox;
    private static final int SIGNATURE_ZHIYECODE = 1010;
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
        listView.setAdapter(new InvolvedItemAdapter(this,list));
        checkBox = (CheckBox) listView.findViewById(R.id.checkbox);
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
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back_icon:
                    finish();
                    break;
                case R.id.save:
                    if(checkBox!=null){
                        Intent saveIntentBtn = getIntent();
                        saveIntentBtn.putExtra("ischeck","");
                        setResult(SIGNATURE_ZHIYECODE,saveIntentBtn);
                        Toast.makeText(InvolvedItemActivity.this, "传递成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(InvolvedItemActivity.this, "请填写完整", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
}
