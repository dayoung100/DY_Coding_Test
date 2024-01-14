import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//BJ S4 11399 ATM
public class App {
    static int N;
    static int sum = 0;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int n=0; n<N; n++) pq.add(Integer.parseInt(st.nextToken()));
        //------입력 완료------
        int time = 0; //흐른 시간
        while(!pq.isEmpty()){
            int pi = pq.poll();
            sum += time + pi;
            time += pi;
        }
        sb.append(sum);
        System.out.println(sb);
        br.close();
    }
}
/* 
 * ATM 1대에서 N명이 인출을 마치는 데 걸리는 최단 시간은?
 * Pi = i번째 사람이 인출에 걸리는 시간
 * 총 시간은 사람 별 대기 시간 포함
 * 인원 N : 1,000 이하
 * 시간 P : 1,000 이하
 * ----------------------------
 * 그리디: Pi가 작은 사람부터 처리
 * 총 시간 = sum(그때까지의 총 시간 + 그때의 Pi)
 */