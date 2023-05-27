package quannkph29999.fpoly.assignment.Model;

public class ThanhVien {
    private String tentv;
    private String matkhau;

    public ThanhVien() {
    }

    public ThanhVien(String tentv, String matkhau) {
        this.tentv = tentv;
        this.matkhau = matkhau;
    }

    public String getTentv() {
        return tentv;
    }

    public void setTentv(String tentv) {
        this.tentv = tentv;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
}
