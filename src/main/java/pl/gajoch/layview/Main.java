package pl.gajoch.layview;

import com.jogamp.opengl.util.FPSAnimator;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import pl.gajoch.layview.graphics3d.SimpleJOGL;
import pl.gajoch.layview.gui.Controller;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.awt.GLJPanel;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Main extends Application {
    GLCanvas glcanvas;
    GLJPanel  frame;
    JFrame jframe;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader load = new FXMLLoader();
        load.setLocation(getClass().getResource("gui/Main.fxml"));
        Parent loader = load.load();

        primaryStage.setTitle("pl.gajoch.layview.Main");
        Scene scene = new Scene(loader);
        primaryStage.setScene(scene);

        Controller windowController = load.getController();
        windowController.setup(primaryStage);

        primaryStage.show();

        // ------------

        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        glcanvas = new GLCanvas(capabilities);
        SimpleJOGL cube = new SimpleJOGL();
        glcanvas.addGLEventListener(cube);
        glcanvas.setSize(700, 700);

        frame = new GLJPanel();
//        jframe = new JFrame();

        JPanel jpanel = new JPanel();
//        jpanel.add(new JButton("AAA"));
        frame.add(glcanvas);
        frame.add(jpanel);
        frame.setSize(700, 700);
        frame.setVisible(true);

//        jframe.add(glcanvas);
//        jframe.setSize(700, 700);
//        jframe.setVisible(true);

        final SwingNode swingNode = new SwingNode();
        swingNode.setContent(frame);


        StackPane pane = new StackPane();
        pane.getChildren().add(swingNode);
//        Scene scene = new Scene(pane, 700, 700);
//        primaryStage.setScene(scene);
//
//        final FPSAnimator animator = new FPSAnimator(glcanvas, 300, true);
//        animator.start();
//
//        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

