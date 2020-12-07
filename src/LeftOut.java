/******************************
 * Sauman Das
 * November 25th, 2020
 * USACO 2019 US Open Silver
 * Left Out
 * All Correct! :)
 ******************************/
import java.io.*;
public class LeftOut {
    static int[][] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("leftout.in"));
        PrintWriter out = new PrintWriter(new FileWriter("leftout.out"));
        int n = Integer.parseInt(f.readLine());
        arr = new int[n][n];
        for (int i = 0; i < n; i++){
            String line = f.readLine();
            for (int j = 0; j < n;j++){
                char c = line.charAt(j);
                if (c == 'L'){
                    arr[i][j] = 0;
                }
                else{
                    arr[i][j] = 1;
                }
            }
        }
        f.close();
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (arr[i][j] == 0){
                    arr[i][j] = 1;
                    if (solvable(i, j)){
                        out.println((i+1) + " " + (j+1));
                        out.close();
                        return;
                    }
                    arr[i][j] = 0;
                }
                if (arr[i][j] == 1){
                    arr[i][j] = 0;
                    if (solvable(i, j)){
                        out.println((i+1) + " " + (j+1));
                        out.close();
                        return;
                    }
                    arr[i][j] = 1;
                }
            }
        }
        out.println(-1);
        out.close();
    }
    public static boolean solvable(int i, int j){
        for (int x = 0; x < arr.length; x++){
            for (int y = 0; y < arr[0].length; y++){
                if (x == i) break;
                if (y == j) continue;
                int val1 = arr[x][y]; int val2 = arr[x][j];
                int val3 = arr[i][j]; int val4 = arr[i][y];
                int sum = val1+val2+val3+val4;
                if (sum == 1 || sum == 3){
                    return false;
                }
            }
        }
        return true;
    }
}
