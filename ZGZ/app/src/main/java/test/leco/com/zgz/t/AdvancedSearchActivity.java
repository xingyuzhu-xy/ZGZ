package test.leco.com.zgz.t;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import test.leco.com.zgz.R;
import test.leco.com.zgz.t.adapter.PositionAdapter;
import test.leco.com.zgz.t.data.PositionItem;
import test.leco.com.zgz.t.http.HttpCallbackListener;
import test.leco.com.zgz.t.http.HttpURL;
import test.leco.com.zgz.t.other.AlterPlaceActivity;

/**
 * Created by Administrator on 2016/12/0014.
 */

public class AdvancedSearchActivity extends Activity {
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

        site = area_text.getText().toString();

        positionData();
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
                    if (pos == false) {
                        pupouwindposition();
                    }else {
                        popupWindow.dismiss();
                        pos = false;
                    }
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
    ListView listView1;

    PositionAdapter positionAdapter;
    ArrayList<PositionItem> positionList = new ArrayList<PositionItem>();
    ArrayList<String> postItemList = new ArrayList<String>();
    boolean pos;
    int positionid;
    ArrayAdapter postAdapter;
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
        pos = popupWindow.isShowing();

        listView1 = (ListView) view.findViewById(R.id.listview1);
        listView2 = (ListView) view.findViewById(R.id.listview2);

        positionAdapter = new PositionAdapter(AdvancedSearchActivity.this,
                positionList);
        positionAdapter.setOnItemClickListener(new PositionAdapter.ItemClickListener() {
            @Override
            public void click(int poistion, String text, String posititoName) {
                positionid = Integer.parseInt(text);
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        postData();
                        Message msg = new Message();
                        msg.what = 0;
                        positionHandler.sendMessage(msg);
                    }
                }.start();
                Log.i("=============", "+++++++++");
            }
        });


        listView1.setAdapter(positionAdapter);
        //listView1.setOnItemClickListener(onItemClickListener);

        postAdapter = new ArrayAdapter(this,
                R.layout.post_item,postItemList);
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("====", "" + adapterView.getItemAtPosition(i));
                positionName =  adapterView.getItemAtPosition(i).toString();
                Log.i("positionName====", "" + positionName);
                input_position_search.setText(positionName);
                popupWindow.dismiss();
            }
        });
    }



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
    public void istime() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat
                ("yyyy-MM-dd");//可以方便地修改日期格式
        String today = dateFormat.format(now);
        Log.i("dateFormat====>", today);
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        Log.i("month====>", "" + month);
        int date = c.get(Calendar.DATE);
        Log.i("month====>", "" + date);
        tingtime = shijian.getText().toString();
        if (tingtime.equals(null)) {
            inssueTime = 19700101 + "";
        } else if (tingtime.equals("一个月前")) {
            inssueTime = "" + year + (month - 1) + date;
            Log.i("inssueTime++++++>", "" + inssueTime);
        } else if (tingtime.equals("一周前")) {
            inssueTime = "" + year + (month - 1) + date;
            Log.i("inssueTime====>", "" + inssueTime);
        } else if (tingtime.equals("今天")) {
            inssueTime = "" + year + month + date;
            Log.i("inssueTime********>", "" + inssueTime);
        } else if (tingtime.equals("不限")) {
            inssueTime = 19700101 + "";
            Log.i("inssueTime/////////>", "" + inssueTime);
        } else {
            Log.i("inssueTime-*-*-*-*-*-*>", "" + inssueTime);
        }
    }

    //总行业选择接口
    public void positionData() {
        String posittionURL = "http://10.0.2.2/index.php/home/index/theirindustry";
        HttpURL.sendRequest(posittionURL, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int status = jsonObject.getInt("status");
                    String message = jsonObject.getString("message");
                    JSONArray positiondata = jsonObject.getJSONArray("positiondata");
                    for (int i = 0; i < positiondata.length(); i++) {
                        PositionItem positionItem = new PositionItem();
                        JSONObject object = positiondata.getJSONObject(i);
                        positionItem.setPositionID(object.getInt("position_id"));
                        positionItem.setPositionName(object.getString("position_name"));
                        positionList.add(positionItem);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    //行业分类接口获取
    public void postData() {
        postItemList.clear();
        String httpURL = "http://10.0.2.2/index.php/home/index/dustry?positionid=" + positionid;
        Log.i("postData =======", "" + positionid);
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(httpURL);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            StringBuilder stringBuilder = new StringBuilder();
            String data;
            while ((data = bufferedReader.readLine()) != null) {
                stringBuilder.append(data);
            }
            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            int status = jsonObject.getInt("status");
            String message = jsonObject.getString("message");
            JSONArray postdata = jsonObject.getJSONArray("postdata");
            for (int i = 0; i < postdata.length(); i++) {

                JSONObject object = postdata.getJSONObject(i);
                int post_id = object.getInt("post_id");
//                postItem.setPositionID(post_id);
//                Log.i("post_id++++++", "" + post_id);
                String post_name = object.getString("post_name");
                Log.i("post_name+++++++", "" + post_name);
                postItemList.add(post_name);
                Log.i("postItemList+++++++", "" + postItemList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Handler positionHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Log.i("++++++++++++", "================");

                    listView2.setAdapter(postAdapter);
                    postAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };
}
