package Tiles;

import main.gamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {

    public static BufferedImage scaleImage(BufferedImage original, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D graphics2D = scaledImage.createGraphics();
        graphics2D.drawImage(original, 0, 0, width, height, null);
        graphics2D.dispose();

        return scaledImage;
    }

    public static int getXForCenterOfText(String text, gamePanel gp, Graphics2D graphics2D) {
        int length = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
        return gp.getScreenWidth() / 2 - length / 2;
    }

    public static int getXForAlightToRightOfText(String text, int tailX, gamePanel gp, Graphics2D graphics2D) {
        int length = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
        return tailX - length;
    }

    public static boolean isInsidePlayerView(int worldX, int worldY, gamePanel gp) {

        return worldX + gp.getTitleSize() > gp.player.getX() - gp.player.screenX
                && worldX - gp.getTitleSize() < gp.player.getX() + gp.player.screenX
                && worldY + gp.getTitleSize() > gp.player.getY() - gp.player.screenY
                && worldY - gp.getTitleSize() < gp.player.getY() + gp.player.screenY;
    }

    public static void changeAlpha(Graphics2D graphics2D, float alphaValue) {
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }
}
