import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//BJ S2 1138 한 줄로 서기
public class BJ_S2_1138{
    static int N;
    static int[] numbers;
    static ArrayList<Integer> order = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        numbers = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int n=0; n<N; n++) numbers[n] = Integer.parseInt(st.nextToken());
        //-----input end -----
        //뒤에서부터 줄세우기
        for(int n=N-1; n>=0; n--){
            //적절한 위치 찾기
            int index = 0;
            int cnt = 0;
            for(int i=0; i<order.size(); i++){
                if(order.get(i) > n) cnt++;
                if(cnt == numbers[n]) {
                    index = i+1;
                    break;
                }
            }
            //리스트에 넣기
            order.add(index, n+1);
        }
        //출력하기
        for(int i=0; i<order.size(); i++) System.out.print(order.get(i)+" ");
        br.close();
    }
}
/* 
 * 자기보다 큰 사람이 왼쪽에 몇명 있는지만을 기억
 * 키는 모두 다름
 * 키 순서대로 왼쪽에 자기보다 큰 사람이 몇 명 있었는지 주어짐
 * -------------------------
 * 키 큰 사람부터 세우고 그 다음 사람은 앞에 몇명있는지 보고 넣기
 * 리스트 -> 인덱스로 삽입
 * ---------------------
 * 키번호 | 1 2 3 4
 * 인풋   | 2 1 1 0
 * -----------------
 * 리스트 [4] -> [4,3] -> [4,2,3] -> [4,2,1,3]
 */