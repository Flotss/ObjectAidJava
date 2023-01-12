package org.teamtree.objectaid.MVC.Model;

import org.teamtree.objectaid.Accessibilite.Accessibilite;
import org.teamtree.objectaid.Classe.Constructeur;
import org.teamtree.objectaid.Classe.Methode;
import org.teamtree.objectaid.Entite.Entite;
import org.teamtree.objectaid.Etat.Etat;
import org.teamtree.objectaid.MVC.Vue.*;
import org.teamtree.objectaid.Classe.ClasseEntiere;
import org.teamtree.objectaid.Classe.Relations.Relation;

import java.nio.file.Path;
import java.util.*;

/**
 * Classe qui permet de gérer les données du diagramme de classe
 */
public class Model implements Sujet {

    /** Liste des classes avec comme clé le nom de la classe */
    private final Map<String, Class<?>> classesPath = new HashMap<>();

    /**
     * Liste des observateurs
     */
    private final List<Observateur> observateurs;

    /**
     * Liste des classes avec leurs flèches
     */
    private final Map<ClasseEntiere, ArrayList<Relation>> relations;
    /**
     * Les classes cachées
     */
    private final List<VueClasseAffichage> hiddenClasses;
    /**
     * Classe sélectionnée
     */
    private VueClasseAffichage currentClickedClass;

    /**
     * Path du projet ouvert
     */
    private Path currentProject;

    /**
     * L'état de l'application
     */
    private ApplicationState applicationState;

    /**
     * Constructeur du model
     */
    public Model() {
        this.observateurs = new ArrayList<>();
        this.relations = new HashMap<>();
        this.currentClickedClass = null;
        this.hiddenClasses = new ArrayList<>();
        this.applicationState = ApplicationState.BOOTSTRAP;
        this.currentProject = null;
    }

    /**
     * Retourne le path du projet ouvert
     * @return Path du projet ouvert
     */
    public Path getCurrentProject() {
        return currentProject;
    }

    /**
     * Définit le path du projet ouvert
     * @param currentProject Path du projet ouvert
     */
    public void setCurrentProject(Path currentProject) {
        this.currentProject = currentProject;

        if (currentProject.resolve("src").toFile().exists()) {
            this.currentProject = currentProject.resolve("src");
        }

        setApplicationState(ApplicationState.PROJECT_LOADED);
    }

    /**
     * Retourne les noms des classes et leur classe correspondante
     * @return Les noms des classes et leur classe correspondante
     */
    public Map<String, Class<?>> getClassesPath() {
        return classesPath;
    }

    /**
     * Ajouter une classe au classesPath
     * @param className Nom de la classe
     * @param clazz Classe
     */
    public void addClassPathEntry(String className, Class<?> clazz) {
        classesPath.put(className, clazz);
    }

    /**
     * Retourne l'état de l'application
     * @return L'état de l'application
     */
    public ApplicationState getApplicationState() {
        return applicationState;
    }

    /**
     * Définit l'état de l'application
     * @param applicationState L'état de l'application
     */
    public void setApplicationState(final ApplicationState applicationState) {
        this.applicationState = applicationState;

        notifierObservateur("app");
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
     * Méthode qui permet de notifier un observateur
     *
     * @param observer La classe de l'observateur à notifier
     */
    public <T extends Observateur> void notifierObservateur(final Class<T> observer) {
        this.observateurs
                .stream()
                .filter(observer::isInstance)
                .forEach(Observateur::actualiser);
    }

    /**
     * Méthode qui permet de notifier les observateurs correspondant à une selection donnée en paramètre
     *
     * @param selection String
     */

    public void notifierObservateur(String selection) {
        switch (selection) {
            case "app":
                this.observateurs.stream().filter(o -> o instanceof ApplicationLayoutView).findFirst().ifPresent(Observateur::actualiser);
                break;
            case "selection":
                this.currentClickedClass.actualiserBordure();
                break;
            case "deplacement selection":
                this.currentClickedClass.actualiserPosition();
                for (Observateur observateur : this.observateurs) {
                    if (observateur instanceof VueClasse) {
                        ((VueClasse) observateur).actualiserFleches();
                        return;
                    }
                }
                break;
            case "classe selection complete":
                this.currentClickedClass.actualiser();
                notifierObservateur("actualisation fleches");
                break;
            case "actualisation fleches":
                for (Observateur observateur : this.observateurs) {
                    if (observateur instanceof VueClasse) {
                        ((VueClasse) observateur).actualiserFleches();
                        return;
                    }
                }
                break;
            case "totalite des classes":
            case "update hidden classes":
                //TODO: A modifier --> ne s'occuper que de la visibilitée
                for(Observateur observateur: this.observateurs){
                    if(observateur instanceof VueClasse){
                        observateur.actualiser();
                        break;
                    }
                }
                break;
            case "update visibilite fleche":
                for (Observateur observateur : this.observateurs) {
                    if (observateur instanceof VueClasse) {
                        ((VueClasse) observateur).actualiserFlechesVisibilite();
                        return;
                    }
                }
            case "update visibilite classe selection":
                this.currentClickedClass.actualiserVisibilite();
                break;
            case "click droit":
                for (Observateur observateur : this.observateurs) {
                    if (observateur instanceof VueContextMenuClasse) {
                        observateur.actualiser();
                        return;
                    }
                }
                break;
            case "listeClasse":
                for (Observateur observateur : this.observateurs) {
                    if (observateur instanceof VueListeClasse) {
                        observateur.actualiser();
                        return;
                    }
                }
                break;
            case "recharger methodes":
                for (Observateur observateur : this.observateurs) {
                    if (observateur instanceof VueClasse) {
                        ((VueClasse) observateur).rechargerMethodes();
                        observateur.actualiser();
                        ((VueClasse) observateur).actualiserFleches();
                        return;
                    }
                }
                break;
        }
    }

    public void notifierObservateurFlecheSpecifique(VueClasseAffichage vueClasseAffichage) {
        for (Observateur observateur : this.observateurs) {
            if (observateur instanceof VueClasse) {
                //TODO: ne modifier que les points
                ((VueClasse) observateur).actualiserFlechesSpecifique(vueClasseAffichage);
                return;
            }
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
            classe.deplacer(x, y);

            //Verification permettant l'execution du main MainBootstrap (car
            if(getObservateur("VueClasse").size() != 0) {
                classe = ((VueClasse) getObservateur("VueClasse").get(0)).ajouterClasse(classe);
            }
            relations.put(classe, new ArrayList<>(classe.getRelations()));
            for (Observateur observateur : this.observateurs) {
                if (observateur instanceof VueClasse) {
                    observateur.actualiser();
                }
            }
        }

    }

    /**
     * Méthode qui permet de récupérer la liste des classes
     *
     * @return Liste des classes
     */
    public List<ClasseEntiere> getClasses() {
        return new ArrayList<>(relations.keySet());
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
     * Retourne la liste des flèches d'une classe
     *
     * @param classe Classe
     * @return Liste des flèches
     */
    public List<Relation> getRelations(ClasseEntiere classe) {
        return relations.get(classe);
    }

    /**
     * Méthode qui permet de récupérer la classe sélectionnée
     *
     * @return Classe sélectionnée
     */
    public VueClasseAffichage getCurrentClickedClass() {
        return currentClickedClass;
    }

    /**
     * Méthode qui permet de définir la classe sélectionnée
     *
     * @param currentClickedClass Classe sélectionnée
     */
    public void setCurrentClickedClass(VueClasseAffichage currentClickedClass) {
        if (this.currentClickedClass == null) {
            this.currentClickedClass = currentClickedClass;
            this.currentClickedClass.classeSelectionnee();
            this.notifierObservateur("selection");
        } else {
            this.currentClickedClass.classeDeSelectionnee();
            this.notifierObservateur("selection");
            if (Objects.equals(currentClickedClass.getNom(), this.currentClickedClass.getNom())) {
                this.currentClickedClass = null;
            } else {
                this.currentClickedClass = currentClickedClass;
                this.currentClickedClass.classeSelectionnee();
                this.notifierObservateur("selection");
            }
        }
    }

    /**
     * Methode qui permet de changer la possibilité d'afficher les attributs
     */
    public void afficherAttributs(boolean affiche) {
        getClasses().forEach(classeEntiere -> classeEntiere.setAttributEstAffiche(affiche));
        notifierObservateur("totalite des classes");
    }

    /**
     * Methode qui permet de changer la possibilité d'afficher les attributs d'une classe spécifique
     */
    public void afficherAttributsSelection() {
        this.currentClickedClass.getClasseEntiere().setAttributEstAffiche(!this.currentClickedClass.getClasseEntiere().isAttributEstAffiche());
        notifierObservateur("classe selection complete");
    }

    /**
     * Methode qui permet de changer la possibilité d'afficher les méthodes
     */
    public void afficherMethodes(boolean affiche) {
        getClasses().forEach(classeEntiere -> classeEntiere.setMethodsEstAffiche(affiche));
        notifierObservateur("totalite des classes");
    }

    /**
     * Methode qui permet de changer la possibilité d'afficher les méthodes d'une classe spécifique
     */
    public void afficherMethodesSelection() {
        this.currentClickedClass.getClasseEntiere().setMethodsEstAffiche(!this.currentClickedClass.getClasseEntiere().isMethodsEstAffiche());
        notifierObservateur("classe selection complete");
    }

    /**
     * Methode qui permet de changer la possibilité d'afficher les constructeurs
     */
    public void afficherConstructeurs(boolean affiche) {
        getClasses().forEach(classeEntiere -> classeEntiere.setConstructeurEstAffiche(affiche));
        notifierObservateur("totalite des classes");
    }

    /**
     * Methode qui permet de changer la possibilité d'afficher les constructeurs d'une classe spécifique
     */
    public void afficherConstructeursSelection() {
        this.currentClickedClass.getClasseEntiere().setConstructeurEstAffiche(!this.currentClickedClass.getClasseEntiere().isConstructeurEstAffiche());
        notifierObservateur("classe selection complete");
    }

    /**
     * Methode qui permet de changer la possibilité d'afficher les relations
     */
    public void afficherRelations(boolean affiche) {
        for (Observateur observateur : this.observateurs) {
            if (observateur instanceof VueClasse) {
                ((VueClasse) observateur).definirVisibiliteFleches(affiche);
                break;
            }
        }
    }

    /**
     * Methode qui permet de changer la possibilité d'afficher les interfaces d'une classe spécifique
     */
    public void afficherInterfaceHeritageSelection(String type) {

        for (Observateur observateur : this.observateurs) {
            if (observateur instanceof VueClasse) {
                ((VueClasse) observateur).actualiserRelationsSpecifique(this.currentClickedClass, type);
                return;
            }
        }
    }

    /**
     * Methode qui permet de deplacer une classe
     */
    public void deplacerClasse(int x, int y) {
        this.getCurrentClickedClass().getClasseEntiere().deplacer(x, y);
        notifierObservateur("deplacement selection");
    }

    /**
     * Methode qui permet de retourner la VueClasseAffichage d'une classe en donnant le nom en paramètre
     *
     * @param nom Le nom de la classe
     */

    public VueClasseAffichage getVueClasseAffichage(String nom) {
        for (Observateur observateur : this.observateurs) {
            if (observateur.getClass().getSimpleName().equals("VueClasse")) {
                return ((VueClasse) observateur).getClasseAffichage(nom);
            }
        }
        return null;
    }


    /**
     * Methode qui permet d'ajouter une classeCachée
     *
     * @param classe La classeCachée à ajouter
     */
    public void ajouterClasseCachee(VueClasseAffichage classe) {
        if (!this.hiddenClasses.contains(classe)) {
            this.hiddenClasses.add(classe);
            this.currentClickedClass = classe;
            this.currentClickedClass.setClasseAffichee();
            this.notifierObservateur("update visibilite classe selection");
            //TODO: A modifier: ne pas mettre à jour toutes les fleches
            this.notifierObservateur("update visibilite fleche");
            this.currentClickedClass = null;

        }
    }


    /**
     * Methode qui permet de supprimer une classeCachée
     *
     * @param classe La classeCachée à supprimer
     */
    public void supprimerClasseCachee(VueClasseAffichage classe) {
        if (this.hiddenClasses.contains(classe)) {
            this.hiddenClasses.remove(classe);
            classe.setClasseAffichee();
            classe.actualiserVisibilite();
            this.notifierObservateur("update visibilite fleche");
        }
    }

    /**
     * Methode qui permet de supprimer une classe affichée
     */
    public void supprimerClasseAffichage(VueClasseAffichage classe) {
        this.currentClickedClass = classe;
        this.currentClickedClass.setClasseAffichee(false);
        this.notifierObservateur("update visibilite classe selection");
        this.notifierObservateur("update visibilite fleche");
        this.currentClickedClass = null;
        supprimerObservateur(classe);
        this.relations.remove(classe.getClasseEntiere());
    }

    /**
     * Methode qui permet de supprimer toutes les classes affichées
     */
    public void supprimerClassesAffichage() {
        for (Observateur observateur : getObservateur("VueClasseAffichage")) {
            supprimerClasseAffichage((VueClasseAffichage) observateur);
        }
        ((VueClasse) getObservateur("VueClasse").get(0)).supprimerFleches();
        ((VueClasse) getObservateur("VueClasse").get(0)).supprimerTout();
        this.notifierObservateur("totalite des classes");
    }

    /**
     * Methode qui retourne la liste des classes cachées
     */
    public List<VueClasseAffichage> getHiddenClasses() {
        return hiddenClasses;
    }

    /**
     * Méthode qui permet de retourner la liste des observateurs d'un type donné
     * @param nom Le nom de la classe de l'observateur
     */
    public List<Observateur> getObservateur(String nom) {
        List<Observateur> observateurs = new ArrayList<>();
        for (Observateur observateur : this.observateurs) {
            if (observateur.getClass().getSimpleName().equals(nom)) {
                observateurs.add(observateur);
            }
        }
        return observateurs;
    }

    /**
     * Methode qui permet de tester si la vue en parametre est une vueClasseAffichage qui est cachée
     * @param vueClasseAffichage1 La vue à tester
     * @return booleen qui indique si la vue est cachée
     */

    public boolean classeMasquee(VueClasseAffichage vueClasseAffichage1) {
        for (VueClasseAffichage vueClasseAffichage2 : hiddenClasses) {
            if (vueClasseAffichage2.getNom().equals(vueClasseAffichage1.getNom())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Methode qui permet d'ajouter une methode à une classe
     * @param accessibilite L'accessibilité de la méthode
     * @param modifiers Liste des etats de la méthode
     * @param nom Le nom de la méthode
     * @param type Le type de retour de la méthode
     * @param param paramètres de la méthode
     */
    public void ajouterMethode(Accessibilite accessibilite, ArrayList<Etat> modifiers, String nom, String type, String param){
        this.currentClickedClass.getClasseEntiere().ajouterMethode(new Methode(nom,type,accessibilite,param,modifiers));
        this.currentClickedClass.setMethodes();
        this.notifierObservateur("classe selection complete");
    }

    /**
     * Méthode qui permet d'ajouter un constructeur à une classe
     * @param accessibilite L'accessibilité du constructeur
     * @param nom           Le nom du constructeur
     * @param param         Les paramètres du constructeur
     */
    public void ajouterConstructeur(Accessibilite accessibilite, String nom, String param){
        this.currentClickedClass.getClasseEntiere().ajouterConstructeur(new Constructeur(nom,accessibilite,param));
        this.currentClickedClass.setConstructeur();
        this.notifierObservateur("classe selection complete");
    }


    /**
     * Méthode qui permet d'ajouter un attribut à une classe
     * @param accessibilite L'accessibilité de l'attribut
     * @param modifiers Liste des etats de l'attribut
     * @param nom Le nom de l'attribut
     * @param type Le type de l'attribut
     */
    public void ajouterAttribut(Accessibilite accessibilite, List<Etat> modifiers, String nom, String type){
        this.currentClickedClass.getClasseEntiere().ajouterAttribut(nom,type,modifiers,accessibilite);
        this.currentClickedClass.setMethodes();
        this.relations.put(this.currentClickedClass.getClasseEntiere(), new ArrayList<>(this.currentClickedClass.getClasseEntiere().getRelations()));
        this.notifierObservateur("classe selection complete");
    }

    /**
     * Méthode permettant d'ajouter une classe vierge dans le graphique
     * @param nom nom de la classe
     * @param nomExtend nom de la classe qu'elle extend
     * @param nomImplemente nom de la classe qu'elle implémente
     * @param accessibilite accesibilite de la classe
     * @param etats etats de la classe
     * @param entite entitée de la classe
     */
    public void ajouterClasse(String nom, String nomExtend, String nomImplemente, Accessibilite accessibilite, ArrayList<Etat> etats, Entite entite){
        ClasseEntiere classe = new ClasseEntiere(nom, nomImplemente, nomExtend, accessibilite, etats, entite);
        this.ajouterClasse(classe);

    }

    /**
     * Méthode qui permet de afficher ou non les Getters et Setters
     */
    public void inverserAffichageGetIsSet(){
        VueClasseAffichage.setIsGetAffichee = !VueClasseAffichage.setIsGetAffichee;
        this.notifierObservateur("recharger methodes");
    }

}
