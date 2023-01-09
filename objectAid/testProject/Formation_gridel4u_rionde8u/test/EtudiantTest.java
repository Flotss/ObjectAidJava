import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tonel.Etudiant;
import org.tonel.Formation;
import org.tonel.Identite;
import org.tonel.Matiere;

import java.util.NoSuchElementException;

class EtudiantTest {

    private Etudiant etudiant;

    @BeforeEach
    void init() {
        final var identite = new Identite(java.util.UUID.randomUUID().toString(), "Thomas", "Vincent");

        final var initialFormation = new Formation("BUTINFO2@univ-lorraine");
        initialFormation.ajouterMatiere(new Matiere("Qualité de développement"), 1.0f);
        initialFormation.ajouterMatiere(new Matiere("Programmation orientée objet"), 1.0f);
        initialFormation.ajouterMatiere(new Matiere("Programmation fonctionnelle"), 1.0f);
        initialFormation.ajouterMatiere(new Matiere("Programmation concurrente"), 1.0f);
        initialFormation.ajouterMatiere(new Matiere("Programmation web"), 1.0f);
        initialFormation.ajouterMatiere(new Matiere("Programmation mobile"), 1.0f);
        initialFormation.ajouterMatiere(new Matiere("Conception objet"), 1.0f);
        initialFormation.ajouterMatiere(new Matiere("Orientée Objet"), 2.0f);

        this.etudiant = new Etudiant(identite, initialFormation);
    }

    @Test
    @DisplayName("AjouterNote should add note to etudiant resultats")
    void ajouterNoteShouldAddNotetoEtudiantResultats() throws Exception {
        // Arrange
        final var matiere = new Matiere("Programmation orientée objet");
        final var note = 10.0;

        // Act / Assert
        Assertions.assertDoesNotThrow(() -> this.etudiant.ajouterNote(matiere, note));
    }

    @Test
    @DisplayName("AjouterNote of inferior born should throw an excception")
    void ajouterNoteOfInfBornShouldThrowAnException() throws Exception {
        // Arrange
        final var matiere = new Matiere("Programmation orientée objet");
        final var note = -1.0;

        // Act / Assert
        Assertions.assertThrows(Exception.class, () -> this.etudiant.ajouterNote(matiere, note));
    }

    @Test
    @DisplayName("AjouterNote of superior born should throw an exception")
    void ajouterNoteOfSuperiorBornShouldThrowAnException() throws Exception {
        // Arrange
        final var matiere = new Matiere("Programmation orientée objet");
        final var note = 21.0;

        // Act / Assert
        Assertions.assertThrows(Exception.class, () -> this.etudiant.ajouterNote(matiere, note));
    }

    @Test
    @DisplayName("AjouterNote of unknown matiere should throw an exception")
    void ajouterNoteOfUnknwonMatiereShouldThrowAnException() {
        // Arrange
        final var matiere = new Matiere("Vincent Thomas - Cours spécial");
        final var note = 10.0;

        // Act / Assert
        Assertions.assertThrows(NoSuchElementException.class, () -> this.etudiant.ajouterNote(matiere, note));
    }

    @Test
    @DisplayName("AjouterNote whenever the matiere is in the formation should not throw")
    void ajouterNoteWheneverTheMatiereIsInTheFormationShouldNotThrowAnyExeption() {
        // Arrange
        final var matiere = new Matiere("Programmation orientée objet");
        final var note = 10.0;

        // Act / Assert
        Assertions.assertDoesNotThrow(() -> this.etudiant.ajouterNote(matiere, note));
    }

    @Test
    @DisplayName("Calculer moyenne should return the average grade of the providen matiere")
    void calculerMoyenneShouldReturnTheAverageGradeOfTheProvidenMatiere() throws Exception {
        // Arrange
        final var matiere = new Matiere("Programmation orientée objet");
        final var note = 10.0;

        // Act
        this.etudiant.ajouterNote(matiere, note);
        this.etudiant.ajouterNote(matiere, 20.0);
        final var moyenne = this.etudiant.calculerMoyenneMatiere(matiere);

        // Assert
        Assertions.assertEquals(15.0, (double) moyenne);
    }

    @Test
    @DisplayName("CalulerMoyenneGenerale should return average grade of multiple matieres with coefficient")
    void calculerMoyenneGeneraleShouldReturnAverageGradeOfMultipleMatieresWithGivenCoefficient() throws Exception {
        // Act
        this.etudiant.ajouterNote(new Matiere("Orientée Objet"), 10.0); // Coeff 1
        this.etudiant.ajouterNote(new Matiere("Conception objet"), 20.0); // Coeff 2

        final var moyenne = this.etudiant.calculerMoyGenerale();

        // Assert
        Assertions.assertEquals(13.33, moyenne, 2);
    }

}
