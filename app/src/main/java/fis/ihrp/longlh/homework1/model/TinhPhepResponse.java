package fis.ihrp.longlh.homework1.model;

public class TinhPhepResponse {
    private String tongPhepNam;
    private String phepDaNghi;
    private String phepCu;
    private String phepCuDaNghi;
    private String tongPhepThang;
    private String phepConLai;
    private String soNgayNghi;

    public TinhPhepResponse(String tongPhepNam, String phepDaNghi, String phepCu, String phepCuDaNghi, String tongPhepThang, String phepConLai, String soNgayNghi) {
        this.tongPhepNam = tongPhepNam;
        this.phepDaNghi = phepDaNghi;
        this.phepCu = phepCu;
        this.phepCuDaNghi = phepCuDaNghi;
        this.tongPhepThang = tongPhepThang;
        this.phepConLai = phepConLai;
        this.soNgayNghi = soNgayNghi;
    }

    public TinhPhepResponse() {

    }

    public String getTongPhepNam() {
        return tongPhepNam;
    }

    public void setTongPhepNam(String tongPhepNam) {
        this.tongPhepNam = tongPhepNam;
    }

    public String getPhepDaNghi() {
        return phepDaNghi;
    }

    public void setPhepDaNghi(String phepDaNghi) {
        this.phepDaNghi = phepDaNghi;
    }

    public String getPhepCu() {
        return phepCu;
    }

    public void setPhepCu(String phepCu) {
        this.phepCu = phepCu;
    }

    public String getPhepCuDaNghi() {
        return phepCuDaNghi;
    }

    public void setPhepCuDaNghi(String phepCuDaNghi) {
        this.phepCuDaNghi = phepCuDaNghi;
    }

    public String getTongPhepThang() {
        return tongPhepThang;
    }

    public void setTongPhepThang(String tongPhepThang) {
        this.tongPhepThang = tongPhepThang;
    }

    public String getPhepConLai() {
        return phepConLai;
    }

    public void setPhepConLai(String phepConLai) {
        this.phepConLai = phepConLai;
    }

    public String getSoNgayNghi() {
        return soNgayNghi;
    }

    public void setSoNgayNghi(String soNgayNghi) {
        this.soNgayNghi = soNgayNghi;
    }


}
