package test.leco.com.zgz.t;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import test.leco.com.zgz.R;

/**
 * Created by Administrator on 2016/12/0011.
 */

public class StartActivity extends Activity {
    ViewFlipper viewFlipper;
    int[] image = {R.mipmap.start1,R.mipmap.start2};

    GestureDetector gestureDetector; //手势监听

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_start_page);
        viewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);

        for (int i = 0; i< image.length;i++){
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT));
            imageView.setImageResource(image[i]);
            viewFlipper.addView(imageView);
        }

        viewFlipper.setFlipInterval(2000); //设置切换时间间隔为2秒
        viewFlipper.startFlipping();

    }

}
