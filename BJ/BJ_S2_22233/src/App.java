import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

//BJ_S2_22233_가희와 키워드
public class App {
    static int N, M;
    static HashSet<String> set = new HashSet<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            set.add(st.nextToken());
        } //N개의 메모장 채우기
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine(), ",");
            while(st.hasMoreTokens()) {
                String keyword = st.nextToken();
                if(set.contains(keyword)) set.remove(keyword);
            }
            sb.append(set.size()+"\n");
        }
        System.out.println(sb);
        br.close();
    }
}
/*
 * N개의 키워드 in 메모장(중복x)
 * 글 작성시 최대 10개의 키워드를 이용
 * 이 키워드는 메모장에 있는 것도 있고 없는 것도 있음
 * 메모장에 있는 키워드는 글 쓴 이후 메모장에서 지워짐(중복x)
 * 글을 쓰고 나서 메모장에 남아있는 키워드 수는?(매번)
 * 글쓸때 사용된 키워드는 ,를 구분자로 해서 구분
 * N,M<=2x10^5
 * -----------------------
 * 메모장에 있는 키워드를 어떤 자료구조에 담아두기
 * 글을 쓸 때마다 사용된 키워드가 그 자구에 있는지 비교 후 제거
 * M번 반복하며 남은 메모 수 출력
 * -----------------------
 * contain 연산 시
 * - ArrayList, LinkedList: O(n)
 * - set: O(1)
 * - map(containsKey): O(1)
 */