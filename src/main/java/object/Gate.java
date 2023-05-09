package object;

import java.util.ArrayList;

public class Gate extends superObject{
    public Gate(){

    }
    public static ArrayList<Gate> gates = new ArrayList<>();
    public static ArrayList<Gate> gatesCopy =new ArrayList<Gate>(gates);

    public Gate(Gate gate) {
//        this.solidArea = gate.solidArea;
//        this.setPosX(gate.getPosX());
//        this.setCollision(gate.isCollision());
//        this.setPosY(gate.getPosY());
//        this.setName(gate.getName());
//        this.setImage(gate.getImage());
//        this.setSolidAreaDefaultPosX(gate.getSolidAreaDefaultPosX());
//        this.setSolidAreaDefaultPosY(gate.getSolidAreaDefaultPosY());
    }
}
