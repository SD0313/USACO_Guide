/******************************
 * Sauman Das
 * November 12th, 2020
 * USACO 2020 US Open Silver
 * Social Distancing
 * 9/10 (Wrong Answer on 7) :|
 ******************************/

import java.io.*;
import java.util.*;
public class SocialDistancing {
    static int n;
    static PriorityQueue<Integer> starts;
    static PriorityQueue<Interval> grass;
    static double maxEnd;
    public static void main (String [] args) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("socdist.in"));
        PrintWriter out = new PrintWriter(new FileWriter("socdist.out"));
        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        starts = new PriorityQueue<>();
        maxEnd = 0;
        double minStart = 1e18;
        grass = new PriorityQueue<>();
        for (int i = 0; i < m; i++){
            st = new StringTokenizer(f.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            grass.add(new Interval(start, end));
            starts.add(start);
            maxEnd = Math.max(maxEnd, end);
            minStart = Math.min(minStart, start);
        }
        int min = 1;
        int max = (int) Math.ceil(((maxEnd-minStart)/(double)n));
        out.println(lstTrue(min, max));
        f.close();
        out.close();
    }
    public static boolean check(int d){
        int cows = n;
        PriorityQueue<Integer> copy = new PriorityQueue<>(starts);
        PriorityQueue<Interval> copyGrass = new PriorityQueue<>(grass);
        Interval curr = copyGrass.remove();
        int prev = -1;
        while(cows > 0){
            if (prev == -1){
                prev = copy.remove();
                cows--;
            }
            else{
                prev += d;
                if (!curr.contains(prev)){
                    while (!curr.contains(prev)) {
                        if (copyGrass.isEmpty()) {
                            return false;
                        }
                        if (copyGrass.peek().s > prev) {
                            prev = copyGrass.peek().s;
                        }

                        curr = copyGrass.remove();

                    }
                }
                cows--;
                if (prev > maxEnd){
                    return false;
                }
            }
        }
        return true;
    }

    public static int lstTrue(int lo, int hi) {
        int res = lo-1;
        while (lo <= hi) {
            int mid = (lo+hi)/2;
            if (check(mid)) {
                res = mid;
                lo = mid+1;
            } else {
                hi = mid-1; // cut mid and after
            }
        }
        return res;
    }
    static class Interval implements Comparable<Interval>{
        int s, e;
        public Interval(int n1, int n2){
            s = n1; e = n2;
        }
        public boolean contains(int n){
            return n >= s && n <= e;
        }
        public int compareTo(Interval i){
            return Integer.compare(s, i.s);
        }
        public String toString(){
            return "("+s+", "+e+")";
        }

    }
}
