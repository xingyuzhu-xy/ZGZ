package test.leco.com.zgz.t;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import test.leco.com.zgz.R;
import test.leco.com.zgz.t.data.IndustryInvolvedItem;
import test.leco.com.zgz.t.other.AlterPlaceActivity;

/**
 * Created by Administrator on 2016/12/0014.
 */

public class AdvancedSearchActivity extends Activity {
    List<IndustryInvolvedItem> list;
    Button search;//搜索
    ImageView back; //返回上级页面
    LinearLayout positionLinear;//行业选择按钮
    TextView input_position_search; //行业选择
    LinearLayout time_search; //发布时间
    LinearLayout search_area; //地区
    EditText postText;//职位输入
    EditText minMoneyEditText;//最低薪资
    EditText maxMoneyEditText;//最高薪资
    TextView area_text;
    TextView zhiye_text;
    String area;
    String zhiye;
    TextView shijian;
    private static final int SIGNATURE_REQUESTCODE = 1010;
    private static final int SIGNATURE_ZHIYECODE = 1020;
    String tingtime;

    String positionName;//行业名称
    String postName;//职位名称
    String site;//地区
    String minPay;//最低薪资
    String maxPay;//最高薪资
    String inssueTime;//发布时间

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_advanced_search);
        search = (Button) findViewById(R.id.search_btn);
        back = (ImageView) findViewById(R.id.back_icon);

        input_position_search = (TextView) findViewById(R.id.input_position_search);
        time_search = (LinearLayout) findViewById(R.id.time_search);
        search_area = (LinearLayout) findViewById(R.id.search_area);
        area_text = (TextView) findViewById(R.id.area_text);
        shijian = (TextView) findViewById(R.id.time);
        zhiye_text = (TextView) findViewById(R.id.zhiye_text);
        positionLinear = (LinearLayout) findViewById(R.id.position_linear);
        postText = (EditText) findViewById(R.id.post_text);
        minMoneyEditText = (EditText) findViewById(R.id.min_money);
        maxMoneyEditText = (EditText) findViewById(R.id.max_money);

        search_area.setOnClickListener(clickListener);
        time_search.setOnClickListener(clickListener);
        positionLinear.setOnClickListener(clickListener);

        search.setOnClickListener(clickListener);
        back.setOnClickListener(clickListener);
        postText.addTextChangedListener(postNameText);
        minMoneyEditText.addTextChangedListener(minMoneyTextWatcher);
        maxMoneyEditText.addTextChangedListener(manMoneyTextWatcher);

        site =  area_text.getText().toString();
    }

    String shijian_time = "不限";
    String[] time = {"一个月前", "一周前", "今天", "不限"};
    int which1 = 3;
    public void createTimeDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(AdvancedSearchActivity.this);
        dialog.setTitle("选择发布时间");
        dialog.setSingleChoiceItems(time, which1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(AdvancedSearchActivity.this,"你选择了"+time[which],Toast.LENGTH_LONG).show();
                shijian_time = time[which];
                which1 = which;
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
            switch (v.getId()) {
                case R.id.search_btn:
                    istime();
                    Log.i("datakjk", "" + positionName + postName + site + minPay + maxPay + inssueTime);
                    intent = new Intent(AdvancedSearchActivity.this, SearchListActivity.class);
                    startActivity(intent);
                    break;
                case R.id.back_icon:
                    finish();
                    break;
                case R.id.search_area:
                    intent = new Intent(AdvancedSearchActivity.this, AlterPlaceActivity.class);
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("ischeck", "fause");
                    intent.putExtras(bundle1);//在用bundle 来Put数据后必需再Put到intent里面，否则没有传递
                    startActivityForResult(intent, SIGNATURE_REQUESTCODE);//第二个参数为请求码
                    break;
                case R.id.time_search:
                    createTimeDialog();
                    break;
                case R.id.position_linear:
                    pupouwindposition();
                    break;
            }
        }
    };


    /*当需要从第二个页面获得数据返回的时候，重写该方法*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //请求码      ，    结果码       ， intent对象（包含绑定的数据）
        switch (resultCode) {
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


    PopupWindow popupWindow;
    ListView listView2;
    ArrayAdapter<String> arrayAdapterListView1;
    ArrayAdapter<String> arrayAdapterListView2;
    String listView1Array[] = {"1", "2", "3"};
    int pos = 0;
    private String[] city = new String[]{"北京", "上海", "天津", "重庆"};
    String[][] pandc = new String[][]{{"北京", "上海", "天津", "重庆"}, {"香港", "澳门"},
            {"哈尔滨", "齐齐哈尔", "牡丹江", "大庆", "伊春", "双鸭山", "鹤岗", "鸡西", "佳木斯",
                    "七台河", "黑河", "绥化", "大兴安岭"}};

    //行业选择框
    public void pupouwindposition() {
        LayoutInflater layoutInflater = LayoutInflater.from(AdvancedSearchActivity.this);
        View view = layoutInflater.inflate(R.layout.dialog_expert_seek_position, null);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        Drawable drawable = getResources().getDrawable(R.drawable.edittext_white);
        popupWindow.setBackgroundDrawable(drawable);
        popupWindow.showAsDropDown(positionLinear);

        ListView listView1 = (ListView) view.findViewById(R.id.listview1);
        listView2 = (ListView) view.findViewById(R.id.listview2);

        arrayAdapterListView1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, listView1Array);

        listView1.setAdapter(arrayAdapterListView1);
        listView1.setOnItemClickListener(onItemClickListener);

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                positionName = pandc[pos][i];
                input_position_search.setText(positionName);
                popupWindow.dismiss();
                Log.i("postname=======>", "" + positionName);
            }
        });
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            pos = i;
            Log.i("pandc=======>", "" + pandc[pos]);
            arrayAdapterListView2 = new ArrayAdapter<String>(AdvancedSearchActivity.this,
                    android.R.layout.simple_expandable_list_item_1, pandc[pos]);
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    Message message = new Message();
                    message.what = 1111;
                    handler.sendMessage(message);
                }
            }.start();

        }
    };
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1111) {
                Log.i("pos=======>", "" + pos);
                Log.i("pandc+++++++>", "" + pandc[pos]);
                // arrayAdapterListView2.notifyDataSetChanged();
                listView2.setAdapter(arrayAdapterListView2);
            }
        }
    };
    //职位名称监听
    TextWatcher postNameText = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            postName = editable.toString();
            Log.i("postName=======>", "" + postName);
        }
    };
    //最低薪资输入框的监听
    TextWatcher minMoneyTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            minPay = editable.toString();
            Log.i("minPay=======>", "" + minPay);
        }
    };
    //最高薪资输入框的监听
    TextWatcher manMoneyTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            maxPay = editable.toString();
            Log.i("maxPay=======>", "" + maxPay);
        }
    };
    //对时间进行的操作
    public void istime(){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat
                ("yyyy-MM-dd");//可以方便地修改日期格式
        String today = dateFormat.format(now);
        Log.i("dateFormat====>", today);
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        Log.i("month====>", ""+month);
        int date = c.get(Calendar.DATE);
        Log.i("month====>", ""+date);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        tingtime = shijian.getText().toString();
        if (tingtime.equals(null)){
            inssueTime = 19700101+"";
        }else if (tingtime.equals("一个月前")){
            inssueTime = ""+ year + (month - 1) + date;
            Log.i("inssueTime++++++>", ""+inssueTime);
        }else if (tingtime.equals("一周前")){
            inssueTime = ""+ year + (month - 1) + date;
            Log.i("inssueTime====>", ""+inssueTime);
        }else if(tingtime.equals("今天")){
            inssueTime = ""+ year + month + date;
            Log.i("inssueTime********>", ""+inssueTime);
        }else if (tingtime.equals("不限")){
            inssueTime = 19700101+"";
            Log.i("inssueTime/////////>", ""+inssueTime);
        }else {
            Log.i("inssueTime-*-*-*-*-*-*>", ""+inssueTime);
        }
    }

}
