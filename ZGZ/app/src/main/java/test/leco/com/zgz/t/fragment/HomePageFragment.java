package test.leco.com.zgz.t.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import test.leco.com.zgz.R;
import test.leco.com.zgz.t.AdvancedSearchActivity;
import test.leco.com.zgz.t.NearWorkActivity;
import test.leco.com.zgz.t.PartTimeJobActivity;
import test.leco.com.zgz.t.WhoSeeMeActivity;
import test.leco.com.zgz.t.adapter.RecommendAdapter;
import test.leco.com.zgz.t.data.RecommendListItem;

/**
 * Created by Administrator on 2016/12/0014.
 */

public class HomePageFragment extends Fragment {

    List<RecommendListItem> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.t_homepage,null);
        ListView listView = (ListView) view.findViewById(R.id.listView); //推荐列表
        TextView advancedSearch = (TextView) view.findViewById(R.id.advancedSearch);   //高级搜索
        TextView whoSeeMe = (TextView) view.findViewById(R.id.whoSeeMe); //谁看过我
        TextView partJob = (TextView) view.findViewById(R.id.partJob); //兼职
        TextView nearWork = (TextView) view.findViewById(R.id.nearWork); //附近的工作
        //给列表添加适配器
        getData();
        listView.setAdapter(new RecommendAdapter(getActivity(),list));
        listView.setFocusable(false);//设置默认不聚焦，即进去之后显示在页面的最上面

        //设置点击事件
        advancedSearch.setOnClickListener(onClickListener);
        whoSeeMe.setOnClickListener(onClickListener);
        partJob.setOnClickListener(onClickListener);
        nearWork.setOnClickListener(onClickListener);
        return view;
    }

    Intent intent;
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.advancedSearch:
                    intent = new Intent(getActivity(), AdvancedSearchActivity.class);
                    startActivity(intent);
                    break;
                case R.id.whoSeeMe:
                    intent = new Intent(getActivity(), WhoSeeMeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.partJob:
                    intent = new Intent(getActivity(), PartTimeJobActivity.class);
                    startActivity(intent);
                    break;
                case R.id.nearWork:
                    intent = new Intent(getActivity(), NearWorkActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
    public void getData(){
        list = new ArrayList<RecommendListItem>();

        RecommendListItem recommendListItem = new RecommendListItem();
        recommendListItem.setImage(R.mipmap.home_page_logo_performance);
        recommendListItem.setPositionName("绩佣会计");
        recommendListItem.setCompanyName("塔尔餐饮");
        recommendListItem.setWorkPlace("渝中区");
        recommendListItem.setSalary("3000-5000/月");
        list.add(recommendListItem);

        RecommendListItem recommendListItem1 = new RecommendListItem();
        recommendListItem1.setImage(R.mipmap.home_page_logo_performance);
        recommendListItem1.setPositionName("绩佣会计");
        recommendListItem1.setCompanyName("塔尔餐饮");
        recommendListItem1.setWorkPlace("渝中区");
        recommendListItem1.setSalary("3000-5000/月");
        list.add(recommendListItem1);

        RecommendListItem recommendListItem2 = new RecommendListItem();
        recommendListItem2.setImage(R.mipmap.home_page_logo_performance);
        recommendListItem2.setPositionName("绩佣会计");
        recommendListItem2.setCompanyName("塔尔餐饮");
        recommendListItem2.setWorkPlace("渝中区");
        recommendListItem2.setSalary("3000-5000/月");
        list.add(recommendListItem2);

        RecommendListItem recommendListItem3 = new RecommendListItem();
        recommendListItem3.setImage(R.mipmap.home_page_logo_performance);
        recommendListItem3.setPositionName("绩佣会计");
        recommendListItem3.setCompanyName("塔尔餐饮");
        recommendListItem3.setWorkPlace("渝中区");
        recommendListItem3.setSalary("3000-5000/月");
        list.add(recommendListItem3);

        RecommendListItem recommendListItem4 = new RecommendListItem();
        recommendListItem4.setImage(R.mipmap.home_page_logo_performance);
        recommendListItem4.setPositionName("绩佣会计");
        recommendListItem4.setCompanyName("塔尔餐饮");
        recommendListItem4.setWorkPlace("渝中区");
        recommendListItem4.setSalary("3000-5000/月");
        list.add(recommendListItem4);
    }
}
