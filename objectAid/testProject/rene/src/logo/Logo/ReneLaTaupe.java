package logo.Logo;


import logo.Affichage.MyImage;

public class ReneLaTaupe extends Logo {
    /**
     * Constructeur
     */
    public ReneLaTaupe() {
        super("ressource/img/Taupe.jpg", 3.65);
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
