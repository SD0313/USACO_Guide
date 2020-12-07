import java.util.*;
import java.io.*;
public class ClockTree {
    static int n;
    static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    static int[] time;
    static HashSet<Integer> done;

    public static void main(String[] args) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("clocktree.in"));
        PrintWriter out = new PrintWriter(new FileWriter("clocktree.out"));
        n = Integer.parseInt(f.readLine());
        time = new int[n];
        StringTokenizer st = new StringTokenizer(f.readLine());

        for (int i = 0; i < n; i++){
            time[i] = Integer.parseInt(st.nextToken())%12;
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < n-1; i++){
            st = new StringTokenizer(f.readLine());
            int n1 = Integer.parseInt(st.nextToken())-1;
            int n2 = Integer.parseInt(st.nextToken())-1;
            adj.get(n1).add(n2);
            adj.get(n2).add(n1);
        }
        for (int i = 0; i < n; i++){
            Collections.shuffle(adj.get(i));
        }
//        time[1]--;
//        System.out.println(check(3));
//        System.out.println(Arrays.toString(time));
        int count = 0;
        for (int i = 0; i < n; i++){
            int[] copy = Arrays.copyOf(time, time.length);
            output = false;
            done = new HashSet<>();
            if (check(i)) count++;
//            System.out.println("Finished!!!");
            time = copy;
        }
        System.out.println(count);
        f.close();
        out.close();
    }
    static boolean output;
    public static boolean check(int n){
        update(n);
        return output;

    }

    public static void update(int n){
        if (output){
            return;
        }
        for (int c : adj.get(n)){
            System.out.println(done);
            System.out.println(Arrays.toString(time));
            if (output) return;
//            if (!done.contains(c) && !(done.size() == time.length || done.size() == time.length-1)) {
            if (!done.contains(c)) {
                time[c] = (time[c]+1)%12;
                if (done.size() == time.length-1 && time[c] == 0){
                    output = true;
                    return;
                }
                if (time[c] == 0 && isLeaf(c)){

                    done.add(c);
                }
                update(c);
            }
        }
    }
    public static boolean isLeaf(int n){
        int total = 0;
        for (int i : adj.get(n)){
            if (!done.contains(i)) total++;
        }
        return total == 1;
    }
}
