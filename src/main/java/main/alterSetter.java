package main;

import object.Box;

import static object.Box.boxes;

import static object.Box.boxesUse;


public class alterSetter {

    private final gamePanel gp;

    public alterSetter(gamePanel gp) {
        this.gp = gp;
    }
    int count =0;

    public void setObj(){
        System.out.println("INITIAL BOXES");
        for (Box box : boxes){
            System.out.println(box.toString());
        }
        System.out.println("___________________");
        System.out.println("INITIAL BOXESUSE");
        for (Box box2 :boxesUse){
            System.out.println(box2.toString());
        }
        System.out.println("_______________");
        for (Box box : boxes){
            System.out.println(box.toString());
        }
        System.out.println("CHANGE BOXESUSE");

       for (Box box :boxesUse){
           box.setPosX(box.getPosX()*gp.getTitleSize());

           box.setPosY(box.getPosY()*gp.getTitleSize());

           System.out.println(box.toString());


       }

    }
}
