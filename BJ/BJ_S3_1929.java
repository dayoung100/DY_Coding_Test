import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//baekjoon S3 1929 소수 구하기
public class BJ_S3_1929 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] numbers = new int[N+1];
        for(int i=2; i<numbers.length; i++) numbers[i] = i;//초기화
        for(int i=2; i*i<=N; i++){
            if(numbers[i] == 0) {
                numbers[i] = 0;
                continue; //무언가의 배수면 패스
            }
            for(int j=i; j<numbers.length; j+=i){
                if(j%i == 0 && j!=i) numbers[j] = 0;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=M; i<N+1; i++) {
            if(numbers[i]!=0) sb.append(i+"\n");
        }
        System.out.println(sb.toString());
    }
}
/*
에라토스테네스의 체 이용:시간복잡도 O(nlog(logn))
출력 : 범위 내 소수 전부
-----------------------------
가장 작은 수 고르기 : 그 수는 소수
-> 그 수의 배수를 전부 제외하기
-> 가장 작은 수 고르기 : 그 수는 소수...
 */