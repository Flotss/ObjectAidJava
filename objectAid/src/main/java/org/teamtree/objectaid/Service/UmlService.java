package org.teamtree.objectaid.Service;

import javafx.stage.FileChooser;
import org.teamtree.objectaid.Classe.ClasseEntiere;


import java.io.*;
import java.util.List;

public class UmlService {

    public void genererUmltoCompilation(List<ClasseEntiere> classes){
        String uml = genererUml(classes);
//        SourceStringReader reader = new SourceStringReader(source);
//        final ByteArrayOutputStream os = new ByteArrayOutputStream();
//        // Write the first image to "os"
//        String desc = reader.generateImage(os, new FileFormatOption(FileFormat.SVG));
//        os.close();
//
//        // The XML is stored into svg
//        final String svg = new String(os.toByteArray(), Charset.forName("UTF-8"));
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
