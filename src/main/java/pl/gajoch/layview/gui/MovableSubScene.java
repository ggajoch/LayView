package pl.gajoch.layview.gui;

import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;

public class MovableSubScene {
    public JPanel scene;
    public SimpleObjectProperty<Rectangle> position;
    private MoveWindowEditor moveWindowEditor;
    private GraphicsWindowManager graphicsWindowManager;
    private JPopupMenu contextMenu;

    public MovableSubScene(GraphicsWindowManager parent, int width, int height) {
        graphicsWindowManager = parent;
        //scene = new SubScene(p, width, height, true, SceneAntialiasing.BALANCED);
        scene = new JPanel();
        scene.setLayout(null);
        moveWindowEditor = new MoveWindowEditor();

        position = new SimpleObjectProperty<>();
        position.setValue(new Rectangle(0, 0, width, height));
        scene.setBounds(position.getValue());

//        scene.setOnMouseClicked(event -> {
//            if( event.getButton() == MouseButton.PRIMARY && event.isStillSincePress() ) {
//                parent.clicked(this);
//                event.consume();
//            }
//        });
    }

    protected void openContextMenu(MouseEvent me) {
        System.out.println("Mouse Left Pressed");
        System.out.println(me.getX());
        System.out.println(me.getY());
        contextMenu.show(scene, me.getX(), me.getY());
        me.consume();
    }
    protected void generateContextMenu(Collection<? extends JMenuItem> list) {
        contextMenu = new JPopupMenu("Menu");
        list.forEach(contextMenu::add);
        JMenuItem item = new JMenuItem("Move...");
        item.addActionListener(e1 -> {
            position.set(this.getPosition());

            SimpleObjectProperty<Rectangle> actual_position = new SimpleObjectProperty<>(position.get());
            actual_position.addListener((observable, oldValue, newValue) -> {
                SwingUtilities.invokeLater(() -> {
                    setPosition(newValue);
                    position.set(actual_position.get());
                });
            });
            Platform.runLater(() ->
                moveWindowEditor.exec(actual_position));
        });
        contextMenu.add(item);


        JMenuItem item2 = new JMenuItem("Delete");
        item2.addActionListener(e1 -> graphicsWindowManager.del(this));
        contextMenu.add(item2);

        scene.setComponentPopupMenu(contextMenu);

        System.out.println("context menu generated!");
    }

    public void setPosition(Rectangle position) {
        scene.setBounds(position);
        scene.repaint();
        fixCenter(position.getWidth(), position.getHeight());
    }

    public void fixCenter(double width, double height) {

    }

    public Rectangle getPosition() {
        return new Rectangle(scene.getX(),
                scene.getY(),
                scene.getWidth(),
                scene.getHeight());
    }


}
