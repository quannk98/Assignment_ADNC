package quannkph29999.fpoly.assignment.Model;

import android.net.Uri;

public class ThanhVien {
    private int id;
    private String tentv;
    private String matkhau;
    private String img;

    public ThanhVien() {
    }

    public ThanhVien(String tentv, String matkhau) {
        this.tentv = tentv;
        this.matkhau = matkhau;
    }

    public ThanhVien(String tentv, String matkhau, String img) {
        this.tentv = tentv;
        this.matkhau = matkhau;
        this.img = img;
    }

    public ThanhVien(String img) {
        this.img = img;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
