package org.teamtree.objectaid.service;

import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.util.FileExtension;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.Arrays;

public class JavaProjectClassLoader extends ClassLoader {
    private final Path rootPath;
    private final StringBuffer packagePath;

    private final Model model;

    public JavaProjectClassLoader(Path rootPath, Model model) {
        this.rootPath = rootPath;
        this.packagePath = new StringBuffer();
        this.model = model;
    }

    public Class<?> loadClass(String name) throws ClassNotFoundException {
        try {
            String path = name.replace('.', '/').concat(".class");
            InputStream is = getResourceAsStream(path);
            if (is == null) {
                return super.loadClass(name);
            }
            byte[] b = new byte[is.available()];
            is.read(b);
            return defineClass(name, b, 0, b.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name);
        }
    }

    public void loadClasses(final File directory) {
        // It should finds the fqn
        Arrays.stream(directory.listFiles()).forEach(file -> {
            if (file.isDirectory()) {
                packagePath.append(file.getName()).append(".");
                loadClasses(file);
            } else {

                if (!FileExtension.isClassFile(file)) return;

                final var className = file.getName().substring(0, file.getName().length() - 6);
                final var fqn = packagePath.toString() + className;

                System.out.println("Class name: " + className);
                System.out.println("Class fqn: " + fqn);

                try {
                    ClassLoader cl = new URLClassLoader(new java.net.URL[]{rootPath.toAbsolutePath().toUri().toURL()});
                    final var c = cl.loadClass(fqn);

                    model.addClassPathEntry(c.getName(), c);

                    System.out.println(c.getConstructors());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}

