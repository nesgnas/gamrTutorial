package main;
import Tiles.UtilityTool;
import entity.Player;
import main.gamePanel;
import main.keyHandle;
import javax.swing.*;
import javax.swing.text.Utilities;
import java.awt.*;

public class UI {
    //main.keyHandle keyHandle = new keyHandle(this); // call keyHandle.class
    gamePanel gp;
    UtilityTool utilityTool;
    public int commandNum = 0;
    public int subState = 0;
    public boolean messageOn =false;
    public String message = "";
    int messageCounter = 0;
    public String currentDialogue = "";
    Font arial_40, arial_80B;
    Graphics2D g2;
    public UI(gamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial",Font.PLAIN,40);
        arial_80B = new Font("Arial",Font.BOLD,80);
        //setupFonts();
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
        if (gp.gameState == gp.pauseState){
            drawPauseScreen();
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
        Color color = new Color(0,0,0, 189);
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
    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSE";
        int x = getXForCenteredText(text);
        int y = gp.getScreenWidth()/2;
        g2.drawString(text,x,y);

    }
    public int getXForCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.getScreenWidth()/2 - length/2;
        return x;
    }
    /*public void drawOptionScreen(){
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));
        int frameX = gp.getOriginalSizeTile() * 6;
        int frameY = gp.getOriginalSizeTile();
        int frameWidth = gp.getOriginalSizeTile() * 8;
        int frameHeight = gp.getOriginalSizeTile() * 10;
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
    public void option_top(int frameX, int frameY){
        int textX;
        int textY;

        //Title
        String text = "Menu";
        textX = utilityTool.getXForCenterOfText(text,gp,g2);
        textY = frameY + gp.getTitleSize();
        g2.drawString(text, textX, textY);

        //New Game on/off
        textX = frameX + gp.getTitleSize();
        textY += gp.getTitleSize()*2;
        g2.drawString("New Game", textX,textY);
        if (commandNum == 0){
            g2.drawString(">",textX-25, textY);
        }
        //Full Screen
        g2.drawString("Full Screen", textX,textY);
        if (commandNum == 0){
            g2.drawString(">",textX-25, textY);
//            if (gp.keyH.enterPressed){
//                if (!gp.fullScreenOn){
//                    gp.fullScreenOn = true;
//                }
//                else if (gp.fullScreenOn){
//                    gp.fullScreenOn = false;
//                }
//                subState = 1;
//            }

        }

        //Pause
        textY += gp.getTitleSize();
        g2.drawString("Pause game", textX,textY);
        if (commandNum == 1){
            g2.drawString(">",textX-25, textY);
        }
        //Reset room
        textY += gp.getTitleSize();
        g2.drawString("Reset room", textX,textY);
        if (commandNum == 2){
            g2.drawString(">",textX-25, textY);
        }
        // Sound/Effect
        textY += gp.getTitleSize();
        g2.drawString("Sound", textX,textY);
        if (commandNum == 3){
            g2.drawString(">",textX-25, textY);
        }
        //Exit
        textY += gp.getTitleSize();
        g2.drawString("Exit", textX,textY);
        if (commandNum == 4){
            g2.drawString(">",textX-25, textY);
        }
        //Back
        textY += gp.getTitleSize()*2;
        g2.drawString("Back", textX,textY);
        if (commandNum == 5){
            g2.drawString(">",textX-25, textY);
        }
        // Full Screen Check Box
        textX = frameX + (int) (gp.getTitleSize() * 4.5);
        textY = frameY + gp.getTitleSize() * 2 + 24;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX,textY,24,24);
        if (gp.fullScreenOn){
            g2.fillRect(textX,textY,24,24);
        }

        //Music Volume
        textY += gp.getTitleSize();
        g2.drawRect(textX,textY,120,24); //120/5 = 24;
//        int volumeWidth = 24 * gp.music.volumeScale;
//        g2.fillRect(textX,textY,volumeWidth,24);

        //SE Volume
        textY += gp.getTitleSize();
        g2.drawRect(textX,textY,120,24);
//        g2.fillRect(textX,textY,volumeWidth,24);


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
    public void drawSubWindow(int x, int y, int width, int height) {
        Color color = new Color(0, 0, 0, 210);
        g2.setColor(color);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        color = new Color(255, 255, 255);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
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

     */
}

