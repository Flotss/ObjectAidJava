package org.teamtree.objectaid.service;

import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.util.FileExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    void compile(Path source, Path target) {

        // clear target dir
        target.toFile().deleteOnExit();

        // Search for settings.gradle in the source directory
        final var isGradleProject = Files.exists(source.resolve("settings.gradle"));


        if (isGradleProject) {

            try {
                // compile all java files, and put the compiled classes in the target directory with gradle
                final var processes = ProcessBuilder.startPipeline(List.of(
                        new ProcessBuilder("./gradlew", "compileJava")
                                .directory(source.toFile())
                                .inheritIO()
                                .redirectOutput(ProcessBuilder.Redirect.PIPE)
                ));

                System.out.println("(GRADLE) Number of files compilated : " + target.toFile().listFiles().length);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            final List<Path> classFiles;
            try {
                classFiles = Files.walk(source)
                        .filter(Files::isRegularFile)
                        .filter(file -> file.toString().endsWith(FileExtension.CLASS_EXTENSION))
                        .collect(Collectors.toList());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println("(GRADLE) Number of files found : " + classFiles.size());

            classFiles.forEach(file -> {
                try {
                    Files.move(file, target.resolve(file.getFileName()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            System.out.println("(GRADLE) Number of files moved : " + target.toFile().listFiles().length);

        }

        if (!isGradleProject) {
          try {
            final var javaFiles = Files.walk(source)
                    .filter(Files::isRegularFile)
                    .filter(p -> p.getFileName().toString().endsWith(FileExtension.JAVA_EXTENSION))
                    .map(Path::toAbsolutePath)
                    .map(Path::toString)
                    .toList();


            List<String> args = Stream.concat(Stream.of("javac", "-d", target.toString()), javaFiles.stream())
                    .collect(Collectors.toList());
            Process process = new ProcessBuilder(args)
                    .redirectErrorStream(true)
                    .inheritIO()
                    .start();
            process.waitFor();

            System.out.println("Compiled " + target.toFile().listFiles().length + "/" + javaFiles.size() + " files");

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        }



    }

}
