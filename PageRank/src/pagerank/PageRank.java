/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagerank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class PageRank {
    HashMap<String,ArrayList<String>> outgoing = new HashMap<>();
    HashMap<String,ArrayList<String>> ingoing = new HashMap<>();
    HashMap<String, Double> outGoingPage = new HashMap<>();

    final double D = 0.85f;
    int ITERATION = 4;
    public PageRank(HashMap<String,ArrayList<String>>[] graph) {
        outgoing = graph[0];
        ingoing = graph[1];
        Rank();
    }
    
    public void Rank(){
       outgoing.forEach((key, value) -> {
           outGoingPage.put(key, 1f/(double) outgoing.size());
       });
       
       double dp = 0;
       int j = 0, k;
       
       while(ITERATION > 0){
           System.out.println("Iterasi " + (5-ITERATION));
           dp = 0;
           for (Map.Entry<String, ArrayList<String>> out : outgoing.entrySet()) {
               Map.Entry<String,Double> temp = null;
               for (Map.Entry<String, Double> outg : outGoingPage.entrySet()) {
                   if(out.getKey().equals(outg.getKey())){
                       temp = outg;
                   }
               }
               if(out.getValue().isEmpty()){
                   dp += D * temp.getValue() / outGoingPage.size();
               }
           }
           
           HashMap<String,Double> inGoingPage = new HashMap<>();
           for (Map.Entry<String, Double> entry : outGoingPage.entrySet()) {
               inGoingPage.put(entry.getKey(), 0d);
           }
           double awal = (double) (dp + (1 - D) / outGoingPage.size());
           for (Map.Entry<String, Double> entry : inGoingPage.entrySet()) {
               entry.setValue(awal);
           }

           for (Map.Entry<String, ArrayList<String>> entry : ingoing.entrySet()) {
               if(!entry.getValue().isEmpty()){
                   j = 0;
                   for (int i = 0; i < entry.getValue().size(); i++) {
                       Map.Entry<String, ArrayList<String>> temp2 = null;
                       for (Map.Entry<String, ArrayList<String>> entry1 : outgoing.entrySet()) {
                           k = 0;
                           if(j < entry.getValue().size()){
                               if(entry1.getKey().equals(entry.getValue().get(j))){
                                   temp2 = entry1;
                               }
                           }
                       }
                       Map.Entry<String,Double> temp3 = null;
                       for (Map.Entry<String, Double> entry1 : outGoingPage.entrySet()) {
                           k = 0;
                           if(j < entry.getValue().size()){
                               if(entry1.getKey().equals(entry.getValue().get(j))){
                                   temp3 = entry1;
                               }
                           }
                       }
                       Map.Entry<String,Double> temp4 = null;
                       for (Map.Entry<String, Double> entry1 : inGoingPage.entrySet()) {
                           if(entry1.getKey().equals(entry.getKey())){
                               temp4 = entry1;
                           }
                       }
                       temp4.setValue(temp4.getValue() + D * temp3.getValue() / temp2.getValue().size());
                       j++;
                   }
               }
           }
           outGoingPage = inGoingPage;
           double sum = 0;
           if(ITERATION > 0){
               for (Map.Entry<String, Double> temp2 : outGoingPage.entrySet()) {
                   sum += temp2.getValue();
                   System.out.println("key : " + temp2.getKey() + ", value : " + temp2.getValue());
               }
               System.out.println("sum : " + sum);
               System.out.println("");
           }
           ITERATION--;
       }
    }
    
    public HashMap<String, Double> take(){
        return outGoingPage;
    }
}
