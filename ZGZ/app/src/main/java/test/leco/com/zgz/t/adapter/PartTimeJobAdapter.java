package test.leco.com.zgz.t.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import test.leco.com.zgz.R;
import test.leco.com.zgz.t.data.PartTimeJobItem;

/**
 * Created by Administrator on 2016/12/0015.
 */

public class PartTimeJobAdapter extends BaseAdapter {

    Context context;
    List<PartTimeJobItem> list;
    LayoutInflater inflater;
    public PartTimeJobAdapter( Context context,List<PartTimeJobItem> list){
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
            convertView = inflater.inflate(R.layout.t_part_time_job_item,null);
            holder.positionName = (TextView) convertView.findViewById(R.id.positionName);
            holder.address = (TextView) convertView.findViewById(R.id.address);
            holder.workTime = (TextView) convertView.findViewById(R.id.workTime);
            holder.salary = (TextView) convertView.findViewById(R.id.salary);
            convertView.setTag(holder);
        }else {
            holder = (Holder) convertView.getTag();
        }
        PartTimeJobItem partTimeJobItem = list.get(position);
        holder.positionName.setText(partTimeJobItem.getPositionName());
        holder.address.setText(partTimeJobItem.getAddress());
        holder.workTime.setText(partTimeJobItem.getWorkTime());
        holder.salary.setText(partTimeJobItem.getSalary());

        return convertView;
    }

    class Holder{
        TextView positionName;
        TextView address;
        TextView workTime;
        TextView salary;
    }
}
