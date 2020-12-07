import java.util.*;
import java.io.*;

public class RentalService {
    public static final String file = "rental";
    public static void main(String[] args) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader(new File(file + ".in")));
        PrintWriter out = new PrintWriter(new FileWriter(new File(file + ".out")));
        String firstLine = f.readLine();
        StringTokenizer st = new StringTokenizer(firstLine);
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        Store[] stores = new Store[m];
        Integer[] rents = new Integer[r];
        Integer[] cows = new Integer[n];
        for (int i = 0; i < n; i++){
            cows[i] = Integer.parseInt(f.readLine());
        }
        Arrays.sort(cows, Collections.reverseOrder());
        int[] prefixSum = new int[n];
        int[] rentPrefixSum = new int[r];
        prefixSum[0] = cows[0];
        for (int i = 1; i < cows.length; i++){
            prefixSum[i] = cows[i]+prefixSum[i-1];
        }

//        System.out.println(Arrays.toString(prefixSum));
//        System.out.println(Arrays.toString(cows));
        int storeGallons = 0; //total gallons in all stores
        for (int i = 0; i < m; i++){
            st = new StringTokenizer(f.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            storeGallons += q;
            stores[i] = new Store(p, q);
        }
        Arrays.sort(stores);
//        System.out.println(Arrays.toString(stores));
        for (int i = 0; i < r; i++){
            rents[i] = Integer.parseInt(f.readLine());
        }
        Arrays.sort(rents, Collections.reverseOrder());
//        System.out.println(Arrays.toString(rents));
        rentPrefixSum[0] = rents[0];
        for (int i = 1; i < rents.length; i++){
            rentPrefixSum[i] = rents[i]+rentPrefixSum[i-1];
        }
        long maxPrice = 0;
        for (int i = 0; i < cows.length; i++){
            int galFromCow = prefixSum[i];
            if (galFromCow > storeGallons) {
//                System.out.println(maxPrice);
                break;
            }
//            System.out.println("i = " + i);

//            int gals = 0;
            int price = 0;
            for (Store s : stores){
//                gals += s.size;
//                System.out.println("remaining gallons: " + galFromCow);
                if (s.size > galFromCow){
                    price += galFromCow*s.price;
                    break;
                }
                else{
                    price += s.price*s.size;
                    galFromCow -= s.size;
                }

            }
//            System.out.println(i);
//            System.out.println(price);
            int index = Math.min(cows.length-i-2, rentPrefixSum.length-1);
//            System.out.println(index);
            if (index >= 0) {
                price += rentPrefixSum[index];
            }
            maxPrice = Math.max(maxPrice, price);
//            System.out.println(price);
//            System.out.println("Price: " + price);
        }
        if (cows.length <= rentPrefixSum.length) {
            maxPrice = Math.max(maxPrice, rentPrefixSum[cows.length - 1]);
        }
        out.println(maxPrice);
        f.close();
        out.close();
    }

}
class Store implements Comparable<Store>{
    int price;
    int size;

    public Store(int q, int p){
        price = p;
        size = q;
    }

    public int compareTo(Store o) {
        return -Integer.compare(this.price, o.price);
    }
    public String toString(){
        return "Gallons: " + size + " Price: " + price;
    }
}
