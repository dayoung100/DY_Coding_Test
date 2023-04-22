import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//baekjoon S5 11866 요세푸스 문제 0
public class BJ_S5_11866 {

	static int N, M;
	static Queue<Integer> q = new ArrayDeque<Integer>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		//--------------input end------------------
		for(int i=1; i<=N; i++) q.offer(i); //큐 초기 세팅
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		while(!q.isEmpty()) {
			for(int i=0; i<M-1; i++) q.offer(q.poll()); //skip
			sb.append(q.poll());
			if(!q.isEmpty()) sb.append(", ");
		}
		sb.append(">");
		System.out.println(sb);
	}

}
