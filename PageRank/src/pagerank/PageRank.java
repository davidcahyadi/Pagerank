/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagerank;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ASUS
 */
public class PageRank {
    HashMap<String,ArrayList<String>> outgoing = new HashMap<>();
    HashMap<String,ArrayList<String>> ingoing = new HashMap<>();
    HashMap<String,Float> outGoingPage = new HashMap<>();
    HashMap<String,Float> inGoingPage = new HashMap<>();
    final float D = 0.85f;
    int ITERATION = 5;
    public PageRank(HashMap<String,ArrayList<String>>[] graph) {
        outgoing = graph[0];
        ingoing = graph[1];
    }
    
    public void Rank(){
       outgoing.forEach((key, value) -> {
            outGoingPage.put(key, 1f/(float) outgoing.size());
        });
       while(ITERATION > 0){
           int dp = 0;
           
           ITERATION--;
       }
    }
}
