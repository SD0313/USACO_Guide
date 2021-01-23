/********************************
 * Sauman Das
 * January 23, 2021
 * USACO 2015 December Gold
 * Problem 1. High Card Low Card
 * All Correct! :)
 ********************************/

import java.io.*; import java.util.*;

public class HighCardLowCard {
    public static final String file = "io//cardgame"; //personal file
//        public static final String file = "cardgame"; //submission file
    public static void main(String[] args) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader(file + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(file + ".out"));
        int n = nextInt(f);
        Set<Integer> e = new HashSet<>();
        Queue<Integer> elsie = new LinkedList<>();
        PriorityQueue<Integer> e_first = new PriorityQueue<>();
        PriorityQueue<Integer> e_sec = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < n; i++){
            int next = nextInt(f);
            if (i < n/2){
                e_first.add(next);
            }
            else{
                e_sec.add(next);
            }
            e.add(next);
            elsie.add(next);
        }
        PriorityQueue<Integer> first = new PriorityQueue<>();
        PriorityQueue<Integer> sec = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 2*n; i >= 1; i--){

            if (!e.contains(i)) {
                if (first.size() < n/2) {
                    first.add(i);
                }
                else{
                    sec.add(i);
                }
            }
        }
        int points = 0;
        int iter = 1;
        while (!e_first.isEmpty() || !e_sec.isEmpty()){
            if (iter <= n/2) {
                int e_move = e_first.remove();
                while (!first.isEmpty()){
                    int b_move = first.remove();
                    if (b_move > e_move){
                        points++;
                        break;
                    }
                }
            }
            else {
                int e_move = e_sec.remove();
                while (!sec.isEmpty()){
                    int b_move = sec.remove();
                    if (b_move < e_move){
                        points++;
                        break;
                    }
                }
            }
            iter++;
        }
        out.println(points);
        f.close(); out.close();
    }
    public static int nextInt(BufferedReader f) throws IOException{return Integer.parseInt(f.readLine());}
    public static int nextInt(StringTokenizer st) { return Integer.parseInt(st.nextToken());}
}
