package test.leco.com.zgz.t.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import test.leco.com.zgz.R;

/**
 * Created by Administrator on 2016/12/0014.
 */

public class HomePageFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.t_homepage,null);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
