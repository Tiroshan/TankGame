/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IOHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.smartcardio.CommandAPDU;
import sun.misc.Queue;

/**
 *
 * @author Tiroshan
 */
public class Writer {
    private Socket server=null;
    private PrintWriter writer;
    private String IP;
    private int port;
    
    public  void SetIP(String ip){
        IP=ip;
        System.out.println(IP);
    }
    public void SetPort(int WritePort){
        port=WritePort;
        System.out.println(port);
    }
   
    public void Wconnect() throws UnknownHostException, IOException{
        server=new Socket(IP, port);
        writer = new PrintWriter(server.getOutputStream(),true);
        writer.write("JOIN#");
        writer.flush();
        server.close();
        writer.close();
    }
    public void TurnRight() throws UnknownHostException, IOException{
        server=new Socket(IP, port);
        writer = new PrintWriter(server.getOutputStream(),true);
        writer.write("RIGHT#");
        writer.flush();
        server.close();
        writer.close();
    }
    
    public void TurnLeft() throws UnknownHostException, IOException{
        server=new Socket(IP, port);
        writer = new PrintWriter(server.getOutputStream(),true);
        writer.write("LEFT#");
        writer.flush();
        server.close();
        writer.close();
    }
    public void MoveDown() throws UnknownHostException, IOException{
        server=new Socket(IP, port);
        writer = new PrintWriter(server.getOutputStream(),true);
        writer.write("DOWN#");
        writer.flush();
        server.close();
        writer.close();
    }
    
    public void MoveUp() throws UnknownHostException, IOException{
        server=new Socket(IP, port);
        writer = new PrintWriter(server.getOutputStream(),true);
        writer.write("UP#");
        writer.flush();
        server.close();
        writer.close();
    }
    
    public void Shoot() throws UnknownHostException, IOException{
        server=new Socket(IP, port);
        writer = new PrintWriter(server.getOutputStream(),true);
        writer.write("SHOOT#");
        writer.flush();
        server.close();
        writer.close();
    }
    
}
