import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tonel.Etudiant;
import org.tonel.Formation;
import org.tonel.Groupe;
import org.tonel.Identite;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TriGroupeTest {
    Formation formation;
    Etudiant etudiant1;
    Etudiant etudiant2;
    Etudiant etudiant3;
    Etudiant etudiant4;
    Groupe groupe;

    @BeforeEach
    public void init() {
        formation = new Formation("test");

        etudiant1 = new Etudiant(new Identite(java.util.UUID.randomUUID().toString(), "Riondé", "Antoine"), formation);
        etudiant2 = new Etudiant(new Identite(java.util.UUID.randomUUID().toString(), "Gridel", "Alexis"), formation);
        etudiant3 = new Etudiant(new Identite(java.util.UUID.randomUUID().toString(), "Pitt", "Brad"), formation);
        etudiant4 = new Etudiant(new Identite(java.util.UUID.randomUUID().toString(), "DiCaprio", "Leonardo"), formation);

        groupe = new Groupe(new ArrayList<>(), formation);
        groupe.ajouterEtudiant(etudiant2);
        groupe.ajouterEtudiant(etudiant3);
        groupe.ajouterEtudiant(etudiant1);
        groupe.ajouterEtudiant(etudiant4);


    }

    @Test
    public void testTriAlpha() {
        List<Etudiant> lstEtudsTrie = new ArrayList<>();
        lstEtudsTrie.add(etudiant4); // DiCaprio
        lstEtudsTrie.add(etudiant2); // Gridel
        lstEtudsTrie.add(etudiant3); // Pitt
        lstEtudsTrie.add(etudiant1); // Riondé

        groupe.triAlpha();

        // verifie que le premier etudiant est bien Dicaprio
        assertEquals("DiCaprio", groupe.getEtudiant(0).getIdentite().getNom());
        // verifie que le dernier etudiant est bien Riondé
        assertEquals("Riondé", groupe.getEtudiant(3).getIdentite().getNom());

        // et que la liste est bien triée
        assertEquals(lstEtudsTrie, groupe.getEtudiants());
    }

    @Test
    public void testTriAntiAlpha() {
        List<Etudiant> lstEtudsTrieInverse = new ArrayList<>();
        lstEtudsTrieInverse.add(etudiant1); // Riondé
        lstEtudsTrieInverse.add(etudiant3); // Pitt
        lstEtudsTrieInverse.add(etudiant2); // Gridel
        lstEtudsTrieInverse.add(etudiant4); // DiCaprio

        groupe.triAntiAlpha();

        // verifie que le premier etudiant est bien Riondé (tri inversé)
        assertEquals("Riondé", groupe.getEtudiant(0).getIdentite().getNom());
        // verifie que le dernier etudiant est bien DiCaprio (tri inversé)
        assertEquals("DiCaprio", groupe.getEtudiant(3).getIdentite().getNom());

        // et que la liste est bien triée
        assertEquals(lstEtudsTrieInverse, groupe.getEtudiants());


    }
}
