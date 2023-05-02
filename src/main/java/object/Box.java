package object;

import java.util.ArrayList;

public class Box extends superObject implements Comparable<Box>{
    private int room;

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }



    public static ArrayList<Box> boxes = new ArrayList<Box>();
    public static ArrayList<Box> boxesUse = new ArrayList<Box>();



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
