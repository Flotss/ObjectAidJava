package org.teamtree.objectaid.Service;

import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.util.FileExtension;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;

/**
 * Représente le chargeur de classe, s'occupant de la partie business de la création de classe
 * à partir de fichiers de classes.
 */
public class JavaProjectClassLoaderService extends ClassLoader {

    private final Path rootPath;
    private final Model model;

    public JavaProjectClassLoaderService(final Path rootPath, final Model model) {
        this.rootPath = rootPath;
        this.model = model;
    }

    /**
     * Se charge de charger les classes de manière récursive à partir d'un dossier préalablement donné.
     *
     * @param directory dossier à partir duquel charger les classes
     */
    public void loadClasses(final File directory) {
        Arrays.stream(Objects.requireNonNull(directory.listFiles())).forEach(file -> {
            if (file.isDirectory()) {
                loadClasses(file);
            } else {
                if (!FileExtension.isClassFile(file)) {
                    return;
                }

                final var service = new JavaClassFullQualifiedNameResolverService(rootPath);
                final var className = file.getName().substring(0, file.getName().length() - 6);
                final var fqn = service.getClassFQN(directory, file, className);
                try {
                    final var cl = new URLClassLoader(
                        new java.net.URL[]{rootPath.toAbsolutePath().toUri().toURL()});
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

