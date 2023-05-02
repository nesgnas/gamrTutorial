package main;

import entity.Entity;
import entity.Player;
import object.Box;

import static object.Box.boxes;
import static object.Box.boxesCopy;


public class CollisionChecker { // call in gamePanel.class

    gamePanel gp;
    Player player;
    int arr[] ;
    public CollisionChecker(gamePanel gp){
        this.gp = gp;
        arr = gp.getArr();

    }




    public void checkTiles(Entity entity){ // dong khung gioi han coline cua player
        int entityLeftWorldX = entity.getX() + entity.getSolidArea().x;
        int entityRightWorldX =entity.getX() + entity.getSolidArea().x + entity.getSolidArea().width;
        int entityTopWorldY = entity.getY() + entity.getSolidArea().y;
        int entityBottomWorldY = entity.getY() + entity.getSolidArea().y + entity.getSolidArea().height;

        int entityLeftCol = entityLeftWorldX/gp.getTitleSize();
        int entityRightCol = entityRightWorldX/gp.getTitleSize();

        int entityTopRow = entityTopWorldY/gp.getTitleSize();
        int entityBottomRow= entityBottomWorldY/gp.getTitleSize();

        int tileNum1=0 , tileNum2 = 0;

        switch (entity.getDirection()){
            case "up":
                entityTopRow = (entityTopWorldY - entity.getSpeed())/gp.getTitleSize();
                tileNum1 = gp.tileManage.mapTileNum[entityLeftCol][entityTopRow];
                for(int i = 0;i<gp.getCountOfArr();i++){
                    if (tileNum1==arr[i]){
                        tileNum1=i;
                        break;
                    }
                }

                tileNum2 = gp.tileManage.mapTileNum[entityRightCol][entityTopRow];
                for(int i = 0;i<gp.getCountOfArr();i++){
                    if (tileNum2==arr[i]){
                        tileNum2=i;
                        break;
                    }
                }
                //System.out.println("case UP ;tileNum1 = "+tileNum1+"tileNum2 ="+ tileNum2);
                if (gp.tileManage.tiles[tileNum1].collision == true || gp.tileManage.tiles[tileNum2].collision == true){
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.getSpeed())/gp.getTitleSize();
                tileNum1 = gp.tileManage.mapTileNum[entityLeftCol][entityBottomRow];
                for(int i = 0;i<gp.getCountOfArr();i++){
                    if (tileNum1==arr[i]){
                        tileNum1=i;
                        break;
                    }
                }
                tileNum2 = gp.tileManage.mapTileNum[entityRightCol][entityBottomRow];
                for(int i = 0;i<gp.getCountOfArr();i++){
                    if (tileNum2==arr[i]){
                        tileNum2=i;
                        break;
                    }
                }
                //System.out.println("case Down ;tileNum1 = "+tileNum1+"tileNum2 ="+ tileNum2);
                if (gp.tileManage.tiles[tileNum1].collision == true || gp.tileManage.tiles[tileNum2].collision == true){
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.getSpeed())/gp.getTitleSize();
                tileNum1 = gp.tileManage.mapTileNum[entityLeftCol][entityTopRow];
                for(int i = 0;i<gp.getCountOfArr();i++){
                    if (tileNum1==arr[i]){
                        tileNum1=i;
                        break;
                    }
                }
                tileNum2 = gp.tileManage.mapTileNum[entityLeftCol][entityTopRow];
                for(int i = 0;i<gp.getCountOfArr();i++){
                    if (tileNum2==arr[i]){
                        tileNum2=i;
                        break;
                    }
                }
                //System.out.println("case Left ;tileNum1 = "+tileNum1+"tileNum2 ="+ tileNum2);
                if (gp.tileManage.tiles[tileNum1].collision == true || gp.tileManage.tiles[tileNum2].collision == true){
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.getSpeed())/gp.getTitleSize();
                tileNum1 = gp.tileManage.mapTileNum[entityRightCol][entityTopRow];
                for(int i = 0;i<gp.getCountOfArr();i++){
                    if (tileNum1==arr[i]){
                        tileNum1=i;
                        break;
                    }
                }
                tileNum2 = gp.tileManage.mapTileNum[entityRightCol][entityBottomRow];
                for(int i = 0;i<gp.getCountOfArr();i++){
                    if (tileNum2==arr[i]){
                        tileNum2=i;
                        break;
                    }
                }
                //System.out.println("case Right ;tileNum1 = "+tileNum1+"tileNum2 ="+ tileNum2);
                if (gp.tileManage.tiles[tileNum1].collision == true || gp.tileManage.tiles[tileNum2].collision == true){
                    entity.setCollisionOn(true);
                }
                break;

        }

    }
    public int checkObj(Entity entity, Boolean playerCondition ){
        int index = 999;
        for(Box box : boxesCopy){
            if (box.getRoom()==player.getRoomPlayerIn()){
                entity.solidArea.x = entity.getX() + entity.getSolidArea().x;
                entity.solidArea.y = entity.getY() + entity.getSolidArea().y;

                box.solidArea.x= box.getPosX() +box.solidArea.x;
                box.solidArea.y= box.getPosY() +box.solidArea.y;

                switch (entity.getDirection()){
                    case "up":
                        entity.solidArea.y -= entity.getSpeed();
                        if (entity.solidArea.intersects(box.solidArea)){
                            if(box.isCollision() ==true){
                                box.setPosY(box.getPosY()-entity.getSpeed());
                                entity.setCollisionOn(true);
                            }


                            System.out.println("up collision");
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.getSpeed();
                        if (entity.solidArea.intersects(box.solidArea)){
                            if(box.isCollision() ==true){
                                box.setPosY(box.getPosY()+entity.getSpeed());
                                entity.setCollisionOn(true);
                            }
                            System.out.println("down collision");
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.getSpeed();
                        if (entity.solidArea.intersects(box.solidArea)){
                            if(box.isCollision() ==true){
                                box.setPosX(box.getPosX()-entity.getSpeed());
                                entity.setCollisionOn(true);
                            }
                            System.out.println("left collision");
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.getSpeed();
                        if (entity.solidArea.intersects(box.solidArea)){
                            if(box.isCollision() ==true){
                                box.setPosX(box.getPosX()+entity.getSpeed());
                                entity.setCollisionOn(true);
                            }
                            System.out.println("right collision");
                        }
                        break;

                }
                entity.solidArea.x=entity.getSolidAreaDefaultX();
                entity.solidArea.y=entity.getSolidAreaDefaultY();
                box.solidArea.x = box.getSolidAreaDefaultPosX();
                box.solidArea.y = box.getSolidAreaDefaultPosY();

            }

        }

        return index;
    }
}
