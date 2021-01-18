import java.io.*; import java.util.*;

public class WhyDidTheCowCross2 {
    public static final String file = "io//maxcross";
    public static void main(String[] args) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader(file + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(file + ".out"));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = nextInt(st);
        int k = nextInt(st);
        int b = nextInt(st);
        int[] lights = new int[b];
        for (int i = 0; i < b; i++){
            lights[i] = nextInt(f);
        }
        Arrays.sort(lights);
//        System.out.println(Arrays.toString(lights));
        ArrayList<Integer> arr = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < b; i++){
            if (i == 0){
                if (lights[i] != 1){
                    arr.add(lights[i]-1);
                    temp.add(0);
                }
                arr.add(1);
                temp.add(1);
            }
            else if (i == b-1){
                arr.add(lights[i]-lights[i-1]-1);
                temp.add(0);
                arr.add(1);
                temp.add(1);
                if (lights[i] != n) {

                    arr.add(n - lights[i]);
                    temp.add(0);
                }
            }
            else{
                arr.add(lights[i]-lights[i-1]-1);
                temp.add(0);
                arr.add(1);
                temp.add(1);
            }

        }
        System.out.println(arr);
        System.out.println(temp);
        int[] num1 = new int[temp.size()];
//        num1[0] = (temp.get(0) == 1) ? 1 : 0;
//        for (int i = 1; i < arr.size(); i++){
//            num1[i] = (temp.get(i) == 1) ? num1[i-1]+1 : num1[i-1];
//        }
        num1[0] = (arr.get(0) == 1) ? 1 : 0;
        for (int i = 1; i < arr.size(); i++){
            num1[i] = (arr.get(i) == 1) ? num1[i-1]+1 : num1[i-1];
        }
        System.out.println(Arrays.toString(num1));
        int answer = Integer.MAX_VALUE;
        int p1 = 0;
        int min_p1 = -1;
//        int p2 = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int p2 = 0; p2 < arr.size(); p2++){
            sum += arr.get(p2);
            while(sum > k && p1 < p2){
//                System.out.println(p1 + " " + p2);
//                System.out.println(sum);
                int next = toFix(num1, p1, p2);
                if (next < answer){
                    map.put(p1, p2);
                    min_p1 = p1;
                    answer = next;
                }
                sum -= arr.get(p1);
                p1++;
            }
            if(sum == k){
//                System.out.println(p1 + " " + p2);
//                answer = Math.min(toFix(num1, p1, p2), answer);
                int next = toFix(num1, p1, p2);
                if (next < answer){
                    map.put(p1, p2);
                    min_p1 = p1;
                    answer = next;
                }
                p1++;
            }
        }
        System.out.println(min_p1 + " " + map.get(min_p1));
        System.out.println(toFix(num1, min_p1, map.get(min_p1)));
        out.println(answer);
        f.close(); out.close();
    }
    public static int toFix(int[] ones, int p1, int p2){
        return (p1==0) ? ones[p2] : ones[p2]-ones[p1-1];
    }
    public static int nextInt(BufferedReader f) throws IOException{return Integer.parseInt(f.readLine());}
    public static int nextInt(StringTokenizer st) { return Integer.parseInt(st.nextToken());}
}
