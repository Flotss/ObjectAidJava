package org.teamtree.objectaid.service;

import org.teamtree.objectaid.util.FileExtension;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class ProjectCompilationProcessService {
    public void compileProject(final File file) {
        if (file.isDirectory()) {
            Arrays.stream(Objects.requireNonNull(file.listFiles())).forEach(this::compileProject);
        } else {
            if (FileExtension.isJavaFile(file.getName())) {
                try {
                    final var process = Runtime.getRuntime().exec("javac " + file.getAbsolutePath());
                    process.waitFor();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
