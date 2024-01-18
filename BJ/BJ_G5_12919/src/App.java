import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

// BJ G5 A와 B 2
public class App {
    static String S, T;
    static HashSet<String> visited = new HashSet<>();
    static int ans = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        S = st.nextToken();
        st = new StringTokenizer(br.readLine());
        T = st.nextToken();
        //-----입력 완료-----
        solution(S);
        sb.append(ans);
        System.out.println(sb);
        br.close();
    }

    //DFS
    public static void solution(String S){
        visited.add(S); //방문 체크
        //백트래킹 조건: ans 값이 1이 되면 종료(만들 수 있다는 뜻이므로)
        if(ans == 1) return;
        //기저조건: S가 T와 같으면 종료
        if(S.equals(T)){
            ans = 1;
            return;
        }
        //기저조건: S가 T의 길이보다 길어지면 종료
        if(S.length() > T.length()) return;
        //백트래킹 조건: S나 S'가 T의 부분문자열이어야함
        StringBuilder sb2 = new StringBuilder();
        String reverseS = sb2.append(S).reverse().toString();
        if(!T.contains(S) && !T.contains(reverseS)) return;
        
        //1. 문자열 뒤에 A 추가
        if(!visited.contains(S+"A")) solution(S+"A");
        //2. 문자열 뒤에 B 추가 후 뒤집기
        sb2 = new StringBuilder();
        sb2.append(S+"B");
        String n_S = sb2.reverse().toString();
        if(!visited.contains(n_S)) solution(n_S);
    }
}
/* 
 * A, B로만 이루어진 영어단어 S, T
 * S -> T로 바꿀 수 있으면 1, 없으면 0 출력
 * [행동]
 * - 문자열 뒤에 A 추가
 * - 문자열 뒤에 B 추가 후 뒤집기
 * S: 1~49, T: 2~50, S길이 < T길이
 * -----------------------------------
 * 문자열 뒤집기 -> sb.reverse
 * 가능 여부 -> S길이 > T길이가 됐을 때 스탑?
 * T와 같은지 여부 -> string.equals로
 * dfs...
 * ------------------------------
 * 10%에서 시간 초과 남 -> visited체크
 * 아니면... 만들어진 문자열이 T 내부에 존재하는지 체크 + 문자열의 역순도
 * -> contains.. + sb.reverse
 */