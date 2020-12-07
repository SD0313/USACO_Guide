/*************************************
 * Sauman Das
 * November 5th, 2020
 * USACO 2018 January Silver: MooTube
 * All Correct! :)
 **************************************/

import java.io.*;
import java.util.*;
public class MooTube {
    static String file = "mootube";
    static int n;
    static int q;
    static ArrayList<ArrayList<TubeEdge>> graph;
    static boolean[] visited;
    static int count;
    public static void main(String[] args) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader(file+".in"));
        PrintWriter out = new PrintWriter(new FileWriter(file + ".out"));
        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        visited = new boolean[n];
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++){
            graph.add(new ArrayList<TubeEdge>());
        }
        for (int i = 0; i < n-1; i++){
            st = new StringTokenizer(f.readLine());
            int p = Integer.parseInt(st.nextToken())-1;
            int q = Integer.parseInt(st.nextToken())-1;
            int r = Integer.parseInt(st.nextToken());
            graph.get(p).add(new TubeEdge(p, q, r));
            graph.get(q).add(new TubeEdge(q, p, r));

        }
        for (int i = 0; i < q; i++){
            st = new StringTokenizer(f.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken())-1;
            count = 0;
            visited = new boolean[n];
            numValid(v, k);
            out.println(count);

        }
        f.close();
        out.close();
    }
    public static void numValid(int a, int k){
        visited[a] = true;
        for (TubeEdge te : graph.get(a)){
            if (!visited[te.b] && te.w >= k){
                count++;
                numValid(te.b, k);
            }
        }
    }

}
class TubeEdge{
    int a, b, w;
    public TubeEdge(int a, int b, int w){
        this.a = a; this.b = b; this.w = w;
    }
}