package main;

import GUI.PlumberGUI;
import GUI.SaboteurGUI;
import Tile.TileManager;
import java.util.Timer;
import java.util.TimerTask;


import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 20;
    final int scale = 3;

    CountdownTimer timer;

    //setting a single tile size by scaling it
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;

    public int gameState = 0;

    //setting the width and height of the screen and tilling it
    public final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    //FPS
    int FPS = 60;
    public TileManager tileManager = new TileManager(this);
    Thread gameThread; //running the game on this thread
    KeyHandler keyHandler = new KeyHandler();
    public PlumberGUI plumber = new PlumberGUI(this, keyHandler);
    public SaboteurGUI saboteur = new SaboteurGUI(this, keyHandler);
    public CollisionChecker collisionChecker = new CollisionChecker(this);

    public GamePanel(CountdownTimer timer) {
        this.timer = timer;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start(); //starting the game thread
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS; //0.0666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {

            Update();
            repaint();
            try {
                double remainingtime = nextDrawTime - System.nanoTime();
                remainingtime = remainingtime / 1000000;
                if (remainingtime < 0) {
                    remainingtime = 0;
                }
                Thread.sleep((long) remainingtime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void Update() {
        if(timer.seconds <= 0)
        {
            timer.seconds = 0;
            gameState = 1;
        }
        if(gameState == 0)
        {
            plumber.update();
            saboteur.update();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileManager.draw(g2);
        plumber.draw(g2);
        saboteur.draw(g2);
        Graphics2D gg = (Graphics2D) g;
        gg.setFont(new Font("Impact", Font.PLAIN, 30));
        String text = "Time remaining: " + timer.seconds;
        int x = tileSize * 6;
        int y = tileSize;
        gg.setColor(Color.black);
        gg.drawString(text, x, y - 20);

        if(gameState == 1)
        {
            String text2 = "Game over";
            int x2 = tileSize * 6;
            int y2 = tileSize * 2;
            gg.setColor(Color.black);
            gg.drawString(text2, x2, y2 - 20);
        }
        g2.dispose();
    }
}
