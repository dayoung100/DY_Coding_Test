import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BJ S3 1515 수 이어 쓰기
public class BJ_S3_1515 {
    static char[] N_arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N_arr = st.nextToken().toCharArray();
        //-----입력 완료-----
        int idx = 0;
        double max = Math.pow(10, N_arr.length+1); //N_arr의 길이로 만들어질 수 있는 최대의 숫자(10의 제곱수)
        for(int tmp=1; tmp<max; tmp++){ //tmp: 특정 수
            char[] T_arr = String.valueOf(tmp).toCharArray(); //특정수를 string으로
            for(int i=0; i<T_arr.length; i++){
                //idx번째의 수가 특정수와 겹치는 부분이 있음
                //-> 그 수가 될 수 있음 -> 다음 수 비교
                if(T_arr[i] == N_arr[idx]) idx++;
                //N_arr을 모두 보면 종료
                if(idx == N_arr.length) {
                    sb.append(tmp);
                    System.out.println(sb);
                    return;
                }
            }
        }
    }
}
/* 
 * 1부터 N까지 모든 수 차례대로
 * -> 몇개를 지움(아무것도 안지울 수 있음)
 * -> 남은 수 보고 최소의 N을 구하기
 * 최대 3000자리까지 가능
 * ---------------------------
 * 234092 -> 2,3,4,10,19,20 -> 20
 * 999909 -> 9,19,29,39,40,49 -> 49
 * 82340329923 -> 8,12,13,14,20,23,29,39,42,43 -> 43
 * ---------------------------
 * N을 자릿수별로 확인 -> 특정 수가 완성되는지
 * 특정수: N이하, 점점 커짐
 * 특정수를 string으로 해서 자릿수별 체크
 */