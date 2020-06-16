/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pagerankGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.*;
import javax.swing.JComponent;

/**
 *
 * @author david
 */
public class Bar extends JComponent{

    private String s;
    private int x,y,value;
    private final int height,maxValue;
    private final Color color;
    private double actualValue;
    Ranking rank;
    public Bar(Node node,int x, int y,double value,int height) {
        this.s = node.getS();
        this.x = x;
        this.y = y;
        this.maxValue = 230;
        this.actualValue = value;
        this.value = (int) (value*this.maxValue*1.0);
        this.height = 8;
        this.color = node.getColor();
        init(height);
    }
    
    private void init(int height){
        this.setBounds(0, 0, this.maxValue, height);
        this.setPreferredSize(new Dimension(this.maxValue,this.height*3));
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.black);
        g2d.setFont(new Font("Roboto",1,12));
        g2d.drawString(s+" - "+this.actualValue, x, y+10);
        g2d.setColor(this.color);
        g2d.fillRect(x,y+12,value,this.height);
    }
    protected void setValue(double n){
        this.value = (int) (n*this.maxValue*1.0);
        this.actualValue = n;
    }
    
    public double getValue(){
        return this.actualValue;
    }
}
