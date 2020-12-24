import java.util.*;
import java.io.*;
public class MooParticle {
    static int n;
    static String file = "io//moop";
    public static void main(String[] args) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader(file + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(file + ".out"));
        n = Integer.parseInt(f.readLine());


        f.close();out.close();
    }
}
