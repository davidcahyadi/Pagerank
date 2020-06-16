/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagerankGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javafx.util.Pair;
import javax.swing.BorderFactory;
import javax.swing.JComponent;

/**
 *
 * @author david
 */
public class Node extends JComponent{

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return the fadedColor
     */
    public Color getFadedColor() {
        return fadedColor;
    }

    /**
     * @param fadedColor the fadedColor to set
     */
    public void setFadedColor(Color fadedColor) {
        this.fadedColor = fadedColor;
    }

    /**
     * @return the radius
     */
    public int getRadius() {
        return radius;
    }

    /**
     * @param radius the radius to set
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

    /**
     * @return the x
     */
    public int getModifiedX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getModifiedY() {
        return y;
    }

    /**
     * @return the s
     */
    public String getS() {
        return s;
    }

    /**
     * @param s the s to set
     */
    public void setS(String s) {
        this.s = s;
    }
    
    public static Node motherNode;
    private int x,y,radius,diameter;
    private String s;
    private HashMap<String,ArrayList<String>> outgoing,ingoing;
    private Display display;
    private Color color;
    private Color fadedColor;
    private Random rand = new Random();
    
    public Node(int x,int y,int radius,String s,Display display){
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.diameter = radius*2;
        this.s = s;
        this.outgoing = display.outgoing;
        this.ingoing = display.ingoing;
        this.display = display;
        this.color = new Color(rand.nextInt(128),rand.nextInt(128),rand.nextInt(128));
        this.fadedColor = new Color((int)(1.6*this.getColor().getRed()),(int)(1.6*this.getColor().getGreen()),(int) (1.6*this.getColor().getBlue()));
        init();
    }
    
    
    private void init(){
        this.setBounds(x-getRadius(), y-getRadius(), diameter, diameter);
        this.setPreferredSize(new Dimension(diameter,diameter));
        this.setName("");
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int currentX = getX()  + e.getX();
                int currentY = getY()  + e.getY();
                setPos(currentX, currentY);
                display.repaint();
            }
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setLink();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(this.getColor());
        g2d.fillOval(0, 0, diameter, diameter);
        drawCenterString(g2d);
    }
    
    private void setPos(int x, int y){
        this.x = x;
        this.y = y;
        
        this.setBounds(x-getRadius(), y-getRadius(), diameter, diameter);
        repaint();
    }
    
    private void drawCenterString(Graphics2D g){
        g.setColor(Color.white);
        // rectangle of object
        Rectangle rect = new Rectangle(0,0,diameter,diameter);
        // create font
        Font font = new Font("Roboto", 1, 14);
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int localx = rect.x + (rect.width - metrics.stringWidth(getS())) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int localy = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        
        g.setFont(font);
        g.drawString(getS(), localx, localy);
    }
    
    private void setLink(){
        if(motherNode == null || motherNode.equals(this)){
            motherNode = this;
            System.out.println("Mother : "+ this.s);
        }
        else{
            System.out.println("Child : "+ this.s);
            outgoing.get(motherNode.getS()).add(getS());
            ingoing.get(getS()).add(motherNode.getS());
            
            display.updateLink(new Pair(motherNode,this));
            motherNode = null;
        }
    }
    public void updateRadius(int n){
        this.radius = n;
        this.diameter = n*2;
        this.setBounds(x-getRadius(), y-getRadius(), diameter, diameter);
        this.setPreferredSize(new Dimension(diameter,diameter));
    }
}
