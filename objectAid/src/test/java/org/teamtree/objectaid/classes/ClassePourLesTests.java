package org.teamtree.objectaid.classes;

import org.teamtree.objectaid.Classe.Parametre;

abstract class ClassePourLesTests {
    private int x;
    private int y;
    protected final static int staticAttribut = 0;


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

    public final static void methodeStatic() {
        throw new Error("Unimplemented");
    }

    ClassePourLesTests(double x, double y, double z) {
        throw new Error("Unimplemented");
    }

    public ClassePourLesTests(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private ClassePourLesTests(int x) {
        this.x  = x;
    }

    protected ClassePourLesTests(){
        throw new Error("Unimplemented");
    }

}