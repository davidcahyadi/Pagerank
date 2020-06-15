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
    private int x,y,value,maxValue;

    public Bar(String s, int x, int y, int value, int maxValue) {
        this.s = s;
        this.x = x;
        this.y = y;
        this.value = value;
        this.maxValue = maxValue;
    }
    
    
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.red);
//        g2d.fillOval(0, 0, diameter, diameter);
//        drawCenterString(g2d);
    }
}
