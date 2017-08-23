package BasicMedicalInformation;

import javax.swing.*;
import java.io.*;

public class Disease {
    private String coding;//疾病编码
    private String name;//疾病名称
    private int category;//疾病种类
    private int reimbursementSigns;//病种报销标志
    private String remarks;//备注

    public void setCoding(String coding) {
        this.coding = coding;
    }

    public String getCoding() {
        return coding;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getReimbursementSigns() {
        return reimbursementSigns;
    }

    public void setReimbursementSigns(int reimbursementSigns) {
        this.reimbursementSigns = reimbursementSigns;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    //成员方法
    public boolean readCSV(String coding) throws IOException
    // 从CSV文件中读取到 Disease 的实例中
    {
        String item[] = new String[10];
        boolean find = false;
        BufferedReader reader = new BufferedReader(new FileReader("data/Disease.csv"));
        reader.readLine();
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
        this.category = Integer.parseInt(item[2]);
        this.reimbursementSigns = Integer.parseInt(item[3]);
        this.remarks = item[4];
        return true;
    }

    public boolean writeCSV() throws IOException
    // Disease 实例的信息写入CSV
    {
        String item[] = new String[10];
        item[0] = this.coding;
        if (readCSV(item[0]))//CSV 中已有当前病种
            return false;
        item[1] = this.name;
        item[2] = Integer.toString(category);
        item[3] = Integer.toString(reimbursementSigns);
        item[4] = this.remarks;
        try {
            File file = new File("data/Disease.csv");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            for (int i = 0; i != 4; ++i) {
                writer.write(item[i] + ",");
            }
            writer.write(item[4]);
            writer.newLine();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "文件未找到", "错误", JOptionPane.ERROR_MESSAGE);
        }
        return true;
    }

    public String toString() {
        String ca = "", re = "";
        switch (this.category) {
            case 1:
                ca = "心血管系统疾病";
                break;
            case 2:
                ca = "消化系统疾病";
                break;
            case 3:
                ca = "代谢内分泌疾病";
                break;
            case 4:
                ca = "造血系统疾病";
                break;
            case 5:
                ca = "肾脏疾病";
                break;
            case 6:
                ca = "神经系统";
                break;
            case 7:
                ca = "免疫系统疾病";
                break;
            case 8:
                ca = "传染病";
                break;
            case 9:
                ca = "呼吸系统疾病";
                break;
            case 10:
                ca = "恶性肿瘤";
                break;
            case 11:
                ca = "精神病";
                break;
            case 12:
                ca = "残疾";
                break;
            case 13:
                ca = "泌尿系统";
                break;
            case 14:
                ca = "其他";
                break;
        }
        switch (this.reimbursementSigns) {
            case 1:
                re = "二次报销";
                break;
            case 2:
                re = "非报销";
                break;
            case 3:
                re = "手工报销";
                break;
            case 4:
                re = "特殊报销";
                break;
            case 5:
                re = "医院就医";
                break;
            case 6:
                re = "中心报销";
                break;
        }
        return "编码" + this.coding + "；" + this.name + "；" + "疾病种类：" + ca + "；" + "报销标志：" + re;
    }
}
