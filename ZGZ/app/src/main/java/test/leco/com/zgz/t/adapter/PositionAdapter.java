package test.leco.com.zgz.t.adapter;

import android.content.Context;
import android.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import test.leco.com.zgz.R;
import test.leco.com.zgz.t.data.PositionItem;

/**
 * Created by Administrator on 2016/12/26.
 */
public class PositionAdapter extends BaseAdapter {
    Context context;
    ArrayList<PositionItem> list;
    LayoutInflater layoutInflater;

    public PositionAdapter(Context context, ArrayList<PositionItem> list) {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHodler viewHodler = null;
        if (view == null) {
            viewHodler = new ViewHodler();
            view = layoutInflater.inflate(R.layout.position_item, null);
            viewHodler.positonID = (TextView) view.findViewById(R.id.position_id);
            viewHodler.positionName = (TextView) view.findViewById(R.id.position_name);
            view.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) view.getTag();
        }
        viewHodler.positonID.setText(list.get(i).getPositionID() + "");
        viewHodler.positionName.setText(list.get(i).getPositionName());

        view.setTag(R.id.positiondid, i);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewHodler viewHodler1 = (ViewHodler) view.getTag();
                int positionid=(int)view.getTag(R.id.positiondid); //取出设置的唯一标识
                String test = (String) viewHodler1.positonID.getText();
                String posititoName = (String) viewHodler1.positionName.getText();
                itemClickListener.click(positionid,test, posititoName);
                Log.i("posititon>>>>>>>>>>", "" + posititoName);
            }
        });


        return view;
    }

    ItemClickListener itemClickListener;
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener; //实现下面这个接口的监听
    }

    public interface ItemClickListener{  //想要得得到的数据
        void click(int poistion,String text, String posititoName);
    }

    class ViewHodler {
        TextView positonID;
        TextView positionName;
    }
}
