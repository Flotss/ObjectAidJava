package org.teamtree.objectaid.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class CLoader extends ClassLoader{
    public Class<?> loadFromFile(final File file) {
        final byte[] bytes;
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return this.defineClass(null, bytes, 0, bytes.length);
    }

}
