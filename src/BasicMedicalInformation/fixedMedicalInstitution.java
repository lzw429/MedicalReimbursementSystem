package BasicMedicalInformation;

public class fixedMedicalInstitution {
    private int coding;//定点医疗机构编号
    private String name;//服务机构名称
    private int hospitalGrade;//医院等级
    private int type;//医疗机构类别
    private int zipcode;//邮政编码
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


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
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
}
