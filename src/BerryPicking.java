import java.util.*; import java.io.*;
public class BerryPicking {
    static final String file = "berries";
    static List<Integer> basks = new ArrayList<>();
    static PriorityQueue<Integer> toChange = new PriorityQueue<>();
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
        for (int i = 0; i < k; i++){
            toChange.add(-1*basks.get(i));
            least = -1*basks.get(i);
        }
//        System.out.println(toChange);
        out.println(answer());
        f.close();out.close();
    }
    static int least;
    static int answer(){
        if (toChange.peek()/2 >= least){
            return kby2();
        }
        int next = toChange.remove();
        int n1 = next/2;
        int n2 = next-n1;
        toChange.add(n1); toChange.add(n2);
        least = n1;
        return -1*answer();
    }
    static int kby2(){
        int sum = 0;
        for (int i = 0; i < k; i++){
            int next = toChange.remove();
            if (i >= (k/2)){
                sum += next;
            }
        }
        return sum;
    }
}
