package org.teamthree.objectaid.introspection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.teamtree.objectaid.Classe.Methode;
import org.teamtree.objectaid.Classe.Parametre;
import org.teamtree.objectaid.Etat.Etat;

import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MethodeTest {

    Methode[] methodes;


    @BeforeEach
    void setUp() throws ClassNotFoundException {
        Class<?> c = Class.forName("org.teamthree.objectaid.introspection.ClassePourLesTests");
        Method[] methods = c.getDeclaredMethods();
        methodes = new Methode[methods.length];
        for (int i = 0; i < methods.length; i++) {
            methodes[i] = new Methode(methods[i]);
        }
    }


    @Test
    void testAccessibilite() {
        for (Methode methode : methodes) {
            switch (methode.getNom()) {
                case "methodeAbstraiteProtected" -> assertEquals("protected", methode.getAccessibilite().getAcces());
                case "MethodepublicAbstract" -> assertEquals("public", methode.getAccessibilite().getAcces());
                case "methodePrivee" -> assertEquals("private", methode.getAccessibilite().getAcces());
                case "methodeAvecParametresDefault" -> assertEquals("default", methode.getAccessibilite().getAcces());
                default -> {
                }
            }
        }
    }

    @Test
    void testEtat() {
        for (Methode methode : methodes) {
            switch (methode.getNom()) {
                case "methodeAbstraiteProtected" -> {
                    ArrayList<Etat> etats1 = methode.getEtats();
                    assertEquals("abstract", etats1.get(0).getEtat());
                }
                case "methodePrivee" -> {
                    ArrayList<Etat> etats2 = methode.getEtats();
                    assertEquals(0, etats2.size());
                }
                case "methodeStatic" -> {
                    ArrayList<Etat> etats3 = methode.getEtats();
                    assertEquals("final", etats3.get(0).getEtat());
                    assertEquals("static", etats3.get(1).getEtat());
                }
                default -> {
                }
            }
        }
    }

    @Test
    void testRetourne() {
        for (Methode methode : methodes) {
            switch (methode.getNom()) {
                case "methodeAbstraiteProtected" -> assertEquals("void", methode.getTypeRetourne());
                case "MethodepublicAbstract" -> assertEquals("int", methode.getTypeRetourne());
                case "methodePrivee" -> assertEquals("void", methode.getTypeRetourne());
                case "methodeAvecParametrePublic" -> assertEquals("Parametre", methode.getTypeRetourne());
                case "methodeAvecParametresDefault" -> assertEquals("void", methode.getTypeRetourne());
                case "methodeStatic" -> assertEquals("void", methode.getTypeRetourne());
                default -> {
                }
            }
        }
    }

    @Test
    void testParametre() {
        for (Methode methode : methodes) {
            System.out.println(methode);
            switch (methode.getNom()) {
                case "methodePrivee" -> {
                    ArrayList<Parametre> parametres1 = methode.getParametre();
                    assertEquals(0, parametres1.size());
                }
                case "methodeAvecParametrePublic" -> {
                    ArrayList<Parametre> parametres2 = methode.getParametre();
                    assertEquals(1, parametres2.size());
                }
                case "methodeAvecParametresDefault" -> {
                    ArrayList<Parametre> parametres3 = methode.getParametre();
                    assertEquals(2, parametres3.size());
                }
                default -> {
                }
            }
        }
    }

}
