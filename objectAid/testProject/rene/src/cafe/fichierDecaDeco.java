package cafe;

public class fichierDecaDeco {
    public static void main(String[] args) {
        Boisson deca = new Deca();

        deca = new BoissonCreme(deca);
        deca = new BoissonChantilly(deca);
    }
}
