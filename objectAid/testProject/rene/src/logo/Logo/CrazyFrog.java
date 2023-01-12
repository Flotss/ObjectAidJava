package logo.Logo;


import logo.Affichage.MyImage;

public class CrazyFrog extends Logo {
    /**
     * Constructeur
     */
    public CrazyFrog() {
        super("ressource/img/CrazyFrog.jpg", 4.65);
    }

    @Override
    public MyImage getLogo() {
        return new MyImage(this.nomIm);
    }

    @Override
    public double combienCaCoute() {
        return this.prix;
    }
}
