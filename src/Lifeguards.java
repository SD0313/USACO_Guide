/***************************************
 * Sauman Das
 * December 2, 2020
 * USACO 2018 January Silver, Problem 1
 * Lifeguards
 * All Correct! :)
 ****************************************/

import java.util.*;
import java.io.*;
public class Lifeguards {
    static final String file = "lifeguards";
    static int n;
    public static void main(String[] args) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader(file+".in"));
        PrintWriter out = new PrintWriter(new FileWriter(file+".out"));
        n = Integer.parseInt(f.readLine());
        PriorityQueue<Pair> ints = new PriorityQueue<>();

        for (int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            long lo = Long.parseLong(st.nextToken());
            long hi = Long.parseLong(st.nextToken());
            ints.add(new Pair(lo, hi));
        }
        PriorityQueue<Pair> copy = new PriorityQueue<>(ints);
        long with_all = 0;
        long prev = -1;
        boolean returnAll = false;
        while (!copy.isEmpty()){
            Pair next = copy.remove();
            if (prev == -1){
                with_all += next.e-next.s;
                prev = next.e;
            }
            else{
                long lo = Math.max(prev, next.s);
                if (next.e-lo < 0){
                    returnAll = true;
                    prev = lo;
                }
                else {
                    with_all += next.e - lo;
                    prev = next.e;
                }
            }
        }
        if (returnAll){
            out.println(with_all);
            f.close(); out.close();
            return;
        }
        prev = -1;
        long min_removed = with_all;
        long answer = -1;
        while (!ints.isEmpty()) {
            Pair now = ints.remove();
            long hi;
            if (!ints.isEmpty()) {
                Pair next = ints.peek();
                hi = Math.min(next.s, now.e);
            }
            else{
                hi = now.e;
            }
            long lo = Math.max(prev, now.s);

            if (hi-lo < min_removed){
                min_removed = hi-lo;
                answer = with_all-(long)(hi-lo);
            }
            prev = now.e;
        }
        out.println(answer);
        f.close(); out.close();
    }
    static class Pair implements Comparable<Pair>{
        long s, e;
        public Pair(long s, long e){this.s=s;this.e=e;}
        public int compareTo(Pair p){
            return Long.compare(this.s, p.s);
        }
        public String toString(){
            return "(" + s + ", " + e + ")";
        }
    }
}
