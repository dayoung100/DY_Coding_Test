import java.util.Scanner;

//baekjoon S3 N과 M (1)
public class BJ_S3_15649 {
	
	static int N, R;
	static int[] numbers;
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		N = sc.nextInt();
		R = sc.nextInt();
		
		numbers = new int[R];
		visited = new boolean[N];
		
		perm(0);
	}

	private static void perm(int cnt) {
		if(cnt == R) {
			for(int j=0; j<R; j++) {
				System.out.print(numbers[j]+" ");
			}
			System.out.println();
			return;
		}
		for(int i=0; i<N; i++) {
			if(visited[i] == true) continue;
			
			numbers[cnt] = i+1;
			visited[i] = true;
			perm(cnt+1);
			visited[i] = false;
		}
		
	}
}