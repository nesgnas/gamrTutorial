package main;

import Tiles.TileManage;
import entity.Player;
import object.Box;
import object.superObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static Tiles.TileManage.*;
import static object.Box.boxes;
import static object.Box.boxesCopy;

public class gamePanel extends JPanel implements Runnable{ // call in Main.class

    // MAKE ARRAY FROM COLLISION CHECKER CAN BE ACCESS
    private int arr[]; // hole all value of tile
    private int countOfArr; //hold count all value of tile
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

    public boolean fullScreenOn = false;

    //Game State
    public int optionsState = 5;
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogue = 3;


    // place to call and link all class

    TileManage tileManage = new TileManage(this);
    keyHandle keyHandle = new keyHandle(this); // call keyHandle.class

    public UI ui = new UI(this);

    Sound music = new Sound();
    Sound soundfe = new Sound();
    public Player player = new Player(this,keyHandle);// from Player.class

    public CollisionChecker checker = new CollisionChecker(this); // from CollisionChecker.class
    public Thread gameThread;



    alterSetter alterSetter = new alterSetter(this);
    superObject object[] = new superObject[1000];




    public void setUpGame(){
        alterSetter.setObj();
        gameState = playState;
        playMusic(0);
    }


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
        if (gameState == playState) {
            player.update();
        }
        if (gameState == pauseState) {
            //nothing
            //player.update();
        }
        if (gameState == dialogue){
            player.speak();
            player.setDialogue();
        }
    }

    public void checkRoomPlayerIn(){
        int valueX;
        int valueY;
        valueX= player.getX()/getTitleSize();
        valueY= player.getY()/getTitleSize();
        for (int i=0;i<=countDownPos;i++){
//               System.out.println("posX ="+box.getPosX()+" posY ="+box.getPosY());
//               System.out.println("LimitUpX ="+findRoomUp[i][1]+" LimitDownX ="+findRoomDown[i][1]);
//               System.out.println("LimitUpY ="+findRoomUp[i][2]+" LimitDownY ="+findRoomDown[i][2]);
            if ((valueX>=findRoomUp[i][1]+2 && valueX<=findRoomDown[i][1]-2)
                    && (valueY>=findRoomUp[i][2]+2 && valueY<=findRoomDown[i][2]-2)){
                player.setRoomPlayerIn(i);
                break;

            }
        }
        //System.out.println("IN ROOM "+player.getRoomPlayerIn());
    }

    boolean boxSoundTriggered[] = new boolean[Box.boxes.size()]; //a boolean array (flags) for each box, 
    // a box get in position will trigger the sound and its flag turns `true`, else, if the box got pushed out, its flag turns `false`
    public void paintComponent(Graphics g){ // draw some object into screen (like a pen)
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;      
        
        try {
            int num = -1;
            
            for (Box box : boxesCopy) {
                num++;
                for (int i = 0; i < row1; i++) {
                    if (bom[0][i] == box.getPosX() / getTitleSize() && bom[1][i] == box.getPosY() / getTitleSize() && bom[2][i] == 1 && bom[3][i] == Player.getRoomPlayerIn()) {
                        box.setImage(
                            ImageIO.read(new File("data/tiles/tile3rd/149.png"))
                        );                        
                        in[num] = 1;
                    }                    
                }
                if(in[num] == 1 && boxSoundTriggered[num]==false) // if the state of the box turn 1, which mean it overlap a bom position, break
                    break; // this break means we get the `num` = id of the box to trigger the SE                
            }
            if(boxSoundTriggered[num]==false && in[num]==1){ // if a box hasn't triggered the sound
                playSE(1); // play SE 
                boxSoundTriggered[num]=true; // turn the flag true, the next time we check this, it will omit the box already triggered SE
            } 
             
            num = -1;
            for (Box box : boxesCopy){
                num ++;
                if (in[num]==1){
                    boolean check = false;
                    for (int i = 0; i < row1; i++) {
                        if (bom[0][i] == box.getPosX() / getTitleSize() && bom[1][i] == box.getPosY() / getTitleSize()
                                && bom[2][i] == 1 && bom[3][i] == Player.getRoomPlayerIn())
                            check = true;
                    }
                    if (!check){
                        box.setImage(
                                ImageIO.read(new File("data/tiles/tile3rd/124.png"))        
                        );
                        in[num] = 0;  
                        boxSoundTriggered[num]=false;     // if a box get push off the position, set the SE flag false again, 
                        //in order to trigger the SE next time we push it in place             
                    }                   
                }                
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //tile
            tileManage.draw(g2);
           
          
        //obj
//        object[0].draw(g2,this);
        checkRoomPlayerIn();

        // Call Object to draw
        //System.out.println("InBoxUse");
        for (Box box : boxesCopy){
            if (box.getRoom()==Player.getRoomPlayerIn()){
                box.draw(g2,this);
            }
        }
        //player
        player.draw(g2);

        //UI
        ui.draw(g2);

        g2.dispose();

    }

    // 
    public void playMusic(int i) {

        music.setFile(i); //call setFile from sound class
        music.play();
        music.loop();
    }

    public void stopMusic(){

        music.stop();
    }

    public void playSE(int i){

        soundfe.setFile(i);
        soundfe.play();
        
    }
}
