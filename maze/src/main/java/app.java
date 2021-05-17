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
    public int Size = 15;
    /**
     * cellánk side hosszusaga
     */
    public static final int Range = 30;
    /**
     * a mapunk igazi mérete
     */
    public int VSize = (Size * 2 + 1) * Range;
    /**
     * térképünk
     */
    public int maze[][] = new int[VSize][VSize];
    /**
     * a már látogatott utvonal
     */
    public int vis[][] = new int[VSize][VSize];
    /**
     * uj nodot csinálunk
     */
    public node f[][] = new node[VSize][VSize];
    /**
     * mozgatási directionünk
     */
    public int[][] dir = { { -Range, 0 }, { Range, 0 }, { 0, -Range }, { 0, Range } };
    /**
     * meghivjuk a createmap osztályt
     */
    public CreateMap c = new CreateMap(Size, Size);
    /**
     * létrehozunk egy téglalapot a rangek nagyságával
     */
    Rectangle rec = new Rectangle(Range, Range, Range, Range);
    private int recX = 30, recY = 30;
    private boolean autoPath = false;

    /**
     * Csekkolja hogy bent vagyunk e
     * @param fx x pozitcio
     * @param fy y pozitcio
     * @return true-t vagy false-t ad ha bent vagyunk
     */
    boolean inside(int fx, int fy) {
        return (fx >= Range && fx <= VSize - Range && fy >= Range && fy <= VSize - Range); //range 30 vsize 31*30
    }
}

