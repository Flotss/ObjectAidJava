package org.teamthree.objectaid.introspection;

import org.teamtree.objectaid.Classe.Parametre;

abstract class ClassePourLesTests {
    protected final static int staticAttribut = 0;
    private final int x;
    private int y;


    ClassePourLesTests(double x, double y, double z) {
        throw new Error("Unimplemented");
    }

    public ClassePourLesTests(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private ClassePourLesTests(int x) {
        this.x = x;
    }

    protected ClassePourLesTests() {
        throw new Error("Unimplemented");
    }

    public final static void methodeStatic() {
        throw new Error("Unimplemented");
    }

    protected abstract void methodeAbstraiteProtected();

    public abstract int MethodepublicAbstract();

    private void methodePrivee() {
        throw new Error("Unimplemented");
    }

    public Parametre methodeAvecParametrePublic(Parametre parametre) {
        return parametre;
    }

    void methodeAvecParametresDefault(Parametre parametre1, double parametre2) {
        throw new Error("Unimplemented");
    }

}