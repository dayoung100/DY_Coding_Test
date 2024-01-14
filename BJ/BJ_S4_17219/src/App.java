import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

//BJ S4 17219 비밀번호 찾기
public class App {
    static int N, M;
    static HashMap<String, String> map = new HashMap<>(); 

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for(int n=0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            String key = st.nextToken();
            String pw = st.nextToken();
            map.put(key, pw);
        }
        for(int m=0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            String question = st.nextToken();
            sb.append(map.get(question)+"\n");
        }
        System.out.println(sb);
        br.close();
    }
}
/* 
 * 총 사이트 수 N(100,000이하)
 * 찾으려는 사이트 수 M(100,000이하)
 * 사이트 주소와 비번은 공백으로 구분, 최대 20자
 * M개의 사이트 비번을 순서대로 출력
 * --------------------
 * 맵 이용
 */