import java.util.Arrays;
import java.util.Scanner;

//baekjoon 16435 스네이크버드
public class BOJ_16435 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int l = sc.nextInt();
		int[] fruit = new int[n];
		
		for(int i=0; i<n; i++) fruit[i] = sc.nextInt();
		Arrays.sort(fruit);
		
		for(int i=0; i<n; i++) {
			if(fruit[i] > l) break;
			l++;
		}
		System.out.println(l);
	}

}
/*
 * 과일 하나 => 길이+1
 * 과일 높이 hi(i=인덱스)
 * 자신의 길이 >= 높이 인 과일 먹음
 * 
 * fruit배열을 정렬
 * -> 인덱스 순서대로 탐색
 * -> 길이 l보다 작으면 먹으면서 전진, 그때마다 길이 +1
 * -> 못먹을때까지
 */