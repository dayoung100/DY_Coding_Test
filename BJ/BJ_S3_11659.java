import java.util.*;
import java.io.*;

//BJ S3 11659 구간 합 구하기 4
public class BJ_S3_11659 {
    private static int N, M;
    private static int[] inputs;
    private static int[] sum;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        inputs = new int[N];
        sum = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int n=0; n<N; n++) {
            inputs[n] = Integer.parseInt(st.nextToken());
            //미리 누적합 구해두기
            if(n==0) sum[n] = inputs[n];
            else sum[n] = inputs[n]+sum[n-1];
        }
        for(int m=0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            int i=Integer.parseInt(st.nextToken());
            int j=Integer.parseInt(st.nextToken());
            sb.append(i==1 ? sum[j-1] : sum[j-1] - sum[i-2]);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
/*
 * N: 수의 개수
 * M: 합을 구해야하는 횟수
 * i~j번째 수까지의 합 출력
 * N, M <=10만
 * -----------------------
 * 구간합 -> 미리 해당 구간까지의 합을 구해두기
 * (1) 1~4까지의 합 구하기
 * (2) 1~2까지의 합 구하기
 * (1) - (2) = 3~4까지의 합
 * 각 구간까지의 합 구하기: 누적합 -> n번
 * m번의 구간합 구하기 -> m번
 * O(n+m) = O(1)
 */