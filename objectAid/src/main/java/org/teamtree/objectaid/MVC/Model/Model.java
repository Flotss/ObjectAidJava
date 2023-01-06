package org.teamtree.objectaid.MVC.Model;

import org.teamtree.objectaid.Classe.ClasseEntiere;
import org.teamtree.objectaid.Fleche;
import org.teamtree.objectaid.MVC.Vue.Observateur;
import org.teamtree.objectaid.service.ProjectCompilationProcessService;

import java.io.File;
import java.util.*;

/**
 * Classe qui permet de gérer les données du diagramme de classe
 */
public class Model implements Sujet {

    private final Map<String, Class<?>> classesPath = new HashMap<>();

    /**
     * Liste des observateurs
     */
    private final ArrayList<Observateur> observateurs;
    /**
     * Liste des classes avec leurs flèches
     */
    private final HashMap<ClasseEntiere, ArrayList<Fleche>> relations;
    private File currentProject;
    // État actuel de l'application
    private ApplicationState applicationState;
    /**
     * Classe sélectionnée
     */
    private String currentClickedClass;

    /**
     * Constructeur du model
     */
    public Model() {
        this.observateurs = new ArrayList<>();
        this.relations = new HashMap<>();
        this.currentClickedClass = "";
        this.applicationState = ApplicationState.BOOTSTRAP;
        this.currentProject = null;
    }

    public File getCurrentProject() {
        return currentProject;
    }

    public void setCurrentProject(File currentProject) {
        final var service = new ProjectCompilationProcessService(this);

        service.compileProject(currentProject);
        this.currentProject = currentProject;

        setApplicationState(ApplicationState.PROJECT_LOADED);
    }

    public Map<String, Class<?>> getClassesPath() {
        return classesPath;
    }

    public void addClassPathEntry(String className, Class<?> clazz) {
        classesPath.put(className, clazz);
    }

    public ApplicationState getApplicationState() {
        return applicationState;
    }

    private void setApplicationState(final ApplicationState applicationState) {
        this.applicationState = applicationState;

        notifierObservateur();
    }

    /**
     * Méthode qui permet d'ajouter un observateur
     *
     * @param o Observateur
     */
    public void ajouterObservateur(Observateur o) {
        observateurs.add(o);
    }

    /**
     * Méthode qui permet de supprimer un observateur
     *
     * @param o Observateur
     */
    public void supprimerObservateur(Observateur o) {
        observateurs.remove(o);
    }

    /**
     * Méthode qui permet de notifier les observateurs
     */
    public void notifierObservateur() {
        for (Observateur o : observateurs) {
            o.actualiser();
        }
    }

    /**
     * Méthode qui permet d'ajouter une relation
     *
     * @param classe Classe
     * @param fleche Flèche
     */
    public void ajouterRelation(ClasseEntiere classe, Fleche fleche) {
        if (relations.containsKey(classe)) {
            relations.get(classe).add(fleche);
        } else {
            relations.put(classe, new ArrayList<>());
            relations.get(classe).add(fleche);
        }
    }

    /**
     * Méthode qui permet de supprimer une relation
     *
     * @param classe Classe
     * @param fleche Flèche
     */
    public void supprimerRelation(ClasseEntiere classe, Fleche fleche) {
        if (relations.containsKey(classe)) {
            relations.get(classe).remove(fleche);
        }
    }

    /**
     * Méthode qui permet d'ajouter une Classe au model
     *
     * @param classe Classe
     */
    public void ajouterClasse(ClasseEntiere classe) {
        if (!relations.containsKey(classe)) {
            int x = getClasses().size() % 6 * 150 + getClasses().size() % 6 * 30 + 30;
            int y = getClasses().size() / 6 * 300 + getClasses().size() / 6 * 30 + 30;
            classe.setX(x);
            classe.setY(y);
            relations.put(classe, new ArrayList<>());
        }
    }

    /**
     * Méthode qui permet de supprimer une Classe du model
     *
     * @param classe Classe
     */
    public void supprimerClasse(ClasseEntiere classe) {
        relations.remove(classe);
    }

    /**
     * Méthode qui permet de récupérer la liste des classes
     *
     * @return Liste des classes
     */
    public ArrayList<ClasseEntiere> getClasses() {
        return new ArrayList<>(relations.keySet());

    }

    /**
     * Retourne la liste des flèches d'une classe
     *
     * @param classe Classe
     * @return Liste des flèches
     */
    public ArrayList<Fleche> getRelations(ClasseEntiere classe) {
        return relations.get(classe);
    }

    /**
     * Méthode qui permet de récupérer la classe sélectionnée
     *
     * @return Classe sélectionnée
     */
    public String getCurrentClickedClass() {
        return currentClickedClass;
    }

    /**
     * Méthode qui permet de définir la classe sélectionnée
     *
     * @param currentClickedClass Classe sélectionnée
     */
    public void setCurrentClickedClass(final String currentClickedClass) {
        this.currentClickedClass = (Objects.equals(currentClickedClass, this.currentClickedClass)) ? "" : currentClickedClass;
    }

    /**
     * Retourne la classe grâce à son nom
     *
     * @param nom Le nom de la classe
     * @return La classe correspondante
     */
    public Optional<ClasseEntiere> getClasse(String nom) {
        return getClasses().stream().filter(classe -> classe.getNom().equals(nom)).findFirst();
    }

    /**
     * Methode qui permet de changer la possibilité d'afficher les attributs
     */
    public void afficherAttributs() {
        getClasses().forEach(c -> {
            c.setAttributEstAffiche(!c.isAttributEstAffiche());
            notifierObservateur();
        });
    }

    /**
     * Methode qui permet de changer la possibilité d'afficher les méthodes
     */
    public void afficherMethodes() {
        getClasses().forEach(c -> {
            c.setMethodsEstAffiche(!c.isMethodsEstAffiche());
            notifierObservateur();
        });
    }

    /**
     * Methode qui permet de changer la possibilité d'afficher les paramètres
     */
    public void afficherConstructeurs() {
        getClasses().forEach(c -> {
            c.setConstructeurEstAffiche(!c.isConstructeurEstAffiche());
            notifierObservateur();
        });
    }
}
