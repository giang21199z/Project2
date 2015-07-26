/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

import com.models.CONFIG;
import com.models.myLibFuntion;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author Administrator
 */
public class Project2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //số nhãn cần tính

        //Phân bố xác suất chuyển trạng thái 
        Double[][] pBXSCTT = new Double[CONFIG.NumberOfLabel + 1][CONFIG.NumberOfLabel];
        //Phân bố xác suất các từ với các nhãn
        HashMap<String, Double[]> pBXST = new HashMap<>();

        pBXSCTT = myLibFuntion.loadData1(CONFIG.FilePhanBoXacSuatChuyenTrangThai);
        pBXST = myLibFuntion.loadData2(CONFIG.FilePhanBoXacSuatTuVsNhan);

        MyViterbiAlgorithm mva = new MyViterbiAlgorithm(pBXSCTT, pBXST);
        mva.mainProcessor();
    }

}
