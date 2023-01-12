package logo.Decorateur;

import logo.Logo.Logo;
import logo.Affichage.MyImage;

public class LogoDecorateur extends Logo {
    protected Logo logo;
    protected int x;
    protected int y;

    /**
     * Constructeur
     * @param i Logo
     * @param prix Le prix de la décoration
     * @param path Le chemin d'accès à l'image de la décoration
     * @param x La position x de l'image de la décoration
     * @param y La position y de l'image de la décoration
     */
    public LogoDecorateur(Logo i , double prix, String path, int x, int y) {
        super(path, prix);
        this.logo = i;
        this.x = x;
        this.y = y;
    }

    @Override
    public MyImage getLogo() {
        MyImage i = this.logo.getLogo();
        i.paintOver(this.nomIm, this.x, this.y);
        return i;
    }

    @Override
    public double combienCaCoute() {
        return this.prix + this.logo.combienCaCoute();
    }

}
