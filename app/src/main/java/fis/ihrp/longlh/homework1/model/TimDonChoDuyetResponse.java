package fis.ihrp.longlh.homework1.model;

public class TimDonChoDuyetResponse {

    private String empID;
    private String empName;
    private String gender;
    private String leaveRecordID;
    private String leaveName;
    private String fromDate;
    private String toDate;
    private String duration;
    private String taken;
    private String actionDate;
    private String reason;
    private String replacePerson;

    public TimDonChoDuyetResponse(String empID, String empName, String gender, String leaveRecordID, String leaveName, String fromDate, String toDate, String duration, String taken, String actionDate, String reason, String replacePerson) {
        this.empID = empID;
        this.empName = empName;
        this.gender = gender;
        this.leaveRecordID = leaveRecordID;
        this.leaveName = leaveName;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.duration = duration;
        this.taken = taken;
        this.actionDate = actionDate;
        this.reason = reason;
        this.replacePerson = replacePerson;
    }

    public TimDonChoDuyetResponse() {
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLeaveRecordID() {
        return leaveRecordID;
    }

    public void setLeaveRecordID(String leaveRecordID) {
        this.leaveRecordID = leaveRecordID;
    }

    public String getLeaveName() {
        return leaveName;
    }

    public void setLeaveName(String leaveName) {
        this.leaveName = leaveName;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTaken() {
        return taken;
    }

    public void setTaken(String taken) {
        this.taken = taken;
    }

    public String getActionDate() {
        return actionDate;
    }

    public void setActionDate(String actionDate) {
        this.actionDate = actionDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReplacePerson() {
        return replacePerson;
    }

    public void setReplacePerson(String replacePerson) {
        this.replacePerson = replacePerson;
    }

}
