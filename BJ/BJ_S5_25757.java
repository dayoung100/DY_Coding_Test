import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BJ_S5_25757 {
    static int N;
    static String game;
    static HashSet<String> set = new HashSet<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        game = st.nextToken();
        for(int n=0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            set.add(st.nextToken());
        }//-----입력 완료-----
        switch (game) {
            case "Y":
                sb.append(set.size());
                break;
            case "F":
                sb.append(set.size()/2);
                break;
            case "O":
                sb.append(set.size()/3);
                break;
        }
        System.out.println(sb);
        br.close();
    }
}
/* 
 * N: 같이 플레이하길 신청한 횟수(1~100,000)
 * 윷놀이Y - 2명
 * 같은 그림 찾기F - 3명
 * 원카드O - 4명
 * 한번 같이 플레이한 사람과는 다시 하지 않음
 * 최대 몇번이나 함께 게임을 플레이할 수 있는가?
 * 임스는 1명 무조건 포함됨
 * --------------------------
 * 전체 입력 받아서 set -> 중복 제거 후 인원수로 나누기
 */