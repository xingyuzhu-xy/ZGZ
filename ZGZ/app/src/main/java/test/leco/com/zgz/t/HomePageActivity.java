package test.leco.com.zgz.t;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import test.leco.com.zgz.R;
import test.leco.com.zgz.t.fragment.HomePageFragment;
import test.leco.com.zgz.t.fragment.InterviewFragment;
import test.leco.com.zgz.t.fragment.MeFragment;
import test.leco.com.zgz.t.fragment.PositionFragment;


/**
 * Created by Administrator on 2016/12/0014.
 */

public class HomePageActivity extends FragmentActivity {
    //底部    首页   职位    面试     我的
    RadioButton homeRadio, positionRadio, interviewRadio, meRadio;
    TextView positionTextview, messageTextview,//职位界面 职位  消息
            loginTextview, registTextview;  //我的界面  登录  注册
    EditText searchEditText;//职位界面搜索框
    ImageView interviewImageview;  //面试界面 image按钮
    //   顶部      首页 ，          职位                面试            我的
    ViewPager viewPager;
    RadioGroup radioGroup;
    List<Fragment> fragmentList;//fragment集合

    //要适配的4个fragment
    HomePageFragment homePageFragment;
    InterviewFragment interviewFragment;
    MeFragment meFragment;
    PositionFragment positionFragment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_page);

        homeRadio = (RadioButton) findViewById(R.id.homepage_radiobutton);
        positionRadio = (RadioButton) findViewById(R.id.positon_radiobutton);
        interviewRadio = (RadioButton) findViewById(R.id.interview_radiobutton);
        meRadio = (RadioButton) findViewById(R.id.me_radiobutton);

        positionTextview = (TextView) findViewById(R.id.position_textview);
        messageTextview = (TextView) findViewById(R.id.message_textview);
        loginTextview = (TextView) findViewById(R.id.login_textview);
        registTextview = (TextView) findViewById(R.id.regist_textview);
        searchEditText = (EditText) findViewById(R.id.search_edittext);
        interviewImageview = (ImageView) findViewById(R.id.interview_imageview);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);


        homePageFragment = new HomePageFragment();
        positionFragment = new PositionFragment();
        interviewFragment = new InterviewFragment();
        meFragment = new MeFragment();
        fragmentList = new ArrayList<>();
        fragmentList.add(homePageFragment);
        fragmentList.add(positionFragment);
        fragmentList.add(interviewFragment);
        fragmentList.add(meFragment);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });

        //显示默认视图
        viewPager.setCurrentItem(0);
        final List<RadioButton> radioButtons = new ArrayList<>();
        radioButtons.add(homeRadio);
        radioButtons.add(positionRadio);
        radioButtons.add(interviewRadio);
        radioButtons.add(meRadio);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                radioButtons.get(position).setChecked(true);
                radioButtons.get(position).setTextColor(getResources().getColor(R.color.my_setting_blue1));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
   /*     viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
               switch (position){
                   case 0:
                       homeRadio.setChecked(true);
                       break;
                   case 1:
                       positionRadio.setChecked(true);
                       break;
                   case 2:
                       interviewRadio.setChecked(true);
                       break;
                   case 3:
                       meRadio.setChecked(true);
                       break;
               }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Animation animation = new TranslateAnimation(0,0,0,0);//平移动画
                animation.setFillAfter(true);//动画终止时停留在最后一帧，不然会回到没有执行前的状态
                animation.setDuration(200);
            }
        });*/
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.homepage_radiobutton:
                        homeRadio.setTextColor(getResources().getColor(R.color.my_setting_blue1));
                        positionRadio.setTextColor(getResources().getColor(R.color.light_black));
                        interviewRadio.setTextColor(getResources().getColor(R.color.light_black));
                        meRadio.setTextColor(getResources().getColor(R.color.light_black));
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.positon_radiobutton:
                        homeRadio.setTextColor(getResources().getColor(R.color.light_black));
                        positionRadio.setTextColor(getResources().getColor(R.color.my_setting_blue1));
                        interviewRadio.setTextColor(getResources().getColor(R.color.light_black));
                        meRadio.setTextColor(getResources().getColor(R.color.light_black));
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.interview_radiobutton:
                        homeRadio.setTextColor(getResources().getColor(R.color.light_black));
                        positionRadio.setTextColor(getResources().getColor(R.color.light_black));
                        interviewRadio.setTextColor(getResources().getColor(R.color.my_setting_blue1));
                        meRadio.setTextColor(getResources().getColor(R.color.light_black));
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.me_radiobutton:
                        homeRadio.setTextColor(getResources().getColor(R.color.light_black));
                        positionRadio.setTextColor(getResources().getColor(R.color.light_black));
                        interviewRadio.setTextColor(getResources().getColor(R.color.light_black));
                        meRadio.setTextColor(getResources().getColor(R.color.my_setting_blue1));
                        viewPager.setCurrentItem(3);
                        break;
                }
            }
        });
        
    }


}
