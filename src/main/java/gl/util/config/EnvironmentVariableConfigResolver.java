package gl.util.config;

import java.util.Optional;

public class EnvironmentVariableConfigResolver implements ConfigResolver {
    @Override
    public Optional<String> resolveOptional(String key) {
        return Optional.ofNullable(System.getenv(toEnvironmentVariableKeyName(key)));
    }
    
    private String toEnvironmentVariableKeyName(String key) {
        return key.toUpperCase().replaceAll("\\.", "_");
    }
}
