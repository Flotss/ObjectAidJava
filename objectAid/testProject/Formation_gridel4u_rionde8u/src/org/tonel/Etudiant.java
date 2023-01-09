package org.tonel;

import org.tonel.exception.GradeOutOfRangeException;

import java.util.*;

public class Etudiant implements Comparable<Etudiant> {

    private static final int MAXIMUM_GRADE = 20;
    private static final int INFERIOR_GRADE = 0;

    /*
     * Comparator pour le tri des etudiants par nom
     */
    public static Comparator<Etudiant> ComparatorNom = new Comparator<Etudiant>() {
        @Override
        public int compare(Etudiant e1, Etudiant e2) {
            return e1.getIdentite().getNom().compareTo(e2.getIdentite().getNom());
        }
    };

    private final Identite identite;
    private final Formation formation;
    private final Map<Matiere, List<Double>> resultats;

    public Etudiant(final Identite identite, final Formation formation) {
        this.identite = identite;
        this.formation = formation;
        this.resultats = new HashMap<>();

        for (final var matiere : formation.getAllMatieres()) {
            this.resultats.put(matiere, new ArrayList<>());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Etudiant etudiant = (Etudiant) o;
        return Objects.equals(identite, etudiant.identite) && Objects.equals(
            formation, etudiant.formation) && Objects.equals(resultats, etudiant.resultats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identite, formation, resultats);
    }

    public void ajouterNote(final Matiere m, final Double i) throws NoSuchElementException, GradeOutOfRangeException {
        final boolean bMatEstDedans = this.formation.contientMatiere(m);

        if (!bMatEstDedans) {
            throw new NoSuchElementException(
                "Map matieres does not contain any matiere as provided : " + m);
        }

        if (INFERIOR_GRADE > i || MAXIMUM_GRADE < i) {
            throw new GradeOutOfRangeException("Note is not in the range of 0 to 20");
        }

        this.resultats.get(m).add(i);
    }

    public Integer calculerMoyenneMatiere(final Matiere m) {
        final var sum = this.resultats
            .get(m)
            .stream()
            .mapToDouble(Double::doubleValue)
            .sum();

        final var size = this.resultats.get(m).isEmpty()
            ? 1
            : this.resultats.get(m).size();

        return (int) sum / size;
    }


    public Double calculerMoyGenerale() {
        var sum = 0;
        for (final var entry : this.resultats.entrySet()) {

            if (entry.getValue().isEmpty()) {
                continue;
            }

            final var currentMatiere = entry.getKey();
            final var coefficient = this.formation.getCoefficient(currentMatiere);

            sum += this.calculerMoyenneMatiere(currentMatiere) * coefficient;
        }

        //TODO: Verifier que ce n'est pas 0
        final var allCoeff = this.getSumOfCoefficient();

        return sum / allCoeff;
    }

    private double getSumOfCoefficient() {
        // return sum of all coefficient in resultats quand la liste n'est pas vide
        return this.resultats
            .keySet()
            .stream()
            .filter(x -> !this.resultats.get(x).isEmpty())
            .mapToDouble(this.formation::getCoefficient)
            .sum();
    }

    public final Formation formation() {
        return this.formation;
    }

    public final Identite getIdentite() {
        return this.identite;
    }

    @Override
    public int compareTo(Etudiant o) {
        return this.getIdentite().getNom().compareTo(o.getIdentite().getNom());
    }
}
