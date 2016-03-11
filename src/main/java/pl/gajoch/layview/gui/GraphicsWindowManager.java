package pl.gajoch.layview.gui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import pl.gajoch.layview.graphics2d.JPanel2D;
import pl.gajoch.layview.graphics3d.*;
import pl.gajoch.layview.scheduler.Scheduler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class GraphicsWindowManager {
    static JPanel pane3D;
    volatile static JFrame frame;
    volatile static ArrayList<MovableJPanel> subScenes;
    static SimpleObjectProperty<MovableJPanel> actual_scene;

    public GraphicsWindowManager() {
        actual_scene = new SimpleObjectProperty<>();
        subScenes = new ArrayList<>();

        actual_scene.addListener((observable, oldValue, newValue) -> {
            try {
                recalculate();
            } catch (NumberFormatException ignored) {
            }
        });

        pane3D = new JPanel();
        java.awt.EventQueue.invokeLater(() -> {
            frame = new JFrame("Main");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setLayout(null);

            JMenu menu = new JMenu("Add...");
            JMenuBar menuBar = new JMenuBar();
            menuBar.add(menu);

            JMenuItem menuItem = new JMenuItem("2D");
            menuItem.addActionListener(e -> {
                this.add();
            });
            menu.add(menuItem);

            menuItem = new JMenuItem("3D");
            menuItem.addActionListener(e -> {
                this.add3D();
            });
            menu.add(menuItem);

            frame.setJMenuBar(menuBar);
            frame.setSize(100,100);
            frame.setVisible(true);
        });
        while(true){
            Scheduler.start();
        }
    }

    private static void recalculate() {
        System.out.println("Rec!");
        SwingUtilities.invokeLater(() -> {
            frame.getContentPane().removeAll();
            for (MovableJPanel scene : subScenes) {
                frame.add(scene);
            }
            frame.repaint();
        });
    }

    private static void recalculateWindowSize() {
        try {
            int maxWidth = (int) subScenes.stream()
                    .mapToDouble(sc -> sc.getPosition().getX() + sc.getPosition().getWidth())
                    .max().getAsDouble();

            int maxHeight = (int) subScenes.stream()
                    .mapToDouble(sc -> sc.getPosition().getY() + sc.getPosition().getHeight())
                    .max().getAsDouble();

            java.awt.EventQueue.invokeLater(() -> {
                Insets insets = frame.getInsets();
                frame.setSize(insets.left + insets.right + maxWidth, insets.top + insets.bottom + maxHeight + 24);
            });
            System.out.println("size: " + maxWidth + " x " + maxHeight);
        } catch (NoSuchElementException ignored) {
            java.awt.EventQueue.invokeLater(() -> {
                frame.setSize(100,100);
            });
        }
    }

    private static void addSizeRecalculations(MovableJPanel scene) {
        ChangeListener<? super Rectangle> handler = (observable1, oldValue1, newValue1) -> {
            recalculate();
            recalculateWindowSize();
        };

        scene.getPositionProperty().addListener(handler);
    }

    public static void add() {
        MovableJPanel view = new JPanel2D(600, 600);
        addSizeRecalculations(view);
        view.generateContextMenu(new ArrayList<>());
        subScenes.add(view);

        recalculate();
        recalculateWindowSize();
        System.out.print("2D++\r\n");
    }

    public static void add3D() {
        MovableJPanel scene = new JPanel3D(600, 600);
        addSizeRecalculations(scene);
        subScenes.add(scene);
        recalculate();
        recalculateWindowSize();
    }

    public static void del(MovableJPanel scene) {
        if (subScenes.contains(scene)) {
            subScenes.remove(scene);
        }
        recalculate();
        recalculateWindowSize();
    }
}
