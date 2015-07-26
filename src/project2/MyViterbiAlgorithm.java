package project2;

import com.models.CONFIG;
import com.models.myLibFuntion;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrator
 */
public class MyViterbiAlgorithm {

    Double[][] pBXSCTT;
    HashMap<String, Double[]> pBXST;

    public MyViterbiAlgorithm(Double[][] pBXSCTT, HashMap<String, Double[]> pBXST) {
        this.pBXSCTT = pBXSCTT;
        this.pBXST = pBXST;
    }

    public void mainProcessor() {
        //q0 : xác suất cho nhãn start = 0;
        //word: tập các từ trong câu
        //v: ma trận viterbi có 2 chiều [word.length][NumberLabel]
        double q0 = 1.0;
        //tính toán xác suất cho từ đầu tiên
        String sentences = "time flies like an arrow";
        //tách từng từ ra
        String word[] = sentences.split(" ");

        for (String w : word) {
            System.out.println(w);
        }
        
        Double v[][] = new Double[word.length][CONFIG.NumberOfLabel];
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[0].length; j++) {
                v[i][j] = 0.0;
            }
        }

        //Tính xác suất cho từ đầu tiên: i = 0
        for (int j = 0; j < CONFIG.NumberOfLabel; j++) {
            v[0][j] = q0 * pBXSCTT[0][j] * pBXST.get(word[0])[j];
        }

        // tính xong xác suất từ đầu tiên. Tính xác suất của từ thứ 2
        for (int r = 1; r < v.length; r++) {
            for (int j = 0; j < CONFIG.NumberOfLabel; j++) {
                //nhãn đầu tiên
                Double w[] = new Double[CONFIG.NumberOfLabel];
                for (int k = 0; k < CONFIG.NumberOfLabel; k++) {
                    w[k] = v[r - 1][k] * pBXSCTT[k + 1][j] * pBXST.get(word[r])[j];
                }
                Double max = myLibFuntion.getMaxDouble(w);
                v[r][j] = max;
            }
        }

        //ma tran viterbi sau khi tính toán xong
        myLibFuntion.printAMatrix(v);
        //Xác định nhãn cho từ loại
        for (int row = 0; row < v.length; row++) {
            double max = myLibFuntion.getMaxDouble(v[row]);
            for (int col = 0; col < v[0].length; col++) {
                if (max == v[row][col]) {
                    System.out.println(word[row] + "=>" + CONFIG.ListLabel.get(col));
                }
            }
        }

//
//        //tính xác suất cho từ đầu tiên I
//        double q[] = new double[CONFIG.NumberOfLabel];
//        //nhãn  # N V ~~ q[0] q[1] q[2]
//        q[0] = 1 * pBXSCTT[0][0] * pBXST.get("I")[0];
//        q[1] = 1 * pBXSCTT[0][1] * pBXST.get("I")[1];
//        q[2] = 1 * pBXSCTT[0][2] * pBXST.get("I")[2];
//        for (double d : q) {
//            System.out.println(d);
//        }
    }
}
