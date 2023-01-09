import org.junit.jupiter.api.Test;
import org.tonel.Formation;
import org.tonel.Matiere;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class FormationTest {

    @Test
    void test_ajouterMatiere() {
        var formation = new Formation("test");
        var matiere = new Matiere("test");
        var coefficient = 1.0f;
        formation.ajouterMatiere(matiere, coefficient);
        assertTrue(formation.contientMatiere(matiere));
    }


    @Test
    void test_supprimerMatiere() {
        var formation = new Formation("test");
        var matiere = new Matiere("test");
        var coefficient = 1.0f;
        formation.ajouterMatiere(matiere, coefficient);
        formation.supprimerMatiere(matiere);
        assertFalse(formation.contientMatiere(matiere));
    }

    @Test
    void test_supprimerMatiereErreur() {
        var formation = new Formation("test");
        var matiere = new Matiere("test");
        var coefficient = 1.0f;
        formation.ajouterMatiere(matiere, coefficient);
        formation.supprimerMatiere(matiere);
        assertFalse(formation.contientMatiere(matiere));
    }


    @Test
    void test_getCoefficientMatiere() {
        var formation = new Formation("test");
        var matiere = new Matiere("test");
        var coefficient = 1.0f;
        formation.ajouterMatiere(matiere, coefficient);
        assertEquals(coefficient, formation.getCoefficient(matiere));
    }

    @Test
    void test_getCoefficientMatiereErreur() {
        var formation = new Formation("test");
        var matiere = new Matiere("test");
        var coefficient = 1.0f;
        //formation.ajouterMatiere(matiere, coefficient);
        assertThrows(NoSuchElementException.class, () -> formation.getCoefficient(matiere));
    }


    @Test
    void test_contientMatiere() {
        var formation = new Formation("test");
        var matiere = new Matiere("test");
        var coefficient = 1.0f;
        formation.ajouterMatiere(matiere, coefficient);
        assertTrue(formation.contientMatiere(matiere));
    }

    @Test
    void test_getAllMatieres() {
        var formation = new Formation("test");
        var matiere = new Matiere("test");
        var coefficient = 1.0f;
        formation.ajouterMatiere(matiere, coefficient);
        assertTrue(formation.getAllMatieres().contains(matiere));
    }

}