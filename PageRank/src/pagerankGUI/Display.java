/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagerankGUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.util.Pair;
import javax.swing.JPanel;

/**
 *
 * @author david
 */
public class Display extends JPanel{

    HashMap<String,ArrayList<String>> outgoing;
    HashMap<String,ArrayList<String>> ingoing;
    ArrayList<Pair<Node,Node>> singleLink;
    ArrayList<Pair<Node,Node>> doubleLink;
    
    
    public Display() {
        init();
        outgoing = new HashMap<>();
        ingoing = new HashMap<>();
        singleLink = new ArrayList<>();
        doubleLink = new ArrayList<>();
    }
    
    private void init(){
        Dimension d = new Dimension(700,450);
        this.setSize(d);
        this.setMaximumSize(d);
        this.setMinimumSize(d);
        this.setPreferredSize(d);
        this.setBackground(Color.WHITE);
    }
    
    public void addNode(Node node){
        this.add(node);
        outgoing.put(node.getS(), new ArrayList<>());
        ingoing.put(node.getS(), new ArrayList<>());
        repaint();
        revalidate();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        super.paintComponents(g); //To change body of generated methods, choose Tools | Templates.
        createLine((Graphics2D) g);
    }
    

    public void updateLink(Pair<Node,Node> p){
        Pair<Node,Node> tmp = null;
        for (Pair<Node, Node> pair : singleLink) {
            if(pair.getKey().equals(p.getValue()) && pair.getValue().equals(p.getKey())){
                tmp = pair;
            }
        }
        if(tmp != null){
            singleLink.remove(tmp);
            doubleLink.add(tmp);
        }
        else{
            singleLink.add(p);
        }
        repaint();
    }
    
    private void createLine(Graphics2D g){
        g.setColor(Color.green);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(new BasicStroke(3));
        for (Pair<Node, Node> pair : singleLink) {
            g.drawLine(
                    pair.getKey().getModifiedX(), 
                    pair.getKey().getModifiedY(), 
                    pair.getValue().getModifiedX(), 
                    pair.getValue().getModifiedY()
            );
            drawArrow(
                    pair.getKey().getModifiedX(), 
                    pair.getKey().getModifiedY(), 
                    pair.getValue().getModifiedX(), 
                    pair.getValue().getModifiedY(),
                    g
            );
        }
        for (Pair<Node, Node> pair : doubleLink) {
            g.drawLine(
                    pair.getKey().getModifiedX()-3, 
                    pair.getKey().getModifiedY()-3, 
                    pair.getValue().getModifiedX()-3, 
                    pair.getValue().getModifiedY()-3
            );
            
            g.drawLine(
                    pair.getKey().getModifiedX()+3, 
                    pair.getKey().getModifiedY()+3, 
                    pair.getValue().getModifiedX()+3, 
                    pair.getValue().getModifiedY()+3
            );
        }
        System.out.println("Draw");
    }
    
    private void drawArrow(int x1,int y1,int x2,int y2, Graphics2D g){
        int d = 30;
        int h = 10;
        int dx = x2 - x1, dy = y2 - y1;
        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = D - d, xn = xm, ym = h, yn = -h, x;
        double sin = dy / D, cos = dx / D;

        x = xm*cos - ym*sin + x1;
        ym = xm*sin + ym*cos + y1;
        xm = x;

        x = xn*cos - yn*sin + x1;
        yn = xn*sin + yn*cos + y1;
        xn = x;

        int[] xpoints = {x2, (int) xm, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yn};
        g.fillPolygon(xpoints, ypoints, 3);
    }
}
