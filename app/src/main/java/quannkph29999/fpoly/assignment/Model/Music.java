package quannkph29999.fpoly.assignment.Model;

public class Music {
    private int idnhac;
    private String tennhac;
    private String linknhac;

    public Music(int idnhac, String tennhac, String linknhac) {
        this.idnhac = idnhac;
        this.tennhac = tennhac;
        this.linknhac = linknhac;
    }

    public Music() {
    }

    public Music(String tennhac, String linknhac) {
        this.tennhac = tennhac;
        this.linknhac = linknhac;
    }

    public int getIdnhac() {
        return idnhac;
    }

    public void setIdnhac(int idnhac) {
        this.idnhac = idnhac;
    }

    public String getTennhac() {
        return tennhac;
    }

    public void setTennhac(String tennhac) {
        this.tennhac = tennhac;
    }

    public String getLinknhac() {
        return linknhac;
    }

    public void setLinknhac(String linknhac) {
        this.linknhac = linknhac;
    }
}
