package main;

import object.Box;

import static object.Box.boxes;
import static object.Box.boxesCopy;


public class alterSetter {

    private final gamePanel gp;

    public alterSetter(gamePanel gp) {
        this.gp = gp;
    }
    int count =0;

    public void setObj(){
       for (Box box :boxesCopy){
           box.setPosX(box.getPosX()*gp.getTitleSize());
           box.setPosY(box.getPosY()*gp.getTitleSize());
       }
    }
}
