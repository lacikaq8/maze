import java.util.Random;

/**
 * Ez a metódus hozza létre a labirintusunkat
 */
public class CreateMap {

    private int row;
    private int column;
    /**
     * array amibe a mapunk taroljuk
     */
    public int[][] map;
    private int r;
    private int c;
    /**
     * Megadjuk neki a oszlopokat és sorokat majd visszaad egy olyan méretú tömböt
     * @param r0 vector sorok szamat keri
     * @param c0 vector oszlopok szamat keri
     */
    CreateMap(int r0, int c0) {
        row = r0;
        column = c0;
        r = 2 * row + 1;
        c = 2 * column + 1;

        map = new int[r][c];
    }
    /**
     * Inicializaljuk a mazeunket builderünket
     */
    public void Init() {

        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                map[i][j] = 0;
        for (int i = 0; i < row; i++)
            for (int j = 0; j < column; j++)
                map[2 * i + 1][2 * j + 1] = 1;

        accLabPrime();
    }
    /**
     * Maze builder
     */
    public void accLabPrime() {

    }
}

