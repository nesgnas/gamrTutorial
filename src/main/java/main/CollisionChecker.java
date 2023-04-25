package main;

import entity.Entity;

public class CollisionChecker { // call in gamePanel.class

    gamePanel gp;
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
                System.out.println("case UP ;tileNum1 = "+tileNum1+"tileNum2 ="+ tileNum2);
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
                System.out.println("case Down ;tileNum1 = "+tileNum1+"tileNum2 ="+ tileNum2);
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
                System.out.println("case Left ;tileNum1 = "+tileNum1+"tileNum2 ="+ tileNum2);
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
                System.out.println("case Right ;tileNum1 = "+tileNum1+"tileNum2 ="+ tileNum2);
                if (gp.tileManage.tiles[tileNum1].collision == true || gp.tileManage.tiles[tileNum2].collision == true){
                    entity.setCollisionOn(true);
                }
                break;

        }

    }
}
