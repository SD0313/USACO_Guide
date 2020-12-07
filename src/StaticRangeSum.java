import java.util.*;
public class StaticRangeSum {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] prefix = new int[n];
        for (int i = 0; i < n; i++){
            if (i == 0){
                prefix[i] = sc.nextInt();
            }
            else{
                prefix[i] = sc.nextInt()+prefix[i-1];
            }
        }
        RangePair[] queries = new RangePair[q];
        for (int i = 0; i < q; i++){
            int l = sc.nextInt()-1;
            int r = sc.nextInt()-1;
            queries[i] = new RangePair(l, r);

        }
        for (RangePair rp : queries){
            int r = rp.r; int l = rp.l;
            try {
                System.out.println(prefix[r] - prefix[l]);
            }
            catch (Exception e){
                System.out.println(prefix[r]);
            }
        }
        sc.close();
    }
    static class RangePair{
        int l, r;
        public RangePair(int r, int c){this.l=r; this.r=c;}
    }
}
