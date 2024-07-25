import java.util.*;
import java.io.*;

//BJ G4 1987 알파벳
public class BJ_G4_1987{
    private static int R, C;
    private static char[][] board;
    private static int[] dr = {0,0,-1,1};
    private static int[] dc = {-1,1,0,0};
    private static boolean[] visited = new boolean[26];
    private static int max = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        for(int r=0; r<R; r++){
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            board[r] = line.toCharArray();
        }//-----input end-----
        visited[board[0][0]-'A'] = true;
        solution(0,0,1);
        System.out.println(max);
    }

    static void solution(int r, int c, int cnt){
        for(int d=0; d<4; d++){
            int nr = r+dr[d];
            int nc = c+dc[d];
            if(nr<0||nc<0||nr>=R||nc>=C) continue;
            if(visited[board[nr][nc]-'A']) continue;
            visited[board[nr][nc]-'A'] = true;
            solution(nr, nc, cnt+1); 
            visited[board[nr][nc]-'A'] = false;
        }
        max = Math.max(max, cnt);
        return;
    }
}
/*
 * 행 R, 열 C / 1행1열에 말이 있음
 * 상하좌우로 이동
 * 새로 이동한 칸은 새로운 알파벳이어야, 즉 같은 알파벳 두번 못감
 * 말이 시작칸 포함 최대 몇칸 지나갈 수 있느냐
 * ------------------
 * 맵은 20x20
 * 정보 갖고 이동하는 DFS
 */