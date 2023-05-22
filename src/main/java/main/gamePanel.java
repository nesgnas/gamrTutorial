package main;

import Tiles.TileManage;
import entity.Player;
import object.Box;
import object.superObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Tiles.TileManage.*;
import static object.Box.boxes;
import static object.Box.boxesCopy;

public class gamePanel extends JPanel implements Runnable, MouseListener, MouseMotionListener { // call in Main.class

    // MAKE ARRAY FROM COLLISION CHECKER CAN BE ACCESS
    private int arr[]; // hole all value of tile
    private int countOfArr; // hold count all value of tile
    public static boolean door_press = false;

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

    private final int originalSizeTile = 16; // size of subject 16x16
    private final int scale = 3; // rate to scale size of subject
    private final int titleSize = originalSizeTile * scale;

    // SETTING THE SCREEN
    private final int maxScreenCol = 20;
    private final int maxScreenRow = 20;
    private final int screenHeight = titleSize * maxScreenRow;
    private final int screenWidth = titleSize * maxScreenCol;

    // SETTING SIZE OF MAP
    public final int maxWorldCol = 100;
    public final int maxWorldRow = 100;
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

    public BufferedImage bg1;

    // Game State
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    // public final int pauseState = 2;
    public final int dialogue = 3;
    public final int optionsState = 2;
    public static boolean resetRoom = false;
    public static boolean soundEffect = true;
    public static boolean soundMusic = true;
    public static String state = "";
    public String guide = "guide";
    public String credit = "credit";
    public String menu = "menu";

    // place to call and link all class

    TileManage tileManage = new TileManage(this);
    public keyHandle keyHandle = new keyHandle(this); // call keyHandle.class
    public mouseHandle mouseHandle = new mouseHandle(this);
    MouseEvent e;
    public UI1 ui1 = new UI1(this);

    Sound music = new Sound();
    Sound soundfe = new Sound();
    public Player player = new Player(this, keyHandle);// from Player.class

    public CollisionChecker checker = new CollisionChecker(this); // from CollisionChecker.class
    public Thread gameThread;

    alterSetter alterSetter = new alterSetter(this);
    superObject object[] = new superObject[1000];

    public void getImage() { // read Image
        try {
            bg1 = ImageIO.read(new File("data/ui/Menu.png"));
            // reset = ImageIO.read(new File("data/ui/Reset.png"));
            // cont = ImageIO.read(new File("data/ui/Continous.png"));
            // sound_music_on = ImageIO.read(new File("data/ui/Sound_Music_on.png"));
            // sound_music_off = ImageIO.read(new File("data/ui/Sound_Music_off.png"));
            // sound_effect_on = ImageIO.read(new File("data/ui/Sound_Effect_on.png"));
            // sound_effect_off = ImageIO.read(new File("data/ui/Sound_Effect_off.png"));
            // pause = ImageIO.read(new File("data/ui/pause.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUpGame() {
        alterSetter.setObj();
        gameState = titleState;
        if (soundMusic)
            playMusic(0);
        else if (!soundMusic)
            stopMusic();
    }

    // simple player in4 -> put into pencil to draw into panel
    int playerX = 100; // pos of player
    int playerY = 100;
    int speedPlayer = 4;

    // set FPS;
    int fps = 60;

    gamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

        this.addKeyListener(keyHandle); // add keyHandle

        this.setFocusable(true); // focus to receive input

    }

    public void starGameThread() { // the method to call a Thread - often use this to make multitask in sametime
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() { // main code of thread
        double drawInterval = 1000000000 / fps; // 10^0 / fps
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;

            // timer +=(currentTime - lastTime);

            lastTime = currentTime;

            if (delta >= 1) {
                update();
                //
                repaint();
                delta--;
                // drawCount++;
            }

        }
    }

    public void update() { // must add some fps to make eye can see
        if (gameState == playState) {
            player.update();
        }
        if (gameState == optionsState) {
            ui1.drawOptionsScreen();
            // Setting.mousePress(e,);
        }

        if (gameState == dialogue) {
            if (main.keyHandle.enterpressed) {
                player.speak();
                player.setDialogue();
            }
        }
        main.keyHandle.enterpressed = false;
    }

    public void checkRoomPlayerIn() {
        int valueX;
        int valueY;
        valueX = player.getX() / getTitleSize();
        valueY = player.getY() / getTitleSize();
        for (int i = 0; i <= countDownPos; i++) {
            // System.out.println("posX ="+box.getPosX()+" posY ="+box.getPosY());
            // System.out.println("LimitUpX ="+findRoomUp[i][1]+" LimitDownX
            // ="+findRoomDown[i][1]);
            // System.out.println("LimitUpY ="+findRoomUp[i][2]+" LimitDownY
            // ="+findRoomDown[i][2]);
            if ((valueX >= findRoomUp[i][1] + 2 && valueX <= findRoomDown[i][1] - 2)
                    && (valueY >= findRoomUp[i][2] + 2 && valueY <= findRoomDown[i][2] - 2)) {
                player.setRoomPlayerIn(i);
                break;

            }
        }
        // System.out.println("IN ROOM "+player.getRoomPlayerIn());
    }

    boolean boxSoundTriggered[] = new boolean[Box.boxes.size()]; // a boolean array (flags) for each box,
    // a box get in position will trigger the sound and its flag turns `true`, else,
    // if the box got pushed out, its flag turns `false`

    public void paintComponent(Graphics g) { // draw some object into screen (like a pen)
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        // if (box.getRoom() == Player.getRoomPlayerIn()) {
        // box.setPosX(boxes.get(num).getPosX());
        // box.setPosY(boxes.get(num).getPosY());
        // System.out.println(box.getPosX());
        // System.out.println(box.getPosY());
        // }
        // TITLE SCREEN
        if (gameState == titleState) {
            ui1.draw(g2);
        } else {
            try {
                int num = -1;

                for (Box box : boxesCopy) {
                    num++;
                    for (int i = 0; i < row1; i++) {
                        if (bom[0][i] == box.getPosX() / getTitleSize() && bom[1][i] == box.getPosY() / getTitleSize()
                                && bom[2][i] == 1 && bom[3][i] == Player.getRoomPlayerIn()) {
                            box.setImage(
                                    ImageIO.read(new File("data/tiles/tile3rd/149.png")));
                            in[num] = 1;
                        }
                    }
                    if (in[num] == 1 && !boxSoundTriggered[num]) // if the state of the box turn 1, which mean it
                                                                 // overlap a bom position, break
                        break; // this break means we get the `num` = id of the box to trigger the SE
                }
                if (!boxSoundTriggered[num] && in[num] == 1) { // if a box hasn't triggered the sound
                    playSE(1); // play SE
                    boxSoundTriggered[num] = true; // turn the flag true, the next time we check this, it will omit the
                                                   // box already triggered SE
                }

                num = -1;
                for (Box box : boxesCopy) {
                    num++;
                    if (in[num] == 1) {
                        boolean check = false;
                        for (int i = 0; i < row1; i++) {
                            if (bom[0][i] == box.getPosX() / getTitleSize()
                                    && bom[1][i] == box.getPosY() / getTitleSize()
                                    && bom[2][i] == 1 && bom[3][i] == Player.getRoomPlayerIn()) {
                                check = true;
                                break;
                            }
                        }
                        if (!check) {
                            box.setImage(
                                    ImageIO.read(new File("data/tiles/tile3rd/124.png")));
                            in[num] = 0;
                            boxSoundTriggered[num] = false; // if a box get push off the position, set the SE flag false
                                                            // again,
                            // in order to trigger the SE next time we push it in place
                        }
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // tile
            tileManage.draw(g2);

            // obj
            // object[0].draw(g2,this);
            checkRoomPlayerIn();

            // // Call Object to draw
            // System.out.println("InBoxUse");
            if (resetRoom) {
                System.out.println("ResetRoom is true");
                int num = -1;
                for (Box box : boxesCopy) {
                    num++;
                    int num1 = -1;
                    for (Box box1 : boxes) {
                        num1++;
                        if (num1 == num && box.getRoom() == Player.getRoomPlayerIn()) {
                            box.setPosX(box1.getPosX() * getTitleSize());
                            box.setPosY(box1.getPosY() * getTitleSize());
                            // System.out.println(box.getPosX()+ " - " +box.getPosY());
                            box.draw(g2, this);
                            break;
                        }
                    }
                }
                resetRoom = false;
            } else {
                for (Box box : boxesCopy) {
                    if (box.getRoom() == Player.getRoomPlayerIn()) {
                        box.draw(g2, this);
                    }
                }
            }
            // player
            player.draw(g2);

            // UI
            ui1.draw(g2);

            g2.dispose();

        }
        // try {
        // int num = -1;

        // for (Box box : boxesCopy) {
        // num++;
        // for (int i = 0; i < row1; i++) {
        // if (bom[0][i] == box.getPosX() / getTitleSize() && bom[1][i] == box.getPosY()
        // / getTitleSize() && bom[2][i] == 1 && bom[3][i] == Player.getRoomPlayerIn())
        // {
        // box.setImage(
        // ImageIO.read(new File("data/tiles/tile3rd/149.png"))
        // );
        // in[num] = 1;
        // }
        // }
        // if(in[num] == 1 && !boxSoundTriggered[num]) // if the state of the box turn
        // 1, which mean it overlap a bom position, break
        // break; // this break means we get the `num` = id of the box to trigger the SE
        // }
        // if(!boxSoundTriggered[num] && in[num]==1){ // if a box hasn't triggered the
        // sound
        // playSE(1); // play SE
        // boxSoundTriggered[num]=true; // turn the flag true, the next time we check
        // this, it will omit the box already triggered SE
        // }

        // num = -1;
        // for (Box box : boxesCopy){
        // num ++;
        // if (in[num]==1){
        // boolean check = false;
        // for (int i = 0; i < row1; i++) {
        // if (bom[0][i] == box.getPosX() / getTitleSize() && bom[1][i] == box.getPosY()
        // / getTitleSize()
        // && bom[2][i] == 1 && bom[3][i] == Player.getRoomPlayerIn()) {
        // check = true;
        // break;
        // }
        // }
        // if (!check){
        // box.setImage(
        // ImageIO.read(new File("data/tiles/tile3rd/124.png"))
        // );
        // in[num] = 0;
        // boxSoundTriggered[num]=false; // if a box get push off the position, set the
        // SE flag false again,
        // //in order to trigger the SE next time we push it in place
        // }
        // }
        // }

        // } catch (IOException e) {
        // throw new RuntimeException(e);
        // }

        // //tile
        // tileManage.draw(g2);

        // //obj
        // // object[0].draw(g2,this);
        // checkRoomPlayerIn();

        // // // Call Object to draw
        // // System.out.println("InBoxUse");
        // if (resetRoom){
        // System.out.println("ResetRoom is true");
        // int num =-1;
        // for (Box box : boxesCopy){
        // num++;
        // int num1 = -1;
        // for (Box box1 : boxes) {
        // num1++;
        // if (num1 == num && box.getRoom() == Player.getRoomPlayerIn()) {
        // box.setPosX(box1.getPosX()*getTitleSize());
        // box.setPosY(box1.getPosY()*getTitleSize());
        // //System.out.println(box.getPosX()+ " - " +box.getPosY());
        // box.draw(g2,this);
        // break;
        // }
        // }
        // }
        // resetRoom = false;
        // }
        // else {
        // for (Box box : boxesCopy) {
        // if (box.getRoom() == Player.getRoomPlayerIn()) {
        // box.draw(g2, this);
        // }
        // }
        // }
        // //player
        // player.draw(g2);

        // //UI
        // ui1.draw(g2);

        // g2.dispose();

    }

    //
    public void playMusic(int i) {
        music.setFile(i); // call setFile from sound class
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {
        if (soundEffect) {
            soundfe.setFile(i);
            soundfe.play();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (gameState == titleState) {
            int mx = e.getX();
            int my = e.getY();
            if (mx >= (worldWidth / 2) - 200 && mx <= ((worldWidth / 2) - 200) + getTitleSize() * 9
                    && my >= (worldHeight / 2) - 250 && my <= (worldHeight / 2) - 250 + (getTitleSize() * 2)) {
                gameState = playState;
            }
        } else {
            int mx = e.getX();
            int my = e.getY();
            if (mx >= 80 && mx <= 80 + 50 && my >= 35 && my <= 35 + 50) {
                if (gameState == playState)
                    gameState = optionsState;
                // else
            }

            if (mx >= 342 && mx <= 342 + 50 && my >= 312 && my <= 312 + 50) { // Reset room
                resetRoom = true;
                System.out.println("Reset room");
            } else if (mx >= 577 && mx <= 577 + 50 && my >= 312 && my <= 312 + 50) { // Menu
                System.out.println("Menu");
            } else if (mx >= 443 && mx <= 443 + 50 && my >= 408 && my <= 408 + 50) { // Continous
                if (gameState == optionsState)
                    gameState = playState;
                System.out.println("Continue");
            } else if (mx >= 342 && mx <= 342 + 50 && my >= 504 && my <= 504 + 50) { // Music
                if (soundMusic)
                    soundMusic = false;
                else if (!soundMusic)
                    soundMusic = true;
                System.out.println("Sound Music");
            } else if (mx >= 572 && mx <= 572 + 50 && my >= 504 && my <= 504 + 50) { // Effect
                if (soundEffect)
                    soundEffect = false;
                else if (!soundEffect)
                    soundEffect = true;
                System.out.println("Sound Effect");
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
