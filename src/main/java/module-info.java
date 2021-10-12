module gl.javafx {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;

    exports gl.javafx;
    exports gl.javafx.control;

    exports gl.util;
    exports gl.util.annotation;
    exports gl.util.config;

    exports gl.file.csv;
}