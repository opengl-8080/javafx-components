package gl.util.config;

import java.util.Optional;
import java.util.function.Supplier;

public interface ConfigResolver {
    
    default String resolveRequired(String key) {
        return resolveOptional(key).orElseThrow(() -> new RuntimeException("key='" + key + "' の設定が解決できません。"));
    }
    
    Optional<String> resolveOptional(String key);

    default String resolveOptional(String key, String defaultValue) {
        return resolveOptional(key).orElse(defaultValue);
    }

    default String resolveOptional(String key, Supplier<String> defaultValueSupplier) {
        return resolveOptional(key).orElseGet(defaultValueSupplier);
    }
}
