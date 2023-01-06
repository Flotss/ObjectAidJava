package org.teamtree.objectaid.service;

import org.teamtree.objectaid.MVC.Model.Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ProjectCompilationProcessService {
    private final Model model;

    public ProjectCompilationProcessService(Model model) {
        this.model = model;
    }

    public void compileProject(final Path path) {
        if (path.toFile().isDirectory()) {
            try {
                final var temporaryOutputDirectory = Files.createTempDirectory("objectaid");
                final var tempFile = temporaryOutputDirectory.toFile();

                compile(path, temporaryOutputDirectory);

                System.out.println(Arrays.stream(Objects.requireNonNull(tempFile.listFiles())).count() + " classes compiled");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    void compile(Path sourceDirectory, Path targetDirectory) {

        // check if sourceDirectory is valid
        if (!sourceDirectory.toFile().isDirectory()) {
            throw new IllegalArgumentException("sourceDirectory is not a directory");
        }

        // check if targetDirectory is valid
        if (!targetDirectory.toFile().isDirectory()) {
            throw new IllegalArgumentException("targetDirectory is not a directory");
        }

        try {
            final var processes = ProcessBuilder.startPipeline(List.of(
                    new ProcessBuilder("find", sourceDirectory.toString(), "-name", "*.java")
                            .inheritIO().redirectOutput(ProcessBuilder.Redirect.PIPE),
                    new ProcessBuilder("xargs", "javac", "-d", targetDirectory.toString())
                            .redirectError(ProcessBuilder.Redirect.INHERIT)
            ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
