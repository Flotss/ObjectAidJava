package org.teamthree.objectaid.introspection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.teamtree.objectaid.Classe.Constructeur;
import org.teamtree.objectaid.Classe.Parametre;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ConstructeurTest {

    Constructeur[] constructeur;


    @BeforeEach
    void setUp() throws ClassNotFoundException {
        Class<?> c = Class.forName("org.teamthree.objectaid.introspection.ClassePourLesTests");
        Constructor<?>[] constructeurs = c.getDeclaredConstructors();
        constructeur = new Constructeur[constructeurs.length];
        for (int i = 0; i < constructeurs.length; i++) {
            constructeur[i] = new Constructeur(constructeurs[i]);
        }
    }


    @Test
    void testAccessibilite() {
        for (Constructeur constructeur : this.constructeur) {
            switch (constructeur.getParametre().size()) {
                case 0 -> assertEquals("protected", constructeur.getAccessibilite().getAcces());
                case 1 -> assertEquals("private", constructeur.getAccessibilite().getAcces());
                case 2 -> assertEquals("public", constructeur.getAccessibilite().getAcces());
                case 3 -> assertEquals("default", constructeur.getAccessibilite().getAcces());
                default -> {
                }
            }
        }
    }

    @Test
    void testParametre() {
        for (Constructeur construc : constructeur) {
            switch (construc.getAccessibilite().getAcces()) {
                case "default" -> {
                    ArrayList<Parametre> parametres1 = construc.getParametre();
                    assertEquals(3, parametres1.size());
                    assertEquals("double", parametres1.get(0).getType());
                    assertEquals("double", parametres1.get(1).getType());
                    assertEquals("double", parametres1.get(2).getType());
                }
                case "public" -> {
                    ArrayList<Parametre> parametres2 = construc.getParametre();
                    assertEquals(2, parametres2.size());
                    assertEquals("int", parametres2.get(0).getType());
                    assertEquals("int", parametres2.get(1).getType());
                }
                case "private" -> {
                    ArrayList<Parametre> parametres3 = construc.getParametre();
                    assertEquals(1, parametres3.size());
                    assertEquals("int", parametres3.get(0).getType());
                }
                case "protected" -> {
                    ArrayList<Parametre> parametres4 = construc.getParametre();
                    assertEquals(0, parametres4.size());
                }
                default -> {
                }
            }
        }
    }

}
