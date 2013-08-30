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
public class Player extends Actor{
    public Player(int x, int y, char pl, char dr) {
        super(x, y);
        String player = Character.toString(pl); 
        String dir = Character.toString(dr); 
        String pic=player+dir;
        System.out.println(pic);
        
        //URL loc = this.getClass().getResource("sokoban.png");
        String ImgSrc="D:/PROJECTS/NET BEANS/TankGameMIne/TankGameMIne/src/Resourses/"+pic+".png";
        ImageIcon iia = new ImageIcon(ImgSrc);
        //"C:\\Windows.old\\Users\\Tiroshan\\Documents\\NetBeansProjects\\TankGameMIne\\src\\Resourses\\player1.png"
        Image image = iia.getImage();
        this.setImage(image);
    }

    public void move(int x, int y) {
        int nx = this.x() + x;
        int ny = this.y() + y;
        this.setX(nx);
        this.setY(ny);
    }
}
