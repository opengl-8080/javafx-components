package gl.javafx;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;

public class FxWindow {
    private final Parent parent;
    private String title;

    private Stage owner;
    private Modality modality;

    private boolean resizable = true;

    public static FxWindow newWindow(Fxml<?, ?> fxml) {
        return new FxWindow(fxml.getParent());
    }

    private FxWindow(Parent parent) {
        this.parent = Objects.requireNonNull(parent);
    }
    
    public FxWindow title(String title) {
        this.title = title;
        return this;
    }
    
    public FxWindow modality(Stage owner, Modality modality) {
        this.owner = Objects.requireNonNull(owner);
        this.modality = Objects.requireNonNull(modality);
        return this;
    }
    
    public FxWindow resizable(boolean resizable) {
        this.resizable = resizable;
        return this;
    }
    
    public void show(Stage stage) {
        initStage(stage);
        stage.show();
    }

    public void show() {
        show(new Stage());
    }

    public void showAndWait(Stage stage) {
        initStage(stage);
        stage.showAndWait();
    }

    public void showAndWait() {
        showAndWait(new Stage());
    }

    private void initStage(Stage stage) {
        if (title != null) {
            stage.setTitle(title);
        }
        if (owner != null) {
            stage.initOwner(owner);
        }
        if (modality != null) {
            stage.initModality(modality);
        }

        stage.setResizable(resizable);

        final Scene scene = new Scene(parent);
        stage.setScene(scene);
    }

}
