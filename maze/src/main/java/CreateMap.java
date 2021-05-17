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
}

