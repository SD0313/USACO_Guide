/**********************************
 * Sauman Das
 * November 4th, 2020
 * CSES Problem Set: Building Roads
 * 6/11 (TLE on rest) :(
 ***********************************/

import java.util.*;
public class BuildingRoads {
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        visited = new boolean[n];
        for (int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int n1 = sc.nextInt()-1;
            int n2 = sc.nextInt()-1;
            graph.get(n1).add(n2);
            graph.get(n2).add(n1);
        }
        ArrayList<Integer> comps = new ArrayList<>();
        int components = -1;
        for (int i = 0; i < n; i++){
            if (!visited[i]){
                dfs(i);
                comps.add(i);
                components++;
            }
        }

        System.out.println(components);
        for (int i = 1; i < comps.size(); i++){
            System.out.println((comps.get(i-1)+1) + " " + (comps.get(i)+1));
        }
        sc.close();
    }
    public static void dfs(int n){
        visited[n] = true;
        for (int child : graph.get(n)){
            if (!visited[child]){
                dfs(child);
            }
        }
    }
}
