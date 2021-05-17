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
    /**
     * a mapunk merete amit majd a prim algoritmus felhasznal a map genarashoz
     */
    public int Size = 15;// Effective map size, used for Prim algorithm to generate map
    /**
     * cellánk side hosszusaga
     */
    public static final int Range = 30;// cell side length
    /**
     * a mapunk igazi mérete
     */
    public int VSize = (Size * 2 + 1) * Range;// Actual map size
    /**
     * térképünk
     */
    public int maze[][] = new int[VSize][VSize];// map
    /**
     * a már látogatott utvonal
     */
    public int vis[][] = new int[VSize][VSize];// Visited path
    /**
     * uj nodot csinálunk
     */
    public node f[][] = new node[VSize][VSize];
    /**
     * mozgatási directionünk
     */
    public int[][] dir = { { -Range, 0 }, { Range, 0 }, { 0, -Range }, { 0, Range } };// moving direction
    /**
     * meghivjuk a createmap osztályt
     */
    public CreateMap c = new CreateMap(Size, Size);
    /**
     * létrehozunk egy téglalapot a rangek nagyságával
     */
    Rectangle rec = new Rectangle(Range, Range, Range, Range);
    private int recX = 30, recY = 30;
    private boolean autoPath = false;// Whether to enable automatic solution
}

