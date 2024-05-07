import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//BJ S1 17615 볼 모으기
public class BJ_S1_17615 {
    static int N;
    static char[] balls;
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        balls = st.nextToken().toCharArray();
        //----------input end-------------
        solution(0, 'R');
        solution(0, 'B');
        solution(1, 'R');
        solution(1, 'B');
        System.out.println(answer);
    }

    /**
     * 이동 시뮬레이션 수행
     * @param flag 0이면 좌->우 / 1이면 좌<-우
     * @param color flag쪽 끝의 컬러
     * @return 이동횟수
     */
    static void solution(int flag, char color){
        char[] balls_copy = Arrays.copyOf(balls, balls.length);
        int count = 0; //옮겨야할 공의 수 = 변경 횟수
        int startIdx = flag==0?0:balls_copy.length-1; //좌->우 / 좌<-우
        int endIdx = flag==0?balls_copy.length:-1;
        int step = flag==0?1:-1;
        boolean countStart = false; //다른 색의 공이 나온 후부터 세야함

        for(int i=startIdx; i!=endIdx; i+=step){
            if(!countStart) {
                if(balls_copy[i] == color) continue; //같은 색 넘어가기
                countStart = true; //다른 색의 공을 찾았으면 옮길 공의 수를 세기 시작
            }else if(balls_copy[i] == color){ 
                count++;
                if(count > answer) return;
            }
        }
        answer = count;
    }
}
/* 
 * 1. 다른 색의 볼은 모두 점핑 가능
 * 2. 처음에 옮긴 색의 볼을 계속 옮겨야함(다른색x)
 * 이때 최소 몇번 옮겨서 같은 색끼리 모을 수 있는가
 * -----------------------------------------
 * 왼쪽으로 빨강 모으기 / 왼쪽으로 파랑 모으기 /
 * 오른쪽으로 빨강 모으기 / 오른쪽으로 파랑 모으기
 * 옮겨야할 공의 수 = 이동 횟수
 */