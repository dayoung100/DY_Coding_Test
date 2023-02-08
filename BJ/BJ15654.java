import java.util.Arrays;
import java.util.Scanner;

public class BJ15654 {

	static int N, M;
	static int[] numbers, inputs;
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		numbers = new int[M];
		inputs = new int[N];
		visited = new boolean[N];
		for(int i=0; i<N; i++) inputs[i] = sc.nextInt();
		Arrays.sort(inputs);
		perm(0);
	}

	private static void perm(int cnt) {
		if(cnt == M) {
			for(int i=0; i<M; i++) {
				System.out.print(numbers[i]+" ");
			}
			System.out.println();
			return;
		}
		for(int i=0; i<N; i++) {
			if(visited[i]) continue;
			
			visited[i] = true;
			numbers[cnt] = inputs[i];
			perm(cnt+1);
			visited[i] = false;
		}
		
	}

}