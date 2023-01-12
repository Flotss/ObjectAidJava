package org.teamtree.objectaid.util;

import java.io.File;

public class FileExtension {

    public static final String JAVA_EXTENSION = ".java";
    public static final String CLASS_EXTENSION = ".class";

    public static boolean isJavaFile(final String fileName) {
        return fileName.endsWith(JAVA_EXTENSION);
    }

    public static boolean isClassFile(final String fileName) {
        return fileName.endsWith(CLASS_EXTENSION);
    }

    public static boolean isJavaFile(final File file) {
        return isJavaFile(file.getName());
    }

    public static boolean isClassFile(final File file) {
        return isClassFile(file.getName());
    }
}
