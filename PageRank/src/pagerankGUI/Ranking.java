/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pagerankGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.util.*;
import javax.swing.JPanel;

/**
 *
 * @author david
 */
public class Ranking extends JPanel{

    /**
     * @return the bars
     */
    public HashMap<String,Bar> getBars() {
        return bars;
    }

    /**
     * @param bars the bars to set
     */
    public void setBars(HashMap<String,Bar> bars) {
        this.bars = bars;
    }
    private HashMap<String,Bar>bars ;
    int ctr;
    int nodeCount = 0;
    public Ranking() {
        init();
        bars = new HashMap<>();
        ctr=10;
    }
    
    private void init(){
        Dimension d = new Dimension(250,450);
        this.setSize(d);
        this.setMaximumSize(d);
        this.setMinimumSize(d);
        this.setPreferredSize(d);
        this.setBackground(Color.WHITE);
        this.setBounds(0, 0, d.width, d.height);
    }
    
    public void addNode(Node node){
        nodeCount++;
        double zeroVal = 1d/(nodeCount*1.0);
        Bar bar = new Bar(node, 10, ctr, zeroVal,this.getHeight());
        updateBarsValue(zeroVal);
        this.add(bar);
        getBars().put(node.getS(), bar); 
        this.repaint();
        this.revalidate();
        ctr+=22;
    }
    
    public void updateBarsValue(double n){
        getBars().forEach((key,val)->{
            val.setValue(n);
        });
    }
}
