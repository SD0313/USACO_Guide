/*********************************************
 * Sauman Das
 * November 11th, 2020
 * USACO 2018 US Open Silver: Multiplayer Moo
 * 5/10 (TLE on Rest) :(
 **********************************************/
import java.util.*;
import java.io.*;
public class MultiMoo {
    static String[][] grid;
    static boolean[][] visited;
    static int n;
    static int currSize = 0;
    static HashMap<String, HashSet<String>> adj = new HashMap<>();
    static Map<String, Integer> sizes;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("multimoo.in"));
        PrintWriter out = new PrintWriter(new FileWriter("multimoo.out"));
        n = Integer.parseInt(f.readLine());
        grid = new String[n][n];
        visited = new boolean[n][n];
        Map<String, Integer> symToCount = new HashMap<>();
        for (int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            for (int j = 0; j < n; j++){
                grid[i][j] = st.nextToken();
                if (!symToCount.containsKey(grid[i][j])){
                    symToCount.put(grid[i][j], -1);
                }

            }
        }
//        print();
        int outOne = 0;
        sizes = new HashMap<>();
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (!visited[i][j]){
                    int newCount = symToCount.get(grid[i][j])+1;
                    symToCount.put(grid[i][j], newCount);
                    String replace = newString(newCount, grid[i][j]);
                    floodfill(i, j, grid[i][j], replace);
                    sizes.put(replace, currSize);
                    outOne = Math.max(currSize, outOne);
                    currSize = 0;
                }
            }
        }
//        print();
        out.println(outOne);
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (!visited[i][j]){
                    ff2(i, j, grid[i][j]);
                }
            }
        }
//        System.out.println(adj);
//        System.out.println(Integer.parseInt("06"));
//        System.out.println(sizes);
        int outTwo = 0;
        for (String key : adj.keySet()){
            int ans = solve(key);
            outTwo = Math.max(ans, outTwo);
        }
        out.println(outTwo);
        f.close();
        out.close();
    }
    static int total = 0;
    static Set<String> vis = new HashSet<>();
    public static int solve(String key){
        int max = 0;
        for (String val : adj.get(key)){
            Set<Integer> toFind = new HashSet<>(Arrays.asList(Integer.parseInt(key), Integer.parseInt(val)));
            answer(key, toFind);
            max = Math.max(max, total);
            total = 0;
            vis = new HashSet<>();
        }
        return max;
    }
    public static void answer(String start, Set<Integer> toFind){
        total += sizes.get(start);
        vis.add(start);
        for (String val : adj.get(start)){
            int k = Integer.parseInt(val);
            if (toFind.contains(k) && !vis.contains(val)){
                answer(val, toFind);
            }
        }
    }
    public static void ff2(int r, int c, String color){
        if(r < 0 || r >= n || c < 0 || c >= n) return;
        if (!grid[r][c].equals(color)){
            if (!adj.containsKey(color)){
                adj.put(color, new HashSet<>());
            }
            adj.get(color).add(grid[r][c]);
            return;
        }
        if(visited[r][c]) return; // already visited this square
        visited[r][c] = true;
        ff2(r-1, c, color);
        ff2(r+1, c, color);
        ff2(r, c-1, color);
        ff2(r, c+1, color);

    }
    public static void floodfill(int r, int c, String color, String replace){
        if(r < 0 || r >= n || c < 0 || c >= n) return; // if outside grid
//        if (color.equals("9")) {
//            System.out.println("hi");
//        }
        if (!grid[r][c].equals(color) && !grid[r][c].equals(replace)){
//            if (!adj.containsKey(replace)){
//                adj.put(replace, new HashSet<>());
//            }
//            adj.get(replace).add(grid[r][c]);
            return;
        }
//        if(grid[r][c] != color) return; // wrong color
        if(visited[r][c]) return; // already visited this square
        visited[r][c] = true; // mark current square as visited
        grid[r][c] = replace;
        currSize++; // increment the size for each square we visit
        // recursively call floodfill for neighboring squares
        floodfill(r, c+1, color, replace);
        floodfill(r, c-1, color, replace);
        floodfill(r-1, c, color, replace);
        floodfill(r+1, c, color, replace);
    }
    public static String newString(int n, String c){
//        String out = "";
        for (int i = 0; i < n; i++){
            c = "0"+c;
        }
        return c;
    }
    public static void print(){
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}