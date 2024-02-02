import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class App {
    static int N, M;
    static ArrayList<String> title = new ArrayList<>();
    static ArrayList<Integer> cutLine = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for(int n=0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            String txt = st.nextToken();
            int number = Integer.parseInt(st.nextToken());
            //중복 제거(오름차순이니까 마지막 값과 같으면 넣지 않음)
            if(n>0 && number == cutLine.get(cutLine.size()-1)) continue;
            title.add(txt);
            cutLine.add(number);
        } //---칭호 부분 세팅 완료 ---
        for(int m=0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            //binarysearch: 찾으면 그 위치, 없으면 (들어갈 위치)*(-1) - 1을 반환
            int result = Collections.binarySearch(cutLine, num);
            //값을 찾으면 -> 그 인덱스에 해당하는 title 가져오기
            if(result >= 0) sb.append(title.get(result));
            //값을 못찾으면 -> 음수 변환 -> 거기에 해당하는 title 가져오기
            else {
                int tmpIdx = (result+1)*(-1);
                sb.append(title.get(tmpIdx)); 
            }
            sb.append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}
/* 
 * 단순 if문 문제? 수가 커서 초과?
 * 10^5명의 캐릭터 x 10^5개의 칭호 탐색...
 * 바이너리
 */