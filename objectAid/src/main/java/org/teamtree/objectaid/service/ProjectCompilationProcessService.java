package org.teamtree.objectaid.service;

import org.teamtree.objectaid.MVC.Model.Model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class ProjectCompilationProcessService {
    private final Model model;

    public ProjectCompilationProcessService(Model model) {
        this.model = model;
    }

    public void compileProject(final File file) {
        if (file.isDirectory()) {
            Arrays.stream(Objects.requireNonNull(file.listFiles())).forEach(this::compileProject);
            try {
                final var temporaryOutputDirectory = Files.createTempDirectory("objectaid");

                System.out.println("Temporary output directory: " + temporaryOutputDirectory);

                final var tempFile = temporaryOutputDirectory.toFile();

                compile(Path.of(file.getAbsolutePath()), temporaryOutputDirectory);

                System.out.println(Arrays.stream(Objects.requireNonNull(tempFile.listFiles())).count() + " classes compiled");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void compile(Path source, Path target) {
        try (Stream<Path> files = Files.list(source)) {
            Stream<String> javaFiles = files.filter(Files::isRegularFile).filter(p -> p.getFileName().toString().endsWith(".java")).map(Path::toAbsolutePath).map(Path::toString);
            List<String> args = Stream.concat(Stream.of("javac", "-d", target.toAbsolutePath().toString()), javaFiles).toList();
            Process process = new ProcessBuilder(args).inheritIO().start();
            process.getInputStream().transferTo(System.out);
            process.waitFor();
        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
