/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Tiroshan
 */
public class CoinPack extends Actor{
    private Image image;
    private int Time;
    private int value;
                

    public CoinPack(int x, int y) {
        super(x, y);
        
        ImageIcon iia = new ImageIcon("D:/PROJECTS/NET BEANS/TankGameMIneNew/TankGameMIne/src/Resourses/coinpack.png");
        image = iia.getImage();
        
        this.setImage(image);

    }
    
        public CoinPack(int x, int y,int val,int time ) {
        super(x, y);
        Time=time;
        value=val;
    }
        public void setTime(int ttime){
            Time =ttime;
        }
        
        public int Time(){
            return Time;
        }
        
        public int Value(){
            return value;
        }
        
        public int X(){
            return super.x();
        }
        
        public int Y(){
            return super.y();
        }
}
