package gl.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;

public class Fxml<C, P extends Parent> {
    private final C controller;
    private final P parent;

    private Fxml(C controller, P parent) {
        this.controller = controller;
        this.parent = parent;
    }

    public static Fxml<?, Parent> load(String fxmlResourcePath) {
        return load(Object.class, fxmlResourcePath);
    }
    
    public static <C> Fxml<C, Parent> load(Class<C> controllerClass, String fxmlResourcePath) {
        return load(controllerClass, Parent.class, fxmlResourcePath);
    }

    public static <C> Fxml<C, Parent> load(Class<C> controllerClass) {
        return load(controllerClass, Parent.class);
    }

    public static <C, P extends Parent> Fxml<C, P> load(Class<C> controllerClass, Class<P> parentClass) {
        if (!controllerClass.isAnnotationPresent(FxmlPath.class)) {
            throw new IllegalArgumentException(controllerClass.getCanonicalName() + " が @FxmlPath で注釈されていません");
        }
        final FxmlPath fxmlPath = controllerClass.getAnnotation(FxmlPath.class);
        return load(controllerClass, parentClass, fxmlPath.value());
    }

    public static <C, P extends Parent> Fxml<C, P> load(Class<C> controllerClass, Class<P> parentClass, String fxmlResourcePath) {
        try {
            final URL fxmlResource = controllerClass.getResource(fxmlResourcePath);
            final FXMLLoader loader = new FXMLLoader(fxmlResource);
            P parent = loader.load();
            return new Fxml<>(loader.getController(), parent);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public C getController() {
        return controller;
    }

    public P getParent() {
        return parent;
    }
}
