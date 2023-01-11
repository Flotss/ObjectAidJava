package org.teamtree.objectaid.service;

import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.util.FileExtension;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.Arrays;

/**
 * Représente le chargeur de classe, s'occupant de la partie business de la création de classe
 * à partir de fichiers de classes.
 */
public class JavaProjectClassLoader extends ClassLoader {
    private final Path rootPath;
    private final StringBuffer packagePath;

    private final Model model;

    public JavaProjectClassLoader(Path rootPath, Model model) {
        this.rootPath = rootPath;
        this.packagePath = new StringBuffer();
        this.model = model;
    }

    /**
     * Se charge de charger les classes de manière récursive à partir d'un dossier préalablement donné.
     *
     * @param directory dossier à partir duquel charger les classes
     */
    public void loadClasses(final File directory) {
        // On itère à travers tous les fichiers du dossier donné en paramètre.
        Arrays.stream(directory.listFiles()).forEach(file -> {
            if (file.isDirectory()) {
                // Si le dossier itéré est un dossier, on le parcourt récursivement en l'ajoutant au packagePath.
                packagePath.append(file.getName()).append(".");
                loadClasses(file);
            } else {

                if (!FileExtension.isClassFile(file)) return;

                final var className = file.getName().substring(0, file.getName().length() - 6);
                final var fqn = packagePath + className;

                try {
                    // On charge la classe à partir du path racine.
                    ClassLoader cl = new URLClassLoader(new java.net.URL[]{rootPath.toAbsolutePath().toUri().toURL()});
                    final var c = cl.loadClass(fqn);

                    model.addClassPathEntry(c.getSimpleName(), c);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}

