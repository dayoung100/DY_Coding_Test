import java.util.Scanner;

//baekjoon 1074 Z
public class BOJ_1074 {
	
	static int N, r, c, cnt;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		r = sc.nextInt();
		c = sc.nextInt();
		
		cnt = 0;
		find(0, 0, N, cnt);
		
		System.out.println(cnt);
	}

	private static void find(int x, int y, int n, int count) {
		//전체에서 2*2까지 분할탐색
		if(n == 1) {
			find2(x, y, count);
			return;
		}
		int half_length = (int) Math.pow(2, n-1);
		if(r < x+half_length && c < y+half_length) 
			find(x, y, n-1, count);
		else if(r < x+half_length && c >= y+half_length) 
			find(x, y + half_length, n-1, count += Math.pow(half_length, 2));
		else if(r >= x+half_length && c < y+half_length) 
			find(x + half_length, y, n-1, count += Math.pow(half_length, 2)*2);
		else if(r >= x+half_length && c >= y+half_length) 
			find(x + half_length, y + half_length, n-1, count += Math.pow(half_length, 2)*3);
	}

	private static void find2(int x, int y, int count) {
		//2*2에서 위치 찾기
		if(r == x && c == y) cnt = count;
		else if(r == x && c == y+1) cnt = count+1;
		else if(r == x+1 && c == y) cnt = count+2;
		else if(r == x+1 && c == y+1) cnt = count+3;
	}
}
/*
 * 분할탐색 이용(4분할)
 * r행 c열 -> 4분할 했을 때의 해당 위치를 찾기 -> 이진탐색처럼 4분할탐색
 * r < 2^(N-1) && c < 2^(N-1) => 좌측상단
 * r < 2^(N-1) && c >= 2^(N-1) => 우측상단
 * r >= 2^(N-1) && c < 2^(N-1) => 좌측하단
 * r >= 2^(N-1) && c >= 2^(N-1) => 우측하단
 * -> 없는 영역은 더 파고들 필요 x
 * 
 * -> N == 1일때까지 자르면서 cnt++
 * 좌측 상단 => 패스
 * 우측 상단 => 좌측상단만큼 cnt up => cnt += 2^(N-1) * 2^(N-1)
 * 좌측 하단 => 상단만큼 cnt up => cnt += 2^(N+1)
 * 우측 하단 => 상단+좌측하단만큼 cnt up => cnt += 2^N*3
 */