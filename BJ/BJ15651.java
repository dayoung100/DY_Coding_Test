import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ15651 {

	static int N, R;
	static int[] numbers, inputs;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		numbers = new int[R];
		inputs = new int[N];
		for(int i=0; i<N; i++) inputs[i] = i+1;
		
		perm(0);
		bw.flush();
		bw.close();
	}

	private static void perm(int cnt) throws Exception {
		if(cnt == R) {
			for(int j=0; j<R; j++) {
				bw.write(numbers[j] + " ");
			}
			bw.write("\n");
			return;
		}
		for(int i=0; i<N; i++) {
			numbers[cnt] = inputs[i];
			perm(cnt+1);
		}
	}
}