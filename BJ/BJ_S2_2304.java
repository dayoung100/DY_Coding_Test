import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_S2_2304{
    static int N;
    static int[][] stick;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        stick = new int[N][2];
        int leftStick = 1001, rightStick = -1; //양 끝이 될 스틱 위치
        for(int n=0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            stick[n][0] = Integer.parseInt(st.nextToken()); //position
            stick[n][1] = Integer.parseInt(st.nextToken()); //height
            leftStick = Math.min(leftStick, stick[n][0]);
            rightStick = Math.max(rightStick, stick[n][0]);
        }//-----input end -----
        Arrays.sort(stick, (o1, o2) -> o2[1] - o1[1]); //높이 순서대로 정렬
        int[] col = new int[1001]; //카운트한 열은 높이로 표시
        int beforeP = stick[0][0]; //직전 스틱 위치
        int beforeH = stick[0][1]; //직전 스틱 높이
        col[beforeP] = beforeH;
        int cnt = 1; //칸 채운 열의 수 -> 양 끝까지 다 채우면 반복 안함(너무 작은 스틱 패스하려고)
        for(int i=1; i<N; i++){
            int nowP = stick[i][0];
            int nowH = stick[i][1];
            if(col[nowP] != 0) continue; //이미 이 자리에 천장이 있음
            int left = Math.min(beforeP, nowP);
            int right = Math.max(beforeP, nowP);
            for(int j=left; j<=right; j++) {
                if(col[j] != 0) continue;
                col[j] = nowH;
                cnt++;
            }
            if(cnt == rightStick - leftStick+1) break;
        }
        //col 기반으로 합 구하기
        int sum = 0;
        for(int idx=leftStick; idx<=rightStick; idx++) sum+=col[idx];
        System.out.println(sum);
        br.close();
    }
}