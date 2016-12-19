package test.leco.com.zgz.zxy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import test.leco.com.zgz.R;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MyResumeActivity extends Activity {
    RelativeLayout name;
    RelativeLayout place;
    ImageView resumeArrow;
    //性别
    RelativeLayout sexChange;
    //男女按钮
    RadioButton man,woman;
    LayoutInflater layoutInflater;
    int w,h;//屏幕宽高
    WindowManager manager;
    DisplayMetrics metrics;
    TextView userSex;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_resume_layout);
        findView();

        manager = getWindowManager();
        metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        w = metrics.widthPixels;
        h = metrics.heightPixels;
        resumeArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sexChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatSexWindow();
            }
        });
        super.onCreate(savedInstanceState);
    }

    public void findView(){
        name = (RelativeLayout) findViewById(R.id.resume_name);
        place = (RelativeLayout) findViewById(R.id.resume_hukou);
        resumeArrow= (ImageView) findViewById(R.id.resume_arrow);
        userSex = (TextView) findViewById(R.id.man_or_woman);
        sexChange= (RelativeLayout) findViewById(R.id.resume_sex);

        name.setOnClickListener(clickListener);
        place.setOnClickListener(clickListener);
    }
    Intent intent;
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.resume_name:
                  //  intent = new Intent(MyResumeActivity.this, AlterNameActivity.class);
                    startActivity(intent);
                    break;
                case R.id.resume_hukou:
                    //intent = new Intent(MyResumeActivity.this, AlterPlaceActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };


    PopupWindow sexChangeWindow;
    public void creatSexWindow(){
        layoutInflater=LayoutInflater.from(MyResumeActivity.this);
        View view =layoutInflater.inflate(R.layout.change_sex_window_layout,null);
        man= (RadioButton) view.findViewById(R.id.man);
        woman= (RadioButton) view.findViewById(R.id.woman);
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
        sexChangeWindow=new PopupWindow(view,w,h/2);
        sexChangeWindow.setBackgroundDrawable(new ColorDrawable(0x0000000));
        sexChangeWindow.setOutsideTouchable(true);
        sexChangeWindow.showAsDropDown(sexChange);
        view.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sex=(woman.isChecked())?"女":"男";
                Toast.makeText(MyResumeActivity.this,"修改成功！",Toast.LENGTH_SHORT).show();
                sexChangeWindow.dismiss();
                userSex.setText(""+sex);
            }
        });
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sexChangeWindow.dismiss();
            }
        });
    }
}
