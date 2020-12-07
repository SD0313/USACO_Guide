/******************************
 * Sauman Das
 * December 7, 2020
 * USACO 2020 January Silver
 * Problem 1. Berry Picking
 * All Correct! :)
 ******************************/

import java.util.*; import java.io.*;
public class BerryPicking {
    static final String file = "berries";
    static List<Integer> basks = new ArrayList<>();
    static PriorityQueue<Integer> toChange;
    static int n, k;
    public static void main(String[] args) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader(file + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(file+".out"));
        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(f.readLine());
        for (int i = 0; i < n; i++){
            basks.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(basks); Collections.reverse(basks);
//        System.out.println(basks);
        toChange = new PriorityQueue<>(n, new NegSort());
        for (int i = 0; i < n; i++){
            toChange.add(basks.get(i));
//            least = basks.get(i);
        }
        int maxB = toChange.peek();
        int maxleast = -1;
        for (int i = maxB; i >= 1; i--){
            int least = getLeast(i, new PriorityQueue<>(toChange));
            maxleast = Math.max(maxleast, least);
        }
        out.println(maxleast);
//        System.out.println(answer());
        f.close();out.close();
    }
    static int getLeast(int b, PriorityQueue<Integer> pq){
        boolean elsie = true;
        ArrayList<Integer> buckets = new ArrayList<>();
        for (int i = 0; i < k; i++){
            if (i < k/2){
                if (pq.peek() < b) return -1;
                int next = pq.remove();
                buckets.add(b);
                pq.add(next-b);
            }
            else{
                int next = pq.remove();
                if (next >= b){
                    buckets.add(b);
                    pq.add(next-b);
                }
                else{
                    buckets.add(next);
                    pq.add(0);
                }
            }
        }
        return kby2(buckets);
    }
//    static int answer(){
//        if (toChange.peek()/2 <= least){
//            return kby2();
//        }
//        int next = toChange.remove();
//        int n1 = next/2;
//        int n2 = next-n1;
//        toChange.add(n1); toChange.add(n2);
//        least = n1;
//        return answer();
//    }
    static int kby2(ArrayList<Integer> arr){
        int sum = 0;
        for (int i = 0; i < k; i++){

            if (i >= (k/2)){
                sum += arr.get(i);
            }
        }
        return sum;
    }
    static class NegSort implements Comparator<Integer>{
        public int compare(Integer a, Integer b){return Integer.compare(b, a);}
    }
}
