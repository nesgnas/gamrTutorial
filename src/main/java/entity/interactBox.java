package entity;

import java.awt.*;

public class interactBox extends Entity{
    public interactBox(int x, int y, Rectangle solid, String direct, int speed, boolean colline) {
        this.setX(x);
        this.setY(y);
        this.setSolidArea(solid);
        this.setDirection(direct);
        this.setSpeed(speed);
        this.setCollisionOn(colline);


    }
}
