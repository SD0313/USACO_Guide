import java.io.*; import java.util.*;

public class PairedUp {
//    public static final String file = "io//pairup"; //personal file
    public static final String file = "pairup"; //submission file
    public static void main(String[] args) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader(file + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(file + ".out"));
        int n = nextInt(f);
        Map<Integer, Integer> freq = new HashMap<>();
        List<Integer> cows = new ArrayList<>();
        for (int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            int a = nextInt(st);
            int b = nextInt(st);
            freq.put(b, a);
            cows.add(b);
        }
        Collections.sort(cows);
//        System.out.println(cows);
//        System.out.println(freq);
        int p1 = 0; int p2 = n-1;
        int p1_freq = 0;
        int p2_freq = 0;
        int curr_max = -1;

        while (p1 <= p2){
            curr_max = Math.max(cows.get(p1)+cows.get(p2), curr_max);
            p1_freq++;
            p2_freq++;
            int inc = Math.min(freq.get(cows.get(p1)), freq.get(cows.get(p2)));
            int old_p1 = p1;
            int old_p2 = p2;
            if (inc == freq.get(cows.get(p1)) && inc == freq.get(cows.get(p2)) ){
                p1++;
                p2--;
            }
            else if (inc == freq.get(cows.get(p1))){
                p1++;
            }
            else{
                p2--;
            }
            freq.put(cows.get(old_p1), freq.get(cows.get(old_p1))-inc);
            freq.put(cows.get(old_p2), freq.get(cows.get(old_p2))-inc);
        }
        out.println(curr_max);

        f.close(); out.close();
    }
    public static int nextInt(BufferedReader f) throws IOException{return Integer.parseInt(f.readLine());}
    public static int nextInt(StringTokenizer st) { return Integer.parseInt(st.nextToken());}
}
