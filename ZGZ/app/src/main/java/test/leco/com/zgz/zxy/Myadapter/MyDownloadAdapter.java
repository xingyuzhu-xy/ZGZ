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

/**
 * Created by Administrator on 2016/12/15.
 */

public class MyDownloadAdapter extends BaseAdapter {
    List<HashMap<String,Object>> list;
    Context context;
    LayoutInflater layoutInflater;
    public MyDownloadAdapter(List<HashMap<String,Object>> list,Context context){
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
            convertView=layoutInflater.inflate(R.layout.listitem_my_download_layout,null);
            viewHolder.cpimage= (ImageView) convertView.findViewById(R.id.my_download_cpimage);
            viewHolder.cpname= (TextView) convertView.findViewById(R.id.my_download_cpname);
            viewHolder.time= (TextView) convertView.findViewById(R.id.cp_download_time);
            convertView.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) convertView.getTag();
        HashMap<String,Object> map=list.get(position);
        viewHolder.cpimage.setImageResource((Integer) map.get("cpimage"));
        viewHolder.cpname.setText(map.get("cpname").toString());
        viewHolder.time.setText(map.get("time").toString());
        return convertView;
    }
    class ViewHolder{
       ImageView cpimage;
        TextView cpname;
        TextView time;
    }
}
