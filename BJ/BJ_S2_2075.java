import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_S2_2075{
    static class Node implements Comparable<Node>{
        int r, c, num;
        public Node(int r, int c, int num){
            this.r = r;
            this.c = c;
            this.num = num;
        }
        @Override
        public int compareTo(Node o){
            return o.num - this.num;
        }
    }

    // static int[][] board;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        // board = new int[N][N];
        int minValue = Integer.MAX_VALUE;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                int num = Integer.parseInt(st.nextToken());
                minValue = Math.min(minValue, num); //최소값 먼저 찾기
                // board[i][j] = num;
                pq.offer(new Node(i, j, num));
            }
        }//-----input end-----
        Node answer = new Node(-1, -1, -1);
        for(int i=0; i<N; i++) answer = pq.poll();
        System.out.println(answer.num);
    }
}
/*
 * pq로 순서대로 탐색 -> N번
 */