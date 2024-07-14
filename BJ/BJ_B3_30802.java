import java.util.*;
import java.io.*;

public class BJ_B3_30802 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] size = new int[6];
        for(int i=0; i<6; i++) size[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        //-----input end-----
        int sum = 0;
        for(int i=0; i<6; i++) {
            sum += size[i] / T;
            if(size[i]%T != 0) sum+=1;
        }
        System.out.println(sum);
        System.out.println(N/P + " "+ N%P);
    }
}
/*
 * [input]
 * N
 * S, M, L, XL, XXL, XXXL
 * T, P
 * [output]
 * 티셔츠 T장씩 최소 몇 묶음
 * 펜 P자루씩 최대 몇 묶음 / 한자루씩은 몇 개
 */