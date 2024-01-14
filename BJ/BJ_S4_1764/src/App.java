import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//BJ S4 1764 듣보잡
public class App {
    static int N, M;
    static HashSet<String> set = new HashSet<>();
    static PriorityQueue<String> pq = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringBuilder sb = new StringBuilder();
       StringTokenizer st;
       st = new StringTokenizer(br.readLine());
       N = Integer.parseInt(st.nextToken());
       M = Integer.parseInt(st.nextToken());
       for(int n=0; n<N; n++){
        st = new StringTokenizer(br.readLine());
        set.add(st.nextToken());
       }
       for(int m=0; m<M; m++){
        st = new StringTokenizer(br.readLine());
        String name = st.nextToken();
        if(set.contains(name)) pq.add(name);
       }
       sb.append(pq.size()+"\n");
       while(!pq.isEmpty()) sb.append(pq.poll()+"\n");
       System.out.println(sb);
       br.close();
    }
}
/* 
 * 듣도 못한 사람들 N명
 * 보도 못한 사람들 M명
 * -> 듣도 보도 못한 사람들은?(교집합)
 * 이름 길이는 20자 이하, N,M은 500,000이하, 중복 없음
 * 듣보잡의 수와 명단(사전순)
 * --------------------------
 * 사전순 -> pq쓰면 알아서 정렬
 * 교집합 -> N명 set에 넣어두고 contains로 체크해 pq에
 */
