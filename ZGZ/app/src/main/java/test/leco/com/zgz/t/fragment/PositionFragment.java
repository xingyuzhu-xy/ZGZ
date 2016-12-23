package test.leco.com.zgz.t.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import test.leco.com.zgz.R;
import test.leco.com.zgz.zxy.Myadapter.PositionAdapter;
import test.leco.com.zgz.zxy.PositionDetailActivity;

/**
 * Created by Administrator on 2016/12/14.
 */

public class PositionFragment extends Fragment {
    ListView listView;
    List<HashMap<String,Object>> list;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.activity_position_layout,null);
        listView= (ListView) view.findViewById(R.id.position_listview);
        list=new ArrayList<>();
        getDate();
        listView.setAdapter(new PositionAdapter(list,getActivity()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), PositionDetailActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    public void getDate(){
        for (int i=0;i<5;i++){
            HashMap<String,Object> map =new HashMap<>();
            map.put("cpimage",R.mipmap.cpimage);
            map.put("position",getResources().getString(R.string.design_director));
            map.put("cpname",getResources().getString(R.string.cpname));
            map.put("city",getResources().getString(R.string.city));
            map.put("someday",getResources().getString(R.string.someday));
            map.put("pay",getResources().getString(R.string.money));
            list.add(map);
        }
    }

}
