import java.io.*; import java.util.*;

public class WhyDidTheCowCross2 {
    public static final String file = "maxcross";
    public static void main(String[] args) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader(file + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(file + ".out"));


        f.close(); out.close();
    }
    public static int nextInt(BufferedReader f) throws IOException{return Integer.parseInt(f.readLine());}
    public static int nextInt(StringTokenizer st) { return Integer.parseInt(st.nextToken());}
}
