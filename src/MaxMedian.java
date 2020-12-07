/********************************
 * Sauman Das
 * November 4th, 2020
 * Codeforces Round #577 (Div. 2)
 * Problem C: Maximum Median
 * All Correct!
 ********************************/

import java.util.*;
public class MaxMedian {
    static long[] nums;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        long k = sc.nextLong();
        nums = new long[n];
        for (int i = 0; i < n; i++){
            nums[i] = sc.nextLong();
        }
        Arrays.sort(nums);
        long max = nums[n - 1] + k;
        long min = 0;
        long out = binSearch(min, max, k);
        System.out.println(out);
        sc.close();
    }
    public static long binSearch(long lo, long hi, long k){
        long res = lo-1;
        while (lo <= hi) {
            long mid = (lo+hi)/2;
            if (check(mid, k)){
                res = mid;
                lo = mid+1;
            }
            else {
                hi = mid-1;
            }
        }
        return res;
    }
    public static boolean check(long n, long k){
        long incs = 0;
        for (int i = (nums.length-1)/2; i < nums.length; i++){
            if (n-nums[i] > 0){
                incs += n-nums[i];
            }
        }
        return incs <= k;
    }
}
