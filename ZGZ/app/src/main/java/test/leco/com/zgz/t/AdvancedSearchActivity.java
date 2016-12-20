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
import android.widget.TextView;
import android.widget.Toast;

import test.leco.com.zgz.R;
import test.leco.com.zgz.t.other.AlterPlaceActivity;

/**
 * Created by Administrator on 2016/12/0014.
 */

public class AdvancedSearchActivity extends Activity{
    Button search;//搜索
    ImageView back; //返回上级页面
    LinearLayout linearLayout; // 行业
    EditText input_position_search; //职位
    LinearLayout time_search; //发布时间
    LinearLayout search_area; //地区
    TextView area_text;
    String area;
    TextView shijian;
    private static final int SIGNATURE_REQUESTCODE = 1010;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_advanced_search);

        search = (Button) findViewById(R.id.search_btn);
        back = (ImageView) findViewById(R.id.back_icon);
        linearLayout = (LinearLayout) findViewById(R.id.hangye);
        input_position_search = (EditText) findViewById(R.id.input_position_search);
        time_search = (LinearLayout) findViewById(R.id.time_search);
        search_area = (LinearLayout) findViewById(R.id.search_area);
        area_text = (TextView) findViewById(R.id.area_text);
        shijian = (TextView) findViewById(R.id.time);

        search_area.setOnClickListener(clickListener);
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
    String shijian_time;
    String[] time = {"一个月前","一周前","今天","不限"};
    public void createDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(AdvancedSearchActivity.this);
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
                shijian.setText(shijian_time);
                shijian.setTextColor(getResources().getColor(R.color.light_black));
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
                case R.id.search_area:
                    intent = new Intent(AdvancedSearchActivity.this,AlterPlaceActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("ischeck", "fause");
                    intent.putExtras(bundle);//在用bundle 来Put数据后必需再Put到intent里面，否则没有传递
                    startActivityForResult(intent,SIGNATURE_REQUESTCODE);//第二个参数为请求码
                    break;
                case R.id.time_search:
                    createDialog();
                    break;
            }
        }
    };

    /*当需要从第二个页面获得数据返回的时候，重写该方法*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //请求码      ，    结果码       ， intent对象（包含绑定的数据）
        switch (resultCode){
            case SIGNATURE_REQUESTCODE:
                if (data != null) {//判断是否为空是未来避免强制返回时，第二个页面没有返回数据而导致的空指针
                    Log.i("BRITH_RESULTCODE", data.toString());
                    Bundle bundle = data.getExtras();
                    area = bundle.getString("ischeck");//第二个页面新传的数据
                    area_text.setText(area);
                    super.onActivityResult(requestCode, resultCode, data);
                }
                break;
        }
    }
}
