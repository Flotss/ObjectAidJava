package org.teamtree.objectaid.Classe;

import org.teamtree.objectaid.Accessibilite.Accessibilite;
import org.teamtree.objectaid.Etat.Etat;
import org.teamtree.objectaid.Entite.Entite;
import org.teamtree.objectaid.Fabrique.FabriqueAccessibilite;
import org.teamtree.objectaid.Fabrique.FabriqueEntite;
import org.teamtree.objectaid.Fabrique.FabriqueEtat;

import java.util.ArrayList;

public class DefinitionClasse {
    private final String nomClasse;
    private final Accessibilite accessibilite;
    private final ArrayList<Etat> etats;
    private final Entite entite;

    public DefinitionClasse(Class<?> classe) {
        this.nomClasse = classe.getSimpleName();

        // Accessibilite
        FabriqueAccessibilite fabriqueAccessibilite = new FabriqueAccessibilite();
        this.accessibilite = fabriqueAccessibilite.getAccessibilite(classe.getModifiers());

        // Etats
        FabriqueEtat fabriqueEtat = new FabriqueEtat();
        this.etats = fabriqueEtat.getEtat(classe.getModifiers());

        // Entite
        FabriqueEntite fabriqueEntite = new FabriqueEntite();
        this.entite = fabriqueEntite.getEntite(classe);
    }

    public String getNom() {
        return nomClasse;
    }

    public String getAccessibilite() {
        return accessibilite.getAcces();
    }

    public ArrayList<Etat> getEtats() {
        return etats;
    }

    public String getEntite() {
        return entite.getEntite();
    }

    @Override
    public String toString() {
        String info = accessibilite.toString();
        if (etats.size() > 0) {
            for (Etat etat : etats) {
                info += " " + etat.getEtat();
            }
        }
        info += " " + entite.getEntite() + " " + nomClasse;
        return info;
    }


}
