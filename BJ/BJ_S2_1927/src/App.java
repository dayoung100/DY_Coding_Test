import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class App {
    static int N;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for(int n=0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            int tmp = Integer.parseInt(st.nextToken());
            if(tmp == 0 && pq.isEmpty()) sb.append(0+"\n");
            else if(tmp == 0) sb.append(pq.poll()+"\n");
            else pq.offer(tmp);
        }
        System.out.println(sb);
        br.close();
    }
}
/* 
 * 그냥 pq
 */