import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BJ S3 19941 햄버거 분배
public class BJ_S3_19941{
    static int N, K;
    static char[] fullArr;
    static char[] hamArr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        hamArr = new char[N];
        st = new StringTokenizer(br.readLine());
        String str = st.nextToken();
        fullArr = str.toCharArray(); //-----입력 완료-----
        for(int i=0; i<N; i++){
            if(fullArr[i] == 'H') hamArr[i] = 'H';
        }
        int cnt = 0; //먹을 수 있는 사람 수
        for(int n=0; n<N; n++){
            if(fullArr[n] == 'H') continue;
            //사람을 찾음 -> 먹을 수 있는지 범위 내 제일 왼쪽부터 체크
            for(int pointer = n-K; pointer<=n+K; pointer++){
                if(pointer < 0 || pointer >= N) continue; //범위 체크
                if(hamArr[pointer] == 'H') {
                    cnt++;
                    hamArr[pointer] = '-'; //먹은 것으로 체크
                    break;
                } 
            }
        }
        sb.append(cnt);
        System.out.println(sb);
        br.close();
    }   
}
/* 
 * N: 식탁의 길이(1~20,000)
 * K: 햄버거 선택할 수 있는 거리(1~10)
 * 햄버거를 먹을 수 있는 사람의 최대 수는?
 * ------------------------------
 * 햄버거+사람 배열 / 햄버거 배열
 * 사람 만나면 그 사람이 먹을 가장 왼편의 햄버거를 체크, 먹을 수 있으면 +1
 * N만큼 돌되 P의 수 만큼 K번 뒤로 가는 연산이 추가됨
 */