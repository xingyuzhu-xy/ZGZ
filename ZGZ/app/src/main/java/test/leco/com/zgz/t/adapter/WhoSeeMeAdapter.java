package test.leco.com.zgz.t.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import test.leco.com.zgz.R;
import test.leco.com.zgz.t.data.WhoSeeMeItem;

/**
 * Created by Administrator on 2016/12/0015.
 */

public class WhoSeeMeAdapter extends BaseAdapter {
    Context context;
    List<WhoSeeMeItem> list;

    LayoutInflater inflater;

    public WhoSeeMeAdapter(Context context, List<WhoSeeMeItem> list) {
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
            convertView = inflater.inflate(R.layout.t_who_see_me_item,null);
            holder.companyName = (TextView) convertView.findViewById(R.id.companyName);
            holder.seeTime = (TextView) convertView.findViewById(R.id.seeTime);
            convertView.setTag(holder);
        }else {
            holder = (Holder) convertView.getTag();
        }
        WhoSeeMeItem whoSeeMeItem = list.get(position);
        holder.companyName.setText(whoSeeMeItem.getCompanyName());
        holder.seeTime.setText(whoSeeMeItem.getTime());

        return convertView;
    }

    class Holder {
        TextView companyName;
        TextView seeTime;
    }
}
