package quannkph29999.fpoly.assignment.Model;

public class News {
    private int idbao;
    private String tenbao;
    private String linkbao;
    private String linkanh;

    public News(int idbao, String tenbao, String linkbao, String linkanh) {
        this.idbao = idbao;
        this.tenbao = tenbao;
        this.linkbao = linkbao;
        this.linkanh = linkanh;
    }

    public News() {
    }

    public News(String tenbao, String linkbao, String linkanh) {
        this.tenbao = tenbao;
        this.linkbao = linkbao;
        this.linkanh = linkanh;
    }

    public int getIdbao() {
        return idbao;
    }

    public void setIdbao(int idbao) {
        this.idbao = idbao;
    }

    public String getTenbao() {
        return tenbao;
    }

    public void setTenbao(String tenbao) {
        this.tenbao = tenbao;
    }

    public String getLinkbao() {
        return linkbao;
    }

    public void setLinkbao(String linkbao) {
        this.linkbao = linkbao;
    }

    public String getLinkanh() {
        return linkanh;
    }

    public void setLinkanh(String linkanh) {
        this.linkanh = linkanh;
    }
}
