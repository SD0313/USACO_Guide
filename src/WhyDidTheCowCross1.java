import java.io.*; import java.util.*;
public class WhyDidTheCowCross1 {
    final static String file = "io//helpcross";
    static int c, n;
    ArrayList<Integer> c_time = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader(file + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(file + ".out"));
        StringTokenizer st = new StringTokenizer(f.readLine());
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        f.close(); out.close();
    }
}
