package org.teamtree.objectaid.Service;

import javafx.stage.FileChooser;
import net.sourceforge.plantuml.FileFormat;
import org.teamtree.objectaid.Classe.ClasseEntiere;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Service s'occupant de générer un diagramme UML à partir d'une liste de classes entières.
 */
public class UmlService {

    public void genererUmltoCompilation(List<ClasseEntiere> classes) {
        String uml = genererUml(classes);
        SourceStringReader reader = new SourceStringReader(uml);
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        // Write the first image to "os"
        try {
            String desc = reader.generateImage(os, new FileFormatOption(FileFormat.SVG));
            os.close();

            // The XML is stored into svg
            final String svg = os.toString(StandardCharsets.UTF_8);

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choisir un fichier");
            File f = fileChooser.showSaveDialog(null);
            if (f == null) {
                return;
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(f));
            writer.write(svg);
            writer.close();

            Alert.afficheAlert("Diagramme UML généré avec succès !");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void genererUmltoFile(List<ClasseEntiere> classes) {
        // Choisir un fichier pour l'enregistrement
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer le fichier");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier PUML", "*.puml"));
        File f = fileChooser.showSaveDialog(null);


        if (f == null) return;

        String contenu = genererUml(classes);

        String message = "Erreur lors de la génération de l'uml !";

        BufferedWriter bw = null;
        try {
            // Verification path
            File file;
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

        Alert.afficheAlert(message);
    }

    private String genererUml(List<ClasseEntiere> classes){
        StringBuilder contenu = new StringBuilder("@startuml\n");
        for (ClasseEntiere classe : classes) {
            contenu.append(classe.getUml()).append("\n");
        }
        contenu.append("@enduml");
        return contenu.toString();
    }
}
