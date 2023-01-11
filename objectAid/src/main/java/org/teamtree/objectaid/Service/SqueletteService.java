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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SqueletteService {

    public void genererSqueletteDiagramme(List<ClasseEntiere> classes) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choisir un dossier");
        File f = directoryChooser.showDialog(null);

        if (f == null) return;

        for (ClasseEntiere classe : classes) {
            genererSquelette(f, classe);
        }
    }


    public void genererSqueletteUniqueClasse(ClasseEntiere classe) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choisir un dossier");
        File f = directoryChooser.showDialog(null);

        if (f == null) return;

        genererSquelette(f, classe);
    }


    private void genererSquelette(File f, ClasseEntiere classe) {
        BufferedWriter bw = null;
        try {
            String squeletteContenu = "";

            // Signature de la classe
            squeletteContenu += classe.getDefinition().getAccessibilite() + " " + classe.getDefinition().getEntite() + " " + classe.getDefinition().getNom();

            // Hérédité
            Heritage heritage = classe.getRelations().stream().filter(relation -> relation instanceof Heritage).map(relation -> (Heritage) relation).findFirst().orElse(null);
            if (heritage != null) {
                squeletteContenu += " extends " + heritage.getDestination();
            }

            // Implémentations
            Implementation[] implementations = classe.getRelations().stream().filter(relation -> relation instanceof Implementation).map(relation -> (Implementation) relation).toArray(Implementation[]::new);
            if (implementations.length > 0) {
                squeletteContenu += " implements ";
                for (int i = 0; i < implementations.length; i++) {
                    squeletteContenu += implementations[i].getDestination();
                    if (i != implementations.length - 1) {
                        squeletteContenu += ", ";
                    }
                }
            }
            squeletteContenu += " {\n";

            // Attributs
            for (Attribut attr : classe.getAttributs()) {
                squeletteContenu += "\t" + attr.getAccessibilite().getAcces() + " " + attr.getType() + " " + attr.getNom() + ";\n\n";
            }

            // Attributs avec Relation
            for (Relation relation : classe.getRelations()) {
                if (!(relation instanceof Association)) continue;

                Attribut attr = ((Association) relation).getAttribut();
                squeletteContenu += "\t" + attr.getAccessibilite().getAcces() + " " + attr.getType() + " " + attr.getNom() + ";\n\n";
            }

            // Constructeurs
            for (Constructeur constructeur : classe.getContructeurs()) {
                squeletteContenu += "\t" + constructeur.getAccessibilite().getAcces() + " " + constructeur.getNom() + "(";
                for (int i = 0; i < constructeur.getParametre().size(); i++) {
                    squeletteContenu += constructeur.getParametre().get(i).getType() + " " + constructeur.getParametre().get(i).getNom();
                    if (i != constructeur.getParametre().size() - 1) {
                        squeletteContenu += ", ";
                    }
                }
                squeletteContenu += ") {\n\t\t\n\t}\n\n";
            }

            // Methodes
            for (Methode methode : classe.getMethods()) {
                squeletteContenu += "\t" + methode.getAccessibilite().getAcces() + " " + methode.getTypeRetourne() + " " + methode.getNom() + "(";
                for (int i = 0; i < methode.getParametre().size(); i++) {
                    squeletteContenu += methode.getParametre().get(i).getType() + " " + methode.getParametre().get(i).getNom();
                    if (i != methode.getParametre().size() - 1) {
                        squeletteContenu += ", ";
                    }
                }
                squeletteContenu += ") {\n\t\t\n\t}\n\n";
            }
            squeletteContenu += "}";

            File file = new File(f.getPath() + "\\" + classe.getNom() + ".java");

            // Si le fichier est existant, on le réécrit
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(squeletteContenu);
            System.out.println("Squelette de classe de : " + classe.getNom() + " genere avec succes");

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
            } catch (Exception ex) {
                System.out.println("Erreur dans la fermeture de BufferedWriter" + ex);
            }
        }
    }
}
