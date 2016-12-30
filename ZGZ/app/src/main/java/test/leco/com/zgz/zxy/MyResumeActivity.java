package test.leco.com.zgz.zxy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;

import agora.openvcall.AGApplication;
import test.leco.com.zgz.R;
import test.leco.com.zgz.t.data.MyAppLication;
import test.leco.com.zgz.t.http.GetUserMessageHttp;
import test.leco.com.zgz.t.http.MyresumeHttp;
import test.leco.com.zgz.t.other.AlterEmailActivity;
import test.leco.com.zgz.t.other.AlterExperienceActivity;
import test.leco.com.zgz.t.other.AlterNameActivity;
import test.leco.com.zgz.t.other.AlterPhoneActivity;
import test.leco.com.zgz.t.other.AlterPositionActivity;
import test.leco.com.zgz.t.other.AlterTimeActivity;
import test.leco.com.zgz.t.other.JiGuanActivity;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MyResumeActivity extends Activity {
    ImageView resumeArrow; //返回上级页面
    TextView save; //保存
    RelativeLayout name;//姓名
    TextView userName;
    RelativeLayout sexChange; //性别
    TextView userSex;
    LinearLayout place; //籍贯
    TextView jiguan;
    LinearLayout burn; //出生年月
    TextView userBurn;
    LinearLayout education; //学历
    TextView educa;
    RelativeLayout position; //应聘职位
    TextView userPosition;
    RelativeLayout experience; //工作经历
    TextView exper;
    RelativeLayout phone; //绑定手机号
    TextView userPhone;
    RelativeLayout email; //邮箱地址
    TextView userEmail;

    RadioButton man, woman;    //男女按钮
    LayoutInflater layoutInflater;
    int w, h;//屏幕宽高
    WindowManager manager;
    DisplayMetrics metrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_resume_layout);
        super.onCreate(savedInstanceState);

        //        sharedPreferences=getSharedPreferences("ZGZ", Context.MODE_PRIVATE);
//        id= sharedPreferences.getInt("user_id",0);
        AGApplication myAppLication = (AGApplication) getApplication();
        id = myAppLication.getId();
        manager = getWindowManager();
        metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        w = metrics.widthPixels;
        h = metrics.heightPixels;
        layoutInflater = LayoutInflater.from(MyResumeActivity.this);
        findView();
        new Thread(){
            @Override
            public void run() {
                Log.i("Thread is start","");
                getData();

            }
        }.start();
        //通过接口获取我的简历的数据并填充到对应的控件上
    }

    public void findView() {
        save = (TextView) findViewById(R.id.save);
        resumeArrow = (ImageView) findViewById(R.id.resume_arrow);
        name = (RelativeLayout) findViewById(R.id.resume_name);
        userName = (TextView) findViewById(R.id.userName);
        userSex = (TextView) findViewById(R.id.man_or_woman);
        sexChange = (RelativeLayout) findViewById(R.id.resume_sex);
        place = (LinearLayout) findViewById(R.id.resume_hukou);
        jiguan = (TextView) findViewById(R.id.jiguan);
        education = (LinearLayout) findViewById(R.id.resume_edu);
        educa = (TextView) findViewById(R.id.education);
        burn = (LinearLayout) findViewById(R.id.resume_born);
        userBurn = (TextView) findViewById(R.id.burn);
        position = (RelativeLayout) findViewById(R.id.resume_good);
        userPosition = (TextView) findViewById(R.id.position);
        experience = (RelativeLayout) findViewById(R.id.resume_exp);
        exper = (TextView) findViewById(R.id.experience);
        phone = (RelativeLayout) findViewById(R.id.resume_phonenumber);
        userPhone = (TextView) findViewById(R.id.userPhone);
        email = (RelativeLayout) findViewById(R.id.resume_emailadress);
        userEmail = (TextView) findViewById(R.id.email);

        name.setOnClickListener(clickListener);
        place.setOnClickListener(clickListener);
        resumeArrow.setOnClickListener(clickListener);
        sexChange.setOnClickListener(clickListener);
        education.setOnClickListener(clickListener);
        burn.setOnClickListener(clickListener);
        position.setOnClickListener(clickListener);
        experience.setOnClickListener(clickListener);
        phone.setOnClickListener(clickListener);
        email.setOnClickListener(clickListener);
        save.setOnClickListener(clickListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null){
            return;
        }
        Bundle bundle = data.getExtras();
        if ( resultCode ==RESULT_OK){
            switch (requestCode){
                case 1001:
                    Log.i("name===>",bundle.getString("userName"));
                    userName.setText(bundle.getString("userName"));
                    break;
                case 1002:
                    userBurn.setText(bundle.getString("mTime"));
                    break;
                case 1003:
                    userPosition.setText(bundle.getString("mPosition"));
                    break;
                case 1004:
                    exper.setText(bundle.getString("mExperience"));
                    break;
                case 1005:
                    userPhone.setText(bundle.getString("mPhone"));
                    break;
                case 1006:
                    userEmail.setText(bundle.getString("mEmail"));
                    break;
                case 1007:
                    jiguan.setText(bundle.getString("mJiGuan"));
                    break;
            }
        }

    }

    Intent intent;
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.resume_arrow: //返回上级页面
                    finish();
                    break;
                case R.id.save:
                    new Thread(){
                        @Override
                        public void run() {
                            putData();
                        }
                    }.start();
                    finish();
                    break;
                case R.id.resume_name: //姓名
                    intent = new Intent(MyResumeActivity.this, AlterNameActivity.class);
                    Bundle bundle1 = new Bundle();
                    intent.putExtras(bundle1);
                    startActivityForResult(intent,1001);
                    break;
                case R.id.resume_sex://性别
                    creatSexWindow();
                    break;
                case R.id.resume_hukou: //籍贯
                    intent = new Intent(MyResumeActivity.this, JiGuanActivity.class);
                    Bundle bundle8 = new Bundle();
                    intent.putExtras(bundle8);
                    startActivityForResult(intent,1007);
                    break;
                case R.id.resume_edu: //学历
                    createDialog();
                    break;
                case R.id.resume_born: //出生年月
                    intent = new Intent(MyResumeActivity.this, AlterTimeActivity.class);
                    Bundle bundle3 = new Bundle();
                    intent.putExtras(bundle3);
                    startActivityForResult(intent,1002);
                    break;
                case R.id.resume_good: //应聘职位
                    intent = new Intent(MyResumeActivity.this, AlterPositionActivity.class);
                    Bundle bundle4 = new Bundle();
                    intent.putExtras(bundle4);
                    startActivityForResult(intent,1003);
                    break;
                case R.id.resume_exp: //工作经历
                    intent = new Intent(MyResumeActivity.this, AlterExperienceActivity.class);
                    Bundle bundle5 = new Bundle();
                    intent.putExtras(bundle5);
                    startActivityForResult(intent,1004);
                    break;
                case R.id.resume_phonenumber: //绑定手机号
                    intent = new Intent(MyResumeActivity.this, AlterPhoneActivity.class);
                    Bundle bundle6 = new Bundle();
                    intent.putExtras(bundle6);
                    startActivityForResult(intent,1005);
                    break;
                case R.id.resume_emailadress: //邮箱
                    intent = new Intent(MyResumeActivity.this, AlterEmailActivity.class);
                    Bundle bundle7 = new Bundle();
                    intent.putExtras(bundle7);
                    startActivityForResult(intent,1006);
                    break;
            }
        }
    };


    PopupWindow sexChangeWindow;
    //设置性别
    public void creatSexWindow() {
        View view = layoutInflater.inflate(R.layout.change_sex_window_layout, null);
        man = (RadioButton) view.findViewById(R.id.man);
        woman = (RadioButton) view.findViewById(R.id.woman);
        man.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                man.setTextColor(getResources().getColor(R.color.color323232));
                woman.setTextColor(getResources().getColor(R.color.black230));
            }
        });
        woman.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                man.setTextColor(getResources().getColor(R.color.black230));
                woman.setTextColor(getResources().getColor(R.color.color323232));
            }
        });
        sexChangeWindow = new PopupWindow(view, w, h / 2);
        sexChangeWindow.setBackgroundDrawable(new ColorDrawable(0x0000000));
        sexChangeWindow.setOutsideTouchable(true);
        sexChangeWindow.showAsDropDown(sexChange);
        view.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sex = (woman.isChecked()) ? "女" : "男";
                Toast.makeText(MyResumeActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();
                sexChangeWindow.dismiss();
                userSex.setText("" + sex);
            }
        });
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sexChangeWindow.dismiss();
            }
        });
    }
    String[] edu = {"硕士","大学本科","大专","中专/技校/高中/职高"};
    String string = "硕士";
    int i = 1;
    public void createDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(MyResumeActivity.this);
        dialog.setTitle("选择学历");
        dialog.setSingleChoiceItems(edu, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MyResumeActivity.this,"你选择了"+edu[which],Toast.LENGTH_LONG).show();
                i = which;
                string = edu[which];
            }
        });
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MyResumeActivity.this,"你选择了"+string,Toast.LENGTH_LONG).show();
                educa.setText(string);
            }
        });
        dialog.create();
        dialog.show();
    }

    int id;
    //上传数据
    public void putData(){

        Log.i("id===>",""+id);
        int bur = Integer.valueOf(userBurn.getText().toString()).intValue();
        try {
            new MyresumeHttp(id,userName.getText().toString(),userSex.getText().toString(),
                    jiguan.getText().toString(),i,bur, userPosition.getText().toString(),
                    exper.getText().toString(),userPhone.getText().toString(),userEmail.getText().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    //获取数据
    public void getData(){
        try {
            GetUserMessageHttp getUserMessageHttp = new GetUserMessageHttp(id);
            String str = getUserMessageHttp.getStringBuilder().toString();
            Log.i("str=========>",str);
            JSONObject jsonObject = new JSONObject(str);
            JSONArray userMessage = jsonObject.getJSONArray("user_message ");
            JSONObject message = userMessage.getJSONObject(0);
            String name = message.getString("user_name");
            userName.setText(name);
            userSex.setText(message.getString("user_sex"));
            jiguan.setText(message.getString("native"));
            educa.setText(edu[message.getInt("user_education")]);
            Log.i("===>",""+message.getInt("uesr_brithday"));
            String s = Integer.toString(message.getInt("uesr_brithday"));
            userBurn.setText(s);
            userPosition.setText(message.getString("user_hobby"));
            exper.setText(message.getString("user_content"));
            userPhone.setText(message.getString("user_tel"));
            userEmail.setText(message.getString("user_email"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
