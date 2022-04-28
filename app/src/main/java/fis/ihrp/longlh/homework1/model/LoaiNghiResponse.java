package fis.ihrp.longlh.homework1.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "loainghi")
public class LoaiNghiResponse {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String id;
    @ColumnInfo(name = "code")
    private String code;
    @ColumnInfo(name = "shortName")
    private String shortName;
    @ColumnInfo(name = "nameEN")
    private String nameEN;
    @ColumnInfo(name = "nameVN")
    private String nameVN;

    public LoaiNghiResponse(String id, String code, String shortName, String nameEN, String nameVN) {
        this.id = id;
        this.code = code;
        this.shortName = shortName;
        this.nameEN = nameEN;
        this.nameVN = nameVN;
    }

    public LoaiNghiResponse() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getNameVN() {
        return nameVN;
    }

    public void setNameVN(String nameVN) {
        this.nameVN = nameVN;
    }

    @Override
    public String toString() {
        return "LoaiNghiResponse{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", shortName='" + shortName + '\'' +
                ", nameEN='" + nameEN + '\'' +
                ", nameVN='" + nameVN + '\'' +
                '}';
    }


}
