package BasicMedicalInformation;

public class diseases {
    private int coding;//疾病编码
    private String name;//疾病名称
    private int catagory;//疾病种类
    private boolean isReimbursed;//病种报销标志
    private String remarks;//备注

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

    public int getCatagory() {
        return catagory;
    }

    public void setCatagory(int catagory) {
        this.catagory = catagory;
    }

    public boolean isReimbursed() {
        return isReimbursed;
    }

    public void setReimbursed(boolean reimbursed) {
        isReimbursed = reimbursed;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
