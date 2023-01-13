package org.teamtree.objectaid.Service;

import javafx.stage.DirectoryChooser;
import org.teamtree.objectaid.Classe.Attribut;
import org.teamtree.objectaid.Classe.ClasseEntiere;
import org.teamtree.objectaid.Classe.Constructeur;
import org.teamtree.objectaid.Classe.Methode;
import org.teamtree.objectaid.Classe.Relations.Association;
import org.teamtree.objectaid.Classe.Relations.Heritage;
import org.teamtree.objectaid.Classe.Relations.Implementation;
import org.teamtree.objectaid.Classe.Relations.Relation;
import org.teamtree.objectaid.MVC.Composant.Alert;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.teamtree.objectaid.MVC.Composant.Alert.afficheAlert;

/**
 * Service s'occupant de générer le squelette d'une classe java à partir d'une liste de classe entière.
 */
public class SqueletteService {

    /**
     * Génère le squelette d'une classe java à partir d'une liste de classe entière.
     *
     * @param classes Liste de classe entière
     */
    public void genererSqueletteDiagramme(List<ClasseEntiere> classes) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choisir un dossier");
        File f = directoryChooser.showDialog(null);

        if (f == null) {
            return;
        }

        StringBuilder messageAlert = new StringBuilder();
        for (ClasseEntiere classe : classes) {
            messageAlert.append(genererSquelette(f, classe)).append("\n");
        }

        afficheAlert(messageAlert.toString());
    }


    /**
     * Génère le squelette d'une classe java à partir d'une classe entière.
     *
     * @param classe Dossier dans lequel générer le squelette
     * @param classe Classe entière
     */
    public void genererSqueletteUniqueClasse(ClasseEntiere classe) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choisir un dossier");
        File f = directoryChooser.showDialog(null);

        if (f == null) {
            return;
        }

        Alert.afficheAlert(genererSquelette(f, classe));
    }


    /**
     * Génère le squelette d'une classe java à partir d'une classe entière.
     *
     * @param f      Dossier dans lequel générer le squelette
     * @param classe Classe entière
     */
    private String genererSquelette(File f, ClasseEntiere classe) {
        String message = "Erreur lors de la génération du squelette de la classe " + classe.getNom() + " !";

        BufferedWriter bw = null;
        try {
            StringBuilder squeletteContenu = new StringBuilder();

            // Signature de la classe
            squeletteContenu.append(classe.getDefinition().getAccessibilite()).append(" ").append(classe.getDefinition().getEntite()).append(" ").append(classe.getDefinition().getNom());

            // Hérédité
            Heritage heritage = classe.getRelations().stream().filter(relation -> relation instanceof Heritage).map(relation -> (Heritage) relation).findFirst().orElse(null);
            if (heritage != null) {
                squeletteContenu.append(" extends ").append(heritage.getDestination());
            }

            // Implémentations
            Implementation[] implementations = classe.getRelations().stream().filter(relation -> relation instanceof Implementation).map(relation -> (Implementation) relation).toArray(Implementation[]::new);
            if (implementations.length > 0) {
                squeletteContenu.append(" implements ");
                for (int i = 0; i < implementations.length; i++) {
                    squeletteContenu.append(implementations[i].getDestination());
                    if (i != implementations.length - 1) {
                        squeletteContenu.append(", ");
                    }
                }
            }
            squeletteContenu.append(" {\n");

            // Attributs
            for (Attribut attr : classe.getAttributs()) {
                squeletteContenu.append("\t").append(attr.getAccessibilite().getAcces()).append(" ").append(attr.getType()).append(" ").append(attr.getNom()).append(";\n\n");
            }

            // Attributs avec Relation
            for (Relation relation : classe.getRelations()) {
                if (!(relation instanceof Association)) {
                    continue;
                }

                Attribut attr = ((Association) relation).getAttribut();

                String acces = "";
                if (!attr.getAccessibilite().getAcces().equals("default")) {
                    acces = attr.getAccessibilite().getAcces();
                }
                squeletteContenu.append("\t").append(acces).append(" ").append(attr.getType()).append(" ").append(attr.getNom()).append(";\n\n");
            }

            // Constructeurs
            for (Constructeur constructeur : classe.getContructeurs()) {
                squeletteContenu.append("\t").append(constructeur.getAccessibilite().getAcces()).append(" ").append(constructeur.getNom()).append("(");
                for (int i = 0; i < constructeur.getParametre().size(); i++) {
                    squeletteContenu.append(constructeur.getParametre().get(i).getType()).append(" ").append(constructeur.getParametre().get(i).getNom());
                    if (i != constructeur.getParametre().size() - 1) {
                        squeletteContenu.append(", ");
                    }
                }
                squeletteContenu.append(") {\n\t\t\n\t}\n\n");
            }

            // Methodes
            for (Methode methode : classe.getMethods()) {
                squeletteContenu.append("\t").append(methode.getAccessibilite().getAcces()).append(" ").append(methode.getTypeRetourne()).append(" ").append(methode.getNom()).append("(");
                for (int i = 0; i < methode.getParametre().size(); i++) {
                    squeletteContenu.append(methode.getParametre().get(i).getType()).append(" ").append(methode.getParametre().get(i).getNom());
                    if (i != methode.getParametre().size() - 1) {
                        squeletteContenu.append(", ");
                    }
                }
                squeletteContenu.append(") {\n\t\t\n\t}\n\n");
            }
            squeletteContenu.append("}");

            File file = new File(f.getPath() + "\\" + classe.getNom() + ".java");

            // Si le fichier est existant, on le réécrit
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(squeletteContenu.toString());
            message = "Squelette de classe de : " + classe.getNom() + " genere avec succes";
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (Exception ex) {
                System.out.println("Erreur dans la fermeture de BufferedWriter" + ex);
            }
        }
        return message;
    }
}
