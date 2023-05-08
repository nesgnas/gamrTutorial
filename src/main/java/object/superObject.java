package object;

import main.gamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class superObject {
    private BufferedImage image;
    private String Name;
    private boolean collision = false;

    public Rectangle solidArea = new Rectangle(0, 0, 45, 45);

    private int solidAreaDefaultPosX = 0;
    private int SolidAreaDefaultPosY = 0;

    private int posX, posY;

    @Override
    public String toString() {
        return "superObject{" +
                "Name='" + Name + '\'' +
                ", collision=" + collision +
                ", posX=" + posX +
                ", posY=" + posY +
                '}';
    }

    public void draw(Graphics2D g2, gamePanel gp) {
        int screenX = getPosX() - gp.player.getX() + gp.player.screenX;
        int screenY = getPosY() - gp.player.getY() + gp.player.screenY;

        if (getPosX() + gp.getTitleSize() > gp.player.getX() - gp.player.screenX &&
                getPosX() - gp.getTitleSize() < gp.player.getX() + gp.player.screenX &&
                getPosY() + gp.getTitleSize() > gp.player.getY() - gp.player.screenY &&
                getPosY() - gp.getTitleSize() < gp.player.getY() + gp.player.screenY) {
            g2.drawImage(getImage(), screenX, screenY, gp.getTitleSize(), gp.getTitleSize(), null);
        }
    }

    public int getSolidAreaDefaultPosX() {
        return solidAreaDefaultPosX;
    }

    public void setSolidAreaDefaultPosX(int solidAreaDefaultPosX) {
        this.solidAreaDefaultPosX = solidAreaDefaultPosX;
    }

    public int getSolidAreaDefaultPosY() {
        return SolidAreaDefaultPosY;
    }

    public void setSolidAreaDefaultPosY(int solidAreaDefaultPosY) {
        SolidAreaDefaultPosY = solidAreaDefaultPosY;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

}
