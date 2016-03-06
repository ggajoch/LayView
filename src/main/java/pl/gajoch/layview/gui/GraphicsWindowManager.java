package pl.gajoch.layview.gui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.SubScene;
import javafx.stage.Stage;
import pl.gajoch.layview.graphics2d.JOGL2DScene;
import pl.gajoch.layview.graphics3d.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class GraphicsWindowManager {
    JPanel pane3D;
    volatile JFrame frame;
    volatile ArrayList<MovableSubScene> subScenes;
    SimpleObjectProperty<MovableSubScene> actual_scene;

    public void clicked(MovableSubScene scene) {
        if (actual_scene.getValue() != null && actual_scene.getValue() == scene) {
            actual_scene.setValue(null);
        } else {
            actual_scene.setValue(scene);
        }
    }

    GraphicsWindowManager(Stage stage, SubScene subScene) {
        actual_scene = new SimpleObjectProperty<>();
        subScenes = new ArrayList<>();

        actual_scene.addListener((observable, oldValue, newValue) -> {
            try {
                recalculate();
            } catch (NumberFormatException ignored) {
            }
        });

        subScene.setOnMouseClicked(event -> {
            if (event.isStillSincePress()) {
                actual_scene.setValue(null);
            }
        });

        pane3D = new JPanel();
        java.awt.EventQueue.invokeLater(() -> {
            frame = new JFrame("Main");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setLayout(null);
            frame.setVisible(false);
        });

    }

    Boolean vis = Boolean.TRUE;

    private void recalculate() {
        System.out.println("Rec!");
        SwingUtilities.invokeLater(() -> {
            frame.getContentPane().removeAll();
            for (MovableSubScene scene : subScenes) {
                System.out.println("Position: " + scene.position.get());
                frame.add(scene.scene);
            }
            frame.repaint();
        });
    }

    private void recalculateWindowSize() {
        try {
            int maxWidth = (int) subScenes.stream()
                    .mapToDouble(sc -> sc.getPosition().getX() + sc.getPosition().getWidth())
                    .max().getAsDouble();

            int maxHeight = (int) subScenes.stream()
                    .mapToDouble(sc -> sc.getPosition().getY() + sc.getPosition().getHeight())
                    .max().getAsDouble();

            java.awt.EventQueue.invokeLater(() -> {
                Insets insets = frame.getInsets();
                frame.setSize(insets.left + insets.right + maxWidth, insets.top + insets.bottom + maxHeight);
                if (!frame.isVisible())
                    frame.setVisible(true);
            });
            System.out.println("size: " + maxWidth + " x " + maxHeight);
        } catch (NoSuchElementException ignored) {
            java.awt.EventQueue.invokeLater(() -> {
                frame.setVisible(false);
            });
        }
//        stage.sizeToScene();
    }

    private void addSizeRecalculations(MovableSubScene scene) {
        ChangeListener<? super Rectangle> handler = (observable1, oldValue1, newValue1) -> {
            recalculate();
            recalculateWindowSize();
        };

        scene.position.addListener(handler);
    }

    public void add() {
        MovableSubScene view = new JOGL2DScene(this, 600, 600);
        addSizeRecalculations(view);
        view.generateContextMenu(new ArrayList<>());
        subScenes.add(view);

        recalculate();
        recalculateWindowSize();
        System.out.print("2D++\r\n");
    }

    public void add3D() {
        MovableSubScene scene = new JOGL3DScene(this, 600, 600);
        addSizeRecalculations(scene);
        subScenes.add(scene);
        recalculate();
        recalculateWindowSize();
    }

    public void del(MovableSubScene scene) {
        if (subScenes.contains(scene)) {
            subScenes.remove(scene);
        }
        recalculate();
        recalculateWindowSize();
    }
}
