/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Tiroshan
 */
public class LifePack extends Actor{
    private Image image;
    private int Time;
    
                

    public LifePack(int x, int y) {
        super(x, y);
        ImageIcon iia = new ImageIcon("D:/PROJECTS/NET BEANS/TankGameMIne/TankGameMIne/src/Resourses/lifepack.png");
        image = iia.getImage();
        this.setImage(image);
    }
    
        public LifePack(int x, int y,int time ) {
        super(x, y);
        Time=time;
    }
        public void setTime(int ttime){
            Time =ttime;
        }
        
        public int Time(){
            return Time;
        }
                
        public int X(){
            return super.x();
        }
        
        public int Y(){
            return super.y();
        }
}
