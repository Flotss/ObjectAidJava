package org.teamtree.objectaid.Classe;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.teamtree.objectaid.Fabrique.FabriqueAffichage;
import org.teamtree.objectaid.MVC.diagramIcons.*;

/**
 * Classe qui représente l'affichage d'une classe
 */
public class ClasseAffichage extends VBox {

    /**
     * Nom de cette classe, permettant de la retrouver lorsqu'on clique dessus notamment
     */
    private String nom;

    /**
     * HBox qui contient la définition de la classe
     */
    private HBox definition;

    /**
     * VBox qui contient les constructeurs de la classe
     */
    private VBox constructeur;

    /**
     * VBox qui contient les attributs de la classe
     */
    private VBox attributs;

    /**
     * VBox qui contient les méthodes de la classe
     */
    private VBox methodes;

    /**
     * Constructeur de la classe
     * @param nom Le nom de la classe
     */

    public ClasseAffichage(String nom){
        this.nom = nom;
        this.definition = new HBox();
        this.constructeur = new VBox();
        this.attributs = new VBox();
        this.methodes = new VBox();
    }

    /**
     * Méthode qui permet de créer la partie définition de la classe
     * @param c La classe à afficher
     */

    public void setDefinition(ClasseEntiere c) {
        //partie definition
        String def = c.getDefinition().getNom();
        Label definitionLabel = new Label(def);

        //Icon selon le type de la classe
        definition.getChildren().addAll(FabriqueAffichage.fabriqueIcon(c).getIcon(), definitionLabel);
    }

    /**
     * Méthode qui permet de créer la partie constructeurs de la classe
     * @param c La classe à afficher
     */

    public void setAttributs(ClasseEntiere c){
        if(c.isAttributEstAffiche()) {
            //Si il existe des attributs, on crée une bordure sur la catégorie du dessus (pour crée une séparation)
            if (c.getAttributs().size() != 0) {
                definition.setStyle("-fx-border-color: transparent transparent black transparent; -fx-border-width: 1px;");
            }
            //Pour chaque attributs
            for (Attribut attributX: c.getAttributs()) {
                String att = FabriqueAffichage.fabriqueAcces(attributX.getAccessibilite()) + " " + attributX.getType() + " " + attributX.getNom();
                Label attributLabel = new Label(att);
                attributs.getChildren().add(attributLabel);
            }
        }
    }

    /**
     * Méthode qui permet de créer la partie constructeurs de la classe
     * @param c La classe à afficher
     */

    public void setConstructeur(ClasseEntiere c){
        //partie constructeur
        //On vérifie que les constructeurs doivent être affichés
        if(c.isConstructeurEstAffiche()) {
            //Si il existe des constructeurs, on crée une bordure sur la catégorie du dessus (pour crée une séparation)
            if (c.getContructeurs().size() != 0) {
                attributs.setStyle("-fx-border-color: transparent transparent black transparent; -fx-border-width: 1px;");
            }
            //Pour chaque Constructeurs
            for (Constructeur constructeurX:c.getContructeurs()) {
                //Debut de la ligne (accesibilité, nom,...)
                String constr = FabriqueAffichage.fabriqueAcces(constructeurX.getAccessibilite()) + " " + constructeurX.getNom() + "(";
                int n = 0;
                //Pour chaque parametre du constructeur, on l'écrit dans l'affichage
                for(Parametre parametreX: constructeurX.getParametre()){
                    constr += parametreX.getType() + " " + parametreX.getNom();
                    if (n != constructeurX.getParametre().size() - 1) {
                        constr += ", ";
                    }
                    n++;
                }
                //On écrit la fin du constructeur puis on l'ajoute avec les autres constructeurs
                constr += ")";
                Label constrLabel = new Label(constr);
                constructeur.getChildren().add(constrLabel);
            }
        }
    }

    /**
     * Méthode qui permet de créer la partie méthodes de la classe
     * @param c La classe à afficher
     */

    public void setMethodes(ClasseEntiere c){
        //partie methodes
        if(c.isMethodsEstAffiche()) {
            if (c.getMethods().size() != 0) {
                constructeur.setStyle("-fx-border-color: transparent transparent black transparent; -fx-border-width: 1px;");
            }
            for (int i = 0; i < c.getMethods().size(); i++) {
                Methode methodeX = c.getMethods().get(i);
                String meth = FabriqueAffichage.fabriqueAcces(methodeX.getAccessibilite()) + " " + methodeX.getNom() + "(";
                for (int j = 0; j < methodeX.getParametre().size(); j++) {
                    meth += methodeX.getParametre().get(j).getType() + " " + methodeX.getParametre().get(j).getNom();
                    if (j != methodeX.getParametre().size() - 1) {
                        meth += ", ";
                    }
                }
                meth += "): " + c.getMethods().get(i).getTypeRetourne();
                Label methodeLabel = new Label(meth);
                methodes.getChildren().add(methodeLabel);
            }
        }
    }

    /**
     * Méthode qui permet de créer l'affichage de la classe
     * @param c La classe à afficher
     */

    public void setPosition(ClasseEntiere c){
        this.setLayoutX(c.getX());
        this.setLayoutY(c.getY());
    }

    /**
     * Méthode qui permet de créer l'affichage de la classe
     * @param c La classe à afficher
     */

    public void afficherClasse(ClasseEntiere c){

    //On ajoute les différentes parties de la classe
        this.getChildren().clear();
        this.getChildren().add(definition);
        if (c.isAttributEstAffiche()) {
            this.getChildren().add(attributs);
        }
        if (c.isConstructeurEstAffiche()) {
            this.getChildren().add(constructeur);
        }
        if (c.isMethodsEstAffiche()) {
            this.getChildren().add(methodes);
        }
    }


    public String getNom(){
        return this.nom;
    }

    public HBox getDefinition() {
        return definition;
    }

    public void setDefinition(HBox definition) {
        this.definition = definition;
    }

    public VBox getConstructeur() {
        return constructeur;
    }

    public void setConstructeur(VBox constructeur) {
        this.constructeur = constructeur;
    }

    public VBox getAttributs() {
        return attributs;
    }

    public void setAttributs(VBox attributs) {
        this.attributs = attributs;
    }

    public VBox getMethodes() {
        return methodes;
    }

    public void setMethodes(VBox methodes) {
        this.methodes = methodes;
    }
}
