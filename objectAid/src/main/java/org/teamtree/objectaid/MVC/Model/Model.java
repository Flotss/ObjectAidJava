package org.teamtree.objectaid.MVC.Model;

import org.teamtree.objectaid.Classe.ClasseEntiere;
import org.teamtree.objectaid.Fleche;
import org.teamtree.objectaid.MVC.Vue.Observateur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class Model implements Sujet {
    private final ArrayList<Observateur> observateurs;
    private final HashMap<ClasseEntiere, ArrayList<Fleche>> relations;
    private String currentClickedClass;

    public Model() {
        this.observateurs = new ArrayList<>();
        this.relations = new HashMap<>();
        this.currentClickedClass = "";
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
            int x = getClasses().size() % 6 * 150 + 30;
            int y = getClasses().size() / 6 * 100 + 30;
            classe.setX(x);
            classe.setY(y);
            relations.put(classe, new ArrayList<>());
        }
    }

    public void supprimerClasse(ClasseEntiere classe) {
        relations.remove(classe);
    }

    public ArrayList<ClasseEntiere> getClasses() {
        return new ArrayList<>(relations.keySet());

    }

    public ArrayList<Fleche> getRelations(ClasseEntiere classe) {
        return relations.get(classe);
    }

    public String getCurrentClickedClass() {
        return currentClickedClass;
    }

    public void setCurrentClickedClass(final String currentClickedClass) {
        this.currentClickedClass = currentClickedClass;
    }

    public Optional<ClasseEntiere> getClasse(String nom) {
        return getClasses().stream().filter(classe -> classe.getNom().equals(nom)).findFirst();
    }

    public void afficherAttributs() {
        getClasses().forEach(c -> {
            c.setAttributEstAffiche(!c.isAttributEstAffiche());
            notifierObservateur();
        });
    }

    public void afficherMethodes() {
        getClasses().forEach(c -> {
            c.setMethodsEstAffiche(!c.isMethodsEstAffiche());
            notifierObservateur();
        });
    }

    public void afficherConstructeurs() {
        getClasses().forEach(c -> {
            c.setConstructeurEstAffiche(!c.isConstructeurEstAffiche());
            notifierObservateur();
        });
    }
}
