import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BJ15656 {
	
	static int N, M;
	static int[] numbers, inputs;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		numbers = new int[M];
		inputs = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			inputs[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(inputs);
		
		perm(0);
		bw.flush();
		bw.close();
	}

	private static void perm(int cnt) throws IOException {
		if(cnt == M) {
			for(int i=0; i<M; i++) {
				bw.write(numbers[i]+" ");
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