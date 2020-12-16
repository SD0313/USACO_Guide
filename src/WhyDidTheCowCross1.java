/*********************************************
 * Sauman Das
 * December 15, 2020
 * USACO 2017 February Silver
 * Problem 1. Why Did the Cow Cross the Road
 * 5/10 (Wrong Answer on 2, 5, 6, 8, 9) :(
 ********************************************/


import java.io.*; import java.util.*;
public class WhyDidTheCowCross1 {
    static String file = "helpcross";
    static int c, n;
    static ArrayList<Integer> c_time = new ArrayList<>();
    static ArrayList<Tuple> cow_ints = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader(file + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(file + ".out"));
        StringTokenizer st = new StringTokenizer(f.readLine());
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < c; i++){
            c_time.add(Integer.parseInt(f.readLine()));
        }
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(f.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            cow_ints.add(new Tuple(a, b));
        }
        Collections.sort(cow_ints);
        Collections.sort(c_time);
//        System.out.println(c_time);
//        System.out.println(cow_ints);
        int count = 0;
        int curr = 0;
        for (int time : c_time){
            if (curr >= n) break;

            while (true){
                if (curr >= n) break;
                Tuple t = cow_ints.get(curr);
                if (time < t.a){
                    break;
                }
                curr++;
                if (t.contains(time)){
//                    System.out.println(t);
//                    System.out.println(time);
                    count++;
//                    curr++;
                    break;
                }
//                curr++;
            }
        }
        out.println(count);
        f.close(); out.close();
    }
    static class Tuple implements Comparable<Tuple>{
        int a, b;
        public Tuple(int a, int b){this.a=a;this.b=b;}

        public int compareTo(Tuple t) {
            if (this.a == t.a){
                return Integer.compare(b, t.b);
            }
            return Integer.compare(a, t.a);
        }
        public boolean contains(int n){
            return n >= a && n <= b;
        }
        public String toString(){
            return "[" + a + ", " + b + "]";
        }
    }
}
