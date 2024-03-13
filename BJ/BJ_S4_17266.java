import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BJ S4 17266 어두운 굴다리
public class BJ_S4_17266{
    static int N, M;
    static int[] load; //0과 1로 길이 밝혀졌는지 표시
    static int[] light;
    static int height = 1;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N  = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        light = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int m=0; m<M; m++) light[m] = Integer.parseInt(st.nextToken());
        //-----입력 완료-----
        int front = light[0]; //0부터 첫번째 가로등까지의 차이
        int gap = 0;
        for(int m=0; m<M-1; m++) {
            gap = Math.max(gap, light[m+1]-light[m]);
        }
        if(gap%2 == 0) gap /= 2;
        else gap  = gap/2+1;
        int back = N - light[M-1]; //마지막 가로등과 굴다리 끝의 차이
        int ans = Math.max(gap, Math.max(front, back));
        System.out.println(ans);
    }
}
/* 
 * 굴다리 모두 밝히기
 * N: 굴다리 길이
 * M: 가로등 설치 개수
 * x: 각 가로등의 위치(중복x)
 * 가로등 높이 H -> 좌우로 H만큼 비출 수 있음
 * 최소한의 높이로 모든 길 밝히기
 * 가로등 높이는 모두 같아야
 * -------------------------------
 * 각 가로등 간 최대거리 /2 or 양 끝은 최대거리 /1
 */