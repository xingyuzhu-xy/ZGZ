package test.leco.com.zgz.t.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import test.leco.com.zgz.R;
import test.leco.com.zgz.t.data.MePositionItem;

/**
 * Created by Administrator on 2016/12/0016.
 */

public class MePositionAdapter extends BaseAdapter {

    Context context;
    List<MePositionItem> list;
    LayoutInflater inflater;
    public MePositionAdapter( Context context,List<MePositionItem> list){
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
            convertView = inflater.inflate(R.layout.z_me_position_item,null);
            holder.companyName = (TextView) convertView.findViewById(R.id.companyName);
            holder.address = (TextView) convertView.findViewById(R.id.address);
            holder.positionName = (TextView) convertView.findViewById(R.id.positionName);
            holder.time = (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        }else {
            holder = (Holder) convertView.getTag();
        }
        MePositionItem mePositionItem = list.get(position);
        holder.companyName.setText(mePositionItem.getCompanyName());
        holder.address.setText(mePositionItem.getAddress());
        holder.positionName.setText(mePositionItem.getPositionName());
        holder.time.setText(mePositionItem.getTime());

        return convertView;
    }

    class Holder{
        TextView companyName,address,positionName,time;
    }
}
