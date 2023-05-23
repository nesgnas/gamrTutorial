package main;

import Tiles.UtilityTool;
import entity.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static main.gamePanel.soundEffect;
import static main.gamePanel.soundMusic;

public class UI1 implements MouseListener {
    // main.keyHandle keyHandle = new keyHandle(this); // call keyHandle.class

    gamePanel gp;
    public BufferedImage menu, cont, reset, sound_music_on, sound_music_off,
            sound_effect_on, sound_effect_off, pause, bg, playgame, guide, sound, credit;
    UtilityTool utilityTool;
    public static int commandNum_music = 1;
    public static int commandNum_effect = 3;
    public int subState = 0;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public String currentDialogue = "";
    Font arial_30, arial_80B;
    Graphics2D g2;
    MouseEvent e;

    public UI1(gamePanel gp) {
        this.gp = gp;
        getImage();
        arial_30 = new Font("ThaleahFat", Font.BOLD, 30);
        arial_80B = new Font("ThaleahFat", Font.PLAIN, 80);
        // setupFonts();
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(arial_30);
        g2.setColor(Color.white);
        draw_pause(70, 105);
        g2.drawString("Room: " + Player.getRoomPlayerIn(), 45, 115);
        // Start game - Title state
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        if (gp.gameState == gp.creditState) {
            drawCreditState();
        }
        // Play State
        if (gp.gameState == gp.playState) {
            // Do playState stuff later
        }
        // Pause State
        if (gp.gameState == gp.optionsState) {
            drawOptionsScreen();
        }
        // Dialogue State
        if (gp.gameState == gp.dialogue) {
            drawDialogueScreen();
        }
    }

    private void drawCreditState() {
        // BACKGOURND
        g2.drawImage(bg, 0, 0, gp.worldWidth, gp.worldHeight, null);
        // Name
        // BAO
        String text = "NGUYEN HUY BAO ITDSIU21076";
        int x = getXForCenteredText(text);
        int y = gp.getTitleSize() * 3;
        g2.setColor(Color.BLACK);
        g2.drawString(text, x - 40, y + 80);
        // NTNT
        String text1 = "NGUYEN TRAN NGUYEN ANH ITITWE20021";

        g2.setColor(Color.BLACK);
        g2.drawString(text1, x - 40, y + 150);
        // My
        String text2 = "NGUYEN HUYNH THAO MY ITCSIU21204";

        g2.setColor(Color.BLACK);
        g2.drawString(text2, x - 40, y + 220);
        // THU HOANG itcsiu21063
        String text3 = "LE THU HOANG ITCSIU21063  ";
        g2.setColor(Color.BLACK);
        g2.drawString(text3, x - 40, y + 290);
        // Sang
        String text4 = "BUI DOAN THE SANG ITCSIU21104 ";
        g2.setColor(Color.BLACK);
        g2.drawString(text4, x - 40, y + 360);

    }

    void drawTitleScreen() {

        // BACKGOURND
        g2.drawImage(bg, 0, 0, gp.worldWidth, gp.worldHeight, null);
        // TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "KEKEKE";
        int x = getXForCenteredText(text);
        int y = gp.getTitleSize() * 3;
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        // playgame
        g2.drawImage(playgame, (gp.worldWidth / 2) - 200, (gp.worldHeight / 2) - 250, gp.getTitleSize() * 9,
                gp.getTitleSize() * 2,
                null);
        // guide
        g2.drawImage(guide, (gp.worldWidth / 2) - 100, (gp.worldHeight / 2) - 150, gp.getTitleSize() * 5,
                gp.getTitleSize() * 2,
                null);
        // sound
        g2.drawImage(sound, (gp.worldWidth / 2) - 100, (gp.worldHeight / 2) - 50, gp.getTitleSize() * 5,
                gp.getTitleSize() * 2,
                null);
        // credit
        g2.drawImage(credit, (gp.worldWidth / 2) - 100, (gp.worldHeight / 2) + 50, gp.getTitleSize() * 5,
                gp.getTitleSize() * 2,
                null);

    }

    public void drawSubWindow(int x, int y, int width, int height) {
        // mousePressed(e);
        // mouseClicked(e);
        Color color = new Color(0, 0, 0, 186);
        g2.setColor(color);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        color = new Color(255, 255, 255);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public void drawDialogueScreen() {
        // Window
        int x = gp.getTitleSize() * 2;
        int y = gp.getTitleSize() / 2;
        int width = gp.getScreenWidth() - (gp.getTitleSize() * 4);
        int height = gp.getTitleSize() * 4;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.getTitleSize();
        y += gp.getTitleSize();
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
        g2.drawString(currentDialogue, x, y);
    }

    public void drawOptionsScreen() {
        g2.setColor(Color.white);
        // g2.setFont(g2.getFont().deriveFont(40F));
        int frameX = (int) (gp.getTitleSize() * 5.5);
        int frameY = gp.getTitleSize() * 4;
        int frameWidth = (int) (gp.getTitleSize() * 9.5);
        int frameHeight = (int) (gp.getTitleSize() * 7.5);
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        option_top(frameX, frameY);
        // if (soundMusic) {
        // commandNum_music = 1;
        // }
        // else {
        // commandNum_music = 2;
        // }
        // if (soundEffect) {
        // commandNum_effect = 3;
        // }
        // else{
        // commandNum_effect = 4;
        // }
    }

    public int getXForCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.getScreenWidth() / 2 - length / 2;
        return x;
    }

    public void draw_pause(int textX, int textY) {
        g2.drawImage(pause, textX, textY - 70, gp.getTitleSize(), gp.getTitleSize(), null); // method to draw
        // System.out.println("textX: " + textX + " - " + "textY" + textY);
    }

    public void option_top(int frameX, int frameY) {
        int textX;
        int textY;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
        int titlesize = gp.getTitleSize() + 10;
        // Title
        String text = "Settings";
        textX = getXForCenteredText(text) - 10;
        textY = frameY;
        g2.drawString("Settings", textX + 10, textY + gp.getTitleSize());
        g2.setFont(g2.getFont().deriveFont(20F));

        // Reset Room
        textX = frameX + gp.getTitleSize();
        textY += gp.getTitleSize() * 2;
        g2.drawString("Reset", textX + 70, textY + gp.getTitleSize());
        g2.drawImage(reset, textX + 70, textY + gp.getTitleSize() - 70, titlesize - 10, titlesize - 10, null); // method
                                                                                                               // to
                                                                                                               // draw
        // System.out.println("textX: " + textX + " - " + "textY" + textY);
        textY += gp.getTitleSize();
        textX += 70;

        // Home
        textY += gp.getTitleSize() * 2;
        g2.drawString("Home", textX + 150, textY - gp.getTitleSize() * 2);
        // g2.drawString("Home", textX,textY);
        g2.drawImage(menu, textX + 150, textY - gp.getTitleSize() * 2 - 70, titlesize - 10, titlesize - 10, null); // method
                                                                                                                   // to
                                                                                                                   // draw
        // System.out.println("textX: " + textX + " - " + "textY" + textY);
        textX += 150;

        // Continue
        // textY += frameY;
        text = "Continue";
        textY += 50;
        textX = getXForCenteredText(text) - 10;
        g2.drawString("Continue", textX + 10, textY - 50);
        g2.drawImage(cont, textX + 20, textY - 120, titlesize - 10, titlesize - 10, null); // method to draw
        // System.out.println("textX: " + textX + " - " + "textY" + textY);
        textY -= 50;

        // Music
        text = "Music";
        textX = frameX + gp.getTitleSize();
        if (commandNum_music == 1) {
            g2.drawString("Music: ON", textX + 60, textY + gp.getTitleSize() * 2);
            g2.drawImage(sound_music_on, textX + 70, textY + gp.getTitleSize() * 2 - 70, titlesize - 10, titlesize - 10,
                    null); // method to draw
        }
        if (commandNum_music == 2) {
            g2.drawString("Music: OFF", textX + 60, textY + gp.getTitleSize() * 2);
            g2.drawImage(sound_music_off, textX + 70, textY + gp.getTitleSize() * 2 - 70, titlesize - 10,
                    titlesize - 10, null); // method to draw
        }
        textX += 70;
        // System.out.println("textX: " + textX + " - " + "textY" + textY);

        // Effect
        if (commandNum_effect == 3) {
            g2.drawString("Effect: ON", textX + 135, textY + gp.getTitleSize() * 2);
            g2.drawImage(sound_effect_on, textX + 150, textY + gp.getTitleSize() * 2 - 70, titlesize - 10,
                    titlesize - 10, null); // method to draw
        }
        if (commandNum_effect == 4) {
            g2.drawString("Effect: OFF", textX + 135, textY + gp.getTitleSize() * 2);
            g2.drawImage(sound_effect_off, textX + 150, textY + gp.getTitleSize() * 2 - 70, titlesize - 10,
                    titlesize - 10, null); // method to draw
        }
        textX += 150;
        // System.out.println("textX: " + textX + " - " + "textY" + textY);
        textY += gp.getTitleSize() * 2;
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
            bg = ImageIO.read(new File("data/ui/backbround.jpg"));
            playgame = ImageIO.read(new File("data/ui/playgamr.png"));
            guide = ImageIO.read(new File("data/ui/guide.png"));
            sound = ImageIO.read(new File("data/ui/sound.png"));
            credit = ImageIO.read(new File("data/ui/credit.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if (mx >= 342 && mx <= 342 + 50 && my >= 312 && my <= 312 + 50) { // Reset room
            // resetRoom = true;
            // gamePanel.state = "game";
            System.out.println("Reset room");
        } else if (mx >= 577 && mx <= 577 + 50 && my >= 312 && my <= 312 + 50) { // Menu
            // gamePanel.state = "game";
            System.out.println("Menu");
        } else if (mx >= 443 && mx <= 443 + 50 && my >= 408 && my <= 408 + 50) { // Continous
            // gamePanel.state = "game";
            System.out.println("Continous");
        } else if (mx >= 342 && mx <= 342 + 50 && my >= 504 && my <= 504 + 50) { // Music
            // gamePanel.state = "game";
            System.out.println("Sound Music");
        } else if (mx >= 572 && mx <= 572 + 50 && my >= 504 && my <= 504 + 50) { // Effect
            // gamePanel.state = "game";
            System.out.println("Sound Effect");
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
}
