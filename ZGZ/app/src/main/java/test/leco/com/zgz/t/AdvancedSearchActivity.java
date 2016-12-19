package test.leco.com.zgz.t;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import test.leco.com.zgz.R;

/**
 * Created by Administrator on 2016/12/0014.
 */

public class AdvancedSearchActivity extends Activity{
    Button search;//搜索
    ImageView back; //返回上级页面
    LinearLayout linearLayout; // 行业
    EditText input_position_search; //职位
    LinearLayout time_search; //发布时间
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_advanced_search);

        search = (Button) findViewById(R.id.search_btn);
        back = (ImageView) findViewById(R.id.back_icon);
        linearLayout = (LinearLayout) findViewById(R.id.hangye);
        input_position_search = (EditText) findViewById(R.id.input_position_search);
        time_search = (LinearLayout) findViewById(R.id.time_search);
        time_search.setOnClickListener(clickListener);
        search.setOnClickListener(clickListener);
        back.setOnClickListener(clickListener);
        linearLayout.setOnClickListener(clickListener);
        //输入框监听
        input_position_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    String[] time = {"一个月前","一周前","今天","不限"};
    public void createDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(AdvancedSearchActivity.this);
        dialog.setTitle("选择发布时间");
        dialog.setSingleChoiceItems(time, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(AdvancedSearchActivity.this,"你选择了"+time[which],Toast.LENGTH_LONG).show();
            }
        });
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(AdvancedSearchActivity.this,"你选择了"+which,Toast.LENGTH_LONG).show();
            }
        });
        dialog.create();
        dialog.show();
    }
    Intent intent;
    //点击事件
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.search_btn:
                    intent = new Intent(AdvancedSearchActivity.this,SearchListActivity.class);
                    startActivity(intent);
                    break;
                case R.id.back_icon:
                    finish();
                    break;
                case R.id.hangye:
                    intent = new Intent(AdvancedSearchActivity.this,IndustryInvolvedActivity.class);
                    startActivity(intent);
                    break;
                case R.id.time_search:
                    createDialog();
                    break;
            }
        }
    };
}
