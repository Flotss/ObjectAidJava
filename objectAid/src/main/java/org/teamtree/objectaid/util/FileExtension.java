package org.teamtree.objectaid.util;

public class FileExtension {

    public static String JAVA_EXTENSION = ".java";
    public static String CLASS_EXTENSION = ".class";

    public static boolean isJavaFile(String fileName) {
        return fileName.endsWith(JAVA_EXTENSION);
    }

    public static boolean isClassFile(String fileName) {
        return fileName.endsWith(CLASS_EXTENSION);
    }
}
