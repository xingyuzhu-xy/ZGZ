package test.leco.com.zgz.t.data;

/**
 * Created by Administrator on 2016/12/0015.
 */

public class IndustryInvolvedItem {
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private  String type;
    boolean checkBox = false;

    public boolean isCheckBox() {
        return checkBox;
    }

    public void setCheckBox(boolean checkBox) {
        this.checkBox = checkBox;
    }
}
