//BJ S3 20310 타노스

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class App {
    static String S;
    static char[] S_arr;
    static int zeroCnt = 0, oneCnt = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        S = st.nextToken(); //입력 완료
        S_arr = S.toCharArray();
        //0과 1 세기
        for(int i=0; i<S.length(); i++){
            if(S.charAt(i) == '0') zeroCnt++;
            else oneCnt++;
        }
        zeroCnt /= 2;
        oneCnt /= 2;
        //앞에서부터 1 제거
        for(int s=0; s<S_arr.length; s++){
            if(oneCnt==0) continue;
            if(S_arr[s] != '1') continue;
            S_arr[s] = '-';
            oneCnt--;
        }
        //뒤에서부터 0 제거
        for(int s=S_arr.length-1; s>=0; s--){
            if(zeroCnt == 0) continue;
            if(S_arr[s] != '0') continue;
            S_arr[s] = '-';
            zeroCnt--;
        }
        //문자열 만들기
        for(int s=0; s<S_arr.length; s++){
            if(S_arr[s] != '-') sb.append(S_arr[s]);
        }
        System.out.println(sb);
        br.close();
    }
}
/* 
 * 0과 1로 이루어진 문자열 S
 * 0과 1의 수를 각각 절반으로 줄인 문자열 S'
 * S'의 후보 중 사전순으로 가장 빠른 것은?
 * ------------------------------
 * 사전순으로 빠름 => 00011111꼴로 구성
 * 0과 1개수 세기-> 절반으로 나누기 -> sb에 0먼저 다 넣고 1 다 넣기
 * ------------------------------
 * 절반의 0과 절반의 1을 제거하여 -> 순서를 다 섞는게 아님
 * -> 00110000 -> 0001이 될 수 없음??? wow
 */