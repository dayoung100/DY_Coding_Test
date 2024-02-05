import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BJ G5 20437 문자열 게임 2
public class App {
    static int T, K;
    static String W;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        for(int t=0; t<T; t++){
            st = new StringTokenizer(br.readLine());
            W = st.nextToken();
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            //-----입력완료-----
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            int[] count = new int[26]; //알파벳
            for(int p=0; p<W.length(); p++){
                char ch = W.charAt(p);
                int chIdx = ch-'a';
                count[chIdx]++;
                if(count[chIdx]<K) continue;
                int cnt = 0;
                for(int i=p; i>=0; i--){
                    if(ch == W.charAt(i)) cnt++;
                    if(cnt == K){
                        int length = p-i+1;
                        min = Math.min(min, length);
                        max = Math.max(max, length);
                        break;
                    }
                }
            }
            if(min == Integer.MAX_VALUE || max == Integer.MIN_VALUE) sb.append(-1+"\n");
            else sb.append(min+" "+max+"\n");
        }
        System.out.println(sb);
        br.close();
    }
}
/* 
 * T: 게임의 수
 * W: 문자열 (길이 1만 이하)
 * K: 어떤 문자를 정확히 K개 포함
 * [구할 것]
 * - 어떤 문자를 딱 K개 포함하는 가장 짧은 연속 문자열 길이
 * - 어떤 문자를 딱 K개 포함하고 문자열의 첫과 끝 문자가 해당 문자인 가장 긴 연속 문자열 길이
 * 없다면 -1 출력
 * -----------------------------
 * 부분 문자열 -> 투포인터?
 * 딱 K개 포함하는 가장 짧은 = 첫과 끝이 해당 문자여야함
 * -> 해당 글자로 시작하고 끝나는데 그 글자를 K개 가지는 문자열의 최소/최대 길이 구하기
 * 특정 글자가 K개 이상 되는 지점에서부터 뒤로 길이 카운트
 */