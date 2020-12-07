/**************************************
 * Sauman Das
 * November 6th, 2020
 * USACO 2016 December Silver: Moocast
 * All Correct! :)
 ***************************************/

import java.util.*;
import java.io.*;
public class Moocast {
    public static final String file = "moocast";
    static int n;
    static ArrayList<ArrayList<Integer>> graph;
    public static void main(String[] args) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader(new File(file + ".in")));
        PrintWriter out = new PrintWriter(new FileWriter(new File(file + ".out")));
        n = Integer.parseInt(f.readLine());
        ArrayList<MooPoint> points = new ArrayList<MooPoint>();
        for (int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            double p = Double.parseDouble(st.nextToken());
            points.add(new MooPoint(x, y, p));
        }
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                double dist = points.get(i).dist(points.get(j));
                if (dist <= points.get(i).p){
                    graph.get(i).add(j);
                }
            }
        }
        out.println(maxCast());
        f.close();
        out.close();
    }
    static boolean[] visited;
    static int traversed;
    public static int maxCast(){
        visited = new boolean[n];
        traversed = 0;
        int out = -1;
        for (int i = 0; i < n; i++){
            dfs(i);
            out = Math.max(out, traversed);
            traversed = 0;
            visited = new boolean[n];
        }
        return out;
    }
    public static void dfs(int n){
        traversed++;
        visited[n] = true;
        for (int c : graph.get(n)){
            if (!visited[c]){
                dfs(c);
            }
        }
    }
}
class MooPoint {
    double x, y, p;
    public MooPoint(double x, double y, double p){
        this.x = x;
        this.y = y;
        this.p = p;
    }
    public double dist(MooPoint mp){
        return calcDist(x, y, mp.x, mp.y);
    }
    public String toString(){
        return "(" + x + ", " + y + ")" + " Power: " + p;
    }
    public double calcDist(double x1,double y1,double x2,double y2) {
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }
}