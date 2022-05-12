package fis.ihrp.longlh.homework1.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class XoaDonRequest {

    @SerializedName("AppVersion")
    @Expose
    private String appVersion;

    @SerializedName("DataItem")
    @Expose
    private List<Param> dataItem = null;

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

    public List<Param> getDataItem() {
        return dataItem;
    }

    public void setDataItem(List<Param> dataItem) {
        this.dataItem = dataItem;
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

    public XoaDonRequest(String appVersion, List<Param> dataItem, String langID, String stoken) {
        this.appVersion = appVersion;
        this.dataItem = dataItem;
        this.langID = langID;
        this.stoken = stoken;
    }

    public XoaDonRequest() {

    }

    public static class Param {
        private String ID;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public Param(String ID) {
            this.ID = ID;
        }

        public Param(){

        }

    }


}
