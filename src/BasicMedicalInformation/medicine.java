package BasicMedicalInformation;

public class medicine {
    private int coding;//药品编码
    private String ChineseName;//药品名称
    private String EnglishName;//英文名称
    private int chargeCategory;//收费类别
    private int prescriptionMark;//处方药标志
    private int feeLevel;//收费项目等级
    private String dosageUnit;//药品剂量单位
    private int maximumPrice;//最高限价
    private boolean hospitalPreparationSigns;//院内制剂标志
    private boolean needApproval;//是否需要审批标志
    private int hospitalGrade;//医院等级

    public int getCoding() {
        return coding;
    }

    public void setCoding(int coding) {
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

    public int getMaximumPrice() {
        return maximumPrice;
    }

    public void setMaximumPrice(int maximumPrice) {
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

}
