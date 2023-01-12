package cafe;

public class PrincipaleCafe {
    public static void main(String[] args) {
//        Boisson deca = new Deca();
//        deca = new BoissonCreme(deca);
//        deca = new BoissonChantilly(deca);
//        deca = new BoissonChantilly(deca);
//
//        System.out.println(deca);

        Boisson expresso = new Expresso();
        expresso = new BoissonChantilly(expresso);
        System.out.println(expresso);
    }
}
