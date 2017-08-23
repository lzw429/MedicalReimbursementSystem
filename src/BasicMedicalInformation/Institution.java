package BasicMedicalInformation;

import javax.swing.*;
import java.io.*;

public class Institution {
    private String coding;//定点医疗机构编号
    private String name;//服务机构名称
    private int hospitalGrade;//医院等级
    private int type;//医疗机构类别
    private String zipcode;//邮政编码
    private String LRName;//法定代表人姓名
    private String LRTel;//法人代表移动电话
    private String contactPersonName;//联系人姓名
    private String contactTel;//联系电话
    private String contactPersonTel;//联系人移动电话
    private String address;//地址
    private String remarks;//备注

    public int getHospitalGrade() {
        return hospitalGrade;
    }

    public void setHospitalGrade(int hospitalGrade) {
        this.hospitalGrade = hospitalGrade;
    }

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

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getLRName() {
        return LRName;
    }

    public void setLRName(String LRName) {
        this.LRName = LRName;
    }

    public String getLRTel() {
        return LRTel;
    }

    public void setLRTel(String LRTel) {
        this.LRTel = LRTel;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getContactPersonTel() {
        return contactPersonTel;
    }

    public void setContactPersonTel(String contactPersonTel) {
        this.contactPersonTel = contactPersonTel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    //成员方法
    public boolean readCSV(String coding) throws IOException
    // 从CSV文件中读取到 Institution 的实例中
    {
        String item[] = new String[20];
        boolean find = false;
        BufferedReader reader = new BufferedReader(new FileReader("data/Institution.csv"));
        reader.readLine();//第一行为标题，跳过
        String line;
        while ((line = reader.readLine()) != null) {
            item = line.split(",");
            if (item[0].equals(coding)) {
                find = true;
                break;
            }
        }
        reader.close();
        if (!find)
            return false;
        this.coding = item[0];
        this.name = item[1];
        this.hospitalGrade = Integer.parseInt(item[2]);
        this.type = Integer.parseInt(item[3]);
        this.zipcode = item[4];
        this.LRName = item[5];
        this.LRTel = item[6];
        this.contactPersonName = item[7];
        this.contactTel = item[8];
        this.contactPersonTel = item[9];
        this.address = item[10];
        this.remarks = item[11];
        return true;
    }

    public boolean writeCSV() throws IOException
    // Institution 实例的信息写入CSV
    {
        String item[] = new String[25];
        item[0] = this.coding;
        if (readCSV(item[0]))// CSV已有当前实例
            return false;
        item[1] = this.name;
        item[2] = Integer.toString(this.hospitalGrade);
        item[3] = Integer.toString(this.type);
        item[4] = this.zipcode;
        item[5] = this.LRName;
        item[6] = this.LRTel;
        item[7] = this.contactPersonName;
        item[8] = this.contactTel;
        item[9] = this.contactPersonTel;
        item[10] = this.address;
        item[11] = this.remarks;
        try {
            File file = new File("data/Institution.csv");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            for (int i = 0; i != 11; ++i)
                writer.write(item[i] + ",");
            writer.write(item[11]);
            writer.newLine();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "文件未找到", "错误", JOptionPane.ERROR_MESSAGE);
        }
        return true;
    }

    public String toString() {
        return "编号" + this.coding + "；" + this.name + "；" + "地址：" + this.address + "；" + "法人：" + this.LRName;
    }
}
