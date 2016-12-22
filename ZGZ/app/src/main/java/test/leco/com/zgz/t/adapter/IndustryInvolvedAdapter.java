package test.leco.com.zgz.t.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import test.leco.com.zgz.R;
import test.leco.com.zgz.t.data.IndustryInvolvedItem;

/**
 * Created by Administrator on 2016/12/0015.
 */

public class IndustryInvolvedAdapter extends BaseAdapter {
    Context context;
    List<IndustryInvolvedItem> list;
    LayoutInflater inflater;

    public IndustryInvolvedAdapter(Context context, List<IndustryInvolvedItem> list) {
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
        if (convertView == null) {
            holder = new Holder();
            convertView = inflater.inflate(R.layout.t_industry_involved_item, null);
            holder.type = (TextView) convertView.findViewById(R.id.type);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        IndustryInvolvedItem industryInvolvedItem = list.get(position);
        holder.type.setText(industryInvolvedItem.getType());

        return convertView;
    }

    class Holder {
        TextView type;
    }
}
