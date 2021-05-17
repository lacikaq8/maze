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
     * Alkalmazásunk startja
     * @param stage vár egy staget
     * @throws Exception Kiirjuk ha hiba van
     */
    public void start(Stage stage) throws Exception {
        CreateMap();
        Pane pane = Init();// Generate maze platform
        Scene scene = new Scene(pane, VSize, VSize);

        scene.setOnKeyPressed(k -> {
            KeyCode code = k.getCode();
            int tx = recX, ty = recY;
            if (code.equals(KeyCode.LEFT) && autoPath == false) { // Left button pressed
                tx -= Range;
            } else if (code.equals(KeyCode.RIGHT) && autoPath == false) {// Right clicked
                tx += Range;
            } else if (code.equals(KeyCode.UP) && autoPath == false) {// Pressed the up arrow key
                ty -= Range;
            } else if (code.equals(KeyCode.DOWN) && autoPath == false) {// Pressed down arrow key
                ty += Range;
            } else if (code.equals(KeyCode.SPACE)) {
                if (autoPath == false) {
                    autoPath = true;
                    node e = new node();
                    e.set(recX, recY);
                    autoMove(e);
                }
            }
            if (inside(tx, ty) && maze[tx][ty] == 1 && autoPath == false) {
                // System.out.println(recX+" "+recY+" "+tx + " " + ty);
                move(tx, ty);
                recX = tx;
                recY = ty;
            } else if (recX == VSize - Range * 2 && recY == VSize - Range * 2) {// Determine whether out of bounds and hit the wall
                Alert alert = new Alert(AlertType.INFORMATION);
                Button button = new Button("Accept");
                alert.titleProperty().set("information");
                alert.headerTextProperty().set("You WIN!!!!");
                alert.showAndWait();
                try {
                    start(stage);
                } catch (Exception e) {
                    // TODO automatically generated catch block
                    e.printStackTrace();
                }
                move(Range, Range);
                recX = Range;
                recY = Range;
            }
        });
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("maze");
        stage.show();

    }
    /**
     * Mozgással felelős metódus
     * @param tx x sik
     * @param ty y sik
     */
    public void move(int tx, int ty) {
        SequentialTransition link = new SequentialTransition();// Animation list
        link.setNode(rec);
        TranslateTransition tt = new TranslateTransition();

        tt.setFromX(recX - 30);
        tt.setToX(tx - 30);
        tt.setFromY(recY - 30);
        tt.setToY(ty - 30);

        // System.out.println(recX+" "+recY+" "+tx+" "+ty);
        link.getChildren().add(tt);
        link.play();
    }

    /**
     * Itt hivjuk meg a Initünket hogy lekeráljuk a mapunk
     */
    public void CreateMap() {
        c.Init();// Generate maze
        for (int i = 0; i < VSize; i += Range) {
            for (int j = 0; j < VSize; j += Range) {
                maze[i][j] = c.map[i / Range][j / Range];
            }
        } // Maze mapping
    }

    /**
     * tábla inicializálás
     * @return táblánkat
     */
    public Pane Init() {

        Pane pane = new Pane();
        for (int i = 0; i < VSize; i += Range) {
            for (int j = 0; j < VSize; j += Range) {
                Rectangle r = new Rectangle(i, j, Range, Range);
                if (maze[i][j] == 0) {
                    r.setFill(Color.BLACK);
                } else if (maze[i][j] == 1) {
                    r.setFill(Color.WHITE);
                }
                if (i == VSize - Range && j == VSize - Range * 2) {
                    r.setFill(Color.GREEN);
                }
                pane.getChildren().add(r);
            }
        }
        rec.setFill(Color.RED);
        pane.getChildren().add(rec);// Show target block
        return pane;
    }

    public void autoMove(node e) {
        SequentialTransition link = new SequentialTransition();// Animation list
        link.setNode(rec);
        Queue<node> queue = new ArrayBlockingQueue<node>(1000);
        int flag = 0;
        System.out.println(e.x + " " + e.y);
        queue.add(e);
        vis[e.x][e.y] = 1;// visited
        while (flag == 0) {// Breadth first traversal, find the shortest path
            node now = queue.remove();
            for (int i = 0; i < 4; i++) {
                int fx = now.x + dir[i][0];
                int fy = now.y + dir[i][1];
                if ((inside(fx, fy) && (vis[fx][fy] == 0) && maze[fx][fy] == 1)) {
                    vis[fx][fy] = 1;
                    f[fx][fy] = new node(now.x, now.y);
                    queue.add(new node(fx, fy));
                }
                if (fx == VSize - Range * 2 && fy == VSize - Range * 2) {// start backtracking when one way reaches the end

                    node ans[] = new node[1000];
                    int cnt = 0;
                    int t1, t2;
                    ans[cnt] = new node(fx, fy);
                    while (f[fx][fy].x != e.x || f[fx][fy].y != e.y) {// Trace back according to the coordinates of the previous point recorded by the point to get the shortest path to the point.
                        t1 = fx;
                        t2 = fy;
                        cnt++;
                        ans[cnt] = new node(f[fx][fy].x, f[fx][fy].y);
                        fx = f[t1][t2].x;
                        fy = f[t1][t2].y;
                    }

                    ans[++cnt] = new node(0, 0);

                    for (int l = cnt - 1; l > 0; l--) {
                        // move(ans[l].x, ans[l].y);
                        // System.out.println(recX + " " + recY + " " + ans[l].x + " " + ans[l].y);
                        // recX = ans[l].x;
                        // recY = ans[l].y;
                        TranslateTransition tt = new TranslateTransition();
                        tt.setFromX(ans[l].x - 30);
                        tt.setToX(ans[l - 1].x - 30);
                        tt.setFromY(ans[l].y - 30);
                        tt.setToY(ans[l - 1].y - 30);
                        link.getChildren().add(tt);
                    }
                    flag = 1;
                    break;

                }
            }
        }
        link.play();

    }

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

