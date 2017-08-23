package BasicInfo;

import java.io.IOException;

public class Unit {
    private String coding;//单位编号
    private String name;//单位名称
    private int type;//单位类型
    private String address;//地址
    private String zipcode;//邮编
    private String tel;//联系电话

    public String getCoding() {
        return coding;
    }

    public void setCoding(String coding) {
        this.coding = coding;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    //成员方法
    public  boolean readCSV(String coding)
        throws IOException
    {
        String item[]=new String [25];
        boolean find = false;
    }
}
