package org.teamtree.objectaid.MVC.Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.MVC.Vue.VueClasseAffichage;


public class ListenerModificationTailleClasse implements ChangeListener {
    private final Model model;
    private final VueClasseAffichage vueClasseAffichage;

    public ListenerModificationTailleClasse(Model model, VueClasseAffichage vueClasseAffichage) {
        this.model = model;
        this.vueClasseAffichage = vueClasseAffichage;

    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        model.notifierObservateurFlecheSpecifique(this.vueClasseAffichage);
    }
}
