import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G5_2467{
    static int N;
    static long[] numbers;
    static int[] ans = new int[] {-1, -1}; //정답의 인덱스
    static long sum = Long.MAX_VALUE; //특성값의 차
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        numbers = new long[N];
        st = new StringTokenizer(br.readLine());
        for(int n=0; n<N; n++) numbers[n] = Long.parseLong(st.nextToken());
        //----- 입력 완료 -----
        int left = 0, right = N-1; //좌우 포인터
        while(left<right){
            long tmpSum = numbers[left]+numbers[right];
            //sum보다 작으면 갱신
            if(Math.abs(tmpSum) < Math.abs(sum)){
                sum = tmpSum;
                ans[0] = left;
                ans[1] = right;
                if(tmpSum == 0) break; //차가 0이면 더 탐색할 필요없음
            }
            //포인터 이동
            if(tmpSum<0) left+=1;
            else if(tmpSum>0) right-=1;
        }
        System.out.println(numbers[ans[0]]+" "+numbers[ans[1]]);
        br.close();
    }
}
/* 
 * 산성: 1~10억
 * 알칼리성: -1~-10억
 * 특성값 두 개의 합이 0에 가장 가까울 때 그 두 용액의 특성값
 * 알칼리끼리, 산성끼리도 가능함
 * 용액 최대 10만개
 * 입력은 오름차순으로 주어짐, 출력도 오름차순으로 해야
 * --------------------------
 * 10만개에서 2개를 뽑는 조합
 * 포인터 두개를 양끝에
 * 양끝의 합이 음수 -> 왼쪽 포인터를 오른쪽으로
 * 양끝의 합이 양수 -> 오른쪽 포인터를 왼쪽으로
 * 둘의 차의 절댓값이 최소가 되면 갱신
 * 포인터 두개가 같거나 엇갈리면 종료
 */