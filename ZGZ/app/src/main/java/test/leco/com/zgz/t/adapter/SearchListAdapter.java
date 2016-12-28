package test.leco.com.zgz.t.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import test.leco.com.zgz.R;
import test.leco.com.zgz.t.data.SearchListItem;

/**
 * Created by Administrator on 2016/12/0015.
 */

public class SearchListAdapter extends BaseAdapter {

    Context context;
    List<SearchListItem> list;
    LayoutInflater inflater;
    public SearchListAdapter(Context context,List<SearchListItem> list){
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null){
            holder = new Holder();
            convertView = inflater.inflate(R.layout.t_advanced_search_list_item,null);
            holder.companyName = (TextView) convertView.findViewById(R.id.companyName);
            holder.time = (TextView) convertView.findViewById(R.id.time);
            holder.positionName = (TextView) convertView.findViewById(R.id.positionName);
            holder.address = (TextView) convertView.findViewById(R.id.address);
            holder.education = (TextView) convertView.findViewById(R.id.education);
            holder.salary = (TextView) convertView.findViewById(R.id.salary);
            holder.positonid = (TextView) convertView.findViewById(R.id.position_id);
            convertView.setTag(holder);
        }else {
            holder = (Holder) convertView.getTag();
        }
        SearchListItem searchListItem = list.get(position);
        holder.companyName.setText(searchListItem.getCompanyName());
        holder.time.setText(searchListItem.getTiem());
        holder.positionName.setText(searchListItem.getPositionName());
        holder.address.setText(searchListItem.getAddress());
        holder.education.setText(searchListItem.getEducation());
        holder.salary.setText(searchListItem.getSalary());
        holder.positonid.setText(searchListItem.getPostid());

        return convertView;
    }

    class Holder{
        TextView companyName;
        TextView time;
        TextView positionName;
        TextView address;
        TextView education;
        TextView salary;
        TextView positonid;
    }
}
