import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S2_2630 {
    static int N;
    static int[][] board;
    static int whiteNum, blueNum;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) board[i][j] = Integer.parseInt(st.nextToken());
        }//-----입력 완료 -----
        cut(0,0,N);
        System.out.println(whiteNum+"\n"+blueNum);
    }

    public static void cut(int r, int c, int size){
        int sum = 0;
        for(int i=r; i<r+size; i++){
            for(int j=c; j<c+size; j++) sum+=board[i][j];
        }

        if(sum == 0){ //흰종이 완성
            whiteNum+=1;
        }else if(sum == size*size){ //파란 종이 완성
            blueNum+=1;
        }else{ //완성 못함
            int half = size/2;
            //사분할
            cut(r, c, half);
            cut(r+half, c, half);
            cut(r, c+half, half);
            cut(r+half, c+half, half);
        }
    }
}
/* 
 * 분할 정복
 */