package Tiles;

import main.gamePanel;
import object.Box;
import object.Gate;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

import static object.Box.boxes;

import static object.Box.boxesCopy;
import static object.Gate.gates;


public class TileManage {

    gamePanel gp;
    public Tile [] tiles;

    public static int mapTileNum[][];  // declare limit of map


    public TileManage(gamePanel gp) {
        this.gp = gp;
        tiles = new Tile[1000000];

        mapTileNum = new int[gp.getMaxWorldCol()][gp.getMaxWorldRow()]; //use to load map

        loadMap("data/map/Mapgrandfinal1.txt"); //load map
        checkRoom();
        manager();
        System.out.println("doneHERE");
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
    int countManager = 0 ;

    public void manager(){ // find box for room and format it
        boolean flagControl = false;

        for(Box box : boxes){
           for (int i=0;i<=countDownPos;i++){
//               System.out.println("posX ="+box.getPosX()+" posY ="+box.getPosY());
//               System.out.println("LimitUpX ="+findRoomUp[i][1]+" LimitDownX ="+findRoomDown[i][1]);
//               System.out.println("LimitUpY ="+findRoomUp[i][2]+" LimitDownY ="+findRoomDown[i][2]);
               if ((box.getPosX()>=findRoomUp[i][1] && box.getPosX()<=findRoomDown[i][1])
               && (box.getPosY()>=findRoomUp[i][2] && box.getPosY()<=findRoomDown[i][2])){
//                   System.out.println("In Room "+i);
                    box.setRoom(i);
                    break;

               }
           }
        }
        Collections.sort(boxes);

        for (Box box : boxes){
            boxesCopy.add(new Box(box));
        }
//        //Collections.copy(boxesCopy,boxesCopy);
//        for(Box box : boxes){
//            System.out.println(box.toString());
//        }
//        System.out.println("*****************8");
//        for (Box box : boxesCopy){
//            System.out.println(box.toString());
//        }
//        System.out.println("__________________");
//
//        System.out.println("__________________");
//        System.out.println("boxes ="+boxes.get(1).getPosX());
//        System.out.println("copy ="+boxesCopy.get(1).getPosX());
//        boxesCopy.get(1).setPosX(boxesCopy.get(1).getPosX()+1);
//        System.out.println("boxes ="+boxes.get(1).getPosX());
//        System.out.println("copy ="+boxesCopy.get(1).getPosX());
//        System.out.println("__________________");

    }



    public static int countUpPos =-1;
    public static int countDownPos = -1;

    public void checkRoom(){

        for (int row =0; row<100;row++){
            for (int col = 0;col<100;col++){
                if (((mapTileNum[col][row]==118)||(mapTileNum[col][row]==125))&&(mapTileNum[col][row-1]==114)&&(
                mapTileNum[col-1][row-1]==115)&&(mapTileNum[col-1][row]==117)){
                    countUpPos++;
                    findRoomUp[countUpPos][1]=col;
                    findRoomUp[countUpPos][2]=row;
                }
                if ((mapTileNum[col][row]==118)&&(mapTileNum[col+1][row]==116)&&(
                        mapTileNum[col+1][row+1]==108)&&(mapTileNum[col][row+1]==112)){
                    countDownPos++;
                    findRoomDown[countDownPos][1]=col;
                    findRoomDown[countDownPos][2]=row;
                }

            }

        }
//        System.out.println("tai diem 47,77 gtri la"+mapTileNum[44][77]);
//        System.out.println("tren la"+mapTileNum[43][77]);
//        System.out.println("cheo la"+mapTileNum[43][76]);
//        System.out.println("tai la "+mapTileNum[44][76]);
//        System.out.println("tai diem 77,77 gtri la"+mapTileNum[77][77]);
//        System.out.println("tren la"+mapTileNum[76][77]);
//        System.out.println("cheo la"+mapTileNum[76][76]);
//        System.out.println("tai la "+mapTileNum[77][76]);
//        System.out.println("UP");
        for (int i = 0; i<=countUpPos ; i++){
            System.out.println(findRoomUp[i][1]+"__"+findRoomUp[i][2]);
        }
        System.out.println("DOWN");
        for (int i = 0; i<=countDownPos ; i++){
            System.out.println(findRoomDown[i][1]+"__"+findRoomDown[i][2]);
        }
    }

    public static int [][] findRoomUp  =new int[1000][1000];

    public static int [][] findRoomDown  =new int[1000][1000];




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

            // call class Box
            // make simple arr store Gate

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

//                    System.out.println("map["+col+"]["+row+"]="+num);

                    // add box into List
                    if (num == 124){
                        Box box = new Box();
                        //System.out.println("ADD");
                        box.setPosX(col);
                        box.setPosY(row);
                        box.setName("Box");
                        box.setImage(
                                ImageIO.read(new File("data/tiles/tile3rd/124.png"))
                        );
                        boxes.add(box);
                        box.setCollision(true);
                        num = 118;
                    }
                    if (num == 128){
                        Gate gate = new Gate();
                        //System.out.println("ADD");
                        gate.setPosX(col);
                        gate.setPosY(row);
                        gate.setName("Gate");
                        gate.setImage(
                                ImageIO.read(new File("data/tiles/tile3rd/128.png"))
                        );
                        gates.add(gate);
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
                if (arr[i]==124){
                    System.out.println("HERE");
                }
                if (arr[i]==118){
                    System.out.println("THERE");
                }
                System.out.println("arr["+i+"]="+arr[i]);
            }
//            System.out.println(" Check Boxes");
//
//            for (Box box1 : boxes){
//                System.out.println(box1.toString());
//            }
//
//            System.out.println("check Gate");
//            for (Gate gate1 : gates){
//                System.out.println(gate1.toString());
//            }

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
                tiles[i].image = ImageIO.read(new File("data/tiles/tile3rd/"+hold+".png"));

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

