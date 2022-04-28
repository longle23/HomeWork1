package fis.ihrp.longlh.homework1.model;

public class NguoiKiemDuyetResponse {

    private String approverID;
    private String approverName;

    public String getApproverID() {
        return approverID;
    }

    public void setApproverID(String approverID) {
        this.approverID = approverID;
    }

    public String getApproverName() {
        return approverName;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName;
    }

    public NguoiKiemDuyetResponse(String approverID, String approverName) {
        this.approverID = approverID;
        this.approverName = approverName;
    }

    public NguoiKiemDuyetResponse() {

    }

}
