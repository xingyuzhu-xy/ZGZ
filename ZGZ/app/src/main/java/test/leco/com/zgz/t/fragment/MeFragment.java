package test.leco.com.zgz.t.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.OutputStream;

import test.leco.com.zgz.R;
import test.leco.com.zgz.t.data.MyAppLication;
import test.leco.com.zgz.zxy.LoginActivity;
import test.leco.com.zgz.zxy.MyAdviceActivity;
import test.leco.com.zgz.zxy.MyAttentionActivity;
import test.leco.com.zgz.zxy.MyCollectActivity;
import test.leco.com.zgz.zxy.MyDeliverActivity;
import test.leco.com.zgz.zxy.MyDownloadActivity;
import test.leco.com.zgz.zxy.MyMessageActivity;
import test.leco.com.zgz.zxy.MyResumeActivity;
import test.leco.com.zgz.zxy.MySysSettingActivity;
import test.leco.com.zgz.zxy.RegistActivity;
import test.leco.com.zgz.zxy.Utils.AlbumTools;
import test.leco.com.zgz.zxy.Utils.HeadImage;
import test.leco.com.zgz.zxy.Utils.ImageCat;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MeFragment extends Fragment {
    HeadImage head_img;
    RelativeLayout resume,deliver,download,message,collect,attention,setting,advice;
    TextView login,register;//登录，注册

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my_setting_layout, null);
        resume = (RelativeLayout) view.findViewById(R.id.my_resume);//我的简历
        deliver = (RelativeLayout) view.findViewById(R.id.my_deliver);//投递记录
        download = (RelativeLayout) view.findViewById(R.id.my_download);//谁下载了我的简历
        message = (RelativeLayout) view.findViewById(R.id.my_message);//消息通知
        collect = (RelativeLayout) view.findViewById(R.id.my_collcet);//我的收藏
        attention = (RelativeLayout) view.findViewById(R.id.my_attention);//我的关注
        setting = (RelativeLayout) view.findViewById(R.id.my_setting);//系统设置
        advice = (RelativeLayout) view.findViewById(R.id.my_advice);//意见反馈
        head_img = (HeadImage) view.findViewById(R.id.head_img);
        login= (TextView) view.findViewById(R.id.login_textview);
        register= (TextView) view.findViewById(R.id.regist_textview);
        resume.setOnClickListener(listener);
        deliver.setOnClickListener(listener);
        download.setOnClickListener(listener);
        message.setOnClickListener(listener);
        collect.setOnClickListener(listener);
        attention.setOnClickListener(listener);
        setting.setOnClickListener(listener);
        advice.setOnClickListener(listener);
        login.setOnClickListener(listener);
        register.setOnClickListener(listener);
        head_img.setOnClickListener(listener);
        return view;


    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.my_resume:
                    //自定义一个Application替换系统自带的application并在里面存放一个boolean值islogin
                    //然后全局的islogin都从application里面取
                    //application里面的islogin是从sharepreferences里面取
                    //所有登录或者注销能够造成登录状态改变的操作都要去修改sp里面islogin的值
                    //如果登录了，。就可以把用户的用户名和密码存在sp里，再次登录时自动调用登录接口，
                    // 而不需要用户手动登录
                    MyAppLication myAppLication=(MyAppLication)getActivity().getApplication();
                    boolean isLogin = myAppLication.isLogin();
                    if (isLogin){
                        intent=new Intent(getActivity(),MyResumeActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getActivity(),"请登录账号",Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.my_deliver:
                    intent=new Intent(getActivity(),MyDeliverActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_download:
                    intent=new Intent(getActivity(),MyDownloadActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_message:
                    intent=new Intent(getActivity(),MyMessageActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_collcet:
                    intent=new Intent(getActivity(),MyCollectActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_attention:
                    intent=new Intent(getActivity(),MyAttentionActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_setting:
                    intent=new Intent(getActivity(),MySysSettingActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_advice:
                    intent=new Intent(getActivity(),MyAdviceActivity.class);
                    startActivity(intent);
                    break;
                case R.id.login_textview:
                    intent=new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    break;
                case R.id.regist_textview:
                    intent=new Intent(getActivity(), RegistActivity.class);
                    startActivity(intent);
                    break;
                case R.id.head_img:
                    AlbumTools.openAlbum(getActivity(),REQUEST);
                    break;
            }
        }
    };

    private final static int REQUEST=100;
    private final static int REQUEST_IMAGE_CAT=101;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST:
                if(data==null){
                    return;
                }
                ImageCat.cat(data.getData(),120,120,getActivity(),REQUEST_IMAGE_CAT);//取得图像后，进行剪切
                break;
            case REQUEST_IMAGE_CAT:
                if(data==null){
                    return;
                }
                Bitmap bitmap=ImageCat.getBitmap(data);
                head_img.setBitmap(bitmap);
                try {
                    OutputStream os=getActivity().openFileOutput("head",getActivity().MODE_PRIVATE);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 90, os);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                //upload();
                break;
        }
    }
}

