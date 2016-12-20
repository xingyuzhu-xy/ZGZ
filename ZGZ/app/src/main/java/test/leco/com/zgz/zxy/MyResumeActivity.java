package test.leco.com.zgz.zxy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
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

import test.leco.com.zgz.R;
import test.leco.com.zgz.t.other.AlterEmailActivity;
import test.leco.com.zgz.t.other.AlterExperienceActivity;
import test.leco.com.zgz.t.other.AlterNameActivity;
import test.leco.com.zgz.t.other.AlterPhoneActivity;
import test.leco.com.zgz.t.other.AlterPlaceActivity;
import test.leco.com.zgz.t.other.AlterPositionActivity;
import test.leco.com.zgz.t.other.AlterTimeActivity;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MyResumeActivity extends Activity {
    RelativeLayout name;
    RelativeLayout place;
    RelativeLayout burn;
    ImageView resumeArrow;
    //学历
    LinearLayout education;
    RelativeLayout position; //应聘职位
    RelativeLayout experience; //工作经历
    RelativeLayout phone; //绑定手机号
    RelativeLayout email; //邮箱地址
    TextView educa;
    //性别
    RelativeLayout sexChange;
    //男女按钮
    RadioButton man, woman;
    LayoutInflater layoutInflater;
    int w, h;//屏幕宽高
    WindowManager manager;
    DisplayMetrics metrics;
    TextView userSex;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_resume_layout);
        manager = getWindowManager();
        metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        w = metrics.widthPixels;
        h = metrics.heightPixels;
        layoutInflater = LayoutInflater.from(MyResumeActivity.this);
        findView();


    }

    public void findView() {
        name = (RelativeLayout) findViewById(R.id.resume_name);
        place = (RelativeLayout) findViewById(R.id.resume_hukou);
        resumeArrow = (ImageView) findViewById(R.id.resume_arrow);
        userSex = (TextView) findViewById(R.id.man_or_woman);
        sexChange = (RelativeLayout) findViewById(R.id.resume_sex);
        education = (LinearLayout) findViewById(R.id.resume_edu);
        educa = (TextView) findViewById(R.id.education);
        burn = (RelativeLayout) findViewById(R.id.resume_born);
        position = (RelativeLayout) findViewById(R.id.resume_good);
        experience = (RelativeLayout) findViewById(R.id.resume_exp);
        phone = (RelativeLayout) findViewById(R.id.resume_phonenumber);
        email = (RelativeLayout) findViewById(R.id.resume_emailadress);

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
    }

    Intent intent;
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.resume_name: //姓名
                    intent = new Intent(MyResumeActivity.this, AlterNameActivity.class);
                    startActivity(intent);
                    break;
                case R.id.resume_hukou: //籍贯
                    intent = new Intent(MyResumeActivity.this, AlterPlaceActivity.class);
                    startActivity(intent);
                    break;
                case R.id.resume_arrow: //返回上级页面
                    finish();
                    break;
                case R.id.resume_sex://修改性别
                    creatSexWindow();
                    break;
                case R.id.resume_edu: //学历
                    createDialog();
                    break;
                case R.id.resume_born: //出生年月
                    intent = new Intent(MyResumeActivity.this, AlterTimeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.resume_good: //应聘职位
                    intent = new Intent(MyResumeActivity.this, AlterPositionActivity.class);
                    startActivity(intent);
                    break;
                case R.id.resume_exp: //工作经历
                    intent = new Intent(MyResumeActivity.this, AlterExperienceActivity.class);
                    startActivity(intent);
                    break;
                case R.id.resume_phonenumber: //绑定手机号
                    intent = new Intent(MyResumeActivity.this, AlterPhoneActivity.class);
                    startActivity(intent);
                    break;
                case R.id.resume_emailadress:
                    intent = new Intent(MyResumeActivity.this, AlterEmailActivity.class);
                    startActivity(intent);
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
    public void createDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(MyResumeActivity.this);
        dialog.setTitle("选择学历");
        dialog.setSingleChoiceItems(edu, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MyResumeActivity.this,"你选择了"+edu[which],Toast.LENGTH_LONG).show();
            }
        });
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MyResumeActivity.this,"你选择了"+which,Toast.LENGTH_LONG).show();
            }
        });
        dialog.create();
        dialog.show();
    }

}
