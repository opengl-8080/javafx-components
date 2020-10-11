package gl.javafx.control;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

public class Dialog {
    
    public static Dialog confirm(String message) {
        final Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message);
        alert.setHeaderText(null);
        final Boolean ok = alert.showAndWait().map(Dialog::isOk).orElse(false);
        return new Dialog(ok);
    }
    
    private static boolean isOk(ButtonType type) {
        final ButtonBar.ButtonData data = type.getButtonData();
        return ButtonBar.ButtonData.OK_DONE.equals(data)
                || ButtonBar.ButtonData.YES.equals(data);
    }
    
    private final boolean ok;

    private Dialog(boolean ok) {
        this.ok = ok;
    }

    public boolean isOk() {
        return ok;
    }

    public boolean isCancel() {
        return !isOk();
    }
}
