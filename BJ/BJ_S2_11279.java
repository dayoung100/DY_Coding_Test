import java.util.*;
import java.io.*;

//BJ S3 11279 최대 힙
public class BJ_S2_11279 {
    private static int N;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        //max heap
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        N = Integer.parseInt(st.nextToken());
        for(int n=0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            //큰값 출력 및 제거
            if(x == 0){
                sb.append(pq.size()==0 ? 0 : pq.poll());
                sb.append("\n");
            }
            //자연수 넣기
            else pq.add(x);
        }
        System.out.println(sb.toString());
    }
}
/* 
 * 그냥 맥스힙 구현
 */