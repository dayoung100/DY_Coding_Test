import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
    int n, m; //위치 정보
    int beforeD; //직전 이동 정보
    int sum; //연료의 합
    public Node(int n, int m, int beforeD, int sum){
        this.n = n;
        this.m = m;
        this.beforeD = beforeD;
        this.sum = sum;
    }

    @Override
    public int compareTo(Node o){
        return this.sum - o.sum;
    }
}

//BJ S3 17484 진우의 달 여행(small)
public class BJ_S3_17848 {
    static int N, M;
    static int[][] board;
    static int[] dm = {-1, 0, 1}; //가로
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for(int n=0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            for(int m=0; m<M; m++) board[n][m] = Integer.parseInt(st.nextToken());
        }//-----입력 완료-----
        for(int i=0; i<M; i++){
            //각 시작 위치에 대해 탐색
            solution(new int[] {0, i});
        }
        System.out.println(ans);
        br.close();
    }

    public static void solution(int[] start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start[0], start[1], -1, board[start[0]][start[1]]));
        while(!pq.isEmpty()){
            Node cur = pq.poll();

            //N에 도달하면 도착
            if(cur.n == N-1){
                ans = Math.min(ans, cur.sum);
                continue;
            }

            for(int d=0; d<3; d++){
                if(d == cur.beforeD) continue; //같은 방향으로 두번 연속 이동 불가
                int nn = cur.n + 1;
                int nm = cur.m + dm[d];
                if(nm<0 || nn<0 || nm>=M || nn>=N) continue;
                if(cur.sum+board[nn][nm] > ans) continue;
                pq.offer(new Node(nn, nm, d, cur.sum+board[nn][nm]));
            }
        }
    }
}
/*
 * 지구 -> 달로 가는 최단거리(위 -> 아래)
 * - 좌하단, 하단, 우하단으로만 이동가능
 * - 같은 방향으로 연속해서 움직일 수 없음
 * - 지구의 위치 어디서든 출발 가능
 * 가로세로 2~6사이, 한 칸은 100이하
 * -----------------------------
 * 시작위치 다르게 탐색(최대 6번)
 */
