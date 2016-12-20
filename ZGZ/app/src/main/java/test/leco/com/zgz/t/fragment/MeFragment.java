package test.leco.com.zgz.t.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import test.leco.com.zgz.R;
import test.leco.com.zgz.zxy.LoginActivity;
import test.leco.com.zgz.zxy.MyAdviceActivity;
import test.leco.com.zgz.zxy.MyAttentionActivity;
import test.leco.com.zgz.zxy.MyCollectActivity;
import test.leco.com.zgz.zxy.MyDeliverActivity;
import test.leco.com.zgz.zxy.MyDownloadActivity;
import test.leco.com.zgz.zxy.MyMessageActivity;
import test.leco.com.zgz.zxy.MyResumeActivity;
import test.leco.com.zgz.zxy.MySysSettingActivity;
import test.leco.com.zgz.zxy.RegistActivity;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MeFragment extends Fragment {

    RelativeLayout resume,deliver,download,message,collect,attention,setting,advice;
    TextView login,register;//登录，注册
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my_setting_layout, null);
        resume = (RelativeLayout) view.findViewById(R.id.my_resume);//我的简历
        deliver = (RelativeLayout) view.findViewById(R.id.my_deliver);//投递记录
        download = (RelativeLayout) view.findViewById(R.id.my_download);//谁下载了我的简历
        message = (RelativeLayout) view.findViewById(R.id.my_message);//消息通知
        collect = (RelativeLayout) view.findViewById(R.id.my_collcet);//我的收藏
        attention = (RelativeLayout) view.findViewById(R.id.my_attention);//我的关注
        setting = (RelativeLayout) view.findViewById(R.id.my_setting);//系统设置
        advice = (RelativeLayout) view.findViewById(R.id.my_advice);//意见反馈
        login= (TextView) view.findViewById(R.id.login_textview);
        register= (TextView) view.findViewById(R.id.regist_textview);
        resume.setOnClickListener(listener);
        deliver.setOnClickListener(listener);
        download.setOnClickListener(listener);
        message.setOnClickListener(listener);
        collect.setOnClickListener(listener);
        attention.setOnClickListener(listener);
        setting.setOnClickListener(listener);
        advice.setOnClickListener(listener);
        login.setOnClickListener(listener);
        register.setOnClickListener(listener);
        return view;
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.my_resume:
                    intent=new Intent(getActivity(),MyResumeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_deliver:
                    intent=new Intent(getActivity(),MyDeliverActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_download:
                    intent=new Intent(getActivity(),MyDownloadActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_message:
                    intent=new Intent(getActivity(),MyMessageActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_collcet:
                    intent=new Intent(getActivity(),MyCollectActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_attention:
                    intent=new Intent(getActivity(),MyAttentionActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_setting:
                    intent=new Intent(getActivity(),MySysSettingActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_advice:
                    intent=new Intent(getActivity(),MyAdviceActivity.class);
                    startActivity(intent);
                    break;
                case R.id.login_textview:
                    intent=new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    break;
                case R.id.regist_textview:
                    intent=new Intent(getActivity(), RegistActivity.class);
                    startActivity(intent);
                    break;

            }
        }
    };

}

