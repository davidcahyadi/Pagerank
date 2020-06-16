/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagerank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david
 */
public class Input {
   
    public HashMap<String,ArrayList<String>>[] read(String path){
        HashMap<String,ArrayList<String>> outgoing = new HashMap<>();
        HashMap<String,ArrayList<String>> ingoing = new HashMap<>();
        HashMap<String,ArrayList<String>>[] hash = null;
        File file = new File(path);
        try {
            Scanner sc = new Scanner(file);
            int n = Integer.parseInt(sc.next());
            String[] s = new String[n];
            for(int i = 0; i < n; i++){
                String key = sc.next();
                outgoing.put(key, new ArrayList<>());
                ingoing.put(key, new ArrayList<>());
                s[i] = key;
            }
            for(int i = 0; i < n; i++){
                int c = Integer.parseInt(sc.next());
                for(int j = 0; j < c; j++){
                    String val = sc.next();
                    outgoing.get(s[i]).add(val);
                    ingoing.get(val).add(s[i]);
                }
            }
            hash = new HashMap[2];
            hash[0] = outgoing;
            hash[1] = ingoing;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Input.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hash;
    }
    
}
