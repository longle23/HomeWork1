package fis.ihrp.longlh.homework1.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TimDonNghiRequest {

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

    public TimDonNghiRequest(String appVersion, List<Param> dataHeader, String langID, String stoken) {
        this.appVersion = appVersion;
        this.dataHeader = dataHeader;
        this.langID = langID;
        this.stoken = stoken;
    }

    public TimDonNghiRequest() {

    }

    /////
    public static class Param {

        private String FromDate;
        private String LeaveTypeID;
        private String Status;
        private String ToDate;
        private String Top;

        public Param(String fromDate, String leaveTypeID, String status, String toDate, String top) {
            FromDate = fromDate;
            LeaveTypeID = leaveTypeID;
            Status = status;
            ToDate = toDate;
            Top = top;
        }

        public Param(){

        }

        public String getFromDate() {
            return FromDate;
        }

        public void setFromDate(String fromDate) {
            FromDate = fromDate;
        }

        public String getLeaveTypeID() {
            return LeaveTypeID;
        }

        public void setLeaveTypeID(String leaveTypeID) {
            LeaveTypeID = leaveTypeID;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String status) {
            Status = status;
        }

        public String getToDate() {
            return ToDate;
        }

        public void setToDate(String toDate) {
            ToDate = toDate;
        }

        public String getTop() {
            return Top;
        }

        public void setTop(String top) {
            Top = top;
        }
    }


}
