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

        int[] acc, noacc;
        int count = row * column;
        int accsize = 0;

        acc = new int[count];
        noacc = new int[count];

        int[] offR = { -1, 1, 0, 0 };
        int[] offC = { 0, 0, 1, -1 };

        int[] offS = { -1, 1, row, -row };
        for (int i = 0; i < count; i++) {
            acc[i] = 0;
            noacc[i] = 0;
        }

        Random rd = new Random();
        acc[0] = rd.nextInt(count);
        int pos = acc[0];
        noacc[pos] = 1;
        while (accsize < count) {
            int x = pos % row;
            int y = pos / row;
            int offpos = -1;
            int w = 0;
            while (++w < 5) {
                int point = rd.nextInt(4);
                int repos;
                int move_x, move_y;
                repos = pos + offS[point];
                move_x = x + offR[point];
                move_y = y + offC[point];
                if (move_y >= 0 && move_x >= 0 && move_x < row && move_y < column && repos >= 0 && repos < count
                        && noacc[repos] != 1) {
                    noacc[repos] = 1;
                    acc[++accsize] = repos;
                    pos = repos;
                    offpos = point;


                    map[2 * x + 1 + offR[point]][2 * y + 1 + offC[point]] = 1;
                    break;
                } else {
                    if (accsize == count - 1)
                        return;
                    continue;
                }
            }
            if (offpos < 0) {
                pos = acc[rd.nextInt(accsize + 1)];}
        }
    }

}

