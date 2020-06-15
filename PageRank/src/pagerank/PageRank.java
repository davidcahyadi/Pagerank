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
    HashMap<String,ArrayList<String>> outgoing = new HashMap<>(); // berisi node yang ada di outgoing
    HashMap<String,ArrayList<String>> ingoing = new HashMap<>(); // berisi node yang ada di ingoing
    HashMap<String, Double> outGoingPage = new HashMap<>(); // berisi node yang ada di outgoing dengan hasil tracing

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
       int j; // untuk indexing value setiap node ingoing
       
       while(ITERATION > 0){
           System.out.println("");
           if((6 - ITERATION) != 5) System.out.println("Iterasi " + (6-ITERATION));
           dp = 0;
           for (Map.Entry<String, ArrayList<String>> out : outgoing.entrySet()) {
               // temp mencari node dari outgoingPage(haisl tracing) yang sama dengan node yang ada di outgoing untuk diambil nilainya
               Map.Entry<String,Double> temp = null;
               for (Map.Entry<String, Double> outg : outGoingPage.entrySet()) {
                   if(out.getKey().equals(outg.getKey())){
                       temp = outg;
                   }
               }
               // menghitung dp jika ada node yang tidak memiliki outgoing
               if(out.getValue().isEmpty()){
                   dp += D * temp.getValue() / outGoingPage.size();
               }
           }
           
           HashMap<String,Double> inGoingPage = new HashMap<>(); // hashmap temporary untuk menyimpan hasil tracing saat ini
           for (Map.Entry<String, Double> entry : outGoingPage.entrySet()) {
               inGoingPage.put(entry.getKey(), 0d);
           }
           // mmeberikan nilai awal untuk ingoingPage
           double awal = (double) (dp + (1 - D) / outGoingPage.size());
           for (Map.Entry<String, Double> entry : inGoingPage.entrySet()) {
               entry.setValue(awal);
           }

           // iterasi awal untuk menghitung nilai setiap node yang ada di ingoing
           for (Map.Entry<String, ArrayList<String>> entry : ingoing.entrySet()) {
               // node di ingoing dengan minimal 1 value yang akan dihitung
               if(!entry.getValue().isEmpty()){
                   j = 0;
                   for (int i = 0; i < entry.getValue().size(); i++) {
                       //tempOutGoing menyimpan node dari node ingoing yang di atas, satu per satu, akan diulang sebanyak size dari arraylist value di node ingoing
                       Map.Entry<String, ArrayList<String>> tempOutGoing = null;
                       // proses mencari node ingoing dari outgoing mulai dari yang paling depan
                       // misal outgoing A -> B,C dan E, maka tempOutGoing akan berisi B untuk iterasi 1, C untuk iterasi 2 dan E untuk iterasi 3
                       for (Map.Entry<String, ArrayList<String>> entry1 : outgoing.entrySet()) {
                           if(j < entry.getValue().size()){
                               if(entry1.getKey().equals(entry.getValue().get(j))){
                                   tempOutGoing = entry1;
                               }
                           }
                       }
                       // tempOutGoingPage adalah node outgoing dari ingoing node lain, untuk mendapatkan value dari ndoe tersebut
                       // misal outoging A -> B, C, E (maka tempOutGoingPage adalah B, C dan E satu persatu0
                       Map.Entry<String,Double> tempOutGoingPage = null;
                       for (Map.Entry<String, Double> entry1 : outGoingPage.entrySet()) {
                           if(j < entry.getValue().size()){
                               if(entry1.getKey().equals(entry.getValue().get(j))){
                                   tempOutGoingPage = entry1;
                               }
                           }
                       }
                       // tempInGoingPage adalah node ingoing dengan hashmap string dengan double (hasil tracing)
                       // misal tracing 2 akan menghitung nilai dari node A, maka tempInGoingPage akan menyimpan node A di hashmap string double
                       Map.Entry<String,Double> tempInGoingPage = null;
                       for (Map.Entry<String, Double> entry1 : inGoingPage.entrySet()) {
                           if(entry1.getKey().equals(entry.getKey())){
                               tempInGoingPage = entry1;
                           }
                       }
                       // mengisi nilai tracing ke node
                       tempInGoingPage.setValue(tempInGoingPage.getValue() + D * tempOutGoingPage.getValue() / tempOutGoing.getValue().size());
                       j++; // menambah index untuk iterasi berikutnya
                   }
               }
           }
           outGoingPage = inGoingPage; // setelah semua node selesai di hitung di satu iterasi, nilai akan dipassing ke outgoingPage
           double sum = 0;
           // output hasil setiap iterasi
           if(ITERATION > 1){
               for (Map.Entry<String, Double> entry : outGoingPage.entrySet()) {
                   sum += entry.getValue();
                   System.out.println("key : " + entry.getKey() + ", value : " + entry.getValue());
               }
               System.out.println("sum : " + sum);
           }
           ITERATION--;
       }
    }
    
    public HashMap<String, Double> take(){
        return outGoingPage;
    }
}
