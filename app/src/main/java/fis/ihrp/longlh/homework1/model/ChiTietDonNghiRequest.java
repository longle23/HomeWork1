package fis.ihrp.longlh.homework1.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChiTietDonNghiRequest {

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

    public ChiTietDonNghiRequest(String appVersion, List<Param> dataHeader, String langID, String stoken) {
        this.appVersion = appVersion;
        this.dataHeader = dataHeader;
        this.langID = langID;
        this.stoken = stoken;
    }

    public ChiTietDonNghiRequest() {

    }

    public static class Param {
        private String ID;

        public Param(String ID) {
            this.ID = ID;
        }

        public Param(){

        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

    }


}
