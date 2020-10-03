package gl.javafx.control;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

import java.util.Objects;

public class ValidationTextField extends TextField {
    private final BooleanProperty valid = new SimpleBooleanProperty(true);
    private final CompositeValidator<String> validator = new CompositeValidator<>();
    private String errorStyle = "error";
    
    public ValidationTextField() {
        textProperty().addListener((e, oldValue, newValue) -> {
            validate(newValue);
        });
        
        valid.addListener((e, oldValue, valid) -> {
            final ObservableList<String> styleClass = getStyleClass();
            if (valid) {
                styleClass.remove(errorStyle);
            } else if (!styleClass.contains(errorStyle)) {
                styleClass.add(errorStyle);
            }
        });
    }
    
    private void validate(String value) {
        valid.setValue(validator.validate(value));
    }

    public void addValidator(Validator<String> validator) {
        this.validator.add(validator);
        validate(getText());
    }

    public ReadOnlyBooleanProperty validProperty() {
        return valid;
    }
    
    public boolean isValid() {
        return valid.getValue();
    }
    
    public void setErrorStyle(String errorStyle) {
        this.errorStyle = Objects.requireNonNull(errorStyle);
    }
}
