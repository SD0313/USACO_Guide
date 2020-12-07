/********************************************
 * Sauman Das
 * November 6th, 2020
 * USACO 2020 January Silver: Wormhole Sort
 * All Correct! :)
 *********************************************/

import java.util.*;
import java.io.*;

public class Wormhole{
    public static final String file = "wormsort";
    static int[] ids;
    static ArrayList<ArrayList<Edge>> graph;
    static boolean[] visited;
    static int n;
    public static void main(String[] args) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader(new File(file + ".in")));
        PrintWriter out = new PrintWriter(new FileWriter(new File(file + ".out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] holes = new int[n];
        ids = new int[n];
        st = new StringTokenizer(f.readLine());
        HashSet<Integer> ooo = new HashSet<>();
        for (int i = 0; i < n; i++){
            holes[i] = Integer.parseInt(st.nextToken())-1;
            if (holes[i] != i) ooo.add(holes[i]);
        }
        ArrayList<Integer> edges = new ArrayList<>();
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++){
            st = new StringTokenizer(f.readLine());
            int p1 = Integer.parseInt(st.nextToken())-1;
            int p2 = Integer.parseInt(st.nextToken())-1;
            int w = Integer.parseInt(st.nextToken());
            Edge this_edge1 = new Edge(p1, p2, w);
            Edge this_edge2 = new Edge(p2, p1, w);
            edges.add(w);
            graph.get(p1).add(this_edge1); graph.get(p2).add(this_edge2);
        }
        if (!ooo.isEmpty()) {
            int lo = 0;
            int hi = 1000000001;//edges.size() - 1;
            for (lo --; lo < hi; ) {
                int mid = (lo+hi+1)/2;
                if(check(mid, ooo)) lo = mid; else hi = mid-1;
            }
            out.println(lo);
        }
        else{
            out.println(-1);
        }
        f.close();
        out.close();
    }

    public static boolean check(int minWeight, HashSet<Integer> set){
        visited = new boolean[n];
        int id = 1;
        for (int i = 0; i < n; i++){
            if (!visited[i]){
                dfs(i, id, minWeight);
                id++;
            }

        }
        int prev_id = -1;
        for (int n : set){
            if (prev_id == -1){
                prev_id = ids[n];
            }
            else if (prev_id != ids[n]) {
                return false;
            }
        }
        return true;
    }
    public static void dfs(int n, int id, int min){
        visited[n] = true;
        ids[n] = id;
        for (Edge e : graph.get(n)){
            if (!visited[e.v2] && e.weight >= min){
                dfs(e.v2, id, min);
            }
        }
    }
}
class Edge implements Comparable<Edge>{
    public int v1;
    public int v2;
    public int weight;
    public Edge(int v1, int v2, int weight){
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }
    public String toString(){
        return v1 + " " + v2 + " " + weight;
    }
    public int compareTo(Edge o) {
        return Integer.compare(weight, o.weight);
    }
}