/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Interface.CoinPack;
import java.util.*;

/**
 *
 * @author Tyron Madushan
 */
public class AI{
    
    private CoinPack coin1;
    private int dir;
    private Node startNode,nextNode,n,n1,n2;
    private boolean[][] bricks,stones,water;
    private int pl_x,pl_y,c1,c2,gap1,gap2,target_x,target_y;
    private Queue<CoinPack> coinQueue;
    private ArrayList<Node> openList;
    private Queue<Node> closedQueue;
    private Queue<String> direction;
    int[][] F=new int[20][20];
    int[][] distance=new int[20][20];
    int[][] G=new int[20][20];
    int[][] H=new int[20][20];
    int least_f_x = 0, least_f_y = 0;
    private  int current_X;
     private  int current_Y;
 
    public AI(){
        openList=new ArrayList<Node>();
        closedQueue=new LinkedList<Node>();
        coinQueue=new LinkedList<CoinPack>();
        direction=new LinkedList<String>();
    }
 
    public void setCoinPile(CoinPack c){
        System.out.println(c.Time());
        coinQueue.add(c);
        System.out.println(coinQueue.element().Time());
        //this.goToCoinPile();
    }

    public void SetOurPlayerCordinates(int i,int j,int d){
        pl_x=i;
        pl_y=j;
        dir=d;
    }
    
    public void setBarriers(boolean[][] b,boolean[][] s,boolean[][] w){
        bricks=new boolean[20][20];
        bricks=b;
        stones=new boolean[20][20];
        stones=s;
        water=new boolean[20][20];
        water=w;
        //this.goToCoinPile();
    }
 
    public void goToCoinPile() {
      
        int min= 1000;
        for(int i=0;i<20;i++){
                for(int j=0;j<20;j++){
                    G[i][j]=0;
                    H[i][j]=0;
                    F[i][j]=0;
                   // checked[i][j]=false;
                }
            }
   
            while(!(coinQueue.isEmpty())){
          
            coin1=(CoinPack) coinQueue.remove();
       
            c1=coin1.X();
            c2=coin1.Y();
            
            gap1= Math.abs(pl_x-c1);
            gap2= Math.abs(pl_y-c2);
            
            G[c1][c2]=gap1+gap2;
            
            if((gap1+gap2) < min){
                min=gap1+gap2;
                target_x=c1;
                target_y=c2;
            }
            }
     
        startNode=new Node(pl_x,pl_y);
        openList.add(startNode);
        System.out.println("Line 96 :"+openList.get(0).getX());
        direction=new LinkedList<String>();
        if(!(openList.isEmpty())){
           
            Node removedNode = openList.remove(0);
            recursive_func(removedNode);
     }
   
        
    }
    
    private void recursive_func(Node n){
         
            int prev_node_x=n.getX();
            int prev_node_y=n.getY();
            System.out.println("recursive function");
            possibleNodes(n.getX(),n.getY());
                       
            n1=removeLeastF_Node();
            System.out.println(n1.getX()+", "+n1.getY());
            getDirection(prev_node_x,prev_node_y,n1.getX(),n1.getY(),dir);
            if(n1!=null){
                closedQueue.add(n1);
                if(!(n1.getX()==target_x)&&!(n1.getY()==target_y)){
                recursive_func(n1);
                }
            }
            
           
   }
    
    private Node removeLeastF_Node(){
        Collections.sort(openList);
         System.out.println("open size: "+openList.size());
        if(openList.size()>0){
           // System.out.println("open size: "+openList.size());
            Node n=openList.get(0);
            openList.remove(0);
            return n;
        }
        return null;
    }
    
    //add the available nodes to the open queue
    private void possibleNodes(int pl_x_new,int pl_y_new){
        current_X = pl_x_new;
        current_Y = pl_y_new;
        System.out.println("152");
        
            if((pl_y_new<19) && ((bricks[pl_x_new][pl_y_new+1]==false) && (stones[pl_x_new][pl_y_new+1]==false) && (water[pl_x_new][pl_y_new+1]==false))){
                // G[pl_x_new][pl_y_new+1]=G_value(pl_x_new,pl_y_new+1);
                System.out.println("164");
                 H[pl_x_new][pl_y_new+1]=H_value(pl_x_new,pl_y_new+1);
                 F[pl_x_new][pl_y_new+1]=G[pl_x_new][pl_y_new+1]+ H[pl_x_new][pl_y_new+1];
                 
                 nextNode=new Node(pl_x_new,pl_y_new+1);
                 nextNode.set_G_val(G_value(pl_x_new,pl_y_new+1)+G_value(current_X, current_Y));
                 nextNode.set_F_val(nextNode.getG_val()+ H_value(pl_x_new,pl_y_new+1));
                 
                 
              //   nextNode.set_F_val(F[pl_x_new][pl_y_new+1]);
                 if(!(closedQueue.contains(nextNode)) && !(openList.contains(nextNode))){
                    openList.add(nextNode);
                     System.out.println("AI 162");
                 }else if((openList.contains(nextNode))){
                     int fval=nextNode.getF_val();
                     int newFval = F[pl_x_new][pl_y_new+1];
                     if(fval > newFval){
                         nextNode.set_F_val(F[pl_x_new][pl_y_new+1]);
                     }
                     
                 }
            }
            
            if((pl_x_new<19) && (bricks[pl_x_new+1][pl_y_new]==false) && (stones[pl_x_new+1][pl_y_new]==false) && (water[pl_x_new+1][pl_y_new]==false)){
               //  G[pl_x_new+1][pl_y_new]=G_value(pl_x_new+1,pl_y_new);
                 H[pl_x_new+1][pl_y_new]=H_value(pl_x_new+1,pl_y_new);
                 F[pl_x_new+1][pl_y_new]=G[pl_x_new+1][pl_y_new]+ H[pl_x_new+1][pl_y_new];
                 
                  nextNode=new Node(pl_x_new+1,pl_y_new);
                 nextNode.set_G_val(G_value(pl_x_new+1,pl_y_new)+G_value(current_X, current_Y));
                 nextNode.set_F_val(nextNode.getG_val()+ H_value(pl_x_new+1,pl_y_new));
                 
                   if(!(closedQueue.contains(nextNode)) || !(openList.contains(nextNode))){
                    openList.add(nextNode);
                    // System.out.println("AI 162");
                 }else if((openList.contains(nextNode))){
                     int fval=nextNode.getF_val();
                     int newFval = F[pl_x_new+1][pl_y_new];
                     if(fval > newFval){
                         nextNode.set_F_val(F[pl_x_new+1][pl_y_new]);
                     }
                     
                 }
            }
            
            if((pl_x_new>0) && (bricks[pl_x_new-1][pl_y_new]==false) && (stones[pl_x_new-1][pl_y_new]==false) && (water[pl_x_new-1][pl_y_new]==false)){
               //  G[pl_x_new-1][pl_y_new]=G_value(pl_x_new-1,pl_y_new);
                 H[pl_x_new-1][pl_y_new]=H_value(pl_x_new-1,pl_y_new);
                 F[pl_x_new-1][pl_y_new]=G[pl_x_new-1][pl_y_new]+ H[pl_x_new-1][pl_y_new];
                 
                  nextNode=new Node(pl_x_new-1,pl_y_new);
                 nextNode.set_G_val(G_value(pl_x_new-1,pl_y_new)+G_value(current_X, current_Y));
                 nextNode.set_F_val(nextNode.getG_val()+ H_value(pl_x_new-1,pl_y_new));
                 
                   if(!(closedQueue.contains(nextNode)) || !(openList.contains(nextNode))){
                    openList.add(nextNode);
                    // System.out.println("AI 162");
                 }else if((openList.contains(nextNode))){
                     int fval=nextNode.getF_val();
                     int newFval = F[pl_x_new-1][pl_y_new];
                     if(fval > newFval){
                         nextNode.set_F_val(F[pl_x_new-1][pl_y_new]);
                     }
                     
                 }
            }
            
            if((pl_y_new>0) && (bricks[pl_x_new][pl_y_new-1]==false) && (stones[pl_x_new][pl_y_new-1]==false) && (water[pl_x_new][pl_y_new-1]==false)){
                // G[pl_x_new][pl_y_new-1]=G_value(pl_x_new,pl_y_new+1);
                 H[pl_x_new][pl_y_new-1]=H_value(pl_x_new,pl_y_new-1);
                 F[pl_x_new][pl_y_new-1]=G[pl_x_new][pl_y_new-1]+ H[pl_x_new][pl_y_new-1];
                 
                 nextNode=new Node(pl_x_new,pl_y_new-1);
                 nextNode.set_G_val(G_value(pl_x_new,pl_y_new-1)+G_value(current_X, current_Y));
                 nextNode.set_F_val(nextNode.getG_val()+ H_value(pl_x_new,pl_y_new-1));
                 
                   if(!(closedQueue.contains(nextNode)) || !(openList.contains(nextNode))){
                    openList.add(nextNode);
                    // System.out.println("AI 162");
                 }else if((openList.contains(nextNode))){
                     int fval=nextNode.getF_val();
                     int newFval = F[pl_x_new][pl_y_new-1];
                     if(fval > newFval){
                         nextNode.set_F_val(F[pl_x_new][pl_y_new-1]);
                     }
                     
                 }
            }
    }
    
    private int H_value(int m,int n){
        int d=Math.abs(target_x-m);
        int f=Math.abs(target_y-n);
        
        return d+f;
    }
    
    private int G_value(int m,int n){
        int value = 0;    
        if((m == current_X+1) || (m == current_X-1) || (n == current_Y-1) || (n==current_Y+1)){
                return 1;
            }
        return value;
 }

    private void getDirection(int pre_x,int pre_y,int l_f_x, int l_f_y,int direct) {
        if(pre_x==l_f_x){
            if(pre_y>l_f_y){
                
               direction.add("RIGHT");
             
            }else {
               direction.add("LEFT");  
            }
        }else if(pre_y==l_f_y){
            if(pre_x>l_f_x){
               direction.add("DOWN");
            }else{
               direction.add("UP");
            }
                
        }
    }
    
    //called by the writer to get direction

    public String getDirectionFromQueue(){
        if(!direction.isEmpty()){
            return direction.remove();
        }
        else return "NULL";
    }
            
}
