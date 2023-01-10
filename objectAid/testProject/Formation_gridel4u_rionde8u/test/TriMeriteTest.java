import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tonel.*;
import org.tonel.exception.GradeOutOfRangeException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TriMeriteTest {
    private Groupe groupe;
    private Etudiant etudiant1;
    private Etudiant etudiant2;
    private Etudiant etudiant3;
    private Etudiant etudiant4;
    private List<Etudiant> etudiants;

    @BeforeEach
    void init() throws GradeOutOfRangeException {

        final var formation = new Formation("BUTINFO2@univ-lorraine");
        formation.ajouterMatiere(new Matiere("maths"), 1.0f);
        formation.ajouterMatiere(new Matiere("francais"), 1.0f);

        this.etudiant1 = new Etudiant(new Identite("vincent.thomas", "Vincent", "Thomas"), formation);
        this.etudiant2 = new Etudiant(new Identite("jean.dupont", "Dupont", "Jean"), formation);
        this.etudiant3 = new Etudiant(new Identite("jean.michel", "Michel", "Jean"), formation);
        this.etudiant4 = new Etudiant(new Identite("jean.jacques", "Jacques", "Jean"), formation);

        final var etudiantsAggregator = List.of(this.etudiant1, this.etudiant2, this.etudiant3, this.etudiant4);
        this.etudiants = new ArrayList<>(etudiantsAggregator);

        // add some notes to every etudiants
        this.etudiant1.ajouterNote(new Matiere("maths"), 12.0d);
        this.etudiant1.ajouterNote(new Matiere("francais"), 15.0d);
        this.etudiant2.ajouterNote(new Matiere("maths"), 10.0d);
        this.etudiant2.ajouterNote(new Matiere("francais"), 12.0d);
        this.etudiant3.ajouterNote(new Matiere("maths"), 8.0d);
        this.etudiant3.ajouterNote(new Matiere("francais"), 10.0d);
        this.etudiant4.ajouterNote(new Matiere("maths"), 6.0d);
        this.etudiant4.ajouterNote(new Matiere("francais"), 8.0d);

        this.groupe = new Groupe((ArrayList<Etudiant>) this.etudiants, formation);
    }


    @Test
    @DisplayName("The first student after being sorted should be jean jacques")
    void ensureThatTheFirstStudentAfterBeingSortedShouldBeJeanJacques() {
        this.groupe.triParMerite();

        assertEquals(this.etudiant4, this.groupe.getEtudiants().get(0));
    }

    @Test
    @DisplayName("The first student after being sorted should be jean jacques and has 7 of average")
    void ensureThatTheFirstStudentAfterBeingSortedShouldBeJeanJacquesAndHas7OfAverage() {
        this.groupe.triParMerite();

        assertEquals(7.0d, this.groupe.getEtudiants().get(0).calculerMoyGenerale());
    }


    @Test
    @DisplayName("The last student after being sorted should be vincent ")
    void ensureThatTheLastStudentIs() {
        this.groupe.triParMerite();
        assertEquals(13.5d, this.groupe.getEtudiants().get(3).calculerMoyGenerale());
        assertEquals(this.etudiant1, this.groupe.getEtudiants().get(3));
    }

}
