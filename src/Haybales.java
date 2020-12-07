import java.io.*;
import java.util.*;

public class Haybales {
    public static final String file = "haybales";
    public static void main(String[] args) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader(new File(file+".in")));
        PrintWriter out = new PrintWriter(new FileWriter(new File(file + ".out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(f.readLine());
        ArrayList<Integer> hay = new ArrayList<>();
        for (int i = 0; i < n; i++){
            hay.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(hay);
        for (int i = 0; i < q; i++){
            st = new StringTokenizer(f.readLine());
            int b1 = Integer.parseInt(st.nextToken());
            int b2 = Integer.parseInt(st.nextToken());

            int low = Collections.binarySearch(hay, b1);
            int high = Collections.binarySearch(hay, b2);
            if (low < 0){
                low = Math.abs(low+1);
            }
            if (high < 0){
                high = Math.abs(high+2);
            }

            /**if (hay.get(0) <  b1-1){
                low = 0;
            }
            if (hay.get(0) < b2){
                high = 0;
            }**/
//            low = Math.max(low, 0);
//            high = Math.max(high, 0);
            out.println(high-low+1);
        }



        f.close();
        out.close();

    }


}
