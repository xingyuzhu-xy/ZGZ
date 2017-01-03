package test.leco.com.zgz.t.data;

/**
 * Created by Administrator on 2016/12/0015.
 */

public class SearchListItem {
    private String postid;//职位id
    private String companyName;//公司名
    private String tiem;//时间
    private String positionName;//职位名
    private String address;//地址
    private String education;//学历
    private String salary;//薪资
    private String recuitPersonId;

    public String getRecuitPersonId() {
        return recuitPersonId;
    }

    public void setRecuitPersonId(String recuitPersonId) {
        this.recuitPersonId = recuitPersonId;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTiem() {
        return tiem;
    }

    public void setTiem(String tiem) {
        this.tiem = tiem;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }


}
