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
        System.out.println("=================== PAGE RANK ===================");
        System.out.println("| Masukkan nama file yang akan dihitung datanya |");
        System.out.println("| contoh : t1.txt                               |");
        System.out.println("| Ada 6 file, dari t0 sampai t5                 |");
        System.out.println("=================================================");
        System.out.print("Path : ");
        String path = sc.nextLine();
        Input in = new Input();
        Output out = new Output();
        HashMap<String,ArrayList<String>>[] hash = in.read(path);
        out.cetakInisiasi(hash);
        PageRank pg = new PageRank(hash);
        out.cetakHasil(pg.take());
    }
}
