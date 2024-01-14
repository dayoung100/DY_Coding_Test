import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// BJ S3 1463 1로 만들기
public class App {
    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        //-----입력 완료-----
        solution();
        System.out.println(sb);
        br.close();
    }

    public static void solution(){
        Queue<Integer> q = new ArrayDeque<>();
        q.add(N);

        int lvl = 0;
        outer:
        while(true){
            int size = q.size();
            for(int s=0; s<size; s++){
                int value = q.poll();
                if(value == 1) break outer;
                for(int k=0; k<3; k++){
                    if(k==0 && value%3 == 0) q.add(value/3);
                    else if(k==1 && value%2 == 0) q.add(value/2);
                    else if(k==2 && value>1) q.add(value-1); 
                } 
            }
            lvl = lvl+1;
        }

        sb.append(lvl);
    }
}
/* 
 * - 3으로 나누기
 * - 2로 나누기
 * - 1 빼기
 * N을 위 세 가지 연산을 통해 1로 만들기
 * 연산의 최소 횟수는?
 * N은 100,000 이하
 * ----------------------
 * 최소 횟수 -> BFS
 * 큐 만들어서 레벨별 체크, 큐에는 연산 후의 값 넣기
 */