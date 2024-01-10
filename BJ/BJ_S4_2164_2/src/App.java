import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class App {
    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        solution();
        System.out.println(sb);
        br.close();
    }

    public static void solution(){
        Queue<Integer> q = new ArrayDeque<>();
        for(int i=1; i<=N; i++) q.offer(i); //큐에 1부터 순서대로 넣음
        //하나남을 때까지 while
        while(q.size() > 1){
            q.poll(); //가장 위의 카드 버리기
            q.offer(q.poll()); //가장 위의 카드 아래로 옮기기
        }
        sb.append(q.poll()+"\n");
    }
}
/*
 * 1부터 N까지의 번호가 붙어있는 카드, 1이 위쪽
 * 카드가 한장 남을 때까지 반복
 * - 제일 위의 카드를 바닥에 버리기
 * - 제일 위에 있는 카드를 제일 아래로 옮기기
 * 제일 마지막에 남게되는 카드는?
 * N<=500,000
 * -------------------------------
 * 일단 생긴 거는 큐
 */