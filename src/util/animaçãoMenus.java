package util;

//import java.time.Duration;
import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 *
 * @author felli
 */
public class animaçãoMenus {
    private static FadeTransition fade;

    private animaçãoMenus() {
    }

    public static void fade(Node no) {
        fade = new FadeTransition(Duration.seconds(1), no);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.setCycleCount(1);
        fade.setAutoReverse(true);
        fade.play();
    }
    public static void fade(Node no, double inicio, double fim, int tempo) {
        fade = new FadeTransition(Duration.seconds(tempo), no);
        fade.setFromValue(inicio);
        fade.setToValue(fim);
        fade.setCycleCount(1);
        fade.setAutoReverse(true);
        fade.play();
    }

    public static void stopfade() {
        fade.stop();
    }
    
}
