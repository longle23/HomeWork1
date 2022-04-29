package fis.ihrp.longlh.homework1.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChuyenDuyetRequest {
    @SerializedName("AppVersion")
    @Expose
    private String appVersion;

    @SerializedName("DataHeader")
    @Expose
    private List<Param> dataHeader = null;

    @SerializedName("LangID")
    @Expose
    private String langID;

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

    public String getStoken() {
        return stoken;
    }

    public void setStoken(String stoken) {
        this.stoken = stoken;
    }

    public ChuyenDuyetRequest(String appVersion, List<Param> dataHeader, String langID, String stoken) {
        this.appVersion = appVersion;
        this.dataHeader = dataHeader;
        this.langID = langID;
        this.stoken = stoken;
    }

    public ChuyenDuyetRequest() {

    }

    /////
    public static class Param {
        private String LeaveTypeID;
        private String FromDate;
        private String ToDate;
        private String IsFromTom;
        private String IsToTom;
        private String Approver;
        private String ReplacePerson;
        private String Reason;
        private String Status;

        public Param(String leaveTypeID, String fromDate, String toDate, String isFromTom, String isToTom, String approver, String replacePerson, String reason, String status) {
            LeaveTypeID = leaveTypeID;
            FromDate = fromDate;
            ToDate = toDate;
            IsFromTom = isFromTom;
            IsToTom = isToTom;
            Approver = approver;
            ReplacePerson = replacePerson;
            Reason = reason;
            Status = status;
        }

        public Param() {

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

        public String getIsFromTom() {
            return IsFromTom;
        }

        public void setIsFromTom(String isFromTom) {
            IsFromTom = isFromTom;
        }

        public String getIsToTom() {
            return IsToTom;
        }

        public void setIsToTom(String isToTom) {
            IsToTom = isToTom;
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