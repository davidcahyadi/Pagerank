/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagerankGUI;

import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author User
 */
public class Animation implements Runnable{
    Display display;
    Thread t;
    ArrayList<HashMap<String, Double>> result = new ArrayList<>();
    double ratio;
    
    public Animation(Display display, ArrayList<HashMap<String, Double>> res, double ratio){
        this.display = display;
        this.result = res;
        this.ratio = ratio;
        System.out.println("Ratio: " + ratio);
    }
    
    @Override
    public void run() {
        for(int i =3; i>=0; i--){
            try {
                Thread.sleep(100);
                for(Component item : display.getComponents()){
                    if(item instanceof Node){
                        ((Node) item).updateRadius((int) (result.get(i).get(((Node)item).getS())*20/ratio)*3); 
                    }
                }
                display.revalidate();
                display.repaint();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
