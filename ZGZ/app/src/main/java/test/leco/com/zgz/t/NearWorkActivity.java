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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

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

import test.leco.com.zgz.R;
import test.leco.com.zgz.t.adapter.PositionAdapter;
import test.leco.com.zgz.t.data.PositionItem;
import test.leco.com.zgz.t.http.HttpCallbackListener;
import test.leco.com.zgz.t.http.HttpURL;

/**
 * Created by Administrator on 2016/12/0015.
 */

public class NearWorkActivity extends Activity {
    LinearLayout vocationType; //行业种类
    ImageView back;
    EditText position;//职位关键词
    EditText xinshui;//最低薪资
    EditText siteEdit;
    TextView push_time;//刷新时间
    TextView search;//搜索
    LinearLayout linpush;
    LinearLayout linjuli;
    TextView positionNameText;

    String tingtime;
    String positionName;//行业名称
    String postName;//职位名称
    String site;//地区
    int minPay;//最低薪资
    String inssueTime;//发布时间

    private final int POSITION_SATE = 21111111;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_near_job);

        vocationType = (LinearLayout) findViewById(R.id.vocationType);
        back = (ImageView) findViewById(R.id.back_icon);
        position = (EditText) findViewById(R.id.position);
        xinshui = (EditText) findViewById(R.id.xinshui);
        push_time = (TextView) findViewById(R.id.push_time);
        linpush = (LinearLayout) findViewById(R.id.linpush);
        linjuli = (LinearLayout) findViewById(R.id.linjuli);
        search = (TextView) findViewById(R.id.search);
        siteEdit = (EditText) findViewById(R.id.site);
        positionNameText = (TextView) findViewById(R.id.position_name_text);

        back.setOnClickListener(onClickListener);
        vocationType.setOnClickListener(onClickListener);
        linpush.setOnClickListener(onClickListener);
        linjuli.setOnClickListener(onClickListener);
        search.setOnClickListener(onClickListener);

        position.addTextChangedListener(postNameText);
        xinshui.addTextChangedListener(minMoneyTextWatcher);
        siteEdit.addTextChangedListener(siteTextWatcher);

        positionData();
    }
    String shijian_time = "不限";
    String[] time = {"一个月前", "一周前", "今天", "不限"};
    int which1 = 3;

    public void createTimeDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(NearWorkActivity.this);
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
                push_time.setText(shijian_time);
                push_time.setTextColor(getResources().getColor(R.color.light_black));
            }
        });
        dialog.create();
        dialog.show();
    }

    int s =0;


    Intent intent;
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.vocationType: //行业类别
                    if (pos == false) {
                        pupouwindposition();
                    }else {
                        popupWindow.dismiss();
                        pos = false;
                    }
                    break;
                case R.id.back_icon:
                    finish();
                    break;
                case R.id.linpush:
                    createTimeDialog();
                    break;
                case R.id.linjuli:

                    break;
                case R.id.search:
                    if (site != null){
                        istime();
                        int dic = 1;
                        Log.i("datakjk", "" + positionName + postName + site + minPay  + inssueTime);
                        intent = new Intent(NearWorkActivity.this, SearchListActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("positionName", positionName);
                        bundle.putString("postName", postName);
                        bundle.putString("site", site);
                        bundle.putInt("minPay", minPay);
                        bundle.putInt("dic", dic);
                        bundle.putInt("inssueTime", Integer.parseInt(inssueTime));
                        intent.putExtras(bundle);
                        startActivityForResult(intent, POSITION_SATE);
                    }else {
                        Toast.makeText(NearWorkActivity.this, "地区不能为空", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

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
        LayoutInflater layoutInflater = LayoutInflater.from(NearWorkActivity.this);
        View view = layoutInflater.inflate(R.layout.dialog_expert_seek_position, null);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        Drawable drawable = getResources().getDrawable(R.drawable.edittext_white);
        popupWindow.setBackgroundDrawable(drawable);
        popupWindow.showAsDropDown(vocationType);
        pos = popupWindow.isShowing();

        listView1 = (ListView) view.findViewById(R.id.listview1);
        listView2 = (ListView) view.findViewById(R.id.listview2);

        positionAdapter = new PositionAdapter(NearWorkActivity.this,
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
                positionNameText.setText(positionName);
                popupWindow.dismiss();
            }
        });
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

    //对时间进行的操作
    public void istime() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat
                ("yyyyMMdd");//可以方便地修改日期格式
        String today = dateFormat.format(now);
        Log.i("dateFormat====>", today);
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        Log.i("WEEK_OF_MONTH====>", "" + c.get(Calendar.DAY_OF_WEEK));
        int date = c.get(Calendar.DATE);
        Log.i("month====>", "" + date);
        tingtime = push_time.getText().toString();
        if (tingtime.equals(null)) {
            inssueTime = 19700101 + "";
        } else if (tingtime.equals("一个月前")) {
            inssueTime =  "" + year + (month - 1) + date;
            Log.i("inssueTime++++++>", "" + inssueTime);
        } else if (tingtime.equals("一周前")) {
            c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) - 7);
            Date lastWeek1 = c.getTime();
            String lastWeek = dateFormat.format(lastWeek1);
            inssueTime = lastWeek;
            Log.i("lastWeek====>", "" + lastWeek);
        } else if (tingtime.equals("今天")) {
            inssueTime = "" +  year + month + date;
            Log.i("inssueTime********>", "" + inssueTime);
        } else if (tingtime.equals("不限")) {
            inssueTime = 19700101 + "";
            Log.i("inssueTime/////////>", "" + inssueTime);
        } else {
            Log.i("inssueTime-*-*-*-*-*-*>", "" + inssueTime);
        }
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
            String minPayid = editable.toString();
            if (minPayid == null){
                minPay = 0;
            }else if(minPayid.equals("")){
                minPay = 0;
            }else {
                minPay = Integer.parseInt(minPayid);
            }
            Log.i("minPay=======>", "" + minPay);
        }
    };
    //对地区的监听
    TextWatcher siteTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            site = editable.toString();
        }
    };

}
