package gl.util;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class Directory {
    private final Path path;
    
    public Directory(Path path, InitializeStrategy strategy) {
        if (Files.notExists(path)) {
            strategy.execute(path);
        } else if (!Files.isDirectory(path)) {
            throw new RuntimeException("指定されたパス(" + path.toAbsolutePath() + ")はディレクトリではありません。");
        }
        
        this.path = Objects.requireNonNull(path);
    }

    public Path path() {
        return path;
    }

    public enum InitializeStrategy {
        CREATE {
            @Override
            void execute(Path path) {
                try {
                    Files.createDirectories(path);
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            }
        },
        ERROR {
            @Override
            void execute(Path path) {
                throw new RuntimeException("指定されたディレクトリ(" + path.toAbsolutePath() + ")が存在しません。");
            }
        },
        ;
        
        abstract void execute(Path path);
    }
}
