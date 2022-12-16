package org.teamtree.objectaid.MVC.Model;

import org.teamtree.objectaid.Classe.ClasseEntiere;
import org.teamtree.objectaid.Fleche;
import org.teamtree.objectaid.MVC.Vue.Observateur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Model implements Sujet{
    private ArrayList<Observateur> observateurs;
    private HashMap<ClasseEntiere, ArrayList<Fleche>> relations;

    public Model() {
        this.observateurs = new ArrayList<>();
        this.relations = new HashMap<>();
    }

    public void ajouterObservateur(Observateur o) {
        observateurs.add(o);
    }

    public void supprimerObservateur(Observateur o) {
        observateurs.remove(o);
    }

    public void notifierObservateur() {
        for (Observateur o : observateurs) {
            o.actualiser();
        }
    }

    public void ajouterRelation(ClasseEntiere classe, Fleche fleche) {
        if (relations.containsKey(classe)) {
            relations.get(classe).add(fleche);
        } else {
            relations.put(classe, new ArrayList<>());
            relations.get(classe).add(fleche);
        }
    }

    public void supprimerRelation(ClasseEntiere classe, Fleche fleche) {
        if (relations.containsKey(classe)) {
            relations.get(classe).remove(fleche);
        }
    }

    public void ajouterClasse(ClasseEntiere classe) {
        if (!relations.containsKey(classe)) {
            relations.put(classe, new ArrayList<>());
        }
    }

    public void supprimerClasse(ClasseEntiere classe) {
        relations.remove(classe);
    }

    public Set<ClasseEntiere> getClasses() {
        return relations.keySet();
    }

    public ArrayList<Fleche> getRelations(ClasseEntiere classe) {
        return relations.get(classe);
    }

}
