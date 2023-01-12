package org.teamtree.objectaid.MVC.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import org.teamtree.objectaid.Classe.ClasseEntiere;
import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.MVC.Vue.VueClasseAffichage;
import org.teamtree.objectaid.MVC.Vue.VueContextMenuClasse;
import org.teamtree.objectaid.ObjectAidApplication;
import org.teamtree.objectaid.Service.SqueletteService;
import org.teamtree.objectaid.Service.UmlService;

/**
 * Classe qui permet de gérer tous les MenuItem de l'application
 */
public class MenuItemController implements EventHandler<ActionEvent> {

    /** Booleens pour savoir l'etat de l'affichage des boutons de l'affichage general des classes */
    private static boolean attributGenerauxAffiche = true;
    private static boolean constructeurGenerauxAffiche = true;
    private static boolean methodesGenerauxAffiche = true;
    private static boolean relationsGeneralesAffiche = true;

    /** Modèle */
    private final Model model;

    /**
     * Constructeur
     * @param model Modèle
     */
    public MenuItemController(Model model) {
        this.model = model;
    }

    /**
     * Méthode qui permet de gérer les MenuItem de l'application
     * @param event Evènement
     */
    @Override
    public void handle(ActionEvent event) {
        switch (((MenuItem)event.getSource()).getText()){
            case "Attributs":
                model.afficherAttributsSelection();
                break;
            case "Méthodes":
                model.afficherMethodesSelection();
                break;
            case "Constructeurs":
                model.afficherConstructeursSelection();
                break;
            case "Cacher":
                for (MenuItem m: ObjectAidApplication.menuBar.getMenus().get(1).getItems()) {
                    if (m.getText().equals(model.getCurrentClickedClass().getNom())) {
                        m.setStyle("-fx-text-fill: red");
                    }
                }
                model.ajouterClasseCachee(model.getCurrentClickedClass());
                break;
            case "Afficher/Cacher":
                VueClasseAffichage classe = model.getClasse(((MenuItem) event.getSource()).getParentMenu().getText()).get().getClasseAffichage();
                System.out.println(model.getCurrentClickedClass());
                if (!model.getHiddenClasses().contains(classe)) {
                    model.setCurrentClickedClass(classe);
                    model.ajouterClasseCachee(classe);
                    ((MenuItem) event.getSource()).getParentMenu().setStyle("-fx-text-fill: red");
                } else {
                    model.supprimerClasseCachee(classe);
                    ((MenuItem) event.getSource()).getParentMenu().setStyle("-fx-text-fill: black");
                }
                break;

            case "Supprimer":
                if(((MenuItem) event.getSource()).getParentMenu() != null){
                    VueClasseAffichage classe1 = model.getClasse(((MenuItem) event.getSource()).getParentMenu().getText()).get().getClasseAffichage();
                    model.supprimerClasseAffichage(classe1);
                    ((MenuItem) event.getSource()).getParentMenu().getParentMenu().getItems().remove(((MenuItem) event.getSource()).getParentMenu());
                } else {
                    VueClasseAffichage classe1 = ((VueContextMenuClasse) ((MenuItem) event.getSource()).getParentPopup()).getClasse();
                    model.supprimerClasseAffichage(classe1);
                    for (MenuItem m: ObjectAidApplication.menuBar.getMenus().get(1).getItems()) {
                        if (m.getText().equals(classe1.getNom())) {
                            ObjectAidApplication.menuBar.getMenus().get(1).getItems().remove(m);
                            break;
                        }
                    }
                }
                break;
            case "Générer le squelette":
                ClasseEntiere classeE = model.getCurrentClickedClass().getClasseEntiere();
                SqueletteService squeletteService = new SqueletteService();
                squeletteService.genererSqueletteUniqueClasse(classeE);
                break;
            case "Cacher interface":
                model.afficherInterfaceHeritageSelection("Implementation");
                break;
            case "Cacher heritage":
                model.afficherInterfaceHeritageSelection("Heritage");
                break;
        }
    }
}
