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
    HashMap<String,Float> outGoingPage = new HashMap<>();

    final float D = 0.85f;
    int ITERATION = 4;
    public PageRank(HashMap<String,ArrayList<String>>[] graph) {
        outgoing = graph[0];
        ingoing = graph[1];
        Rank();
    }
    
    public void Rank(){
       outgoing.forEach((key, value) -> {
           outGoingPage.put(key, 1f/(float) outgoing.size());
       });
       for (Map.Entry<String, Float> temp2 : outGoingPage.entrySet()) {
       }
       
       int dp = 0;
       int j = 0, k;
       for (Map.Entry<String, ArrayList<String>> out : outgoing.entrySet()) {
           k = 0;
           Map.Entry<String,Float> temp = null;
           for (Map.Entry<String, Float> outg : outGoingPage.entrySet()) {
               if(k == j){
                   temp = outg;
               }
               k++;
           }
           if(out.getValue().isEmpty()){
               dp += D * temp.getValue() / outGoingPage.size();
           }
           j++;
       }
       
       while(ITERATION > 0){
           HashMap<String,Float> inGoingPage = new HashMap<>();
           for (Map.Entry<String, Float> entry : outGoingPage.entrySet()) {
               inGoingPage.put(entry.getKey(), 1f);
           }
           float awal = dp + (1 - D) / outGoingPage.size();
           for (Map.Entry<String, Float> entry : inGoingPage.entrySet()) {
               entry.setValue(awal);
           }
//               temp.setValue(awal);
           for (Map.Entry<String, ArrayList<String>> entry : ingoing.entrySet()) {
               if(!entry.getValue().isEmpty()){
                   j = 0;
                   for (int i = 0; i < entry.getValue().size(); i++) {
                       Map.Entry<String, ArrayList<String>> temp2 = null;
                       int index = 0;
                       for (Map.Entry<String, ArrayList<String>> entry1 : outgoing.entrySet()) {
                           k = 0;
                           if(j < entry.getValue().size()){
                               if(entry1.getKey().equals(entry.getValue().get(j))){
                                   temp2 = entry1;
                               }
                           }
                       }
                       Map.Entry<String,Float> temp3 = null;
                       index = 0;
                       for (Map.Entry<String, Float> entry1 : outGoingPage.entrySet()) {
                           k = 0;
                           if(j < entry.getValue().size()){
                               if(entry1.getKey().equals(entry.getValue().get(j))){
                                   temp3 = entry1;
                               }
                           }
                       }
                       Map.Entry<String,Float> temp4 = null;
                       for (Map.Entry<String, Float> entry1 : inGoingPage.entrySet()) {
                           if(entry1.getKey().equals(entry.getKey())){
                               temp4 = entry1;
                           }
                       }
                       temp4.setValue(temp4.getValue() + D * temp3.getValue() / temp2.getValue().size());
                       j++;
                   }
               }
           }
           ITERATION--;
           outGoingPage = inGoingPage;
       }
    }
    
    public HashMap<String, Float> take(){
        return outGoingPage;
    }
}
