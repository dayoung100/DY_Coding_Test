import java.util.*;

public class PG_2_pccp2 {
    public static void main(String[] args) throws Exception{
        int[][] land = new int[][] {{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}};
        Solution.solution(land);
    }
}

class Solution {
    static int[][] copyLand; //방문체크용
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int N, M;
    static int cnt = 0; //영역의 크기
    static HashSet<Integer> col_set; //영역이 거친 열 정보 저장
    static int[] col_sum; //열마다 얻을 수 있는 양 저장
    
    public static int solution(int[][] land) {
        int answer = 0;
        N = land.length;
        M = land[0].length;
        copyLand = new int[N][M];
        col_sum = new int[M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++) {
                if(land[i][j] == 0) continue;
                else if(copyLand[i][j] == 0){ //이미 채워진 칸은 탐색x
                    cnt = 1; //초기화
                    col_set = new HashSet<>(); //초기화
                    col_set.add(j); //첫 컬럼의 인덱스 저장
                    //계산하기(BFS)
                    ArrayDeque<int[]> q = new ArrayDeque<>();
                    q.offer(new int[] {i, j});
                    copyLand[i][j] = 1; //방문체크
                    
                    while(!q.isEmpty()){
                        int[] cur = q.poll();
                        for(int d=0; d<4; d++){
                            int nr = cur[0]+dr[d];
                            int nc = cur[1]+dc[d];
                            if(nr<0 || nc<0 || nr>=N || nc>=M) continue;
                            if(land[nr][nc] == 0) continue;
                            if(copyLand[nr][nc] == 1) continue;
                            copyLand[nr][nc] = 1;
                            cnt+=1;
                            col_set.add(nc);
                            q.offer(new int[] {nr, nc});
                        }
                    }// cnt 구하기 완료
                    
                    for(int col : col_set) col_sum[col]+=cnt;
                }
            }
        } //-----col_sum 채우기-----
        
        for(int col : col_sum) answer = Math.max(answer, col);
        
        return answer;
    }
}
/*
세로로 관을 꽂을 때 가장 많은 석유를 얻을 수 있는 석유의 양
맵의 가로 세로는 최대 100
[석유 영역 계산]
처음에 land의 1인 값(석유있는땅)만 체크
-> 크기 계산 bfs나 dfs로
이때 방문체크는 copymap같은걸로
-> 크기 계산 중에 해당 영역이 거치는 열을 기록해두기
-> 열마다 얻을 수 있는 석유량(영역크기) 합하기
[세로로 꽂기]
최대 100번 -> 전체 반복 -> 시간 초과인듯
-> 별도의 배열로 관리: 열마다 얻을 수 있는 양 배열 만들기
*/