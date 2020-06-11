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
}
