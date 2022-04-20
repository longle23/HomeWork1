package fis.ihrp.longlh.homework1.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataHeader {

    @SerializedName("F")
    @Expose
    private String f;

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public DataHeader(String f) {
        this.f = f;
    }

    public DataHeader(){

    }

}