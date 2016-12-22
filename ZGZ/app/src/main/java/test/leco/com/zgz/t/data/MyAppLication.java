package test.leco.com.zgz.t.data;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/12/0022.
 */

public class MyAppLication extends Application {
    private int id ;
    private boolean isLogin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        this.isLogin = login;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences sharedPreferences=getSharedPreferences("ZGZ", Context.MODE_PRIVATE);
        isLogin = sharedPreferences.getBoolean("isLogin",false);
        id = sharedPreferences.getInt("user_id",-1);
    }
}
