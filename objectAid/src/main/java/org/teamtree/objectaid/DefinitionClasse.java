package main.java.org.teamtree.objectaid;

import org.teamtree.objectaid.Etat;
import org.teamtree.objectaid.Entite;
import org.teamtree.objectaid.Accessibilite;

import java.util.ArrayList;

public class DefinitionClasse {
    private final String nomClasse;
    private final Accessibilite accessibilite;
    private final ArrayList<Etat> etats;
    private final Entite entite;

    public DefinitionClasse(String nomClasse, Accessibilite accessibilite, ArrayList<Etat> etats, Entite entite) {
        this.nomClasse = nomClasse;
        this.accessibilite = accessibilite;
        this.etats = etats;
        this.entite = entite;
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


}
