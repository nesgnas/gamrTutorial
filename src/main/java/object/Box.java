package object;

import entity.Player;
import main.gamePanel;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static Tiles.TileManage.bom;
import static Tiles.TileManage.row1;
import static main.gamePanel.*;

public class Box extends superObject implements Comparable<Box>,Cloneable{
    private int room;
    gamePanel gp;

    public Box(){

    }
    public Box(Box box) {
        this.room = box.room;
        this.solidArea = box.solidArea;
        this.setPosX(box.getPosX());
        this.setCollision(box.isCollision());
        this.setPosY(box.getPosY());
        this.setName(box.getName());
        this.setImage(box.getImage());
        this.setSolidAreaDefaultPosX(box.getSolidAreaDefaultPosX());
        this.setSolidAreaDefaultPosY(box.getSolidAreaDefaultPosY());

    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }


    public static ArrayList<Box> boxes = new ArrayList<Box>();
    public static ArrayList<Box> boxesCopy =new ArrayList<Box>(boxes);
    // Check! If the reset is clicked, the room will reset

    @Override
    public int compareTo(Box o) {
        final int HIGHER = 1;
        final int LOWER = -1;
        final int EQUAL = 0;
        if(this.room==o.room){
            return EQUAL;
        }
        if (this.room>o.room){
            return HIGHER;
        }
        if (this.room<o.room){
            return LOWER;
        }
        return 0;
    }


}
