package test.leco.com.zgz.t.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import test.leco.com.zgz.R;
import test.leco.com.zgz.t.data.RecommendListItem;

/**
 * Created by Administrator on 2016/12/0014.
 */

public class RecommendAdapter extends BaseAdapter {

    Context context;
    List<RecommendListItem> list;
    LayoutInflater inflater;
    public RecommendAdapter(Context context,List<RecommendListItem> list){
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
            convertView = inflater.inflate(R.layout.t_recommend_list_item,null);
            holder.image = (ImageView) convertView.findViewById(R.id.image);
            holder.whatJob = (TextView) convertView.findViewById(R.id.what_job);
            holder.address = (TextView) convertView.findViewById(R.id.address);
            holder.type = (TextView) convertView.findViewById(R.id.type);
            holder.money = (TextView) convertView.findViewById(R.id.money);
            convertView.setTag(holder);
        }else {
            holder = (Holder) convertView.getTag();
        }
        RecommendListItem recommendListItem = list.get(position);
        holder.image.setImageResource(recommendListItem.getImage());
        holder.whatJob.setText(recommendListItem.getPositionName());
        holder.address.setText(recommendListItem.getWorkPlace());
        holder.type.setText(recommendListItem.getCompanyName());
        holder.money.setText(recommendListItem.getSalary());

        return convertView;
    }

    class Holder{
        ImageView image;
        TextView whatJob;
        TextView address;
        TextView type;
        TextView money;
    }
}
