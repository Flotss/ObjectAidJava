package cafe;

public class BoissonCreme extends DecorateurIngredient{
    /**
     * Constructeur
     * @param boisson Boisson à décorer
     */
    public BoissonCreme(Boisson boisson) {
        super(0.55, "Creme", boisson);
    }
}
