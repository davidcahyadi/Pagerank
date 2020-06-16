/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pagerankGUI;

import java.awt.Color;
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
    public Bar(Node node,int x, int y,double value) {
        this.s = node.getName();
        this.x = x;
        this.y = y;
        this.maxValue = 250;
        this.value = (int) value*this.maxValue;
        this.height = 10;
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.black);
        
        g2d.drawString(s, x, y);
        g2d.drawRoundRect(x, y+10, this.value, this.height, 5, 5);
    }
}
