import java.util.*;
import java.io.*;

//BJ S5 1316 그룹 단어 체커
public class BJ_S5_1316{
    private static int cnt = 0;
    private static boolean[] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        for(int n=0; n<N; n++){
            visited = new boolean[26];
            st = new StringTokenizer(br.readLine());
            String word = st.nextToken();
            char prev = '0';
            boolean flag = true;
            for(int i=0; i<word.length(); i++){
                if(word.charAt(i) == prev) continue;
                if(visited[word.charAt(i)-'a']) {
                    flag = false;
                    break;
                }
                visited[word.charAt(i)-'a'] = true;
                prev = word.charAt(i);
            }
            if(flag) cnt++;
        }
        System.out.println(cnt);
    }
}
/* 
 * 그룹단어: 단어에 존재하는 모든 문자에 대해 각 문자가 연속하는 경우
 * ccazzzbb : 그룹단어 / kin : 그룹단어
 * aabbbccb : 그룹단어 아님
 * 입력 받으면 그룹단어의 개수 출력
 * 26개의 알파벳 등장했는지 체크하는 배열 -> 재등장없어야
 * 직전 알파벳과 같으면 넘어가고 다르면 등장 여부 체크
 */