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
 * @author david
 */
public class Output {
    public void cetakInisiasi(HashMap<String,ArrayList<String>>[] hash){
        System.out.println("\nOutgoing : ");
        hash[0].forEach((key,val) ->{
            System.out.print(">> " + key + " => ");
            for (String string : val) {
                System.out.print(string + ", ");
            }
            System.out.println();
        });
        System.out.println("\nIngoing : ");
        hash[1].forEach((key,val) ->{
            System.out.print(">> " + key + " => ");
            for (String string : val) {
                System.out.print(string + ", ");
            }
            System.out.println();
        });
    }
    
    public void cetakHasil(HashMap<String, Double> temp){
        double sum = 0;
        System.out.println("Iterasi 5");
        for (Map.Entry<String, Double> entry : temp.entrySet()) {
            sum += entry.getValue();
            System.out.println("key : " + entry.getKey() + ", value : " + entry.getValue());
        }
        System.out.println("sum : " + sum);
    }
}
