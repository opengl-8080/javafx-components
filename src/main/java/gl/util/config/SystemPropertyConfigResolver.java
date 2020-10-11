package gl.util.config;

import java.util.Optional;

public class SystemPropertyConfigResolver implements ConfigResolver {
    @Override
    public Optional<String> resolveOptional(String key) {
        return Optional.ofNullable(System.getProperty(key));
    }
}
