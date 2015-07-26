/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class myLibFuntion {

    public static void printAMatrix(Double[][] input) {
        for (Double[] pB : input) {
            for (Double xs : pB) {
                System.out.print(xs + " ");
            }
            System.out.println("");
        }
    }

    public static void printAHashMap(HashMap<String, Double[]> hm) {
        for (Map.Entry<String, Double[]> entry : hm.entrySet()) {
            String key = entry.getKey();
            Double[] value = entry.getValue();
            System.out.print(key);
            for (Double d : value) {
                System.out.print(" " + d);
            }
            System.out.println("");
        }
    }

    //Hàm load các xác suất P(Ci|Cj)
    //Đầu vào: Tên file
    //Đầu ra: Mảng 2 chiều 
    public static Double[][] loadData1(String path) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File(path)));
        String line = "";
        int row = 0;
        Double[][] matrix = new Double[CONFIG.NumberOfLabel + 1][CONFIG.NumberOfLabel];
        while ((line = br.readLine()) != null) {
            int col = 0;
            String[] xacSuat = line.split(" ");
            for (String xs : xacSuat) {
                if (xs.length() > 0) {
                    matrix[row][col++] = Double.parseDouble(xs);
                }
            }
            row++;
        }
        return matrix;
    }

    //Hàm load xác suất các từ kèm theo các nhãn
    public static HashMap<String, Double[]> loadData2(String path) throws FileNotFoundException, IOException {
        HashMap<String, Double[]> hm = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(new File(path)));
        String line = "";
        while ((line = br.readLine()) != null) {
            String[] arr = line.split(" ");
            String word = arr[0];
            Double[] array = new Double[CONFIG.NumberOfLabel];
            for (int i = 1; i < arr.length; i++) {
                array[i - 1] = Double.parseDouble(arr[i]);
            }
            hm.put(word, array);
        }
        return hm;
    }

    public static Double getMaxDouble(Double[] d) {
        Double max = d[0];
        for (int i = 1; i < d.length; i++) {
            if (d[i] > max) {
                max = d[i];
            }
        }
        return max;
    }

    public static ArrayList<String> loadLabel(String path) {
        BufferedReader br = null;
        ArrayList<String> arr = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(new File(path)));
            String line = "";
            while ((line = br.readLine()) != null) {
                arr.add(line);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(myLibFuntion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(myLibFuntion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(myLibFuntion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arr;
    }
}
