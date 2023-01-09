package org.teamtree.objectaid.service;

import javafx.scene.effect.Reflection;
import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.util.FileExtension;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProjectCompilationProcessService {
    private final Model model;

    public ProjectCompilationProcessService(Model model) {
        this.model = model;
    }

    public void compileProject(final Path path) {
        if (path.toFile().isDirectory()) {
            final var files = Arrays.stream(Objects.requireNonNull(path.toFile().listFiles())).filter(File::isFile).filter(FileExtension::isClassFile).toList();

            files.forEach(file -> {
                try {
                    final var loader = new CLoader();
                    final var clazz = loader.loadClass(file.getAbsolutePath());

                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });

        }
    }

    /**
     void compile(Path source, Path target) {

     // clear target dir
     target.toFile().deleteOnExit();

     final var start = System.currentTimeMillis();

     try {
     final var javaFiles = Files.walk(source).filter(Files::isRegularFile).filter(p -> p.getFileName().toString().endsWith(FileExtension.JAVA_EXTENSION)).map(Path::toAbsolutePath).map(Path::toString).toList();

     final var args = Stream.concat(Stream.of("javac", "-cp", source.toString()), javaFiles.stream()).toArray(String[]::new);
     final var process = new ProcessBuilder(args).redirectErrorStream(true).inheritIO().start();
     process.waitFor();

     } catch (IOException | InterruptedException e) {
     throw new RuntimeException(e);
     }

     }

     */
}