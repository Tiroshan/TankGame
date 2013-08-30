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
public class Wall extends Actor {
     private Image image;

    public Wall(int x, int y) {
        super(x, y);

       // URL loc = this.getClass().getResource("wall.png");
        ImageIcon iia = new ImageIcon("D:/PROJECTS/NET BEANS/TankGameMIne/TankGameMIne/src/Resourses/brick.png");
        image = iia.getImage();
        this.setImage(image);

    }
}
