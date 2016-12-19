package test.leco.com.zgz.t.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import test.leco.com.zgz.R;
import test.leco.com.zgz.zxy.MyAdviceActivity;
import test.leco.com.zgz.zxy.MyAttentionActivity;
import test.leco.com.zgz.zxy.MyCollectActivity;
import test.leco.com.zgz.zxy.MyDeliverActivity;
import test.leco.com.zgz.zxy.MyDownloadActivity;
import test.leco.com.zgz.zxy.MyMessageActivity;
import test.leco.com.zgz.zxy.MyResumeActivity;
import test.leco.com.zgz.zxy.MySysSettingActivity;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MeFragment extends Fragment {

    RelativeLayout resume,deliver,download,message,collect,attention,setting,advice;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my_setting_layout, null);
        resume = (RelativeLayout) view.findViewById(R.id.my_resume);
        deliver = (RelativeLayout) view.findViewById(R.id.my_deliver);
        download = (RelativeLayout) view.findViewById(R.id.my_download);
        message = (RelativeLayout) view.findViewById(R.id.my_message);
        collect = (RelativeLayout) view.findViewById(R.id.my_collcet);
        attention = (RelativeLayout) view.findViewById(R.id.my_attention);
        setting = (RelativeLayout) view.findViewById(R.id.my_setting);
        advice = (RelativeLayout) view.findViewById(R.id.my_advice);
        resume.setOnClickListener(listener);
        deliver.setOnClickListener(listener);
        download.setOnClickListener(listener);
        message.setOnClickListener(listener);
        collect.setOnClickListener(listener);
        attention.setOnClickListener(listener);
        setting.setOnClickListener(listener);
        advice.setOnClickListener(listener);
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

            }
        }
    };

}

