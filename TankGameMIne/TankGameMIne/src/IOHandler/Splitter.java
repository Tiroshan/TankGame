/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IOHandler;

import Core.AI;
import Core.shortestPath;
import Interface.*;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tiroshan
 */
public class Splitter {

    private String servermsg;
    private String[] PlayerID;
    private String[][] map;
    private boolean[][] booleanCoinMap;
    private boolean[][] booleanWaterMap;
    private boolean[][] booleanBrickMap;
    private boolean[][] booleanStoneMap;
    private InterfaceUI GUI;
    private ArrayList coins = new ArrayList();
    private ArrayList life = new ArrayList();
    private int player = 0;
    String ourPlayer;
    private int[][] pointsTable;
    private AI core;
    private Writer write;

    public Splitter(Writer wr) {
        write = wr;
        PlayerID = new String[5];
    }

    public void SetMap(int x, int y) {
        map = new String[x][y];
        //booleanCoinMap = new boolean[x][y];
        booleanBrickMap = new boolean[x][y];
        booleanWaterMap = new boolean[x][y];
        booleanStoneMap = new boolean[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                map[i][j] = "V";
                booleanBrickMap[i][j]=false;
                booleanWaterMap[i][j]=false;
                booleanStoneMap[i][j]=false;
            }
        }
        core = new AI();
        
    }

    public void setServerMsg(String msg) throws UnknownHostException, IOException {
        servermsg = msg;
        msgDecoder();
    }

    public void msgDecoder() throws UnknownHostException, IOException {
        System.out.println("test");
        if (servermsg != null) {
            servermsg = servermsg.split("#")[0];
            System.out.println(servermsg);

            StringTokenizer stk = new StringTokenizer(servermsg, ":");
            String type = stk.nextToken();
            System.out.println(type);

            if ("G".equals(type)) {
                System.out.println("Global Update oncea second");
                setMap();
                Update(stk);
                GUI.updateMap(map);
                PrintMap();
                GUI.updateFrame();
                core.goToCoinPile();
                String move = core.getDirectionFromQueue();
                System.out.println(move);
                if ("LEFT".equals(move)) {
                    write.TurnLeft();
                } else if ("RIGHT".equals(move)) {
                    write.TurnRight();
                } else if ("UP".equals(move)) {
                    write.MoveUp();
                } else if ("DOWN".equals(move)) {
                    write.MoveDown();
                } else {
                    write.Shoot();
                    System.out.println("INVALID COMMAND");
                }

            } else if ("I".equals(type)) {
                System.out.println("Game Initiating at the start");
                generateMap(stk);
                GUI = new InterfaceUI(map);
                PrintMap();
                GUI.setPlayer((String) ourPlayer + "0");
//                this.setBooleanBrickMap();
//                //this.setBooleanCoinMap();
//                this.setBooleanStoneMap();
//                this.setBooleanWaterMap();

            } else if ("S".equals(type)) {
                System.out.println("Game is started");
                setPlayer(stk);
                PrintMap();
                GUI.updateFrame();
                core.setBarriers(booleanBrickMap, booleanStoneMap, booleanWaterMap);
                this.PrintBooleanBrickMap();
            } else if ("L".equals(type)) {
                System.out.println("Lifepack");
                setLifePack(stk);
                PrintMap();
                GUI.updateFrame();
            } else if ("C".equals(type)) {
                System.out.println("CoinPack");
                setCoinPack(stk);
                PrintMap();
                GUI.updateFrame();
                //write.MoveUp();
            }
        }
    }

    public void generateMap(StringTokenizer stk) {
        String ChrPlyr = stk.nextToken();
        ourPlayer = String.valueOf(ChrPlyr.charAt(1));
        System.out.println(ourPlayer);

        int selecttype = 1; //1-brick "B", 2-stone "S, 3-water "W"
        StringTokenizer stk1;
        int x;
        int y;

        while (stk.hasMoreTokens()) {
            if (selecttype == 1) {
                stk1 = new StringTokenizer(stk.nextToken(), ";");
                while (stk1.hasMoreTokens()) {
                    String s = stk1.nextToken();
                    String[] values = s.split(",");
                    y = Integer.parseInt(values[0]);
                    x = Integer.parseInt(values[1]);
                    map[x][y] = "B";
                    booleanBrickMap[x][y]=true;
                }
            }

            if (selecttype == 2) {
                stk1 = new StringTokenizer(stk.nextToken(), ";");
                while (stk1.hasMoreTokens()) {
                    String s = stk1.nextToken();
                    String[] values = s.split(",");
                    y = Integer.parseInt(values[0]);
                    x = Integer.parseInt(values[1]);
                    map[x][y] = "S";
                    booleanStoneMap[x][y]=true;
                }
            }

            if (selecttype == 3) {
                stk1 = new StringTokenizer(stk.nextToken(), ";");
                while (stk1.hasMoreTokens()) {
                    String s = stk1.nextToken();
                    String[] values = s.split(",");
                    y = Integer.parseInt(values[0]);
                    x = Integer.parseInt(values[1]);
                    map[x][y] = "W";
                    booleanWaterMap[x][y]=true;
                }
            }
            selecttype++;
        }
    }

    public void Update(StringTokenizer stk) {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (map[i][j].charAt(0) == 'P') {
                    map[i][j] = "V";
                }
            }
        }
        StringTokenizer stk1;
        String s, playerno, dir;
        String[] values;
        int pl = 0;
        int x;
        int y;
        int direcion, shot, health, points, coin;
        int tokencount = stk.countTokens();
        for (int i = 1; i < tokencount; i++) {
            stk1 = new StringTokenizer(stk.nextToken(), ";");
            playerno = stk1.nextToken();
            s = stk1.nextToken();
            values = s.split(",");
            y = Integer.parseInt(values[0]);
            x = Integer.parseInt(values[1]);
            direcion = Integer.parseInt(stk1.nextToken());
            shot = Integer.parseInt(stk1.nextToken());
            health = Integer.parseInt(stk1.nextToken());
            points = Integer.parseInt(stk1.nextToken());
            coin = Integer.parseInt(stk1.nextToken());
            map[x][y] = (String) (playerno + direcion);
            //0-health  1-points  2-coins
            pointsTable[0][pl] = health;
            pointsTable[1][pl] = points;
            pointsTable[2][pl] = coin;
            pl++;
        }

        int xx, yy, status;

        String brickDetails = stk.nextToken();
        StringTokenizer stk2 = new StringTokenizer(brickDetails, ";");
        while (stk2.hasMoreTokens()) {
            String sss = stk2.nextToken();
            String[] token = sss.split(",");
            yy = Integer.parseInt(token[0]);
            xx = Integer.parseInt(token[1]);
            status = Integer.parseInt(token[1]);
        }
        GUI.SetPointTable(pointsTable, pl);
    }

    public void setPlayer(StringTokenizer stk) {
        int x;
        int y;
        int direcion;
        int playercount = stk.countTokens();
        pointsTable = new int[3][playercount];

        while (stk.hasMoreTokens() && player < 5) {
            StringTokenizer stk2 = new StringTokenizer(stk.nextToken(), ";");
            PlayerID[player] = stk2.nextToken();
            String s = stk2.nextToken();
            String[] values = s.split(",");
            y = Integer.parseInt(values[0]);
            x = Integer.parseInt(values[1]);
            direcion = Integer.parseInt(stk2.nextToken());
            map[x][y] = (String) ("P" + player + direcion);

            System.out.println(PlayerID[player] + "x" + x + "y" + y + "dir" + direcion);

            if (Integer.parseInt(ourPlayer) == player) {
                core.SetOurPlayerCordinates(x, y, direcion);// Laksheen you can use your method that is used to et player X,Y and direction
            }
            player++;
        }
    }

    public void setLifePack(StringTokenizer stk) {
        int x;
        int y;
        int displaytime;

        String s = stk.nextToken();
        String[] values = s.split(",");
        y = Integer.parseInt(values[0]);
        x = Integer.parseInt(values[1]);
        displaytime = Integer.parseInt(stk.nextToken());


        map[x][y] = "L";
        LifePack templife = new LifePack(x, y, displaytime);
        life.add(templife);

        System.out.println(s + " time" + displaytime);
    }

    public void setCoinPack(StringTokenizer stk) {
        int x;
        int y;
        int displaytime;
        int Value;

        String s = stk.nextToken();
        String[] values = s.split(",");
        y = Integer.parseInt(values[0]);
        x = Integer.parseInt(values[1]);
        displaytime = Integer.parseInt(stk.nextToken());
        Value = Integer.parseInt(stk.nextToken());

        map[x][y] = "C";
        CoinPack tempcoin = new CoinPack(x, y, Value, displaytime);
        core.setCoinPile(tempcoin);

        coins.add(tempcoin);

        System.out.println(s + " time" + displaytime + "val" + Value);

    }

    public String[][] returnMap() {
        return map;
    }

    public void setMap() {
        System.out.println("setMap");
        int xx = 0;
        int yy = 0;

        for (int i = 0; i < coins.size(); i++) {
            CoinPack item = (CoinPack) coins.get(i);
            int temptime = item.Time();
            if (temptime > 0) {
                temptime -= 1000;
                item.setTime(temptime);
            } else {
                xx = item.X();
            }
            yy = item.Y();
            map[xx][yy] = "V";

        }

        int x = 0;
        int y = 0;
        for (int j = 0; j < life.size(); j++) {
            LifePack item = (LifePack) life.get(j);
            int temptime = item.Time();
            if (temptime > 0) {
                temptime -= 1000;
                item.setTime(temptime);
            } else {
                x = item.X();
            }
            y = item.Y();
            map[x][y] = "V";
        }
    }

    public void CountDown(int time) {
    }

    public void PrintMap() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.print("\n");
        }


    }

//    public void setBooleanCoinMap() {
//        for (int i = 0; i < map.length; i++) {
//            for (int j = 0; j < map.length; j++) {
//                if ("C".equals(map[i][j])) {
//                    booleanCoinMap[i][j] = true;
//                    //System.out.println(booleanCoinMap[i][j]);
//                } else {
//                    booleanCoinMap[i][j] = false;
//                }
//            }
//        }
//    }
//
//    public void setBooleanBrickMap() {
//        for (int i = 0; i < map.length; i++) {
//            for (int j = 0; j < map.length; j++) {
//                if ("B".equals(map[i][j])) {
//                    booleanBrickMap[j][i] = true;
//                    //System.out.println(booleanBrickMap[i][j]);
//                } else {
//                    booleanBrickMap[j][i] = false;
//                }
//            }
//        }
//    }
//
//    public void setBooleanStoneMap() {
//        for (int i = 0; i < map.length; i++) {
//            for (int j = 0; j < map.length; j++) {
//                if ("S".equals(map[i][j])) {
//                    booleanStoneMap[j][i] = true;
//                    // System.out.println(booleanStoneMap[i][j]);
//                } else {
//                    booleanStoneMap[j][i] = false;
//                }
//            }
//        }
//    }
//
//    public void setBooleanWaterMap() {
//        for (int i = 0; i < map.length; i++) {
//            for (int j = 0; j < map.length; j++) {
//                if ("W".equals(map[i][j])) {
//                    booleanWaterMap[j][i] = true;
//                    // System.out.println(booleanWaterMap[i][j]);
//                } else {
//                    booleanWaterMap[j][i] = false;
//                }
//            }
//        }
//    }
//
    public void PrintBooleanBrickMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {

                System.out.print(booleanBrickMap[i][j] + " ");

            }
            System.out.println("\n");
        }
    }
}
