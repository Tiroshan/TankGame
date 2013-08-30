
package Interface;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author Tiroshan
 */
public final class GameBoard extends javax.swing.JPanel {

    //variables
    private int frmmesize=20;
    private final int OFFSET = 10;
    private final int SPACE = 30;
    
    //details
    private ArrayList brick = new ArrayList();
    private ArrayList stone = new ArrayList();
    private ArrayList water = new ArrayList();
    private ArrayList coins = new ArrayList();
    private ArrayList life = new ArrayList();
    private ArrayList players = new ArrayList();
    
    //players
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    private Player player5;
    
    private boolean setPlayer= false;
    private boolean setCoin= false;
    private boolean setLife= false;
    
    private int w = 0;
    private int h = 0;
    
    private String [][]map;
    
    
    
    
    public GameBoard(String [][] tempmap) {
        initComponents();
        map=tempmap;
        setFocusable(true);
        initializeWorld();
    }
    
    public void setmap(String [][] tempmap){
        map=tempmap;
    }
    public void initializeWorld(){
        int x = OFFSET;
        int y = OFFSET;
        char charactor;
        char player;
        
        Wall tempbrick;
        Stone tempstone;
        water tempwater;
        Player tempPlayer;
        CoinPack tempcoin;
        LifePack tempLife;
        
        for(int i=0;i<frmmesize;i++){
            for(int j=0;j<frmmesize;j++){
                charactor=map[i][j].charAt(0);
                
                if(charactor=='B'){
                    tempbrick=new Wall(x, y);
                    brick.add(tempbrick);
                    x+=SPACE; 
                }
                else if(charactor=='S'){
                    tempstone=new Stone(x, y);
                    stone.add(tempstone);
                    x+=SPACE;                    
                }
                else if(charactor=='W'){
                    tempwater=new water(x, y);
                    water.add(tempwater);
                    x+=SPACE;                    
                }
                else if(charactor=='P'){
//                    player1=new Player(x, y);
                    tempPlayer=new Player(x, y,map[i][j].charAt(1),map[i][j].charAt(2));
                    players.add(tempPlayer);
                    x+=SPACE; 
                    setPlayer=true;
                }
                else if(charactor=='C'){
                    tempcoin=new CoinPack(x, y);
                    coins.add(tempcoin);
                    x+=SPACE; 
                    setCoin=true;
                }
                else if(charactor=='L'){
                    tempLife=new LifePack(x, y);
                    life.add(tempLife);
                    x+=SPACE; 
                    setLife=true;
                }
                else if(charactor=='V'){
                    x+=SPACE;                    
                }
            }
            
            y+=SPACE;
            if (this.w < x) {
                    this.w = x;
                }

                x = OFFSET;
                
                 h = y;
        }
    }

    
        public void buildWorld(Graphics g) {
        
        initializeWorld();    
        g.setColor(new Color(250, 240, 170));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        ArrayList world = new ArrayList();
        world.addAll(brick);
        world.addAll(water);
        world.addAll(stone);
        if(setPlayer==true)
            world.addAll(players);
        if(setCoin==true)            
            world.addAll(coins);
        if(setLife==true)
            world.addAll(life);
            
        

        for (int i = 0; i < world.size(); i++) {

            Actor item = (Actor) world.get(i);

            if (item instanceof Actor) {
                g.drawImage(item.getImage(), item.x() + 2, item.y() + 2, this);
            } else {
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }
        }
        life.clear();
        coins.clear();
        players.clear();
        world.clear();
    }
        
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        buildWorld(g);
    }
    
    public int getBoardWidth() {
        return this.w;
    }

    public int getBoardHeight() {
        return this.h;
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 624, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 485, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
