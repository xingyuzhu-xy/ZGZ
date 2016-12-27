package test.leco.com.zgz.t;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import test.leco.com.zgz.R;

/**
 * Created by Administrator on 2016/12/0015.
 */

public class NearWorkActivity extends Activity {
    LinearLayout vocationType; //行业种类
    ImageView back;
    EditText position;//职位关键词
    EditText xinshui;//最低薪资
    TextView push_time;//刷新时间
    TextView scope;//距离范围
    TextView search;//搜索
    String[] time = {"10秒","30秒","1分钟","2分钟","10分钟"};
    String[] scop ={"10公里以内","30公里以内","100公里以内","200公里以内"};
    LinearLayout linpush;
    LinearLayout linjuli;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_near_job);

        vocationType = (LinearLayout) findViewById(R.id.vocationType);
        back = (ImageView) findViewById(R.id.back_icon);
        position = (EditText) findViewById(R.id.position);
        xinshui = (EditText) findViewById(R.id.xinshui);
        push_time = (TextView) findViewById(R.id.push_time);
        scope = (TextView) findViewById(R.id.scope);
        linpush = (LinearLayout) findViewById(R.id.linpush);
        linjuli = (LinearLayout) findViewById(R.id.linjuli);
        search = (TextView) findViewById(R.id.search);

        back.setOnClickListener(onClickListener);
        vocationType.setOnClickListener(onClickListener);
        linpush.setOnClickListener(onClickListener);
        linjuli.setOnClickListener(onClickListener);
        search.setOnClickListener(onClickListener);
    }
    public void createdialogepush_time(){
        AlertDialog.Builder builder = new AlertDialog.Builder(NearWorkActivity.this);
        builder.setTitle("请设置刷新时间：");
        builder.setSingleChoiceItems(time, s, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                push_time.setText(time[which]);
                s = which;
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(AdvancedSearchActivity.this,"你选择了"+which,Toast.LENGTH_LONG).show();
            }
        });
        builder.setCancelable(false);//点击屏幕不消失
        builder.create();
        builder.show();
    }
    int s =0;
    public void createdialogescop(){
        AlertDialog.Builder builder = new AlertDialog.Builder(NearWorkActivity.this);
        builder.setTitle("请设置距离范围：");
        builder.setSingleChoiceItems(scop, s, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                scope.setText(scop[which]);
                s = which;
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(AdvancedSearchActivity.this,"你选择了"+which,Toast.LENGTH_LONG).show();
            }
        });
        builder.setCancelable(false);//点击屏幕不消失
        builder.create();
        builder.show();
    }
    Intent intent;
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.vocationType: //行业类别
                    intent = new Intent(NearWorkActivity.this,IndustryInvolvedActivity.class);
                    startActivity(intent);
                    break;
                case R.id.back_icon:
                    finish();
                    break;
                case R.id.linpush:
                    createdialogepush_time();
                    break;
                case R.id.linjuli:
                    createdialogescop();
                    break;
                case R.id.search:
                    intent = new Intent(NearWorkActivity.this,SearchListActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}
