import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import javafx.scene.control.Button;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.AnchorPane;
/**
 * csomópontok
 */
class node {
    public int x, y;

    node() {
    }

    node(int a, int b) {
        x = a;
        y = b;
    }

    void set(int a, int b) {
        x = a;
        y = b;
    }
}
/**
 * A játékunk törzse
 */
public class app extends Application {

}
