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
    HashMap<String, Double> nodeRealSize = new HashMap<>();
    HashMap<String, Double> deltaRadius = new HashMap<>();
    double ratio;   
    
    public Animation(Display display, ArrayList<HashMap<String, Double>> res, double ratio){
        this.display = display;
        this.result = res;
        this.ratio = ratio;
        
        init(res.get(0));
    }
    
    
    private void init(HashMap<String, Double> res){
        res.forEach((key,val)->{
            nodeRealSize.put(key, 20d);
            deltaRadius.put(key, 0d);
        });
    }
    
    @Override
    public void run() {
        for(int i =3; i>=0; i--){
            try {
                
                result.get(i).forEach((String key,Double value)->{
                    final double newRadius = value*20/ratio;
                    final double currentRadius = nodeRealSize.get(key);
                    deltaRadius.replace(key, (newRadius-currentRadius)/50);
                });
                
                for(int j = 0; j < 50; j++){
                    for(Component item : display.getComponents()){
                        if(item instanceof Node){
                            nodeRealSize.replace(((Node) item).getS(), nodeRealSize.get(((Node) item).getS())+deltaRadius.get(((Node) item).getS()));
                            int radius =  (int) ((double)nodeRealSize.get(((Node) item).getS()));
                            ((Node) item).updateRadius(radius); 
                        }
                    }
                    display.revalidate();
                    display.repaint();
                    Thread.sleep(40);
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void start(){
        t = new Thread(this);
        t.start();
    }
    
}
