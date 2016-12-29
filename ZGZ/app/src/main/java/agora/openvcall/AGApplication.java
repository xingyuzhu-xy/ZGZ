package agora.openvcall;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import agora.openvcall.model.CurrentUserSettings;
import agora.openvcall.model.WorkerThread;

public class AGApplication extends Application {

    private WorkerThread mWorkerThread;

    public synchronized void initWorkerThread() {
        if (mWorkerThread == null) {
            mWorkerThread = new WorkerThread(getApplicationContext());
            mWorkerThread.start();

            mWorkerThread.waitForReady();
        }
    }

    public synchronized WorkerThread getWorkerThread() {
        return mWorkerThread;
    }

    public synchronized void deInitWorkerThread() {
        mWorkerThread.exit();
        try {
            mWorkerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mWorkerThread = null;
    }

    public static final CurrentUserSettings mVideoSettings = new CurrentUserSettings();


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
