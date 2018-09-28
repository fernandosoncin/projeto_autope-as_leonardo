package controller;

import static controller.appMain.palco;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Screen;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author felli
 */
public class login extends Application {

    private double xOffset = 0;
    private double yOffset = 0;
    public static Stage palco;

    @Override
    public void start(Stage stage) throws Exception {
        palco = stage;
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        palco.getIcons().addAll(new Image(login.class.getResourceAsStream("/view/imgs/imagemInicioLogo.png")));
        stage.setTitle("Sistema de Auto Peças - Login");
        stage.setScene(scene);
        stage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2); 
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2); 

    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
