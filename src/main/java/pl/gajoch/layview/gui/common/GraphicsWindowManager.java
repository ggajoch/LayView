package pl.gajoch.layview.gui.common;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import pl.gajoch.layview.graphics2d.JPanel2D;
import pl.gajoch.layview.graphics3d.JPanel3D;
import pl.gajoch.layview.gui.common.export.ExportMenu;
import pl.gajoch.layview.gui.common.movable.MovableJPanel;
import pl.gajoch.layview.options.ExportOptions;
import pl.gajoch.layview.scheduler.Scheduler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class GraphicsWindowManager {
    volatile static JFrame frame;
    volatile static ArrayList<MovableJPanel> subScenes;
    private static final ExportMenu exportMenu = new ExportMenu();

    SimpleObjectProperty<ExportOptions> exportOptions = new SimpleObjectProperty<>(new ExportOptions());

    public GraphicsWindowManager() {
        subScenes = new ArrayList<>();

        java.awt.EventQueue.invokeLater(() -> {
            frame = new JFrame("Main");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setLayout(null);
            frame.setResizable(false);

            createContextMenu();

            frame.pack();
            frame.setVisible(true);
        });

        while (true) {
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
            java.awt.EventQueue.invokeLater(() -> frame.pack());
        }
    }

    public static void add2D() {
        MovableJPanel view = new JPanel2D(600, 600);
        addPanel(view);
    }

    public static void add3D() {
        MovableJPanel scene = new JPanel3D(600, 600);
        addPanel(scene);
    }

    private static void addSizeRecalculations(MovableJPanel scene) {
        ChangeListener<? super Rectangle> handler = (observable1, oldValue1, newValue1) -> {
            recalculate();
            recalculateWindowSize();
        };

        scene.getPositionProperty().addListener(handler);
    }

    private static void addPanel(MovableJPanel view) {
        addSizeRecalculations(view);
        subScenes.add(view);
        recalculate();
        recalculateWindowSize();
    }

    public static void delPanel(MovableJPanel scene) {
        if (subScenes.contains(scene)) {
            subScenes.remove(scene);
        }
        recalculate();
        recalculateWindowSize();
    }

    private void openExportDialog() {
        exportMenu.exec(exportOptions);
    }

    private void createContextMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Add...");
        menuBar.add(menu);

        JMenuItem menuItem = new JMenuItem("2D");
        menuItem.addActionListener(e -> add2D());
        menu.add(menuItem);

        menuItem = new JMenuItem("3D");
        menuItem.addActionListener(e -> add3D());
        menu.add(menuItem);

        JMenuItem export = new JMenuItem("Export...");
        export.addActionListener(e -> openExportDialog());
        menuBar.add(export);

        frame.setJMenuBar(menuBar);
    }
}
