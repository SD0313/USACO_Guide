/****************************
 * Sauman Das
 * December 24, 2020
 * CSES Problem Set
 * Forest Queries
 * All Correct! :)
 *****************************/

import java.util.*;
import java.io.*;
public class ForestQueries {
    static int[][] prefix;
    static int n;
    public static void main(String[] args) throws IOException{
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(f.readLine());
        n = nextInt(st);
        int q = nextInt(st);
        prefix = new int[n][n];
        for (int i = 0; i < n; i++){
            String s = f.readLine();
            for (int j = 0; j < n; j++){
                if (s.charAt(j) == '*') prefix[i][j]++;
            }
        }
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (i != 0){
                    prefix[i][j] += prefix[i-1][j];
                }
                if (j != 0) {
                    prefix[i][j] += prefix[i][j-1];
                }
                if (i != 0 && j != 0){
                    prefix[i][j] -= prefix[i-1][j-1];
                }
            }
        }
        for (int i = 0; i < q; i++){
            st = new StringTokenizer(f.readLine());
            int x1 = nextInt(st)-1;
            int y1 = nextInt(st)-1;
            int x2 = nextInt(st)-1;
            int y2 = nextInt(st)-1;
            out.println(getTrees(x1, y1, x2, y2));
        }
        f.close(); out.close();
    }
    static int getTrees(int x1, int y1, int x2, int y2){
        int term1 = prefix[x2][y2];
        int term2 = 0;
        if (x1 != 0) term2 = prefix[x1-1][y2];
        int term3 = 0;
        if (y1 != 0) term3 = prefix[x2][y1-1];
        int term4 = 0;
        if (x1 != 0 && y1 != 0){
            term4 = prefix[x1-1][y1-1];
        }
        return term1-term2-term3+term4;
    }
    static int nextInt(BufferedReader f) throws IOException{
        return Integer.parseInt(f.readLine());
    }
    static int nextInt(StringTokenizer st){
        return Integer.parseInt(st.nextToken());
    }
}
