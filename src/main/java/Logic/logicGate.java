package Logic;

import static Tiles.TileManage.mapTileNum;

public class logicGate {

    // algorithm
    public int loang(int posX, int posY, int value, int count ){
        if (mapTileNum[posX+1][posY]==value){
             loang(posX+1,posY,value,count+1);
        }

        return count;
    }
}
