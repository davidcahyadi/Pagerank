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
    HashMap<String,Bar>bars = new HashMap<>();
    int ctr;
    
    public Ranking() {
        init();
        ctr=10;
    }
    
    private void init(){
        Dimension d = new Dimension(250,450);
        this.setSize(d);
        this.setMaximumSize(d);
        this.setMinimumSize(d);
        this.setPreferredSize(d);
        this.setBackground(Color.WHITE);
    }
    
    public void addNode(Node node){
        Bar bar = new Bar(node, 10, ctr, 10, this);
        this.add(bar);
        bars.put(node.getS(), bar);
        ctr+=20;
        repaint();
        revalidate();
    }
    public void updateBarsValue(double n){
        for (Map.Entry<String, Bar> set : bars.entrySet()) {
            set.getValue().setValue(n);
            System.out.println("Updated bar value: " + n);
            System.out.println("Bar key: " + set.getKey());
        }
    }
}
