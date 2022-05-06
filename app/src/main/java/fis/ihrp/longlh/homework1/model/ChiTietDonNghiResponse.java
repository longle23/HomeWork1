package fis.ihrp.longlh.homework1.model;

public class ChiTietDonNghiResponse {

    private String leaveRecordID;
    private String empID;
    private String empName;
    private String leaveTypeID;
    private String leaveName;
    private String fromDate;
    private String toDate;
    private String aP1;
    private String aP2;
    private String duration;
    private String statusID;
    private String taken;
    private String statusName;
    private String reason;
    private String actionDate;
    private String replacePerson;
    private String replacePersonName;
    private String approver;
    private String approverName;

    public ChiTietDonNghiResponse(String leaveRecordID, String empID, String empName, String leaveTypeID, String leaveName, String fromDate, String toDate, String aP1, String aP2, String duration, String statusID, String taken, String statusName, String reason, String actionDate, String replacePerson, String replacePersonName, String approver, String approverName) {
        this.leaveRecordID = leaveRecordID;
        this.empID = empID;
        this.empName = empName;
        this.leaveTypeID = leaveTypeID;
        this.leaveName = leaveName;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.aP1 = aP1;
        this.aP2 = aP2;
        this.duration = duration;
        this.statusID = statusID;
        this.taken = taken;
        this.statusName = statusName;
        this.reason = reason;
        this.actionDate = actionDate;
        this.replacePerson = replacePerson;
        this.replacePersonName = replacePersonName;
        this.approver = approver;
        this.approverName = approverName;
    }

    public ChiTietDonNghiResponse(){

    }

    public String getLeaveRecordID() {
        return leaveRecordID;
    }

    public void setLeaveRecordID(String leaveRecordID) {
        this.leaveRecordID = leaveRecordID;
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

    public String getLeaveTypeID() {
        return leaveTypeID;
    }

    public void setLeaveTypeID(String leaveTypeID) {
        this.leaveTypeID = leaveTypeID;
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

    public String getaP1() {
        return aP1;
    }

    public void setaP1(String aP1) {
        this.aP1 = aP1;
    }

    public String getaP2() {
        return aP2;
    }

    public void setaP2(String aP2) {
        this.aP2 = aP2;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStatusID() {
        return statusID;
    }

    public void setStatusID(String statusID) {
        this.statusID = statusID;
    }

    public String getTaken() {
        return taken;
    }

    public void setTaken(String taken) {
        this.taken = taken;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
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

    public String getReplacePerson() {
        return replacePerson;
    }

    public void setReplacePerson(String replacePerson) {
        this.replacePerson = replacePerson;
    }

    public String getReplacePersonName() {
        return replacePersonName;
    }

    public void setReplacePersonName(String replacePersonName) {
        this.replacePersonName = replacePersonName;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getApproverName() {
        return approverName;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName;
    }


}
