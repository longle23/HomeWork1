package fis.ihrp.longlh.homework1.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataHeader2 {
    @SerializedName("AP1")
    @Expose
    private String ap1;
    @SerializedName("AP2")
    @Expose
    private String ap2;
    @SerializedName("FromDate")
    @Expose
    private String fromDate;
    @SerializedName("IsFromTom")
    @Expose
    private String isFromTom;
    @SerializedName("IsToTom")
    @Expose
    private String isToTom;
    @SerializedName("LeaveTypeID")
    @Expose
    private String leaveTypeID;
    @SerializedName("ToDate")
    @Expose
    private String toDate;

    public String getAp1() {
        return ap1;
    }

    public void setAp1(String ap1) {
        this.ap1 = ap1;
    }

    public String getAp2() {
        return ap2;
    }

    public void setAp2(String ap2) {
        this.ap2 = ap2;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getIsFromTom() {
        return isFromTom;
    }

    public void setIsFromTom(String isFromTom) {
        this.isFromTom = isFromTom;
    }

    public String getIsToTom() {
        return isToTom;
    }

    public void setIsToTom(String isToTom) {
        this.isToTom = isToTom;
    }

    public String getLeaveTypeID() {
        return leaveTypeID;
    }

    public void setLeaveTypeID(String leaveTypeID) {
        this.leaveTypeID = leaveTypeID;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public DataHeader2(String ap1, String ap2, String fromDate, String isFromTom, String isToTom, String leaveTypeID, String toDate) {
        this.ap1 = ap1;
        this.ap2 = ap2;
        this.fromDate = fromDate;
        this.isFromTom = isFromTom;
        this.isToTom = isToTom;
        this.leaveTypeID = leaveTypeID;
        this.toDate = toDate;
    }

    public DataHeader2(){

    }

}