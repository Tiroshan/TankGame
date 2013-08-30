/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

/**
 *
 * @author Laksheen
 */
public class Node implements Comparable<Node> {
    
    private int x;
    private int y;
    private int g_val;
    private int f_val;
   
   public Node(int i,int j){
       x=i;
       y=j;    
   }     
   
   public Node(int i,int j,int k,int l){
       x=i;
       y=j;
       g_val=k;
       f_val=l;
   }
   
   public Node moveDown(){
       if(x<19){
           return new Node(x+1,y);
       }
       else
           return null;
   }
   
   public Node moveUp(){
       if(x>0){
           return new Node(x-1, y); 
       }
       else
           return null;
   }
 
   public Node moveRight(){
       if(y<19){
           return new Node(x, y+1); 
       }
       else 
          return null; 
   }    
   
   public Node moveLeft(){
       if(y>0){
           return new Node(x, y-1);
       }
       else 
           return null;
   }
   
   public int getX(){
       return x;
   }
   
   public int getY(){
       return y;
   }

    public boolean equals(Node node) {
        return (node.x == x) && (node.y == y);
    }

    @Override
    public int compareTo(Node otherNode) {

        if (this.f_val < otherNode.f_val) {
            return -1;
        } else if (this.f_val > otherNode.f_val) {
            return 1;
        } else {
            return 0;
        }
    }
   
   public void set_F_val(int f){
       f_val=f;
   }
   
   public void set_G_val(int f){
       g_val=f;
   }
   
   public int getF_val(){
       return f_val;
   }
   
    public int getG_val(){
       return g_val;
   }
}
