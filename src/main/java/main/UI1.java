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
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;

public class UI1 implements MouseListener{
    //main.keyHandle keyHandle = new keyHandle(this); // call keyHandle.class

    gamePanel gp;
    public BufferedImage menu, cont, reset, sound_music_on, sound_music_off,
            sound_effect_on, sound_effect_off, pause;
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
    public UI1(gamePanel gp) {
        this.gp = gp;
        getImage();
        arial_40 = new Font("ThaleahFat",Font.ROMAN_BASELINE,40);
        arial_80B = new Font("ThaleahFat",Font.PLAIN,80);
        //setupFonts();
    }
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2) {
        //mouseClicked(e);
        //mousePressed(e);
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        draw_pause(80,85);
        g2.drawString("Room: " + Player.getRoomPlayerIn(), 50,100);
        //Play State
        if (gp.gameState == gp.playState){
            //Do playSatte stuff later
        }
        //Pause State
        if (gp.gameState == gp.optionsState){
            drawOptionsScreen();
        }
        //Dialogue State
        if (gp.gameState == gp.dialogue){
            drawDialogueScreen();
        }
    }
    public void drawSubWindow(int x, int y, int width, int height){
        //mousePressed(e);
        //mouseClicked(e);
        Color color = new Color(0, 0, 0, 186);
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
    public void drawOptionsScreen() {
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(40F));
        int frameX = (int) (gp.getTitleSize() * 5.5);
        int frameY = gp.getTitleSize() * 4;
        int frameWidth = (int) (gp.getTitleSize() * 9.5);
        int frameHeight = (int) (gp.getTitleSize() * 7.5);
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        option_top(frameX, frameY);
    }

    public int getXForCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.getScreenWidth()/2 - length/2;
        return x;
    }
    public void draw_pause(int textX, int textY){
        g2.drawImage(pause, textX, textY - 70, gp.getTitleSize(), gp.getTitleSize(), null); // method to draw
    }
    public void option_top(int frameX, int frameY){
        int textX;
        int textY;

        //Title
        String text = "Setting";
        textX = getXForCenteredText(text);
        textY = frameY + gp.getTitleSize();
        g2.drawString(text, textX, textY);
        g2.setFont(g2.getFont().deriveFont(20F));

        //Reset Room
        textX = frameX + gp.getTitleSize() + 30;
        textY += gp.getTitleSize()*2;
        g2.drawString("Reset", textX,textY);
        //if (commandNum == 0){
            g2.drawImage(reset, textX, textY - 70, gp.getTitleSize(), gp.getTitleSize(), null); // method to draw
        //System.out.println("textX: " + textX + " - " + "textY" + textY);

        //Menu
        textX += 235;
        g2.drawString("Setting", textX,textY);
        g2.drawImage(menu, textX+5, textY - 70, gp.getTitleSize(), gp.getTitleSize(), null); // method to draw
        //System.out.println("textX: " + textX + " - " + "textY" + textY);

        //Continous
        textX -=134;
        textY += gp.getTitleSize()*2;
        g2.drawString("Continue", textX,textY);
        g2.drawImage(cont, textX + 14, textY - 70, gp.getTitleSize(), gp.getTitleSize(), null); // method to draw
        //System.out.println("textX: " + textX + " - " + "textY" + textY);


        // Music
        textX = frameX + gp.getTitleSize() + 30;
        textY += gp.getTitleSize()*2;
        if (gp.soundMusic){
            g2.drawString("Music", textX + 2,textY);
            g2.drawImage(sound_music_on, textX, textY - 70, gp.getTitleSize(), gp.getTitleSize(), null); // method to draw
        }
        else
        if (!gp.soundMusic){
            g2.drawString("Music", textX + 2,textY);
            g2.drawImage(sound_music_off, textX, textY - 70, gp.getTitleSize(), gp.getTitleSize(), null); // method to draw
        }
        //System.out.println("textX: " + textX + " - " + "textY" + textY);

        // Effect
        textX += 230;
        if (gp.soundEffect) {
            g2.drawString("Effect", textX, textY);
            g2.drawImage(sound_effect_on, textX + 5, textY - 70, gp.getTitleSize(), gp.getTitleSize(), null); // method to draw
        }
        else
            if (!gp.soundEffect){
                g2.drawString("Effect", textX, textY);
                g2.drawImage(sound_effect_off, textX+5, textY - 70, gp.getTitleSize(), gp.getTitleSize(), null); // method to draw
            }
            //System.out.println("textX: " + textX + " - " + "textY" + textY);

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

    public void getImage() { // read Image
        try {
            menu = ImageIO.read(new File("data/ui/home.png"));
            reset = ImageIO.read(new File("data/ui/Reset.png"));
            cont = ImageIO.read(new File("data/ui/play.png"));
            sound_music_on = ImageIO.read(new File("data/ui/Sound_Music_on.png"));
            sound_music_off = ImageIO.read(new File("data/ui/Sound_Music_off.png"));
            sound_effect_on = ImageIO.read(new File("data/ui/Sound_Effect_on.png"));
            sound_effect_off = ImageIO.read(new File("data/ui/Sound_Effect_off.png"));
            pause = ImageIO.read(new File("data/ui/pause.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if (mx>=342 && mx<=342+50 && my>= 312 && my<=312+50) { //Reset room
            //resetRoom = true;
            //gamePanel.state = "game";
            System.out.println("Reset room");
        }
        else
        if (mx>=577 && mx<=577+50 && my>= 312 && my<=312+50) { //Menu
            //gamePanel.state = "game";
            System.out.println("Menu");
        }
        else
        if (mx>=443 && mx<=443+50 && my>= 408 && my<=408+50) { //Continous
            //gamePanel.state = "game";
            System.out.println("Continous");
        }
        else
        if (mx>=342 && mx<=342+50 && my>= 504 && my<= 504+50) { //Music
            //gamePanel.state = "game";
            System.out.println("Sound Music");
        }
        else
        if (mx>=572 && mx<=572+50 && my>= 504 && my<=504+50) { //Effect
            //gamePanel.state = "game";
            System.out.println("Sound Effect");
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}

