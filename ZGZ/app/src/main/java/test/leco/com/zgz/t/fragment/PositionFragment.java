package test.leco.com.zgz.t.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import test.leco.com.zgz.R;

/**
 * Created by Administrator on 2016/12/14.
 */

public class PositionFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.activity_position_layout,null);
        return view;
    }
}
