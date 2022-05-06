package fis.ihrp.longlh.homework1.model;

public class TimDonNghiResponse {

    private String leaveRecordID;
    private String empCode;
    private String empName;
    private String company;
    private String level1;
    private String level2;
    private String level3;
    private String level4;
    private String jobTitle;
    private String fromDate;
    private String toDate;
    private String duration;
    private String leaveName;
    private String taken;
    private String reason;
    private String actionDate;
    private String statusID;
    private String status;
    private String nguoiPheDuyet;

    public TimDonNghiResponse(String leaveRecordID, String empCode, String empName, String company, String level1, String level2, String level3, String level4, String jobTitle, String fromDate, String toDate, String duration, String leaveName, String taken, String reason, String actionDate, String statusID, String status, String nguoiPheDuyet) {
        this.leaveRecordID = leaveRecordID;
        this.empCode = empCode;
        this.empName = empName;
        this.company = company;
        this.level1 = level1;
        this.level2 = level2;
        this.level3 = level3;
        this.level4 = level4;
        this.jobTitle = jobTitle;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.duration = duration;
        this.leaveName = leaveName;
        this.taken = taken;
        this.reason = reason;
        this.actionDate = actionDate;
        this.statusID = statusID;
        this.status = status;
        this.nguoiPheDuyet = nguoiPheDuyet;
    }

    public TimDonNghiResponse(){

    }

    public String getLeaveRecordID() {
        return leaveRecordID;
    }

    public void setLeaveRecordID(String leaveRecordID) {
        this.leaveRecordID = leaveRecordID;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLevel1() {
        return level1;
    }

    public void setLevel1(String level1) {
        this.level1 = level1;
    }

    public String getLevel2() {
        return level2;
    }

    public void setLevel2(String level2) {
        this.level2 = level2;
    }

    public String getLevel3() {
        return level3;
    }

    public void setLevel3(String level3) {
        this.level3 = level3;
    }

    public String getLevel4() {
        return level4;
    }

    public void setLevel4(String level4) {
        this.level4 = level4;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
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

    public String getLeaveName() {
        return leaveName;
    }

    public void setLeaveName(String leaveName) {
        this.leaveName = leaveName;
    }

    public String getTaken() {
        return taken;
    }

    public void setTaken(String taken) {
        this.taken = taken;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getActionDate() {
        return actionDate;
    }

    public void setActionDate(String actionDate) {
        this.actionDate = actionDate;
    }

    public String getStatusID() {
        return statusID;
    }

    public void setStatusID(String statusID) {
        this.statusID = statusID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNguoiPheDuyet() {
        return nguoiPheDuyet;
    }

    public void setNguoiPheDuyet(String nguoiPheDuyet) {
        this.nguoiPheDuyet = nguoiPheDuyet;
    }


}

