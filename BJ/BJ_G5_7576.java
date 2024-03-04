import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

//BJ G5 7576 토마토
public class BJ_G5_7576 {
    static int N, M, cnt, day;
    static int[][] board;
    static boolean[][] visited;
    static int[] dn = {-1,1,0,0};
    static int[] dm = {0,0,-1,1};
    static ArrayDeque<int[]> q = new ArrayDeque<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[N][M];
        cnt = 0;
        day = -1;
        for(int n=0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            for(int m=0; m<M; m++){
                int num = Integer.parseInt(st.nextToken());
                if(num == 0) cnt++; //안익은 토마토 수 카운트
                else if(num == 1){
                    q.offer(new int[] {n, m});
                    visited[n][m] = true;
                }
                board[n][m] = num;
            }
        }//----- 입력 완료 -----
        while(!q.isEmpty()){
            day++;
            int size = q.size();
            for(int i=0; i<size; i++){
                int[] cur = q.poll();
                
                for(int d=0; d<4; d++){
                    int nn = cur[0]+dn[d];
                    int nm = cur[1]+dm[d];
                    if(nn<0 || nm<0 || nn>=N || nm>=M) continue;
                    if(visited[nn][nm]) continue;
                    if(board[nn][nm] != 0) continue; //토마토 없으면 큐에 x
                    q.offer(new int[] {nn, nm});
                    visited[nn][nm] = true;
                    cnt--;
                }
            }            
        }
        System.out.println(cnt<=0 ? day : -1);
    }
}
/* 
 * 하루 후: 익은 토마토 상하좌우 -> 익음
 * 며칠이 지나면 다 익는가?(최소날짜)
 * N: 세로칸 수, M: 가로칸 수 (2~1000)
 * 1: 익은 토마토/0: 익지않은 토마토/-1:토마토 없음
 * 시작부터 다 익어있으면 0/모두 익지는 못하는 상황이면 -1
 * --------------------------------
 * BFS -> 최단거리
 * 미리 익지 않은 토마토 개수 카운트 -> 마지막에 다 익었는지 비교
 */