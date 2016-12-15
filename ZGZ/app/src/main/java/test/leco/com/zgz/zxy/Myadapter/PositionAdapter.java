package test.leco.com.zgz.zxy.Myadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import test.leco.com.zgz.R;

import static test.leco.com.zgz.R.id.someday;

/**
 * Created by Administrator on 2016/12/15.
 */

public class PositionAdapter extends BaseAdapter {
    List<HashMap<String,Object>> list;
    Context context;
    LayoutInflater layoutInflater;
    public PositionAdapter(List<HashMap<String,Object>> list,Context context){
        this.list=list;
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
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
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=layoutInflater.inflate(R.layout.listitem_position_layout,null);
            viewHolder.cpimage= (ImageView) convertView.findViewById(R.id.logo);
            viewHolder.position= (TextView) convertView.findViewById(R.id.position);
            viewHolder.cpname= (TextView) convertView.findViewById(R.id.cpname);
            viewHolder.city= (TextView) convertView.findViewById(R.id.city);
            viewHolder.someday= (TextView) convertView.findViewById(someday);
            viewHolder.pay= (TextView) convertView.findViewById(R.id.pay);
            convertView.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) convertView.getTag();
        HashMap<String,Object> map =list.get(position);
        viewHolder.cpimage.setImageResource((Integer) map.get("cpimage"));
        viewHolder.position.setText(map.get("position").toString());
        viewHolder.cpname.setText(map.get("cpname").toString());
        viewHolder.city.setText(map.get("city").toString());
        viewHolder.someday.setText(map.get("someday").toString());
        viewHolder.pay.setText(map.get("pay").toString());
        return convertView;
    }
    class ViewHolder{
        ImageView cpimage;
        TextView position,cpname,city,someday,pay;
    }
}
