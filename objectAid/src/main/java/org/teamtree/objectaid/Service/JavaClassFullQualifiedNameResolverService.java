package org.teamtree.objectaid.Service;

import java.io.File;
import java.nio.file.Path;

/**
 * Service s'occupant de récupérer le fqn (full qualified name) à partir d'un fichier.
 * eg: org.teamtree.objectaid.Service.JavaClassFullQualifiedNameResolverService
 */
public class JavaClassFullQualifiedNameResolverService {

    public static final char SEPARATOR = '.';
    public static final String SEPARATOR_STRING = String.valueOf(SEPARATOR);
    private final Path rootPath;

    public JavaClassFullQualifiedNameResolverService(final Path rootPath) {
        this.rootPath = rootPath;
    }

    protected String getClassFQN(final File directory, final File file, final String className) {
        var packageName = directory.getAbsolutePath()
            .substring(rootPath.toAbsolutePath().toString().length())
            .replace(File.separator, SEPARATOR_STRING);
        if (packageName.startsWith(SEPARATOR_STRING)) {
            packageName = packageName.substring(1);
        }
        return packageName + SEPARATOR_STRING + className;
    }
}
