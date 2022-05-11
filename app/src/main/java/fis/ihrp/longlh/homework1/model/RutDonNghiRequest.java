package fis.ihrp.longlh.homework1.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RutDonNghiRequest {

    @SerializedName("AppVersion")
    @Expose
    private String appVersion;

    @SerializedName("DataHeader")
    @Expose
    private List<Param> dataHeader = null;
    @SerializedName("LangID")
    @Expose
    private String langID;
    @SerializedName("OS")
    @Expose
    private String os;
    @SerializedName("Stoken")
    @Expose
    private String stoken;

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public List<Param> getDataHeader() {
        return dataHeader;
    }

    public void setDataHeader(List<Param> dataHeader) {
        this.dataHeader = dataHeader;
    }

    public String getLangID() {
        return langID;
    }

    public void setLangID(String langID) {
        this.langID = langID;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getStoken() {
        return stoken;
    }

    public void setStoken(String stoken) {
        this.stoken = stoken;
    }

    public RutDonNghiRequest(String appVersion, List<Param> dataHeader, String langID, String os, String stoken) {
        this.appVersion = appVersion;
        this.dataHeader = dataHeader;
        this.langID = langID;
        this.os = os;
        this.stoken = stoken;
    }

    public RutDonNghiRequest() {

    }

    /////
    public static class Param{

        private String LeaveRecordID;
        private String LeaveTypeID;
        private String FromDate;
        private String ToDate;
        private String AP1;
        private String AP2;
        private String Approver;
        private String ReplacePerson;
        private String Reason;
        private String Status;

        public Param(String leaveRecordID, String leaveTypeID, String fromDate, String toDate, String AP1, String AP2, String approver, String replacePerson, String reason, String status) {
            LeaveRecordID = leaveRecordID;
            LeaveTypeID = leaveTypeID;
            FromDate = fromDate;
            ToDate = toDate;
            this.AP1 = AP1;
            this.AP2 = AP2;
            Approver = approver;
            ReplacePerson = replacePerson;
            Reason = reason;
            Status = status;
        }

        public Param(){

        }

        public String getLeaveRecordID() {
            return LeaveRecordID;
        }

        public void setLeaveRecordID(String leaveRecordID) {
            LeaveRecordID = leaveRecordID;
        }

        public String getLeaveTypeID() {
            return LeaveTypeID;
        }

        public void setLeaveTypeID(String leaveTypeID) {
            LeaveTypeID = leaveTypeID;
        }

        public String getFromDate() {
            return FromDate;
        }

        public void setFromDate(String fromDate) {
            FromDate = fromDate;
        }

        public String getToDate() {
            return ToDate;
        }

        public void setToDate(String toDate) {
            ToDate = toDate;
        }

        public String getAP1() {
            return AP1;
        }

        public void setAP1(String AP1) {
            this.AP1 = AP1;
        }

        public String getAP2() {
            return AP2;
        }

        public void setAP2(String AP2) {
            this.AP2 = AP2;
        }

        public String getApprover() {
            return Approver;
        }

        public void setApprover(String approver) {
            Approver = approver;
        }

        public String getReplacePerson() {
            return ReplacePerson;
        }

        public void setReplacePerson(String replacePerson) {
            ReplacePerson = replacePerson;
        }

        public String getReason() {
            return Reason;
        }

        public void setReason(String reason) {
            Reason = reason;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String status) {
            Status = status;
        }

    }


}
