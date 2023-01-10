import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tonel.*;

import java.util.ArrayList;

class GroupeTest {

    private Groupe groupe;

    @BeforeEach
    void init() {
        final var vincentThomasEtudiant = new Etudiant(
                new Identite("vincent.thomas", "Vincent", "Thomas"),
                new Formation("BUTINFO2@univ-lorraine"));
        final var jeanDupontEtudiant = new Etudiant(new Identite("jean.dupont", "Dupont", "Jean"),
                new Formation("BUTINFO2@univ-lorraine"));
        final var jeanMichelEtudiant = new Etudiant(new Identite("jean.michel", "Michel", "Jean"),
                new Formation("BUTINFO2@univ-lorraine"));
        final var jeanJacquesEtudiant = new Etudiant(
                new Identite("jean.jacques", "Jacques", "Jean"),
                new Formation("BUTINFO2@univ-lorraine"));

        final var etudiants = new ArrayList<Etudiant>();
        etudiants.add(vincentThomasEtudiant);
        etudiants.add(jeanDupontEtudiant);
        etudiants.add(jeanMichelEtudiant);
        etudiants.add(jeanJacquesEtudiant);

        groupe = new Groupe(etudiants, new Formation("BUTINFO2@univ-lorraine"));
    }

    @Test
    @DisplayName("Add etudiant in groupe should add it to groupe etudiants")
    void addEtudiantInGroupeShouldAddItToGroupeEtudiants() {
        // Arrange
        final var etudiant = new Etudiant(new Identite("jean.dupont", "Dupont", "Jean"),
                new Formation("BUTINFO2@univ-lorraine"));

        // Act
        groupe.ajouterEtudiant(etudiant);

        // Assert
        Assertions.assertEquals(5, groupe.etudiants().size());
    }

    @Test
    @DisplayName("Remove etudiant in groupe should remove it from groupe etudiants if it exists")
    void removeEtudiantInGroupeShouldRemoveItFromGroupeEtudiantsIfItExists() {
        // Arrange
        final var etudiant = new Etudiant(new Identite("jean.dupont", "Dupont", "Jean"),
                new Formation("BUTINFO2@univ-lorraine"));

        // Act
        groupe.supprimerEtudiant(etudiant);

        // Assert
        Assertions.assertEquals(3, groupe.etudiants().size());
    }

    @Test
    @DisplayName("AjouterEtudiant should not be added in groupe if already exists")
    void ajouterEtudiantShouldNotBeAddedInGroupeIfAlreadyExists() {
        // Arrange
        final var etudiant = new Etudiant(new Identite("jean.dupont", "Dupont", "Jean"),
                new Formation("BUTINFO2@univ-lorraine"));

        // Act
        groupe.ajouterEtudiant(etudiant);

        // Assert
        Assertions.assertEquals(5, groupe.etudiants().size());
    }

    @Test
    @DisplayName("Calculer moyenne par matière should return 0 if no etudiant in groupe")
    void calculerMoyenneParMatiereShouldReturn0IfNoEtudiantInGroupe() {
        // Arrange
        final var groupe = new Groupe(new ArrayList<>(), new Formation("BUTINFO2@univ-lorraine"));

        // Act
        final var moyenne = groupe.calculerMoyenneParMatiere(new Matiere("Java"));

        // Assert
        Assertions.assertEquals(0, moyenne);
    }

    @Test
    @DisplayName("Calculer moyenne générale should return 0 if no etudiant in groupe")
    void calculerMoyenneGeneraleShouldReturn0IfNoEtudiantInGroupe() {
        // Arrange
        final var groupe = new Groupe(new ArrayList<>(), new Formation("BUTINFO2@univ-lorraine"));

        // Act
        final var moyenne = groupe.calculerMoyenneGenerale();

        // Assert
        Assertions.assertEquals(0, moyenne);
    }
}
