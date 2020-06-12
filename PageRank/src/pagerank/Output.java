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
    public void write(HashMap<String,ArrayList<String>>[] hash){
        hash[0].forEach((key,val) ->{
            System.out.print(">> " + key + " => ");
            for (String string : val) {
                System.out.print(string + ", ");
            }
            System.out.println();
        });
    }
    
    public void write2(HashMap<String, Float> temp){
        float sum = 0;
        for (Map.Entry<String, Float> temp2 : temp.entrySet()) {
            sum += temp2.getValue();
            System.out.println("key : " + temp2.getKey() + ", value : " + temp2.getValue());
        }
        System.out.println("sum : " + sum);
    }
}
