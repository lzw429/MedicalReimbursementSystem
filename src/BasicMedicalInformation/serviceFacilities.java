package BasicMedicalInformation;

public class ServiceFacilities {
    private int coding;//医疗服务设施编码
    private String name;//服务设施名称
    private int chargeCategory;//收费类别
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

    public int getChargeCategory() {
        return chargeCategory;
    }

    public void setChargeCategory(int chargeCategory) {
        this.chargeCategory = chargeCategory;
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
