/*****************************************
 * Sauman Das
 * December 4th, 2020
 * CSES Problem Set
 * Tree Diameter
 * 10/14 - TLE on [6, 9] :|
 ******************************************/

import java.util.*; import java.io.*;
public class TreeDiameterCSES {
    static int n;
    static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        n = Integer.parseInt(f.readLine());
        if (n == 1){
            out.println(0);
            f.close(); out.close();
            return;
        }
        for (int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < n-1; i++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            int n1 = Integer.parseInt(st.nextToken())-1;
            int n2 = Integer.parseInt(st.nextToken())-1;
            adj.get(n1).add(n2); adj.get(n2).add(n1);
        }
        int answer = findDiameter();
        out.println(answer);
        f.close(); out.close();
    }
    public static int findDiameter(){
        int start = farthest(0);
        return distance(start);
    }
    public static int distance(int n){
        Queue<Pair> fringe = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        visited.add(n);
        fringe.add(new Pair(n, 0));
        int max_depth = -1;
        int farthestNode = -1;
        while(!fringe.isEmpty()){
            Pair next = fringe.remove();
            int v = next.s; int depth = next.e;
            for (int child : adj.get(v)){
                if (!visited.contains(child)){
                    fringe.add(new Pair(child, depth+1));
                    if (depth+1 > max_depth){
                        max_depth = depth+1;
                        farthestNode = child;
                    }
                    visited.add(child);
                }
            }
        }
        return max_depth;
    }
    public static int farthest(int n){
        Queue<Pair> fringe = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        visited.add(n);
        fringe.add(new Pair(n, 0));
        int max_depth = -1;
        int farthestNode = -1;
        while(!fringe.isEmpty()){
            Pair next = fringe.remove();
            int v = next.s; int depth = next.e;
            for (int child : adj.get(v)){
                if (!visited.contains(child)){
                    fringe.add(new Pair(child, depth+1));
                    if (depth+1 > max_depth){
                        max_depth = depth+1;
                        farthestNode = child;
                    }
                    visited.add(child);
                }
            }
        }
        return farthestNode;
    }
    static class Pair{int s, e; public Pair(int s, int e){this.s=s;this.e=e;}}
}
