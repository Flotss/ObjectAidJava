package org.tonel;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Represents a formation with an idetnfiants and a map of matieres.
 *
 * @author alexischangridel
 */
public class Formation {

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Formation formation = (Formation) o;
        return Objects.equals(identifiant, formation.identifiant) && Objects.equals(
            matieres, formation.matieres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifiant, matieres);
    }

    private final String identifiant;
    private final Map<Matiere, Float> matieres;

    public Formation(final String identifiant) {
        this.identifiant = identifiant;
        this.matieres = new HashMap<>();
    }

    public void ajouterMatiere(final Matiere matiere, final float coefficient) {
        this.matieres.putIfAbsent(matiere, coefficient);
    }

    public void supprimerMatiere(final Matiere matiere) {
        if (!this.matieres.containsKey(matiere)) {
            return;
        }

        this.matieres.remove(matiere);
    }

    public final Float getCoefficient(final Matiere matiere) {
        if (!this.matieres.containsKey(matiere)) {
            throw new NoSuchElementException("Map matieres does not contain any matiere : " + matiere);
        }

        return this.matieres.get(matiere);
    }

    public final boolean contientMatiere(final Matiere matiere) {
        return this.matieres.containsKey(matiere);
    }

    public final Collection<Matiere> getAllMatieres() {
        return this.matieres.keySet();
    }

    public Map<Matiere, Float> matieres() {
        return this.matieres;
    }
}
