package org.tonel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Groupe {
    private final Formation formation;
    private final List<Etudiant> etudiants;

    public Groupe(final ArrayList<Etudiant> etudiants, final Formation formation) {
        this.etudiants = etudiants;
        this.formation = formation;
    }

    public Etudiant getEtudiant(final int index) {
        return this.etudiants.get(index);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (null == o || this.getClass() != o.getClass()) {
            return false;
        }
        final Groupe groupe = (Groupe) o;
        return Objects.equals(this.formation, groupe.formation) && Objects.equals(
                this.etudiants, groupe.etudiants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.formation, this.etudiants);
    }

    public List<Etudiant> etudiants() {
        return this.etudiants;
    }

    public void ajouterEtudiant(final Etudiant etudiant) {
        if (etudiant.formation().equals(this.formation)) {
            this.etudiants.add(etudiant);
        }
    }

    public void supprimerEtudiant(final Etudiant etudiant) {
        this.etudiants.remove(etudiant);
    }

    /**
     * Permet de trier la liste des étudiants du groupe selon l'ordre alphabétique de leur nom.
     */
    public void triAlpha() {
        Collections.sort(this.etudiants, Etudiant.ComparatorNom);
    }

    public ArrayList<Etudiant> getEtudiants() {
        return (ArrayList<Etudiant>) this.etudiants;
    }

    /**
     * Permet de trier la liste des étudiants du groupe dans l'ordre inverse de l'ordre alphabétique de leur nom.
     */
    public void triAntiAlpha() {
        Collections.sort(this.etudiants, Collections.reverseOrder(Etudiant.ComparatorNom));
    }

    public final double calculerMoyenneParMatiere(final Matiere matiere) {
        return this.etudiants.stream()
                .mapToDouble(etudiant -> etudiant.calculerMoyenneMatiere(matiere))
                .average()
                .orElse(0);
    }

    public final double calculerMoyenneGenerale() {
        return this.etudiants.stream()
                .mapToDouble(Etudiant::calculerMoyGenerale)
                .average()
                .orElse(0);
    }

    public void triParMerite() {
        this.etudiants.sort(new EtudiantComparatorMoyenne());
    }
}

