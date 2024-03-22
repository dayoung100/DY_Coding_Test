import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_B1_1110 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int cnt = 0;
        int num = N;
        while(true){
            int left = 0, right = 0;
            //한자리수면 두자리로 만들기
            if(num < 10) right = num;
            else{
                left = num/10;
                right = num%10;
            }
            //각 자리의 수 더하기
            int sum = left + right;
            //원래 수의 오른쪽 자리 + 합의 오른쪽 자리
            int newNum = right*10 + sum%10;
            cnt++;
            //새로운 수가 같은지 체크
            if(newNum == N) break;
            else num = newNum;
        }
        System.out.println(cnt);
    }
}
