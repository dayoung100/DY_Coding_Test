import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BJ S2 2512 예산
public class App {
    static int N;
    static int[] budgets;
    static int maxBudget;
    static int[] cases; //후보군 -> 이분 탐색
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //지방의 수
        st = new StringTokenizer(br.readLine());
        int sum = 0;
        int budgets_max = 0;
        budgets = new int[N]; //예산 요청
        for(int n=0; n<N; n++){
            budgets[n] = Integer.parseInt(st.nextToken());
            if(budgets[n] > budgets_max) budgets_max = budgets[n];
            sum += budgets[n];
        }
        st = new StringTokenizer(br.readLine());
        maxBudget = Integer.parseInt(st.nextToken()); //최대 예산
        //----- 입력 완료 -----
        //예산요청들의 합이 최대 예산을 넘지 않을 때 처리
        if(sum <= maxBudget){
            sb.append(budgets_max);
            System.out.println(sb);
            br.close();
            return;
        }
        //0~예산요청 중 최댓값 사이를 이분탐색
        cases = new int[budgets_max+1];
        for(int i=0; i<cases.length; i++) cases[i] = i;
        //이분탐색 구현
        int low = 0; int high = cases.length;
        int mid = (low+high) / 2;
        while(low <= high){
            int cur = cases[mid];
            //상한값이 mid의 값일 때 총 예산을 계산
            int total = 0;
            for(int i=0; i<budgets.length; i++){
                if(budgets[i]<=cur) total+=budgets[i];
                else total+= cur;
            }
            //총 예산이 최대예산보다 작으면 더 커져야
            if(total < maxBudget) {
                low = mid+1;
                mid = (low+high) / 2;
            }
            //총 예산이 최대예산보다 크면 더 줄여야
            else if(total > maxBudget) {
                high = mid - 1;
                mid = (low+high) / 2;
            }
            //둘이 같으면 찾은 것
            else break;
            //최대예산보다 총 예산이 작아지는 경우중 최대의 값 찾기
        }
        sb.append(cases[mid]);
        System.out.println(sb);
        br.close();
    }
}
/* 
 * 상한액: 상한액 이상인 요청에는 상한액만큼 배정
 *        상한액 이하의 요청에는 요청한 금액만큼 배정
 * 정해진 예산 총액 이하에서 가능한 최대의 예산을 배정하기
 * 그때 배정된 예산 중 최댓값 출력
 * --------------------------
 * 출력값 = 상한액 OR 예산요청 중 최댓값
 * 걍 이분탐색..? 
 * ----------------
 * [이분탐색 범위]
 * 0~최대예산 -> 메모리초과
 * 0~요청 예산 중 최댓값 -> 통과
 */