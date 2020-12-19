/*************************************
 * Sauman Das
 * December 19, 2020
 * USACO 2017 December Silver
 * Problem 1. My Cow Ate My Homework
 * All Correct! :)
 **************************************/


import java.util.*;
import java.io.*;
public class MyCowAteMyHomework {
    static int n;
    static final String file = "homework";
    public static void main(String[] args) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader(file+".in"));
        PrintWriter out = new PrintWriter(new FileWriter(file + ".out"));
        n = Integer.parseInt(f.readLine());
        int[] arr = new int[n];
        int[] prefix = new int[n];
        int[] mins = new int[n];
        StringTokenizer st = new StringTokenizer(f.readLine());
        int prev = 0;
        for (int i = 0; i < n; i++){
            int next = Integer.parseInt(st.nextToken());
            prefix[i] = prev+next;
            arr[i] = next;
            prev = prefix[i];
        }
//        System.out.println(Arrays.toString(arr));
//        System.out.println(Arrays.toString(prefix));
        int prevMin = arr[n-1];
        for (int i = n-2; i >= 0; i--){
            int min = Math.min(arr[i], prevMin);
            mins[i] = min;
            prevMin = min;
        }
        double maxAvg = -1.;
        Set<Integer> ans = new HashSet<>();
        for (int k = 1; k <= n-2; k++){
            int minRemainingVal = mins[k];
            int sum = prefix[n-1]-prefix[k-1];
            int actSum = sum-minRemainingVal;
            int scores = (n-k-1);
            double avg = (double)actSum/(double)scores;
            if (avg > maxAvg){
                maxAvg = avg;
                ans = new HashSet<>();
                ans.add(k);
            }
            if (avg == maxAvg){
                ans.add(k);
            }
        }
//        System.out.println(Arrays.toString(mins));
//        System.out.println(ans);
        for (int k : ans){
            out.println(k);
        }
        f.close();out.close();
    }
}
