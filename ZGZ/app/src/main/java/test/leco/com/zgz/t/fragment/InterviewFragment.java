package test.leco.com.zgz.t.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import test.leco.com.zgz.R;
import test.leco.com.zgz.t.MePositionActivity;
import test.leco.com.zgz.zxy.InterviewFaceActivity;
import test.leco.com.zgz.zxy.InterviewMessageActivity;

/**
 * Created by Administrator on 2016/12/14.
 */

public class InterviewFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_interview_layout,null);
        TextView  interview = (TextView) view.findViewById(R.id.interview);
        LinearLayout mePosition = (LinearLayout) view.findViewById(R.id.me_position);
        LinearLayout positionMe = (LinearLayout) view.findViewById(R.id.position_me);
        mePosition.setOnClickListener(clickListener);
        interview.setOnClickListener(clickListener);
        positionMe.setOnClickListener(clickListener);
        return view;
    }


    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.interview:
                    intent = new Intent(getActivity(), InterviewFaceActivity.class);
                    startActivity(intent);
                    break;
                case R.id.me_position:
                    intent = new Intent(getActivity(), MePositionActivity.class);
                    startActivity(intent);
                    break;
                case R.id.position_me:
                    intent = new Intent(getActivity(), InterviewMessageActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}
