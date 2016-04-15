package MineSweeper;

import com.sun.javafx.geom.Vec2d;

import java.util.ArrayList;
import java.util.Collections;

public class MineField {
    public String[][] mineField;
    public String bomb = "B";
    private int width;
    private int height;

    public MineField(int width, int height, int bombCount) {
        this.width = width;
        this.height = height;
        mineField = new String[width][height];

        ArrayList<Vec2d> fieldArray = new ArrayList<Vec2d>();

        for (int i = 0; i < width; i++) {
            for (int n = 0; n < height; n++) {
                mineField[i][n] = " ";
                fieldArray.add(new Vec2d(i, n));
            }
        }

        Collections.shuffle(fieldArray);

        for (int b = 0; b < bombCount; b++) {
            Vec2d pos = fieldArray.get(b);
            mineField[(int) pos.x][(int) pos.y] = bomb;
        }

        for (int i = 0; i < width; i++) {
            for (int n = 0; n < height; n++) {
                if (!mineField[i][n].equals(bomb)) {
                    mineField[i][n] = String.valueOf(bombCount(i, n));
                }
            }
        }
    }

    public int bombCount(int x, int y) {
        int count = 0;
        if (x - 1 >= 0)          if (mineField[x - 1][y].equals(bomb)) count += 1;
        if (x + 1 < width)      if (mineField[x + 1][y].equals(bomb)) count += 1;
        if (y - 1 >= 0)          if (mineField[x][y - 1].equals(bomb)) count += 1;
        if (y + 1 < height)     if (mineField[x][y + 1].equals(bomb)) count += 1;
        if (x - 1 >= 0 && y - 1 >= 0)             if (mineField[x - 1][y - 1].equals(bomb)) count += 1;
        if (x - 1 >= 0 && y + 1 < height)        if (mineField[x - 1][y + 1].equals(bomb)) count += 1;
        if (x + 1 < width && y - 1 >= 0)         if (mineField[x + 1][y - 1].equals(bomb)) count += 1;
        if (x + 1 < width && y + 1 < height)    if (mineField[x + 1][y + 1].equals(bomb)) count += 1;
        return count;
    }
}
