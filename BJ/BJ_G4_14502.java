import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

//BJ G4 14502 연구소
public class BJ_G4_14502 {
    static int N, M;
    static int[][] board;
    static boolean[][] visited;
    static ArrayList<int[]> virus = new ArrayList<>();
    static int[] dx = new int[] {-1,1,0,0};
    static int[] dy = new int[] {0,0,-1,1};
    static int max = 0, safe = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[N][M];
        for(int n=0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            for(int m=0; m<M; m++) {
                int num = Integer.parseInt(st.nextToken());
                board[n][m] = num;
                if(num==2) virus.add(new int[]{n,m});
                if(num==0) safe++; //안전지대 미리 카운트
            }
        }//-----입력 완료-----
        solution(0);
        sb.append(max);
        System.out.println(sb);
        br.close();
    }

    public static void solution(int cnt){
        if(cnt == 3){
            //3개의 벽을 다 세우면 그만
            int[][] copyBoard = new int[N][M];
            for(int i=0; i<N; i++) copyBoard[i] = board[i].clone();
            max = Math.max(simulation(copyBoard), max);
            return;
        }
        for(int n=0; n<N; n++){
            for(int m=0; m<M; m++){
                if(board[n][m] != 0 || visited[n][m]) continue;
                board[n][m] = 1;
                visited[n][m] = true;
                solution(cnt+1);
                board[n][m] = 0;
                visited[n][m] = false;
            }
        }
    }

    public static int simulation(int[][] copyBoard){
        ArrayDeque<int[]> q = new ArrayDeque<>();
        for(int[] virusPos : virus) q.add(virusPos);
        int cnt = 0; //안전지대->바이러스 가 된 곳 수
        while(!q.isEmpty()){
            int[] cur = q.poll();
            for(int d=0; d<4; d++){
                int nx = cur[0]+dx[d];
                int ny = cur[1]+dy[d];
                if(nx<0 || ny<0 || nx>=N || ny>= M) continue;
                if(copyBoard[nx][ny] != 0) continue;
                copyBoard[nx][ny] = 2;
                cnt++;
                q.add(new int[] {nx, ny});
            }
        }
        return safe - cnt - 3; //기존 안전지대 - 감염된 곳 - 벽세운곳(3개)
    }
}
/* 
 * 0: 빈칸/1: 벽/2: 바이러스
 * 지도 가로 N, 세로 M(3~8)
 * 바이러스는 가로세로로 뻗어나갈 수 있음
 * 벽 3개를 세워서(더도말고 덜도말고) 안전 영역을 최대로
 * 안전영역 크기의 최댓값 구하기
 * ---------------------------
 * 최대 64개 중에서 3개를 뽑는 중복조합의 수(재귀)
 * => 각 경우에 시뮬레이션bfs으로 안전영역 계산...
 */