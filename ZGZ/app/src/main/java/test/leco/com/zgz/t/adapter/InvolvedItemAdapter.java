package test.leco.com.zgz.t.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import test.leco.com.zgz.R;
import test.leco.com.zgz.t.data.IndustryInvolvedItem;

/**
 * Created by Administrator on 2016/12/0015.
 */

public class InvolvedItemAdapter extends BaseAdapter {

    Context context;
    List<IndustryInvolvedItem> list;
    LayoutInflater inflater;
    public InvolvedItemAdapter(Context context,List<IndustryInvolvedItem> list){
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
            convertView = inflater.inflate(R.layout.t_involved_item_list_item,null);
            holder.type = (TextView) convertView.findViewById(R.id.type);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
            convertView.setTag(holder);
        }else {
            holder = (Holder) convertView.getTag();
        }
        IndustryInvolvedItem industryInvolvedItem = list.get(position);
        holder.type.setText(industryInvolvedItem.getType());
        convertView.setTag(R.id.ps,position);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Holder holder= (Holder) v.getTag();
                if(!holder.checkBox.isChecked()){
                    holder.checkBox.setChecked(true);
                }else {
                    holder.checkBox.setChecked(false);
                }
                int ps=(int)v.getTag(R.id.ps);
                String test = (String) holder.type.getText();
                itemClickListener.click(ps,test);
            }
        });
        return convertView;
    }
    ItemClickListener itemClickListener;
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }

    public interface ItemClickListener{
        void click(int poistion,String text);
    }
    class Holder{
        TextView type;
        CheckBox checkBox;
    }
}
