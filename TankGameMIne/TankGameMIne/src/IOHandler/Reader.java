/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IOHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tiroshan
 */
public class Reader implements Runnable{
    
     private ServerSocket Listner=null;
     private long timeout=(long) 1000.0;
     private Splitter decorder;
     private int ListenPort;
     
     public Reader(Splitter deco){
         decorder=deco;
     }
     public void SetListnerPort(int port){
         ListenPort=port;
         System.out.println(ListenPort);
     }
          
     public void RConnect() throws IOException, InterruptedException{
             Socket clientSocket = null;
             Listner = new ServerSocket(ListenPort);
             clientSocket=Listner.accept();
             
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             String message=in.readLine();
             System.out.println(message);
             
             if("PLAYERS_FULL#".equals(message)||"GAME_ALREADY_STARTED#".equals(message)){
                 System.out.println("PLAYERS_FULL or GAME_ALREADY_STARTED");
                 System.exit(0);
             }
             else if("ALREADY_ADDED#".equals(message)){
                 System.out.println("ALREADY_ADDED");
             }
             else if("OBSTACLE#".equals(message)){
                 System.out.println("OBSTACLE");
             }
             else if("CELL_OCCUPIED#".equals(message)){
                 System.out.println("CELL_OCCUPIED");
             }
             else if("DEAD#".equals(message)){
                 System.out.println("DEAD");
             }
             else if("TOO_QUICK#".equals(message)){
                 System.out.println("TOO_QUICK");
             }
             else if("INVALID_CELL#".equals(message)){
                 System.out.println("INVALID_CELL");
             }
             else if("GAME_HAS_FINISHED#".equals(message)){
                 System.out.println("GAME_HAS_FINISHED");
             }
             else if("GAME_NOT_STARTED_YET#".equals(message)){
                 System.out.println("GAME_NOT_STARTED_YET");
             }
             else if("NOT_A_VALID_CONTESTANT#".equals(message)){
                 System.out.println("NOT_A_VALID_CONTESTANT");
             }
             
             else{
                 decorder.setServerMsg(message);
             }
             in.close();
             Listner.close();
     }
     
    
    @Override
    public void run() {
        while(true){
            try {
                try {
                    RConnect();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 
}
