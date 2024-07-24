import java.util.*;
import java.io.*;

//BJ B4 2480 주사위 세개
public class BJ_B4_2480 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int first = Integer.parseInt(st.nextToken());
        int second = Integer.parseInt(st.nextToken());
        int third = Integer.parseInt(st.nextToken());
        //-----input end-----
        if(first==second && second == third) {
            System.out.println(10000+1000*first);
        }else if(first==second || first==third) {
            System.out.println(1000+first*100);
        }else if(second==third){
            System.out.println(1000+second*100);
        }else{
            int max = 0;
            max = Math.max(first, Math.max(second, third));
            System.out.println(max*100);
        }
    }
}
