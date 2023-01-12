package logo.Logo;


import logo.Affichage.MyImage;

public abstract class Logo {
	/**
     * Chemin d'acces au fichier
     * contenant l'image de fond du logo
     */
    protected String nomIm;

    /**
     * Prix du logo
     */
    protected double prix;

    /**
     * Constructeur
     */
    public Logo(String path, double prix) {
        this.nomIm = path;
        this.prix = prix;
    }
    
    /**
     * @return l'objet de MyImage correspondant a nomIm
     */
    public abstract MyImage getLogo();

    
    /**
     * @return le prix du logo
     */
    public abstract double combienCaCoute();

}
