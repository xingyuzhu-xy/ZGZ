package test.leco.com.zgz.zxy.Myadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import test.leco.com.zgz.R;

/**
 * Created by Administrator on 2016/12/15.
 */

public class MyCollectAdapter extends BaseAdapter {
    List<HashMap<String,Object>> list;
    Context context;
    LayoutInflater layoutInflater;
    public MyCollectAdapter(List<HashMap<String,Object>> list,Context context){
        this.list=list;
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
    }
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
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=layoutInflater.inflate(R.layout.listitem_my_collect_layout,null);
            viewHolder.position= (TextView) convertView.findViewById(R.id.my_collect_position);
            viewHolder.cpname= (TextView) convertView.findViewById(R.id.my_collect_cpname);
            viewHolder.location= (TextView) convertView.findViewById(R.id.my_collect_location);
            viewHolder.exp= (TextView) convertView.findViewById(R.id.my_collect_exp);
            viewHolder.pay= (TextView) convertView.findViewById(R.id.my_collect_pay);
            convertView.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) convertView.getTag();
        HashMap<String,Object> map=list.get(position);
        viewHolder.position.setText(map.get("position").toString());
        viewHolder.cpname.setText(map.get("cpname").toString());
        viewHolder.location.setText(map.get("location").toString());
        viewHolder.exp.setText(map.get("exp").toString());
        viewHolder.pay.setText(map.get("pay").toString());
        return convertView;
    }

    class ViewHolder{
        TextView position;
        TextView cpname;
        TextView location;
        TextView exp;
        TextView pay;
    }
}
