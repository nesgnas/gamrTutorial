package Tiles;

import main.gamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManage {

    gamePanel gp;
    public Tile [] tiles;

    public int mapTileNum[][];  // declare limit of map


    public TileManage(gamePanel gp) {
        this.gp = gp;
        tiles = new Tile[1000000];

        mapTileNum = new int[gp.getMaxWorldCol()][gp.getMaxWorldRow()]; //use to load map

        loadMap("data/map/map100.txt"); //load map
        takeArrColline("data/logic/col100x100.txt");

        getTileImage(); // read element per tile

        // MAKE ARRAY HOLD TILE CAN BE ACCESS BY MANY CLASS ( GP IS THE CLASS MUST BE CALL IN ALMOST CLASS)
        gp.setArr(arr);
        gp.setCountOfArr(count);
    }

    // ________________DECLARE SOME VAL USING BELOW____________

    // ARRAY USE TO STORE ALL TILE
    public  int count = 0;
    public  int arr[] = new int[10000000];
    // USE TO OWN THE TILE WITHOUT COLLINE VAL
    public int colArr[]= new int[1000];
    int countCol =0;
    //
    int onlyOne=0;


    public void takeArrColline(String filePath){ // use to take arr hold without colline val
        try {

            File file = new File(filePath);
            InputStream is = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line  = br.readLine())!=null){
//                System.out.println("PRINT HERE");
//                System.out.println(line);
                String hold = line;
                while (!hold.equals("")){
                    String temp[] = hold.split(" ");
                    hold = "";
                    for (String w : temp){
                        int num = Integer.parseInt(w);
                        colArr[countCol] = num;
//                        System.out.println("num COl is "+colArr[countCol]);
                        countCol ++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void loadMap(String filePath){ // done IT WORKs
        try{
            File file = new File(filePath);
            InputStream is = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            // create array to store all value happen in map
            Boolean flag = false;
            int temp = 0;
            while (col < gp.getMaxWorldCol()&& row<gp.getMaxWorldRow()){
                String line =br.readLine();
                while (col < gp.getMaxWorldCol()){
                    String number[] = line.split(" ");
                    int num = Integer.parseInt(number[col]);

                    if (onlyOne!=1){ // check unique state in arr
                        arr[count]=num;
                        onlyOne=1;
                        count++;
                    }
                    mapTileNum[col][row]=num;

                    for (int i=0;i<count;i++){
                        if (arr[i] !=num){
                            flag =true;
                            temp=num;
                        }
                        else { flag = false;
                            break;
                        }
                    }
                    if (flag==true){
                        arr[count] = temp;
                        count++;
                        flag = false;
                    }
                    col++;
                }
                if (col == gp.getMaxWorldCol()){
                    col=0;
                    row++;
                }
            }
            // check array
            for (int i=1;i<count;i++){
                System.out.println(arr[i]+" ");
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
    } // use to load map

    public void getTileImage(){ // place to add element
        try {
            String hold;
            Boolean sw = false;
            for (int i=0;i<count;i++){
                hold = String.valueOf(arr[i]);
                sw = true;
                for (int j =0;j<count;j++){ // find which one is not own colline value
                    if (colArr[j]==arr[i]){
                        sw = false;
                        break;
                    }
                }
                //declare-type and set image for each tile
                tiles[i] = new Tile();
                tiles[i].image = ImageIO.read(new File("data/tiles/hold/"+hold+".png"));

                // SET COLLINE
                if (sw==true){
                    tiles[i].collision = true;
//                    System.out.println("value has colline");
//                    System.out.println("tiles["+i+"]="+hold);
                }else {

//                    System.out.println("value has no colline");
//                    System.out.println("tiles["+i+"]="+hold);
                }

            }
//            System.out.println("DONE");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) { // draw map

        int worldCol =0;
        int worldRow= 0;


        int tileNum = 0;

        while (worldCol<gp.getMaxWorldCol()&& worldRow<gp.getMaxWorldRow()){
            // FORMAT TILE-NUM BASE ON ARR
            for(int i=0;i<count; i++){
                if (arr[i]==mapTileNum[worldCol][worldRow]){
                     tileNum=i;
                     break;
                }
            }


            int worldX = worldCol *gp.getTitleSize();
            int worldY = worldRow *gp.getTitleSize();

            int screenX = worldX - gp.player.getX() +gp.player.screenX;
            int screenY = worldY - gp.player.getY() +gp.player.screenY;

            if (worldX + gp.getTitleSize() > gp.player.getX() - gp.player.screenX &&
                    worldX- gp.getTitleSize()< gp.player.getX() + gp.player.screenX &&
                    worldY+ gp.getTitleSize()> gp.player.getY() - gp.player.screenY &&
                    worldY- gp.getTitleSize()< gp.player.getY() + gp.player.screenY){
                g2.drawImage(tiles[tileNum].image,screenX,screenY,gp.getTitleSize(),gp.getTitleSize(),null);
            }


            worldCol++;


            if (worldCol == gp.getMaxWorldCol()){
                worldCol =0;

                worldRow++;

            }
        }

    }
}

