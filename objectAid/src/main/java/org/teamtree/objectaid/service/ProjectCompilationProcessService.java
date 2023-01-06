package org.teamtree.objectaid.service;

import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.util.FileExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
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

    /**
     * void compile(Path sourceDirectory, Path targetDirectory) {
     * <p>
     * // check if sourceDirectory is valid
     * if (!sourceDirectory.toFile().isDirectory()) {
     * throw new IllegalArgumentException("sourceDirectory is not a directory");
     * }
     * <p>
     * // check if targetDirectory is valid
     * if (!targetDirectory.toFile().isDirectory()) {
     * throw new IllegalArgumentException("targetDirectory is not a directory");
     * }
     * <p>
     * try {
     * final var processes = ProcessBuilder.startPipeline(List.of(
     * new ProcessBuilder("find", sourceDirectory.toString(), "-name", "*.java")
     * .inheritIO().redirectOutput(ProcessBuilder.Redirect.PIPE),
     * new ProcessBuilder("xargs", "javac", "-d", targetDirectory.toString())
     * .redirectError(ProcessBuilder.Redirect.INHERIT)
     * ));
     * <p>
     * System.out.println("Number of files compilated : " + targetDirectory.toFile().listFiles().length);
     * } catch (IOException e) {
     * throw new RuntimeException(e);
     * }
     * <p>
     * }
     **/

    void compile(Path source, Path target) {

        // Search for settings.gradle in the source directory
        final var isGradleProject = Files.exists(source.resolve("settings.gradle"));


        if (isGradleProject) {
            try {
                final var processes = ProcessBuilder.startPipeline(List.of(
                        new ProcessBuilder("gradle", "build", "-p", source.toString())
                                .inheritIO().redirectOutput(ProcessBuilder.Redirect.PIPE),
                        new ProcessBuilder("unzip", "-o", source.resolve("build").resolve("libs").resolve("objectaid.jar").toString(), "-d", target.toString())
                                .redirectError(ProcessBuilder.Redirect.INHERIT)
                ));

                System.out.println("(GRADLE) Number of files compilated : " + target.toFile().listFiles().length);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                final var processes = ProcessBuilder.startPipeline(List.of(
                        new ProcessBuilder("find", source.toString(), "-name", "*.java")
                                .inheritIO().redirectOutput(ProcessBuilder.Redirect.PIPE),
                        new ProcessBuilder("xargs", "javac", "-d", target.toString())
                                .redirectError(ProcessBuilder.Redirect.INHERIT)
                ));

                System.out.println("Number of files compilated : " + target.toFile().listFiles().length);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
