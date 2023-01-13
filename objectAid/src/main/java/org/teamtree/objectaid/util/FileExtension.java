package org.teamtree.objectaid.util;

import java.io.File;

/**
 * Classe utilitaire pour les fichiers dans un contexte de projet java.
 */
public class FileExtension {

    // Représente l'extension d'un fichier java.
    public static final String JAVA_EXTENSION = ".java";

    // Représente l'extension d'un fichier class.
    public static final String CLASS_EXTENSION = ".class";

    // Retourne si le fichier est un fichier java.
    public static boolean isJavaFile(final String fileName) {
        return fileName.endsWith(JAVA_EXTENSION);
    }

    // Retourne si le fichier est un fichier class.
    public static boolean isClassFile(final String fileName) {
        return fileName.endsWith(CLASS_EXTENSION);
    }

    // Retourne si le fichier est un fichier class.
    public static boolean isClassFile(final File file) {
        return isClassFile(file.getName());
    }
}
