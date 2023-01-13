package org.teamtree.objectaid.util.scripts;

import org.teamtree.objectaid.Classe.ClasseEntiere;
import org.teamtree.objectaid.Service.UmlService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MakeUmlOfThisProject {

    public static final List<String> pathClasses = new ArrayList<>();

    public static String findAllClasses(File file, String path) {
        if (file.isDirectory()) {
            path += file.getName() + ".";
            for (File f : Objects.requireNonNull(file.listFiles())) {
                findAllClasses(f, path);
            }
        } else {
            if (file.getName().endsWith(".class")) {
                pathClasses.add(path + file.getName().replace(".class", ""));
            }
        }
        return path;
    }

    public static void main(String[] args) {
        File file = new File("build/classes/java/main/org");
        findAllClasses(file, "");
        pathClasses.stream().filter(s -> s.endsWith(".")).forEach(pathClasses::remove);
        pathClasses.forEach(s -> s = s.trim());
        for (int i = 0; i < pathClasses.size(); i++) {
            System.out.println(pathClasses.get(i));
        }

        List<ClasseEntiere> classes = new ArrayList<>();
        for (String path : pathClasses) {
            try {
                classes.add(new ClasseEntiere(path));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        UmlService umlService = new UmlService();
        umlService.genererUmltoFile(classes);
    }
}
