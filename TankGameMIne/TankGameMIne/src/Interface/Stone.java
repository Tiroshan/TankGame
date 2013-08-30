/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author Tiroshan
 */
public class Stone extends Actor{
    public Stone(int x, int y) {
        super(x, y);

        //URL loc = this.getClass().getResource("stone.png");
        ImageIcon iia = new ImageIcon("D:/PROJECTS/NET BEANS/TankGameMIne/TankGameMIne/src/Resourses/stone.png");
        //ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }
}
