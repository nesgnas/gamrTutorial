package main;

import Tiles.TileManage;
import entity.Player;

import javax.swing.*;
import java.awt.*;

// EHEHEHEHE - check branch nesgnas
public class gamePanel extends JPanel implements Runnable{ // call in Main.class

    // MAKE ARRAY FROM COLLISION CHECKER CAN BE ACCESS
    private int arr[]; // hole all value of tile
    private int countOfArr; //hold count all value of tile

    public int getCountOfArr() {
        return countOfArr;
    }

    public void setCountOfArr(int countOfArr) {
        this.countOfArr = countOfArr;
    }

    public int[] getArr() {
        return arr;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    private final int originalSizeTile = 16;    // size of subject 16x16
    private final int scale = 3; // rate to scale size of subject
    private final int titleSize = originalSizeTile * scale;

    // SETTING THE SCREEN
    private final int maxScreenCol = 20;
    private final int maxScreenRow = 20;
    private final int screenHeight = titleSize * maxScreenRow;
    private final int screenWidth = titleSize * maxScreenCol;

    // SETTING SIZE OF MAP
    public final int maxWorldCol =100;
    public final int maxWorldRow =100;
    public final int worldWidth = titleSize * maxScreenCol;
    public final int worldHeight = titleSize * maxScreenRow;

    public int getMaxWorldCol() {
        return maxWorldCol;
    }

    public int getMaxWorldRow() {
        return maxWorldRow;
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }

    public int getOriginalSizeTile() {
        return originalSizeTile;
    }

    public int getScale() {
        return scale;
    }

    public int getTitleSize() {
        return titleSize;
    }

    public int getMaxScreenCol() {
        return maxScreenCol;
    }

    public int getMaxScreenRow() {
        return maxScreenRow;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    // place to call and link all class

    TileManage tileManage = new TileManage(this);
    keyHandle keyHandle = new keyHandle(); // call keyHandle.class

    public Player player = new Player(this,keyHandle);// from Player.class

    public CollisionChecker checker = new CollisionChecker(this); // from CollisionChecker.class
    public Thread gameThread;

    // simple player in4 -> put into pencil to draw into panel
    int playerX = 100; //pos of player
    int playerY = 100;
    int speedPlayer =4;

    // set FPS;
    int fps = 60;





    gamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

        this.addKeyListener(keyHandle); // add keyHandle

        this.setFocusable(true); // focus to receive input

    }

    public void starGameThread(){ // the method to call a Thread - often use this to make multitask in sametime
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public  void run(){ // main code of thread
        double drawInterval= 1000000000/fps; //10^0 / fps
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime ;

        long timer = 0;
        int drawCount = 0;

        while(gameThread !=null){
            currentTime = System.nanoTime();
            delta +=(currentTime - lastTime) / drawInterval;

            //timer +=(currentTime - lastTime);

            lastTime =currentTime;

            if (delta>=1){
                update();
                repaint();
                delta --;
                //drawCount++;
            }

        }
    }

    public void update(){ // must add some fps to make eye can see
        player.update();
    }

    public void paintComponent(Graphics g){ // draw some object into screen (like a pen)
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tileManage.draw(g2);
        player.draw(g2);
        g2.dispose();

    }
}
