package pl.gajoch.layview.gui;

import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Collection;

abstract public class MovableJPanel extends JPanel {
    private SimpleObjectProperty<Rectangle> position;
    private final MoveWindowEditor moveWindowEditor;
    private JPopupMenu contextMenu;

    protected MovableJPanel(int width, int height) {
        super();
        this.setLayout(null);
        moveWindowEditor = new MoveWindowEditor();

        position = new SimpleObjectProperty<>();
        position.setValue(new Rectangle(0, 0, width, height));
        this.setBounds(position.getValue());
    }

    protected void openContextMenu(MouseEvent me) {
        contextMenu.show(this, me.getX(), me.getY());
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
        item2.addActionListener(e1 -> GraphicsWindowManager.del(this));
        contextMenu.add(item2);

        this.setComponentPopupMenu(contextMenu);
    }

    public void setPosition(Rectangle position) {
        this.setBounds(position);
        this.repaint();
        fixCenter(position.getWidth(), position.getHeight());
    }

    public void fixCenter(double width, double height) {

    }

    public Rectangle getPosition() {
        return new Rectangle(this.getX(),
                this.getY(),
                this.getWidth(),
                this.getHeight());
    }

    public final SimpleObjectProperty<Rectangle> getPositionProperty() {
        return this.position;
    }
}
