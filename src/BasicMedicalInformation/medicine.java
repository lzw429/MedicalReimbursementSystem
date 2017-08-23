package BasicMedicalInformation;

import javax.swing.*;
import java.io.*;

public class Medicine {
    private String coding;//药品编码
    private String ChineseName;//药品名称
    private String EnglishName;//英文名称
    private int chargeCategory;//收费类别：西药0 中成药1 中草药2
    private int prescriptionMark;//处方药标志：处方药0 甲类非处方药1 乙类非处方药2
    private int feeLevel;//收费项目等级：甲0 乙1 丙2
    private String dosageUnit;//药品剂量单位
    private double maximumPrice;//最高限价（人民币：元）
    private boolean hospitalPreparationSigns;//院内制剂标志 是1 否0
    private boolean needApproval;//是否需要审批标志 是1 否0
    private int hospitalGrade;//医院等级 一级医院0 二级医院1 三级医院2 社区医院3
    private String dosageForm;//剂型
    private String frequency;//使用频次
    private String usage;//用法
    private String unit;//单位
    private String specification;//规格
    private String limitDays;//限定天数
    private String tradeName;//药品商品名
    private String factory;//药厂名称
    private String ChineseMedicineProspectiveWord;//国药准字
    private String remarks;//备注
    private String nationalCatelogCode;//国家目录编码
    private String limitUsage;//限制使用范围
    private String origin;//产地

    //set 与 get 方法
    public String getCoding() {
        return coding;
    }

    public void setCoding(String coding) {
        this.coding = coding;
    }

    public String getChineseName() {
        return ChineseName;
    }

    public void setChineseName(String chineseName) {
        ChineseName = chineseName;
    }

    public String getEnglishName() {
        return EnglishName;
    }

    public void setEnglishName(String englishName) {
        EnglishName = englishName;
    }

    public int getChargeCategory() {
        return chargeCategory;
    }

    public void setChargeCategory(int chargeCategory) {
        this.chargeCategory = chargeCategory;
    }

    public int getPrescriptionMark() {
        return prescriptionMark;
    }

    public void setPrescriptionMark(int prescriptionMark) {
        this.prescriptionMark = prescriptionMark;
    }

    public int getFeeLevel() {
        return feeLevel;
    }

    public void setFeeLevel(int feeLevel) {
        this.feeLevel = feeLevel;
    }

    public String getDosageUnit() {
        return dosageUnit;
    }

    public void setDosageUnit(String dosageUnit) {
        this.dosageUnit = dosageUnit;
    }

    public double getMaximumPrice() {
        return maximumPrice;
    }

    public void setMaximumPrice(double maximumPrice) {
        this.maximumPrice = maximumPrice;
    }

    public boolean isHospitalPreparationSigns() {
        return hospitalPreparationSigns;
    }

    public void setHospitalPreparationSigns(boolean hospitalPreparationSigns) {
        this.hospitalPreparationSigns = hospitalPreparationSigns;
    }

    public boolean isNeedApproval() {
        return needApproval;
    }

    public void setNeedApproval(boolean needApproval) {
        this.needApproval = needApproval;
    }

    public int getHospitalGrade() {
        return hospitalGrade;
    }

    public void setHospitalGrade(int hospitalGrade) {
        this.hospitalGrade = hospitalGrade;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getLimitDays() {
        return limitDays;
    }

    public void setLimitDays(String limitDays) {
        this.limitDays = limitDays;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getChineseMedicineProspectiveWord() {
        return ChineseMedicineProspectiveWord;
    }

    public void setChineseMedicineProspectiveWord(String chineseMedicineProspectiveWord) {
        ChineseMedicineProspectiveWord = chineseMedicineProspectiveWord;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getNationalCatelogCode() {
        return nationalCatelogCode;
    }

    public void setNationalCatelogCode(String nationalCatelogCode) {
        this.nationalCatelogCode = nationalCatelogCode;
    }

    public String getLimitUsage() {
        return limitUsage;
    }

    public void setLimitUsage(String limitUsage) {
        this.limitUsage = limitUsage;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    //成员方法
    public boolean readCSV(String coding) throws IOException
    // 从CSV文件中读取到 Mediciine 的实例中
    {
        String item[] = new String[25];
        boolean find = false;
        BufferedReader reader = new BufferedReader(new FileReader("data/Medicine.csv"));
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
        this.ChineseName = item[1];
        this.EnglishName = item[2];
        this.chargeCategory = Integer.parseInt(item[3]);
        this.prescriptionMark = Integer.parseInt(item[4]);
        this.feeLevel = Integer.parseInt(item[5]);
        this.dosageUnit = item[6];
        this.maximumPrice = Double.parseDouble(item[7]);
        this.hospitalPreparationSigns = Integer.parseInt(item[8]) == 1;
        this.needApproval = Integer.parseInt(item[9]) == 1;
        this.hospitalGrade = Integer.parseInt(item[10]);
        this.dosageForm = item[11];
        this.frequency = item[12];
        this.usage = item[13];
        this.unit = item[14];
        this.specification = item[15];
        this.limitDays = item[16];
        this.tradeName = item[17];
        this.factory = item[18];
        this.ChineseMedicineProspectiveWord = item[19];
        this.remarks = item[20];
        this.nationalCatelogCode = item[21];
        this.limitUsage = item[22];
        this.origin = item[23];
        return true;
    }


    public boolean writeCSV() throws IOException
    // Medicine 实例的信息写入CSV
    {
        String item[] = new String[25];
        item[0] = this.coding;
        if (readCSV(item[0]))//CSV 中已有当前实例
            return false;
        item[1] = this.ChineseName;
        item[2] = this.EnglishName;
        item[3] = Integer.toString(chargeCategory);
        item[4] = Integer.toString(prescriptionMark);
        item[5] = Integer.toString(feeLevel);
        item[6] = dosageUnit;
        item[7] = Double.toString(maximumPrice);
        if (hospitalPreparationSigns)
            item[8] = "1";
        else item[8] = "0";
        if (needApproval)
            item[9] = "1";
        else item[9] = "0";
        item[10] = Integer.toString(hospitalGrade);
        item[11] = dosageForm;
        item[12] = frequency;
        item[13] = usage;
        item[14] = unit;
        item[15] = specification;
        item[16] = limitDays;
        item[17] = tradeName;
        item[18] = factory;
        item[19] = ChineseMedicineProspectiveWord;
        item[20] = remarks;
        item[21] = nationalCatelogCode;
        item[22] = limitUsage;
        item[23] = origin;
        try {
            File file = new File("data/Medicine.csv");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            for (int i = 0; i != 23; ++i)
                writer.write(item[i] + ",");

            writer.write(item[23]);
            writer.newLine();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "文件未找到", "错误", JOptionPane.ERROR_MESSAGE);
        }
        return true;
    }

    public String toString() {
        return "编码" + this.coding + "；" + this.ChineseName + "；" + "单位：" + this.unit + "；" + "规格：" + this.specification;
    }
}
