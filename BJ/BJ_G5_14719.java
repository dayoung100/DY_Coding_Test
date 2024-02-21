import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BJ G5 14719 빗물
public class BJ_G5_14719{
    static int W, H;
    static int[][] blocks;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        blocks = new int[H][W];
        st = new StringTokenizer(br.readLine());
        for(int w=0; w<W; w++){
            int height = Integer.parseInt(st.nextToken());
            for(int h=0; h<height; h++) blocks[h][w] = 1;
        }//----- 입력 완료 -----
        int sum = 0; //빗물 총량
        for(int h=0; h<H; h++){
            int cnt = 0; //한 줄의 빗물 카운트
            boolean isOpen = false; //고일 수 있는가 벽을 체크
            for(int w=0; w<W; w++){
                //빈칸인데 왼편에 열린 괄호가 없음 -> 패스
                if(blocks[h][w] == 0 && !isOpen) continue;
                //열린 괄호가 없는데 벽이 나타남 -> 괄호 열기
                if(blocks[h][w] == 1 && !isOpen) isOpen = true;
                //괄호가 열려있는데 빈칸임 -> 물 고일 수 있음 -> cnt업
                else if(blocks[h][w] == 0 && isOpen) cnt++;
                //물이 고이고 있는데(cnt) 벽이 나타남 -> 물 고임 -> cnt 반영
                else if(blocks[h][w] == 1 && cnt > 0) {
                    sum += cnt;
                    cnt = 0;
                }
            }
        }
        sb.append(sum);
        System.out.println(sb);
        br.close();
    }
}
/* 
 * H:최대 세로 / W: 최대 가로(1~500)
 * 고이는 빗물의 총량
 * --------------------
 * 2차원 배열 만들기 -> 아래에서부터 행방향으로 빈 공간 찾기
 * 괄호 열고닫기처럼 시작과 끝을 체크, 시작과 끝이 다 있어야 물이 고일 수 있음
 */