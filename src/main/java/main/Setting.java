package main;

import Tiles.UtilityTool;
import entity.Player;
import main.gamePanel;
import main.keyHandle;
import main.mouseHandle;
import main.Menu;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.Utilities;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;

public class Setting extends Background{
    gamePanel gp;
    public BufferedImage menu;
    UtilityTool utilityTool;
    public int commandNum = 0;
    public int subState = 0;
    public boolean messageOn =false;
    public String message = "";
    int messageCounter = 0;
    public String currentDialogue = "";
    Font arial_40, arial_80B;
    Graphics2D g2;
    MouseEvent e;
    public double orbitX = -700; /* x-coordinate in orbit's center */
    public double orbitY = -600; /* y-coordinate in orbit's center */

    public int mouseX_Y (int x){
        return x;
    }
    public Setting(gamePanel gp){
        this.gp = gp;
        getImage();
        arial_40 = new Font("ThaleahFat",Font.ROMAN_BASELINE,40);
        arial_80B = new Font("ThaleahFat",Font.PLAIN,80);
    }
    static void mousePress(MouseEvent e,int textX, int textY) {
//        int mx = gamePanel.e.getX();
//        int my = gamePanel.e.getY();
//        gamePanel.mouseX = mouseX_Y(mx);
//        gamePanel.mouseY = mouseX_Y(my);
//
//        int frameX = gp.getTitleSize()*5;
//        int frameY = gp.getTitleSize()*4;
//        int textX = frameX + gp.getTitleSize() + 50;
//        int textY = gp.getTitleSize()*3;
//
//        if (mx>=textX && mx<=textX+50
//                && my>= textY && my<=textY+50) { //Reset room
//            //gamePanel.state = "game";
//            System.out.println("Mouse click");
////            g2.drawString("Select", textX + 50,textY -30);
//        }

//        if (mx > 60 && mx < 210 && my > 250 && my < 290) //Play button
//            gamePanel.state = "game";
//        else if (mx > 60 && mx < 210 && my > 300 && my < 340) // intro button
//            gamePanel.state = "intro";
//        else if (mx > 60 && mx < 210 && my > 350 && my < 390) //guide button
//            gamePanel.state = "guide";
//        else if (mx > 60 && mx < 210 && my > 400 && my < 440) // credits button
//            gamePanel.state = "credits";
//        else if (mx > 60 && mx < 210 && my > 450 && my < 490)
//            System.exit(1);
    }
    public void getImage() { // read Image
        try {
            menu = ImageIO.read(new File("data/ui/Menu.png"));
            //reset_room = ImageIO.read(new File("data/ui/Menu.png"));
            //continue = ImageIO.read(new File("data/ui/Menu.png"));
            //reset_room = ImageIO.read(new File("data/ui/Menu.png"));
            //reset_room = ImageIO.read(new File("data/ui/Menu.png"));

//            up2 = ImageIO.read(new File("data/sprite/up/3.png"));
//            down1 = ImageIO.read(new File("data/sprite/down/2.png"));
//            down2 = ImageIO.read(new File("data/sprite/down/3.png"));
//            // down1 = ImageIO.read(new File("data/walk/npc1.png"));
//            // down2 = ImageIO.read(new File("data/walk/npc2.png"));
//            left1 = ImageIO.read(new File("data/sprite/idle/left/2.png"));
//            left2 = ImageIO.read(new File("data/sprite/idle/left/3.png"));
//            right1 = ImageIO.read(new File("data/sprite/idle/right/2.png"));
//            right2 = ImageIO.read(new File("data/sprite/idle/right/3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        g2.drawString("Room: " + Player.getRoomPlayerIn(), 50,50);
        //Play State
        if (gp.gameState == gp.playState){
            //Do playSatte stuff later
        }
        //Pause State
        if (gp.gameState == gp.optionsState){
            //drawPauseScreen();
            drawOptionsScreen();
        }
        //Dialogue State
        if (gp.gameState == gp.dialogue){
            drawDialogueScreen();
        }
        //this.g2 = g2;
        //setupDefaultGraphics(graphics2D);
        //drawOptionScreen();
    }
    public void drawSubWindow(int x, int y, int width, int height){
        Color color = new Color(0, 0, 0, 199);
        g2.setColor(color);
        g2.fillRoundRect(x,y,width,height,35,35);

        color = new Color(255,255, 255);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5,width-10,height-10,25,25);
    }
    public void drawDialogueScreen(){
        //Window
        int x = gp.getTitleSize() * 2;
        int y = gp.getTitleSize() / 2;
        int width = gp.getScreenWidth() - (gp.getTitleSize()*4);
        int height = gp.getTitleSize() * 4;
        drawSubWindow(x,y,width,height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32F));
        x += gp.getTitleSize();
        y += gp.getTitleSize();
        for (String line : currentDialogue.split("\n")){
            g2.drawString(line,x,y);
            y += 40;
        }
        //g2.drawString(currentDialogue,x,y);
    }
    public void drawOptionsScreen(){
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(20F));
        int frameX = gp.getTitleSize()*5;
        int frameY = gp.getTitleSize()*4;
        int frameWidth = gp.getTitleSize() * 10;
        int frameHeight = gp.getTitleSize() * 8;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);
        switch (subState){
            case 0:
                option_top(frameX,frameY);
                break;
            case 1:
                option_fullScreenNottification(frameX,frameY);
                break;
            case 2:
                break;
        }
    }
    //    public void drawPauseScreen(){
//        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
//        String text = "PAUSE";
//        int x = getXForCenteredText(text);
//        int y = gp.getScreenWidth()/2;
//        g2.drawString(text,x,y);
//
//    }
    public int getXForCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.getScreenWidth()/2 - length/2;
        return x;
    }
    public void option_top(int frameX, int frameY){
        int textX;
        int textY;

        //Title
        //String text = "Setting";
        //textX = utilityTool.getXForCenterOfText(text,gp,g2);
        textY = frameY + gp.getTitleSize();
//        g2.drawString(text, textX, textY);

        //New Game on/off
        textX = frameX + gp.getTitleSize() + 50;
        textY += gp.getTitleSize()*2;
        g2.drawString("Reset", textX,textY);
        if (commandNum == 0){
            g2.drawImage(menu, textX, textY - 70, gp.getTitleSize(), gp.getTitleSize(), null); // method to draw
            //g2.drawString(">",textX-25, textY);
        }

//        textX += 50;
//        g2.drawString("Reset", textX,textY);
//        textX -=50;

//        //Full Screen
//        g2.drawString("Full Screen", textX,textY);
//        if (commandNum == 0){
//            g2.drawString(">",textX-25, textY);
////            if (gp.keyH.enterPressed){
////                if (!gp.fullScreenOn){
////                    gp.fullScreenOn = true;
////                }
////                else if (gp.fullScreenOn){
////                    gp.fullScreenOn = false;
////                }
////                subState = 1;
////            }
//
//        }

        //Reset Room
        //Menu
        textX += 280;
        g2.drawString("Menu", textX,textY);

        //Continous
        //textX = utilityTool.getXForCenterOfText(text,gp,g2);
        textX -=160;
        textY += gp.getTitleSize()*2;
        g2.drawString("Continous", textX,textY);


        // Music
        textX = frameX + gp.getTitleSize() + 50;
        textY += gp.getTitleSize()*2;
        g2.drawString("Music", textX,textY);

        // Effect
        textX += 270;
        g2.drawString("Effect", textX,textY);
        //Click



//        else if (mx>60 && mx<210 && my>300 && my<340) // intro button
//            gamePanel.state = "intro";
//        else if (mx>60 && mx<210 && my>350 && my<390) //guide button
//            gamePanel.state = "guide";
//        else if (mx>60 && mx<210 && my>400 && my<440) // credits button
//            gamePanel.state = "credits";
//        else if(mx>60 && mx<210 && my>450 && my<490)
//            System.exit(1);


    }
    public void option_fullScreenNottification(int frameX, int frameY){
        int textX = frameX + gp.getTitleSize();
        int textY = frameY + gp.getTitleSize()*3;
        //currenDialogue = " the change will take "
//        for (String line: currenDialogue.split("\n")){
//            g2.drawString(line,textX,textY);
//            textY += 40;
//        }

        //Back

        textY = frameY + gp.getTitleSize()*9;
        g2.drawString("Back", textX,textY);
        if (commandNum == 0){
            g2.drawString(">",textX-25,textY);
//            if (gp.keyH.enterPressed == true){
//                subState = 0;
//            }
        }
    }
    private void setupDefaultGraphics(Graphics2D graphics2D) {
        //graphics2D.setFont(maruMonica);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.setColor(Color.WHITE);
    }

    //    private void setupFonts() {
//        try {
//            InputStream inputStream = getClass().getResourceAsStream("/fonts/x12y16pxMaruMonica.ttf");
//            this.maruMonica = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(inputStream));
//        } catch (FontFormatException | IOException e) {
//            e.printStackTrace();
//        }
//    }
}

