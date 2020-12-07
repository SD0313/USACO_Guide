/*****************************************
 * Sauman Das
 * December 4th, 2020
 * USACO 2019 December Silver, Problem 3
 * Milk Visits
 * All Correct! :)
 ******************************************/

import java.util.*;
import java.io.*;
public class MilkVisits {
    static int n, m;
    static String type;
    static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    static final String file = "milkvisits";
    static int[] ids;
    public static void main(String[] args) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader(file+".in"));
        PrintWriter out = new PrintWriter(new FileWriter(file + ".out"));
        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        ids = new int[n];
        visited = new boolean[n];
        type = f.readLine();
        for (int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < n-1; i++){
            st = new StringTokenizer(f.readLine());
            int n1 = Integer.parseInt(st.nextToken())-1;
            int n2 = Integer.parseInt(st.nextToken())-1;
            adj.get(n1).add(n2);
            adj.get(n2).add(n1);
        }
        makeIDs();
        for (int i = 0; i < m; i++){
            st = new StringTokenizer(f.readLine());
            int start = Integer.parseInt(st.nextToken())-1;
            int end = Integer.parseInt(st.nextToken())-1;
            char c = st.nextToken().charAt(0);
            if (ids[start] != ids[end]){
                out.print(1);
            }
            else if (type.charAt(start) == c){
                out.print(1);
            }
            else{
                out.print(0);
            }
        }
        out.println();
        f.close(); out.close();
    }
    static boolean[] visited;
    public static void makeIDs(){
        int id = 1;
        for (int i = 0; i < n;i++){
            if (!visited[i]){
                dfs(id, i, type.charAt(i));
                id++;
            }
        }
    }
    public static void dfs(int id, int n, char c){
        if (visited[n] || type.charAt(n) != c) return;
        visited[n] = true; ids[n] = id;
        for (int child : adj.get(n)){
            dfs(id, child, c);
        }
    }
}
