package entity;

import main.gamePanel;
import main.keyHandle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity {

    gamePanel gp;
    keyHandle keyHandle;

    public final int screenX;
    public final int screenY;

    private static int roomPlayerIn;

    public static int getRoomPlayerIn() {
        return roomPlayerIn;
    }

    public static void setRoomPlayerIn(int roomPlayerIn) {
        Player.roomPlayerIn = roomPlayerIn;
    }

    public Player(gamePanel gp, keyHandle keyH) { //call in gamePanel.class
        this.gp = gp;
        this.keyHandle = keyH;

        screenX = gp.getScreenWidth()/2 - (gp.getTitleSize()/2);
        screenY = gp.getScreenHeight()/2 - (gp.getTitleSize()/2);

        // set area value of player
        solidArea = new Rectangle();


        solidArea.x =8;
        solidArea.y =8;
        solidArea.width = 32;
        solidArea.height = 32;

        setSolidAreaDefaultX(solidArea.x);
        setSolidAreaDefaultY(solidArea.y);

        setDefaultValue();
        getPlayerImage(); // cal image for Player
    }

    // SET INITIAL PLACE OF PLAYER
    public void setDefaultValue(){
        this.setX(gp.getTitleSize()*15); // set place for player
        this.setY(gp.getTitleSize()*12);
        this.setSpeed(4);
    }


    public void getPlayerImage(){ // read Image
        try {
            up1 = ImageIO.read(new File("data/walk/boy_up_1.png"));
            up2 = ImageIO.read(new File("data/walk/boy_up_2.png"));
            down1 = ImageIO.read(new File("data/walk/boy_down_1.png"));
            down2 = ImageIO.read(new File("data/walk/boy_down_2.png"));
//            down1 = ImageIO.read(new File("data/walk/npc1.png"));
//            down2 = ImageIO.read(new File("data/walk/npc2.png"));
            left1 = ImageIO.read(new File("data/walk/boy_left_1.png"));
            left2 = ImageIO.read(new File("data/walk/boy_left_2.png"));
            right1 = ImageIO.read(new File("data/walk/boy_right_1.png"));
            right2 = ImageIO.read(new File("data/walk/boy_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update(){ // refresh per frame by key-cap
        int pressing = 0;
        if (keyHandle.upKey == true){
            this.setDirection("up");
            pressing = 1;
//            this.setY(getY()-getSpeed());
        }
        if (keyHandle.downKey == true){
            this.setDirection("down");
            pressing = 1;
//            this.setY(getY()+getSpeed());
        }
        if (keyHandle.leftKey == true){
            this.setDirection("left");
            pressing = 1;
//            this.setX(getX()-getSpeed());
        }
        if (keyHandle.rightKey == true){
            this.setDirection("right");
            pressing = 1;
//            this.setX(getX()+getSpeed());
        }
        if (keyHandle.leftKey==false && keyHandle.upKey==false &&keyHandle.downKey==false &&keyHandle.rightKey==false){
            pressing = 0;
        }
        // check tile collision
        setCollisionOn(false);
        gp.checker.checkTiles(this);


        // check obj collision
        int hold = gp.checker.checkObj(this,true);

        //System.out.println("dirrection = "+ getDirection() + "pressing" +pressing);

        // condition -> if collinsion false, player can't move
        if (getCollisionOn() ==false && pressing!=0 ) {
            switch (getDirection()){
                case "up":
                    this.setY(getY()-getSpeed());
                    break;
                case "down":
                    this.setY(getY()+getSpeed());
                    break;
                case "left":
                    this.setX(getX()-getSpeed()); // bi loi cai z do nen phim A k an
                    break;
                case "right":
                    this.setX(getX()+getSpeed());
                    break;

            }
        }

        // IDEA to like multiple action of Character -> count each 10 frames, change another pic.
        setCountFrame(getCountFrame()+1);
        if (getCountFrame()>20){    //frame
            if (getFlagPic()==1){
                setFlagPic(2);
            }else if (getFlagPic()==2){
                setFlagPic(1);
            }
            setCountFrame(0);
        }


        // check Coline
        gp.checker.checkTiles(this);

    }

    public void draw (Graphics2D g2){

        BufferedImage image = null;
        switch (getDirection()){ // condition to build Movingd
            case "up":
                if (getFlagPic()==1){
                    image=up1;
                }
                if (getFlagPic()==2) {
                    image = up2;
                }
                break;
            case "down":
                if (getFlagPic()==1){
                    image=down1;
                }
                if (getFlagPic()==2) {
                    image = down2;
                }
                break;
            case "left":
                if (getFlagPic()==1){
                    image=left1;
                }
                if (getFlagPic()==2) {
                    image = left2;
                }

                break;
            case "right":
                if (getFlagPic()==1){
                    image=right1;
                }
                if (getFlagPic()==2) {
                    image = right2;
                }

                break;
        }

        g2.drawImage(image,screenX,screenY,gp.getTitleSize(),gp.getTitleSize(),null); // method to draw

    }
}
