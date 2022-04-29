package fis.ihrp.longlh.homework1.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TinhPhepRequest {

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

    public TinhPhepRequest(String appVersion, List<Param> dataHeader, String langID, String stoken) {
        this.appVersion = appVersion;
        this.dataHeader = dataHeader;
        this.langID = langID;
        this.stoken = stoken;
    }

    public TinhPhepRequest() {

    }

    public static class Param {
        private String AP1;
        private String AP2;
        private String FromDate;
        private String IsFromTom;
        private String IsToTom;
        private String LeaveTypeID;
        private String ToDate;

        public Param(String AP1, String AP2, String fromDate, String isFromTom, String isToTom, String leaveTypeID, String toDate) {
            this.AP1 = AP1;
            this.AP2 = AP2;
            FromDate = fromDate;
            IsFromTom = isFromTom;
            IsToTom = isToTom;
            LeaveTypeID = leaveTypeID;
            ToDate = toDate;
        }

        public Param() {

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

        public String getFromDate() {
            return FromDate;
        }

        public void setFromDate(String fromDate) {
            FromDate = fromDate;
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

        public String getLeaveTypeID() {
            return LeaveTypeID;
        }

        public void setLeaveTypeID(String leaveTypeID) {
            LeaveTypeID = leaveTypeID;
        }

        public String getToDate() {
            return ToDate;
        }

        public void setToDate(String toDate) {
            ToDate = toDate;
        }

    }


}