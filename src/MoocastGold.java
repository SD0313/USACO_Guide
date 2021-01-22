/******************************
 * Sauman Das
 * January 21, 2020
 * USACO 2016 December Gold
 * Moocast
 * All Correct! :)
 ******************************/

import java.io.*; import java.util.*;

public class MoocastGold {
    public static final String file = "io//moocast_gold"; //my personal file
//    public static final String file = "moocast"; //actual test file
    static int n;
    static ArrayList<Pair> points = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader(file + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(file + ".out"));
        n = nextInt(f);

        for (int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            long x = (long) nextInt(st);
            long y = (long) nextInt(st);
            points.add(new Pair(x, y));
        }
//        System.out.println(points);
        long hi = 2*25000*25000;
        long lo = 0;
        for (hi++; lo < hi;){
            long mid = (lo+hi)/2;
            if (oneComponent(mid)) hi = mid; else lo = mid+1;
        }
        out.println(lo);
        f.close(); out.close();
    }
    static Set<Integer> visited;
    static boolean oneComponent(long x){
        visited = new HashSet<>();
        dfs(0, x);
        return visited.size() == n;
    }
    static void dfs(int v, long x){
        if (visited.contains(v)) return;
        visited.add(v);
        for (int i = 0; i < n; i++){
            if (i != v){
                if (points.get(v).canReach(x, points.get(i))){
                    dfs(i, x);
                }
            }
        }

    }
    static class Pair{
        long x, y;
        public Pair(long x, long y){this.x=x; this.y=y;}
        public boolean canReach(long X, Pair p){
            return dist(p) <= X;
        }
        private double dist(Pair p){
            return ((x-p.x)*(x-p.x)) + ((y-p.y)*(y-p.y));
        }
        public String toString(){
            return "(" + x + ", " + y + ")";
        }
    }
    public static int nextInt(BufferedReader f) throws IOException{return Integer.parseInt(f.readLine());}
    public static int nextInt(StringTokenizer st) { return Integer.parseInt(st.nextToken());}
}
