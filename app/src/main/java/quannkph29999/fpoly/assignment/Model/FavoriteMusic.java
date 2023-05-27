package quannkph29999.fpoly.assignment.Model;

public class FavoriteMusic {
    private int idfav;
    private String tennhac;

    public FavoriteMusic(int idfav, String tennhac) {
        this.idfav = idfav;
        this.tennhac = tennhac;
    }

    public FavoriteMusic() {
    }

    public FavoriteMusic(String tennhac) {
        this.tennhac = tennhac;
    }

    public int getIdfav() {
        return idfav;
    }

    public void setIdfav(int idfav) {
        this.idfav = idfav;
    }

    public String getTennhac() {
        return tennhac;
    }

    public void setTennhac(String tennhac) {
        this.tennhac = tennhac;
    }
}
