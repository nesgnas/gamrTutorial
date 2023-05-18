package Tiles;

import entity.Player;
import main.gamePanel;
import object.Box;
import object.Gate;

import javax.imageio.ImageIO;
import javax.management.loading.PrivateMLet;
import java.awt.*;
import java.io.*;
import java.util.Collections;

import static object.Box.boxes;
import static object.Box.boxesCopy;
import static object.Gate.gates;
//import static object.Gate.gatesCopy;

public class TileManage {

    gamePanel gp;
    Player player;
    public Tile[] tiles;

    public static int mapTileNum[][];  // declare limit of map

    public TileManage(gamePanel gp) {
        this.gp = gp;
        tiles = new Tile[1000000];

        mapTileNum = new int[gp.getMaxWorldCol()][gp.getMaxWorldRow()]; //use to load map

        loadMap("data/map/13May.txt"); //load map
        checkRoom();
        manager();
        bom_pos();
        //prt();
        System.out.println("doneHERE");
        takeArrColline("data/logic/col100x100.txt");


        getTileImage(); // read element per tile

        // MAKE ARRAY HOLD TILE CAN BE ACCESS BY MANY CLASS ( GP IS THE CLASS MUST BE CALL IN ALMOST CLASS)
        gp.setArr(arr);
        gp.setCountOfArr(count);

    }

    // ________________DECLARE SOME VAL USING BELOW____________

    // ARRAY USE TO STORE ALL TILE
    public int count = 0;
    public static int numbox = 0;
    public static int row1 = 0;
    public static int in[] = new int [1000];
    public static int gatenum = 0;
    public int arr[] = new int[10000000];
    // USE TO OWN THE TILE WITHOUT COLLINE VAL
    public int colArr[] = new int[1000];
    public static int bom[][] = new int[10000][10000]; //store the value of bom
    public static int g[][] = new int[10000][10000]; // store the value of gate
    int countCol = 0;
    //
    int onlyOne = 0;
    int countManager = 0;

    public void manager() { // find box for room and format it
        boolean flagControl = false;

        for (Box box : boxes) {
            for (int i = 0; i <= countDownPos; i++) {
//               System.out.println("posX ="+box.getPosX()+" posY ="+box.getPosY());
//               System.out.println("LimitUpX ="+findRoomUp[i][1]+" LimitDownX ="+findRoomDown[i][1]);
//               System.out.println("LimitUpY ="+findRoomUp[i][2]+" LimitDownY ="+findRoomDown[i][2]);
                if ((box.getPosX() >= findRoomUp[i][1] && box.getPosX() <= findRoomDown[i][1])
                        && (box.getPosY() >= findRoomUp[i][2] && box.getPosY() <= findRoomDown[i][2])) {
//                   System.out.println("In Room "+i);
                    box.setRoom(i);
                    break;
                }
            }
        }
        Collections.sort(boxes);

        for (Box box : boxes) {
            boxesCopy.add(new Box(box));
        }
//        for (Gate gate : gates){
//            gatesCopy.add(new Gate(gate));
//        }

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


    public static int countUpPos = -1;
    public static int countDownPos = -1;

    public void checkRoom() {
        for (int row = 0; row < 100; row++) {
            for (int col = 0; col < 100; col++) {
//                if ((mapTileNum[col][row] == 118) && (mapTileNum[col][row - 1] == 114) && (
//                        mapTileNum[col - 1][row - 1] == 115) && (mapTileNum[col - 1][row] == 117)) {
//                    countUpPos++;
//                    findRoomUp[countUpPos][1] = col;
//                    findRoomUp[countUpPos][2] = row;
//                }
//                if ((mapTileNum[col][row] == 118) && (mapTileNum[col + 1][row] == 116) && (
//                        mapTileNum[col + 1][row + 1] == 108) && (mapTileNum[col][row + 1] == 112)) {
//                    countDownPos++;
//                    findRoomDown[countDownPos][1] = col;
//                    findRoomDown[countDownPos][2] = row;
//                }
                if (((mapTileNum[col][row] == 118) || (mapTileNum[col][row] == 125) || (mapTileNum[col][row] == 127)) && (mapTileNum[col][row - 1] == 114) && (
                        mapTileNum[col - 1][row - 1] == 115) && (mapTileNum[col - 1][row] == 117)) {
                    countUpPos++;
                    findRoomUp[countUpPos][1] = col;
                    findRoomUp[countUpPos][2] = row;
                }
                if ((mapTileNum[col][row] == 118 || (mapTileNum[col][row] == 125) || (mapTileNum[col][row] == 127)) && (mapTileNum[col + 1][row] == 116) && (
                        mapTileNum[col + 1][row + 1] == 108) && (mapTileNum[col][row + 1] == 112)) {
                    countDownPos++;
                    findRoomDown[countDownPos][1] = col;
                    findRoomDown[countDownPos][2] = row;
                }
            }

        }
        System.out.println("UP");
        for (int i = 0; i <= countUpPos; i++) {
            System.out.println(findRoomUp[i][1] + "__" + findRoomUp[i][2]);
        }
        System.out.println("DOWN");
        for (int i = 0; i <= countDownPos; i++) {
            System.out.println(findRoomDown[i][1] + "__" + findRoomDown[i][2]);
        }
    }

    public static int[][] findRoomUp = new int[1000][1000];

    public static int[][] findRoomDown = new int[1000][1000];


    public void takeArrColline(String filePath) { // use to take arr hold without colline val
        try {

            File file = new File(filePath);
            InputStream is = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = br.readLine()) != null) {
//                System.out.println("PRINT HERE");
//                System.out.println(line);
                String hold = line;
                while (!hold.equals("")) {
                    String temp[] = hold.split(" ");
                    hold = "";
                    for (String w : temp) {
                        int num = Integer.parseInt(w);
                        colArr[countCol] = num;
//                        System.out.println("num COl is "+colArr[countCol]);
                        countCol++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    public void loadMap(String filePath) { // done IT WORKs
        try {
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
            while (col < gp.getMaxWorldCol() && row < gp.getMaxWorldRow()) {
                String line = br.readLine();
                while (col < gp.getMaxWorldCol()) {
                    String number[] = line.split(" ");
                    int num = Integer.parseInt(number[col]);

                    if (onlyOne != 1) { // check unique state in arr
                        arr[count] = num;
                        onlyOne = 1;
                        count++;
                    }
                    // add box into List
                    if (num == 124) {
                        Box box = new Box();
                        //System.out.println("ADD");
                        box.setPosX(col);
                        box.setPosY(row);
                        box.setName("Box");
                        box.setImage(
                                ImageIO.read(new File("data/tiles/tile3rd/124.png"))
                        );
                        boxes.add(box);
                        in[numbox++] = 0;
                        box.setCollision(true);
                        num = 118;
                    }
                    if (num == 128) {
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
                    if (num == 125 && col != 0 && row!=0) {
                        bom[0][row1] = col;
                        bom[1][row1] = row;
                        bom[2][row1] = 0;
                        bom[4][row1++] = -1;
                    }
                    mapTileNum[col][row] = num;

                    for (int i = 0; i < count; i++) {
                        if (arr[i] != num) {
                            flag = true;
                            temp = num;
                        } else {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        arr[count] = temp;
                        count++;
                        flag = false;
                    }
                    col++;
                }
                if (col == gp.getMaxWorldCol()) {
                    col = 0;
                    row++;
                }
            }
            // check array
            for (int i = 1; i < count; i++) {
                if (arr[i] == 124) {
                    System.out.println("HERE");
                }
                if (arr[i] == 118) {
                    System.out.println("THERE");
                }
                System.out.println("arr[" + i + "]=" + arr[i]);
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
            arr[count++] = 129;
            arr[count++] = 149;
            br.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
    } // use to load map

    public void getTileImage() { // place to add element
        try {
            String hold;
            Boolean sw = false;
            for (int i = 0; i < count; i++) {
                hold = String.valueOf(arr[i]);
                sw = true;
                for (int j = 0; j < count; j++) { // find which one is not own colline value
                    if (colArr[j] == arr[i]) {
                        sw = false;
                        break;
                    }
                }
                //declare-type and set image for each tile
                tiles[i] = new Tile();
                tiles[i].image = ImageIO.read(new File("data/tiles/tile3rd/" + hold + ".png"));

                // SET COLLINE
                if (sw) {
                    tiles[i].collision = true;
//                    System.out.println("value has colline");
//                    System.out.println("tiles["+i+"]="+hold);
                } else {

//                    System.out.println("value has no colline");
//                    System.out.println("tiles["+i+"]="+hold);
                }

            }
//            System.out.println("DONE");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void check(){
        int nub = -1;
        for (Box box: boxesCopy){
            nub++;
            boolean chen = false;
            if (box.getRoom() == Player.getRoomPlayerIn()) {
                int x = box.getPosX() / gp.getTitleSize();
                int y = box.getPosY() / gp.getTitleSize();
                for (int i = 0; i < row1; i++)
                    if (bom[3][i] == Player.getRoomPlayerIn() && x == bom[0][i] && y == bom[1][i]) {
                        chen = true;
                        bom[2][i] = 1;
                        bom[4][i] = nub;
                        for (int j = 0; j < row1; j++)
                            if ((bom[4][j] == nub) && (i != j) && bom[3][j] == Player.getRoomPlayerIn()) {
                                bom[2][j] = 0;
                                bom[4][j] = -1;
                                //System.out.println("True " + bom[0][j] + " " + bom[1][j] + " " + bom[2][j] + " " + bom[3][j] + " " + bom[4][j]);
                            }
                    }
                if (!chen)
                    for (int i = 0; i< row1;i++){
                        if (bom[4][i] == nub && bom[3][i] == Player.getRoomPlayerIn()){
                            bom[2][i]=0;
                            bom[4][i]=-1;
                            //System.out.println("True " + bom[0][i] + " " + bom[1][i] + " " + bom[2][i] + " " + bom[3][i] + " " + bom[4][i]);
                        }
                    }
            }

        }
    }
    public void draw(Graphics2D g2) { // draw map

        int worldCol = 0;
        int worldRow = 0;
        int tileNum = 0;
        int nub = -1;
        //check();
        for (Box box: boxesCopy){
            nub++;
            if (box.getRoom() == Player.getRoomPlayerIn()) {
                boolean chen = false;
                int x = box.getPosX() / gp.getTitleSize();
                int y = box.getPosY() / gp.getTitleSize();
                for (int i = 0; i < row1; i++) {
                    if (bom[3][i] == Player.getRoomPlayerIn() && x == bom[0][i] && y == bom[1][i]) {
                        chen = true;
                        bom[2][i] = 1;
                        bom[4][i] = nub;
                        for (int j = 0; j < row1; j++)
                            if ((bom[4][j] == nub) && (i != j) && (bom[2][j] == 1) && bom[3][j] == Player.getRoomPlayerIn()) {
                                bom[2][j] = 0;
                                bom[4][j] = -1;
                                //System.out.println("True " + bom[0][j] + " " + bom[1][j] + " " + bom[2][j] + " " + bom[3][j] + " " + bom[4][j]);
                            }
                    }
                }
                if (!chen) {
                    for (int j = 0; j < row1; j++)
                        if ((bom[4][j] == nub) && (bom[2][j] == 1) && bom[3][j] == Player.getRoomPlayerIn()) {
                            bom[2][j] = 0;
                            bom[4][j] = -1;
                        }
                    }
                }
            }
//        for (int i = 0; i< row1;i++) {
//            if (bom[3][i] == Player.getRoomPlayerIn()){// && bom[0][i] !=0 && bom[1][i]!=0) {
//                int nub = -1;
//                for (Box box : boxesCopy) {
//                    nub++;
//                    int x = box.getPosX()/ gp.getTitleSize();
//                    int y = box.getPosY()/ gp.getTitleSize();
//                    if (x == bom[0][i] && y == bom[1][i]) {
//                        bom[2][i] = 1;
//                        bom[4][i] = nub;
//                        for (int j = 0; j < row1; j++)
//                            if ((bom[4][j] == nub) && (i!=j) && (bom[2][j] == 1) && bom[3][j]==Player.getRoomPlayerIn()){
//                                bom[2][j] = 0;
//                                bom[4][j] = -1;
//                                //System.out.println("True " + bom[0][j] + " " + bom[1][j] + " " + bom[2][j] + " " + bom[3][j] + " " + bom[4][j]);
//                            }
//                    }
//                }
//            }
//        }
        while (worldCol < gp.getMaxWorldCol() && worldRow < gp.getMaxWorldRow()){
            for (int i = 0; i < count ; i++){
                if (mapTileNum[worldCol][worldRow] == 128 && complete(Player.getRoomPlayerIn()) && gate_pos(worldCol, worldRow) == Player.getRoomPlayerIn()){
                    for (int j = 0; j < count; j++)
                        if (arr[j] == 129) {
                            mapTileNum[worldCol][worldRow] = 129;
                            tileNum = j;

                            gp.soundE(3);

                            break;
                        }
                }
                else
                if (mapTileNum[worldCol][worldRow] == 128 && gate_pos(worldCol, worldRow) != Player.getRoomPlayerIn() && !complete(Player.getRoomPlayerIn())){
                    for (int j = 0; j < count; j++)
                        if (arr[j] == 129) {
                            mapTileNum[worldCol][worldRow] = 129;
                            tileNum = j;

                            gp.soundE(3);

                            break;
                        }
                }
                else
                if (mapTileNum[worldCol][worldRow] == 129 && !complete(Player.getRoomPlayerIn()) && gate_pos(worldCol, worldRow) == Player.getRoomPlayerIn()){
                    for (int j = 0; j < count; j++)
                        if (arr[j] == 128) {
                            mapTileNum[worldCol][worldRow] = 128;
                            tileNum = j;

                            gp.soundE(3);

                            break;
                        }
                }
                else
                if (arr[i] == mapTileNum[worldCol][worldRow]) {
                    tileNum = i;

                    break;
                }
            }

//        while (worldCol < gp.getMaxWorldCol() && worldRow < gp.getMaxWorldRow()){
//            for (int i = 0; i < count ; i++){
//                boolean K = false;
//                if (mapTileNum[worldCol][worldRow] == 129 && !complete(Player.getRoomPlayerIn()) && gate_pos(worldCol,worldRow)==Player.getRoomPlayerIn()) {
//                    for (int j = 0; j < count; j++)
//                        if (arr[j] == 128) {
//                            mapTileNum[worldCol][worldRow] = 128;
//                            tileNum = j;
//                            break;
//                        }
//                }
//                else
//                    if (mapTileNum[worldCol][worldRow] == 128 && complete(Player.getRoomPlayerIn()) && gate_pos(worldCol,worldRow)==Player.getRoomPlayerIn()){
//                        for (int j = 0; j < count; j++)
//                            if (arr[j] == 129) {
//                                mapTileNum[worldCol][worldRow] = 129;
//                                tileNum = j;
//                                break;
//                            }
//                    }
//                    else
//                        if (arr[i] == mapTileNum[worldCol][worldRow]) {
//                            tileNum = i;
//                            break;}
//            }

            int worldX = worldCol * gp.getTitleSize();
            int worldY = worldRow * gp.getTitleSize();

            int screenX = worldX - gp.player.getX() + gp.player.screenX;
            int screenY = worldY - gp.player.getY() + gp.player.screenY;

            if (worldX + gp.getTitleSize() > gp.player.getX() - gp.player.screenX &&
                    worldX - gp.getTitleSize() < gp.player.getX() + gp.player.screenX &&
                    worldY + gp.getTitleSize() > gp.player.getY() - gp.player.screenY &&
                    worldY - gp.getTitleSize() < gp.player.getY() + gp.player.screenY) {
                g2.drawImage(tiles[tileNum].image, screenX, screenY, gp.getTitleSize(), gp.getTitleSize(), null);
            }
            worldCol++;

            if (worldCol == gp.getMaxWorldCol()) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
    public boolean complete(int numroom){
        boolean compl = true;
        for (int i = 0; i < row1; i++) {
            if (bom[3][i] == numroom && bom[2][i] == 0 && bom[0][i]!=0 && bom[1][i]!=0) {
                    compl = false;
                    break;
                }
        }
        return compl;
    }
    public int gate_pos(int col, int row) {
        int gate_check = 0;
        for (int j = 0; j <=countDownPos; j++) {
            if ((col >= findRoomUp[j][1] - 1 && col <= findRoomDown[j][1] + 1)
                    && (row >= findRoomUp[j][2] - 1 && row <= findRoomDown[j][2] + 1)) {
//                if( j == 0)
//                    for (int i = 0; i < row1; i++) {
//                        if (bom[3][i] == 0 )
//                            System.out.println(bom[0][i] + " " + bom[1][i] + " " + bom[2][i] + " " + bom[3][i]);
//                    }
                gate_check = j;
                break;
            }
        }
        return gate_check;
    }

    public void bom_pos(){
        for (int i = 0 ; i < row1 ; i++){
            for (int j =0 ; j<= countUpPos ; j++)
                if ((bom[0][i] >= findRoomUp[j][1] && bom[0][i] <= findRoomDown[j][1])
                        && (bom[1][i] >= findRoomUp[j][2] && bom[1][i] <= findRoomDown[j][2])) {
                    bom[3][i] = j;
                    break;
                }
        }
        for (int i=0;i< row1;i++){
            for (int j = i+1; j < row1;j++){
                if (bom[3][j]>bom[3][j+1]){
                    int temp = bom[3][j];
                    bom[3][j] = bom[3][j+1];
                    bom[3][j+1] = temp;

                    temp = bom[2][j];
                    bom[2][j] = bom[2][j+1];
                    bom[2][j+1] = temp;

                    temp = bom[1][j];
                    bom[1][j] = bom[1][j+1];
                    bom[1][j+1] = temp;

                    temp = bom[0][j];
                    bom[0][j] = bom[0][j+1];
                    bom[0][j+1] = temp;

                    temp = bom[4][j];
                    bom[4][j] = bom[4][j+1];
                    bom[4][j+1] = temp;
                }
            }
        }
    }
    public void prt() {
        System.out.println("Col - Row - flag - room");
        for (int j = 0; j < row1; j++) {
            if (bom[0][j] != 0 && bom[1][j] != 0)
                System.out.println(bom[0][j] + " " + bom[1][j] + " " + bom[2][j] + " " + bom[3][j]);
        }
    }
}

