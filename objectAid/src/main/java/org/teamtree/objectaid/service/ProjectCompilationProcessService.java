package org.teamtree.objectaid.service;

import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.util.FileExtension;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Objects;

public class ProjectCompilationProcessService {
    private final Model model;

    public ProjectCompilationProcessService(Model model) {
        this.model = model;
    }

    public void compileProject(final File file) {
        if (file.isDirectory()) {
            Arrays.stream(Objects.requireNonNull(file.listFiles())).forEach(this::compileProject);
        } else {
            if (FileExtension.isJavaFile(file.getName())) {
                try {
                    final var temporaryOutputDirectory = Files.createTempDirectory("objectaid").toFile();
                    final var processBuilder = new ProcessBuilder("javac", "-d", temporaryOutputDirectory.getAbsolutePath(), file.getAbsolutePath());

                    System.out.println(processBuilder.command());

                    final var res = processBuilder.start().waitFor();

                    System.out.println(Arrays.toString(temporaryOutputDirectory.listFiles()));

                    // if listFiles is empty, then humanize the error res
                    if (res != 0) {
                        System.out.println("Compilation error for file " + file.getName() + " with error code " + res);
                    } else {
                        final var classLoader = new URLClassLoader(new URL[]{temporaryOutputDirectory.toURI().toURL()});
                        final var className = file.getName().replace(".java", "");
                        final var clazz = classLoader.loadClass(className);
                    }

                    final var url = temporaryOutputDirectory.toURI().toURL();
                    System.out.println(url);
                    final var clazz = new URLClassLoader(new URL[]{url}).loadClass(url + file.getName().replace(".java", ""));

                    this.model.addClassPathEntry(clazz.getSimpleName(), clazz);

                    System.out.println("Class " + clazz.getSimpleName() + " loaded");

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
