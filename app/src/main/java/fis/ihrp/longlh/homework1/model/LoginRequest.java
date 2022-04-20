package fis.ihrp.longlh.homework1.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("OS")
    @Expose
    private String os;
    @SerializedName("DeviceID")
    @Expose
    private String deviceID;
    @SerializedName("Version")
    @Expose
    private Integer version;
    @SerializedName("LangID")
    @Expose
    private String langID;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getLangID() {
        return langID;
    }

    public void setLangID(String langID) {
        this.langID = langID;
    }

    public LoginRequest(String username, String password, String os, String deviceID, Integer version, String langID) {
        this.username = username;
        this.password = password;
        this.os = os;
        this.deviceID = deviceID;
        this.version = version;
        this.langID = langID;
    }

    public LoginRequest(){

    }
}