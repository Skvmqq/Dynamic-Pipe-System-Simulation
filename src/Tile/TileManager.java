package Tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    public BufferedImage gameBackground;
    public Tile[][] tile;
    int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[gp.maxScreenCol][gp.maxScreenRow];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getBackgroundImage();
        loadMap();
    }

    public void getBackgroundImage() {
        try {
            gameBackground = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/res/desert.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/main/res/map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();
                while (col < gp.maxScreenCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    switch (num) {
                        case 0:
                            tile[col][row] = new Tile("desert");
                            break;
                        case 1:
                            tile[col][row] = new Tile("pipe");
                            break;
                        case 2:
                            tile[col][row] = new Tile("pump");
                            break;
                        case 3:
                            tile[col][row] = new Tile("cistern");
                            break;
                        case 4:
                            tile[col][row] = new Tile("spring");
                            break;
                        case 5:
                            tile[col][row] = new Tile("boundary");
                    }
                    col++;
                }
                if (col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        g2.drawImage(gameBackground, 0, 0, gp.tileSize * gp.maxScreenCol, gp.tileSize * gp.maxScreenRow, null);

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            g2.drawImage(tile[col][row].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;
            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}
