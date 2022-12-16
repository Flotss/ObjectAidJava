package org.teamtree.objectaid.Classe;

import org.teamtree.objectaid.Etat.Etat;
import org.teamtree.objectaid.Accessibilite.Accessibilite;
import org.teamtree.objectaid.Fabrique.FabriqueAccessibilite;
import org.teamtree.objectaid.Fabrique.FabriqueEtat;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;

public class Methode {
    private final String nom;
    private final String typeRetourne;

    private final ArrayList<Parametre> parametre;
    private final ArrayList<Etat> etats;
    private final Accessibilite accessibilite;

    public Methode(Method method) {
        // Nom de la methode
        this.nom = method.getName();

        // Type de retour
        this.typeRetourne = method.getReturnType().getSimpleName();


        // Accessibilite : public, private, protected, default
        FabriqueAccessibilite fabriqueAccessibilite = new FabriqueAccessibilite();
        this.accessibilite = fabriqueAccessibilite.getAccessibilite(method.getModifiers());


        // Parametres de la methode
        this.parametre = new ArrayList<>();
        for (Parameter parameter : method.getParameters()) {
            this.parametre.add(new Parametre(parameter));
        }


        // Etats de la methode : static, final, abstract
        FabriqueEtat fabriqueEtat = new FabriqueEtat();
        this.etats = fabriqueEtat.getEtat(method.getModifiers());
    }

    public String getTypeRetourne() {
        return typeRetourne;
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<Parametre> getParametre() {
        return parametre;
    }

    public ArrayList<Etat> getEtats() {
        return etats;
    }

    public String getAccessibilite() {
        return accessibilite.getAcces();
    }

    @Override
    public String toString() {
        String info = accessibilite.getAcces();
        if (etats.size() > 0){
            for (Etat etat : etats) {
                info += " " + etat.getEtat();
            }
        }
        info += " " + nom + "(";
        if (parametre.size() > 0) {
            for (Parametre param : parametre) {
                info += param.toString() + ", ";
            }
            info = info.substring(0, info.length() - 2);
        }
        info += ") : " + typeRetourne;

        return info + "\n";
    }
}
