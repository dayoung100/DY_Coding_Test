import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BJ S2 18111 마인크래프트
public class App {
    static int N, M, B;
    static int maxH = 0, minH = Integer.MAX_VALUE;
    static int[][] board;
    static int ansTime=Integer.MAX_VALUE, ansH=0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for(int n=0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            for(int m=0; m<M; m++) {
                int num = Integer.parseInt(st.nextToken());
                board[n][m] = num;
                maxH = Math.max(maxH, num);
                minH = Math.min(minH, num);
            }
        }
        //-----입력 완료----
        for(int i=minH; i<=maxH; i++){
            //가장 높은 높이와 가장 낮은 높이 사이의 값으로 땅을 고를것
            //이때 i가 타겟높이, 모든 i를 계산
            int time = 0; //걸리는 시간 계산
            int blockNum = B; //채우는데 필요한 블럭 수
            for(int n=0; n<N; n++){
                for(int m=0; m<M; m++){
                    int num = board[n][m];
                    //높으면 파내기
                    if(num > i) {
                        time += (num-i)*2;
                        blockNum += num-i;
                    }
                    //낮으면 채우기
                    else if(num < i) {
                        time += i-num;
                        blockNum -= i-num;
                    }
                }
            }
            if(blockNum<0) continue;
            if(time <= ansTime){
                ansTime = time;
                ansH = i; //i는 점점 커지니까 항상 더 큰 값으로 갱신될 것
            }
        }
        sb.append(ansTime+" "+ansH);
        System.out.println(sb);
        br.close();
    }
}
