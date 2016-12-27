package test.leco.com.zgz.t.http;

/**
 * Created by Administrator on 2016/12/26.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
