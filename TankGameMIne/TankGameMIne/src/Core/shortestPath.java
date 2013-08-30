/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import IOHandler.Writer;
import Interface.CoinPack;
import java.io.IOException;
import java.net.UnknownHostException;
import sun.misc.Queue;

/**
 *
 * @author Tyron Madushan
 */
public class shortestPath {
    
    private Queue coinsQueue;
    private CoinPack tempCoin;
    private String [][] map;
    private int ourPlayerX;
    private int ourPlayerY;
    private Thread t;
    private Writer write;
    private boolean action;
    private boolean coinStatus;
    private int direction;
    
//    public shortestPath(String[][] tempmap){  
//        coinsQueue=new Queue();
//        map=tempmap;
//    }
    
    public shortestPath(Writer tempWriter){  
        coinsQueue=new Queue();
        map=new String[20][20];
        write=tempWriter;
        action=true;
        coinStatus= false;
    }
    
    public void setCoinMap(CoinPack coin){
        coinsQueue.enqueue(coin);
    }
    
    public void updateMap(String[][] UpdateMap){
        map=UpdateMap;
        coinStatus= true;
    }
    
    public void SetOurPlayerCordinates(int oX,int oY,int dir){
        ourPlayerX=oX;
        ourPlayerY=oY;
        direction=dir;
    }
    
    public boolean IsThinking(){
        return action;
    }
    
   
}

