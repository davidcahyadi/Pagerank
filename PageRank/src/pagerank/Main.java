/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagerank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author david
 */
public class Main {
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.print("Path : ");
        String path = sc.nextLine();
        Input in = new Input();
        Output out = new Output();
        HashMap<String,ArrayList<String>>[] hash = in.read(path);
        PageRank pg = new PageRank(hash);
        out.write(hash);
        out.write2(pg.take());
    }
}
