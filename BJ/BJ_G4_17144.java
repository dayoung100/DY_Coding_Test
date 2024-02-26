import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BJ G4 17144 미세먼지 안녕!
public class BJ_G4_17144 {
    static int R, C, T;
    static int[][] room;
    static int[] cleaner = new int[2]; //열은 1 고정
    static int[][] calc; //1초 확산 계산용
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        room = new int[R][C];
        int cnt = 0; //cleaner idx
        for(int r=0; r<R; r++){
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<C; c++){
                int num =  Integer.parseInt(st.nextToken());
                room[r][c] = num;
                if(num == -1) {
                    cleaner[cnt] = r;
                    cnt++;
                }
            }
        }//-----입력 완료-----
        for(int t=0; t<T; t++){
            //1. 미세먼지 확산
            spread();
            //2. 공기청정기 작동
            clean();
        }
        //3. 남은 미세먼지 카운트
        int sum = 0;
        for(int r=0; r<R; r++){
            for(int c=0; c<C; c++) sum += room[r][c];
        }
        sb.append(sum+2);
        System.out.println(sb);
        br.close();
    }    

    public static void spread(){
        calc = new int[R][C]; //초기화
        for(int r=0; r<R; r++){
            for(int c=0; c<C; c++){
                if(room[r][c] <= 0) continue;
                int value = 0;
                for(int d=0; d<4; d++){
                    int nr = dr[d]+r;
                    int nc = dc[d]+c;
                    if(nr<0 || nc<0 || nr>=R || nc>=C) continue;
                    if(room[nr][nc] == -1) continue;
                    calc[nr][nc] += room[r][c]/5; //확산
                    value += room[r][c]/5;
                }
                calc[r][c] -= value; //(r,c)에 남을 양
            }
        }
        //calc 기반으로 room 갱신
        for(int r=0; r<R; r++){
            for(int c=0; c<C; c++) room[r][c] += calc[r][c];
        }
    }

    public static void clean(){
        //위쪽 바람: 반시계 순환
        for(int r=cleaner[0]-1; r>0; r--) room[r][0] = room[r-1][0];
        for(int c=0; c<C-1; c++) room[0][c] = room[0][c+1];
        for(int r=0; r<cleaner[0]; r++) room[r][C-1] = room[r+1][C-1];
        for(int c=C-1; c>1; c--) room[cleaner[0]][c] = room[cleaner[0]][c-1];
        room[cleaner[0]][1] = 0;
        //아래쪽 바람: 시계 순환
        for(int r=cleaner[1]+1; r<R-1; r++) room[r][0] = room[r+1][0];
        for(int c=0; c<C-1; c++) room[R-1][c] = room[R-1][c+1];
        for(int r=R-1; r>cleaner[1]; r--) room[r][C-1] = room[r-1][C-1];
        for(int c=C-1; c>1; c--) room[cleaner[1]][c] = room[cleaner[1]][c-1];
        room[cleaner[1]][1] = 0;
    }
}
/* 
 * 공기청정기 항상 1번열, 두 행을 차지
 * [1초동안]
 * 1. 미세먼지 확산
 * - 모든 칸에서 동시에
 * - 상하좌우로 확산(벽이나 공기청정기 위치는 x)
 * - 양은 '원래 미세먼지 양 나누기 5'
 * - 원위치에 남는 양은 -원래 양 - 확산된 양'
 * 2. 공기청정기 작동
 * - 위쪽 바람: 반시계 순환
 * - 아래쪽 바람: 시계방향 순환
 * - 바람이 불면 먼지가 한 칸씩 이동
 * - 공기청정기로 들어간 먼지는 정화
 * [출력] T초 후 남아있는 미세먼지의 양
 */
