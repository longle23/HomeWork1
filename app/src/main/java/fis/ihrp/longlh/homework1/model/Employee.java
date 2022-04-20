package fis.ihrp.longlh.homework1.model;

public class Employee {
    private String empID;
    private String gender;
    private String item1;
    private String item2;
    private String item4;
    private String avatar;

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getItem1() {
        return item1;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public String getItem4() {
        return item4;
    }

    public void setItem4(String item4) {
        this.item4 = item4;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Employee(String empID, String gender, String item1, String item2, String item4, String avatar) {
        this.empID = empID;
        this.gender = gender;
        this.item1 = item1;
        this.item2 = item2;
        this.item4 = item4;
        this.avatar = avatar;
    }

    public Employee(){

    }

}
