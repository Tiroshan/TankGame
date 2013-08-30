/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tankgame;

/**
 *
 * @author Tiroshan
 */

import Core.shortestPath;
import IOHandler.Reader;
import IOHandler.Splitter;
import IOHandler.Writer;
import Interface.Login;
import java.io.IOException;
import java.net.UnknownHostException;

public class TankGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, IOException {
        
        Writer writer=new Writer();
        Splitter deco =new Splitter(writer);
        Reader reader=new Reader(deco);
        Login loging=new Login(writer,reader,deco);
        loging.setVisible(true);
    }
}
