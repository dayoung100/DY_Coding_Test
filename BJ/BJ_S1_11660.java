import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S1_11660 {
    static int N, M;
    static int[][] board;
    static int[][] sumBoard;
    static int[] sumLine;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        //board 채우기
        board = new int[N][N];
        sumBoard = new int[N][N];
        sumLine = new int[N];
        for(int n=0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            int tempSum = 0;
            for(int i=0; i<N; i++) {
                int input = Integer.parseInt(st.nextToken());
                board[n][i] = input;
                //첫째점부터 모든 칸까지의 구간합 미리 계산
                tempSum += input;
                sumBoard[n][i] = sumLine[i]+tempSum;
                sumLine[i] = sumBoard[n][i];
            }
        }
        //구간합 계산하기
        for(int m=0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken())-1;
            int y1 = Integer.parseInt(st.nextToken())-1;
            int x2 = Integer.parseInt(st.nextToken())-1;
            int y2 = Integer.parseInt(st.nextToken())-1;
            int sum = sumBoard[x2][y2];
            sum -= x1-1 >= 0 ? sumBoard[x1-1][y2] : 0;
            sum -= y1-1 >= 0 ? sumBoard[x2][y1-1] : 0;
            sum += x1-1 >= 0 && y1-1 >= 0 ? sumBoard[x1-1][y1-1] : 0;
            sb.append(sum+"\n");
        }
        System.out.println(sb);
        br.close();
    }
}
/* 
 * 첫 칸에서부터 구간합 미리 구하기
 * (x1, y1)에서 (x2, y2)까지의 합 
 * = (0,0)에서 (x2, y2)까지의 합
 * - (0,0)에서 (x1-1, y2)까지의 합
 * - (0,0)에서 (x2, y1-1)까지의 합
 * + (0,0)에서 (x1-1,y1-1)까지의 합
 */