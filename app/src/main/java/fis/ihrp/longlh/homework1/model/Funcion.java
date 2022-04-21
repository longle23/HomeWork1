package fis.ihrp.longlh.homework1.model;

import fis.ihrp.longlh.homework1.adapter.FuncionAdapter;

public class Funcion {
    private int id;
    private int parentID;
    private String code;
    private String functionName;
    private String src;

    public Funcion(int id, int parentID, String code, String functionName, String src) {
        this.id = id;
        this.parentID = parentID;
        this.code = code;
        this.functionName = functionName;
        this.src = src;
    }

    public Funcion() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

}
