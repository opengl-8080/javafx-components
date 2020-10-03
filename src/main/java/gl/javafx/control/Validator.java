package gl.javafx.control;

import java.util.regex.Pattern;

public interface Validator<T> {
    
    boolean validate(T value);
    
    static <T> Validator<T> permitAll() {
        return value -> true;
    }
    
    static Validator<String> patternOf(Pattern pattern) {
        return value -> pattern.matcher(value).matches();
    }
}
