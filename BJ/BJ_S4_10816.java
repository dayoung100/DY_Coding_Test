import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//baekjoon S4 10816 숫자 카드2
public class BJ_S4_10816 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] card = new int[20000000+1]; //10,000,000 = 0
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) card[Integer.parseInt(st.nextToken())+10000000]++;
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			sb.append((card[Integer.parseInt(st.nextToken())+10000000]+" "));
		}
		System.out.println(sb);
	}

}
