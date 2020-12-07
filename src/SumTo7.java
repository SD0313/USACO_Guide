/***************
 * Sauman Das
 * All Correct!
 ***************/

import java.util.*;
import java.io.*;
public class SumTo7 {
    public static final String file = "div7";
    public static void main(String[] args) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader(new File(file + ".in")));
        PrintWriter out = new PrintWriter(new FileWriter(new File(file + ".out")));
        int n = Integer.parseInt(f.readLine());
        ArrayList<Integer> prefix = new ArrayList<Integer>();
        for (int i = 0; i < n; i++){
            if (i == 0) {
                prefix.add(Integer.parseInt(f.readLine())%7);
            }
            else{
                prefix.add((prefix.get(i-1)+Integer.parseInt(f.readLine()))%7);
            }
        }
//        System.out.println(prefix);
        int max = prefix.lastIndexOf(0)+1;
        for (int i = 1; i < 7; i++){
            max = Math.max(max, prefix.lastIndexOf(i)-prefix.indexOf(i));
        }
        out.println(max);
        f.close();
        out.close();
    }
}
