package BasicMedicalInformation;

public class treatment {
    private int coding;//项目编码
    private String name;//项目名称
    private int chargeCatagory;//收费等级
    private int feeLevel;//收费项目等级
    private int hospitalGrade;//医院等级
    private boolean needApproval;//是否需要审批
    private String unit;//单位
    private String manufacturer;//生产厂家
    private String remarks;//备注
    private String restrictions;//限制使用范围

    public int getCoding() {
        return coding;
    }

    public void setCoding(int coding) {
        this.coding = coding;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChargeCatagory() {
        return chargeCatagory;
    }

    public void setChargeCatagory(int chargeCatagory) {
        this.chargeCatagory = chargeCatagory;
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
}
