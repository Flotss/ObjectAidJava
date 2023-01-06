package org.teamtree.objectaid.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class ProjectCompilationProcessService {
    public void compileProject(final File file) {
        if (file.isFile()) {
            throw new IllegalArgumentException("Le fichier passé en paramètre n'est pas un dossier");
        }

        final var processBuilder = new ProcessBuilder();
        processBuilder.directory(file.getParentFile());
        processBuilder.inheritIO();

        Arrays.stream(Objects.requireNonNull(file.listFiles())).forEach(f -> {
            if (f.isDirectory()) {
                compileProject(f);
            }

            if (f.getName().endsWith(".java")) {
                processBuilder.command().add(f.getAbsolutePath());
                try {
                    processBuilder.start();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
