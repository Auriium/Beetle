package me.aurium.beetle.file.database;

import me.aurium.beetle.datacore.LocalDBType;
import me.aurium.beetle.file.WrongEndingTypeException;

import java.nio.file.Path;
import java.util.Optional;

/**
 * Collection of default implementations of SourceableExtension
 */
public enum DBExtensions implements SourceableFileExtension {

    H2(".h2",LocalDBType.H2),
    DB(".db",LocalDBType.SQLite);

    private final String key;
    private final LocalDBType dbType;

    DBExtensions(String key, LocalDBType dbType) {
        this.dbType = dbType;
        this.key = key;
    }

    @Override
    public Path of(Path path) {
        return fromPath(path,key);
    }

    @Override
    public LocalDBType getType() {
        return dbType;
    }

    Optional<String> getStringEnding(String fileName) {
        return Optional.ofNullable(fileName)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(fileName.lastIndexOf(".")));
    }

    Path fromPath(Path name, String key) {
        String internal = name.toString();
        String endingString = getStringEnding(internal).orElse("NONEXISTENT");

        if (endingString.equals("NONEXISTENT")) {
            internal += endingString;
        } else if (!endingString.equals(key)) {
            throw new WrongEndingTypeException(endingString,key);
        }

        return Path.of(internal);
    }
}
