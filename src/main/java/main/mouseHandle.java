package main;

import entity.Player;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;

public class mouseHandle implements MouseListener{
    static MouseEvent me;
    gamePanel gp;
    Player player;
    Menu menu;
    Credits credits;
    Guide guide;
    //Exit exit;
    public mouseHandle(gamePanel gp){

        this.gp =gp;
        addMouseListener(this);
    }
    public void init(){
        addMouseListener(this);
    }
    public void mousePrint(MouseEvent me){
        System.out.println("Mouse position: " + me.getX() + " " + me.getY());
    }
    public void mouseClicked(MouseEvent me) {
    }
    public int return_x(MouseEvent me){
        return me.getX();
    }
    public int return_y(MouseEvent me){
        return me.getY();
    }
    public void mousePressed(MouseEvent me) {

        System.out.println("Press mouse");
        switch (gp.state){
            case "credits":
                credits.mousePress(me);
                break;
            case "menu":
                menu.mousePress(me);
                break;
            case "guide":
                guide.mousePress(me);
        }
    }

    public void mouseReleased(MouseEvent me) {

        if(gp.state=="menu")
            menu.mousePress = false;
        if (gp.state=="credits")
            credits.mousePress=false;
        if (gp.state=="guide")
            guide.mousePress=false;
    }

    public void mouseEntered(MouseEvent me) {
    }
    public void mouseExited(MouseEvent me) {
    }


}
