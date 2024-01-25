import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class App {
    static int N, P, newScore;
    static PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder()); //역순pq
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        newScore = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        pq.add(newScore);
        //리스트에 있는 점수가 없으면 1등 출력하고 끝내기
        if(N == 0) {
            System.out.println(1);
            return;
        }
        //점수들 입력받기
        st = new StringTokenizer(br.readLine());
        int bigger = 0;
        int same = 0;
        for(int n=0; n<N; n++){
            int score = Integer.parseInt(st.nextToken());
            if(score > newScore) bigger++;
            if(score == newScore) same++;
        }//-----입력 완료-----
        if(bigger + same < P) sb.append(bigger+1);
        else sb.append(-1);
        System.out.println(sb);
        br.close();
    }
}
/* 
 * N: 리스트 크기(0~P)
 * 새로운 점수 (int내의 자연수)
 * P: 랭킹에 올라갈 수 있는 점수의 갯수(10~50)
 * 랭킹순으로 점수 목록(N>0일떄만)
 * 새로운 점수가 몇등하는지 출력 / 랭킹 리스트에 없다면 -1
 * 동일한 점수는 그 점수의 등수 중 가장 높은 등수
 * -------------------------------
 * 새로운 점수 보다 큰 수 개수 + 새로운 점수와 같은 수 개수 < P 여야 OK
 */