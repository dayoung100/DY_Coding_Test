import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

//BJ S2 15666 N과 M(12)
public class BJ_S2_15666 {
    static int N, M;
    static Set<Integer> numberSet = new HashSet<>();
    static ArrayList<Integer> numbers = new ArrayList<>();
    static int[] check;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int n=0; n<N; n++) numberSet.add(Integer.parseInt(st.nextToken()));
        //----- 입력 완료 -----
        for(int num : numberSet) numbers.add(num); //중복 제거된 리스트
        Collections.sort(numbers);
        check = new int[M];
        for(int m=0; m<M; m++) check[m] = -1; //초기화
        solution(0, 0);
        System.out.println(sb);
        br.close();
    }

    public static void solution(int start, int cnt){
        if(cnt == M){
            //M개 다 뽑았으면 종료
            for(int m=0; m<M; m++) sb.append(numbers.get(check[m])+" ");
            sb.append("\n");
            return;
        }
        for(int i=start; i<numbers.size(); i++){
            check[cnt] = i;
            solution(i, cnt+1);
            check[cnt] = -1;
        }
    }
}
/* 
 * N: 자연수 개수 / M: 뽑을 수 개수 (1~8)
 * 같은 수 여러번 고르기 가능
 * 비내림차순(점점 커지기)
 * 만족하는 수열을 사전 증가순으로 출력
 * -----------------------------
 * 사전 증가 순 -> 정렬해주기
 * 같은 수 여러번 고르기 가능 -> 중복 수 제거(1799로 만드는 건 179로 만드는 것과 같음)
 * M이 8까지 -> 재귀
 */