package org.teamtree.objectaid.Service;

import javafx.stage.FileChooser;
import org.teamtree.objectaid.Classe.ClasseEntiere;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class UmlService {

    public void genererUmltoFile(List<ClasseEntiere> classes) {
        // Choisir un fichier pour l'enregistrement
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer le fichier");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier PUML", "*.puml"));
        File f = fileChooser.showSaveDialog(null);


        if (f == null) return;

        String contenu = "@startuml\n";
        for (ClasseEntiere classe : classes) {
             contenu += classe.getUml() + "\n";
        }
        contenu += "@enduml";

        String message = "Erreur lors de la génération de l'uml !";

        BufferedWriter bw = null;
        try {
            // Verification path
            File file = null;
            if (! f.getPath().contains(".puml")){
                file = new File(f.getPath() + ".puml");
            }else{
                file = new File(f.getPath());
            }


            // Si le fichier est existant, on le réécrit
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(contenu);
            message = "UML généré avec succès !";
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

        Alert.afficheAlert("UML généré avec succès !");
    }
}
