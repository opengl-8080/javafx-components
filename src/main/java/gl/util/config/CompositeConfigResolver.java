package gl.util.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompositeConfigResolver implements ConfigResolver {
    private final List<ConfigResolver> resolvers;
    
    public CompositeConfigResolver() {
        this.resolvers = new ArrayList<>();
    }

    public CompositeConfigResolver(List<ConfigResolver> resolvers) {
        this.resolvers = new ArrayList<>(resolvers);
    }
    
    public void addResolver(ConfigResolver resolver) {
        resolvers.add(resolver);
    }

    @Override
    public Optional<String> resolveOptional(String key) {
        for (ConfigResolver resolver : resolvers) {
            final Optional<String> value = resolver.resolveOptional(key);
            if (value.isPresent()) {
                return value;
            }
        }
        return Optional.empty();
    }
}
