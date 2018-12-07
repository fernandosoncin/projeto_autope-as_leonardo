package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.mensagens;

/**
 *
 * @author felli
 */
public class appMain extends Application {

    private double xOffset = 0;
    private double yOffset = 0;
    public static Stage palco;
    private static AnchorPane page;
    private static Scene cena;

    private Screen screen = Screen.getPrimary();
    private Rectangle2D windows = screen.getVisualBounds();

    appMain(String user) {

    }





    @Override
    public void start(Stage stage) throws Exception {
        palco = stage;
        page = FXMLLoader.load(getClass().getResource("/view/app.fxml"));
        cena = new Scene(page);

        stage.setScene(cena);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        palco.getIcons().addAll(new Image(login.class.getResourceAsStream("/view/imgs/imagemInicioLogo.png")));
        stage.setTitle("Sistema de Auto Peças");
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
