package gl.javafx.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CompositeValidator<T> implements Validator<T> {
    private final List<Validator<T>> validatorList = new ArrayList<>();
    
    @Override
    public boolean validate(T value) {
        return validatorList.stream().allMatch(validator -> validator.validate(value));
    }
    
    public void add(Validator<T> validator) {
        this.validatorList.add(Objects.requireNonNull(validator));
    }
}
