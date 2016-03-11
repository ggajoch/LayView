package pl.gajoch.layview.gui;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class JFXPanelWindow<T> {
    protected T windowController;
    protected JFrame frame;
    protected final JFXPanel jfxPanel;

    public JFXPanelWindow(String label, String fxmlPath) {
        frame = new JFrame(label);
        frame.setLayout(new BorderLayout());
        jfxPanel = new JFXPanel();
        frame.add(jfxPanel, BorderLayout.CENTER);
        frame.setVisible(false);

        Platform.runLater(() ->
                windowController = initFX(jfxPanel, fxmlPath));
    }

    public T initFX(JFXPanel panel, String fxmlName) {
        try {
            FXMLLoader root = new FXMLLoader();
            root.setLocation(getClass().getResource(fxmlName));
            Parent loader = root.load();
            Scene scene = new Scene(loader);
            panel.setScene(scene);
            return root.getController();
        } catch (IOException exc) {
            exc.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    void open() {
        frame.pack();
        frame.setVisible(true);
    }
}
