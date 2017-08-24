package BasicMedicalInformation;

import javax.swing.*;
import java.io.*;

public class Treatment {
    private String coding;//项目编码
    private String name;//项目名称
    private int chargeCategory;//收费类别
    private int feeLevel;//收费项目等级
    private int hospitalGrade;//医院等级
    private boolean needApproval;//是否需要审批 1需要 0不需要
    private String unit;//单位
    private String manufacturer;//生产厂家
    private String remarks;//备注
    private String restrictions;//限制使用范围

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

    public int getChargeCategory() {
        return chargeCategory;
    }

    public void setChargeCategory(int chargeCategory) {
        this.chargeCategory = chargeCategory;
    }

    public int getFeeLevel() {
        return feeLevel;
    }

    public void setFeeLevel(int feeLevel) {
        this.feeLevel = feeLevel;
    }

    public int getHospitalGrade() {
        return hospitalGrade;
    }

    public void setHospitalGrade(int hospitalGrade) {
        this.hospitalGrade = hospitalGrade;
    }

    public boolean isNeedApproval() {
        return needApproval;
    }

    public void setNeedApproval(boolean needApproval) {
        this.needApproval = needApproval;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions;
    }

    //成员方法
    public boolean readCSV(String coding) throws IOException
    // 从CSV文件中读取到 Treatment 的实例中
    {
        String item[] = new String[15];
        boolean find = false;
        BufferedReader reader = new BufferedReader(new FileReader("data/Treatment.csv"));
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
        this.chargeCategory = Integer.parseInt(item[2]);
        this.feeLevel = Integer.parseInt(item[3]);
        this.hospitalGrade = Integer.parseInt(item[4]);
        this.needApproval = Integer.parseInt(item[5]) == 1;
        this.unit = item[6];
        this.manufacturer = item[7];
        this.remarks = item[8];
        this.restrictions = item[9];
        return true;
    }

    public boolean writeCSV() throws IOException
    //Treatment 实例的信息写入CSV
    {
        String item[] = new String[15];
        item[0] = this.coding;
        item[1] = this.name;
        item[2] = Integer.toString(this.chargeCategory);
        item[3] = Integer.toString(this.feeLevel);
        item[4] = Integer.toString(this.hospitalGrade);
        if (this.needApproval)
            item[5] = "1";
        else item[5] = "0";
        item[6] = this.unit;
        item[7] = this.manufacturer;
        item[8] = this.remarks;
        item[9] = this.restrictions;
        try {
            File file = new File("data/Treatment.csv");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            for (int i = 0; i != 9; ++i)
                writer.write(item[i] + ",");

            writer.write(item[9]);
            writer.newLine();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "文件未找到", "错误", JOptionPane.ERROR_MESSAGE);
        }

        return true;
    }

    public String toString() {
        return "编码" + this.coding + "；" + this.name + "；" + "单位：" + this.unit + "；" + "生产厂家：" + this.manufacturer;
    }
}
