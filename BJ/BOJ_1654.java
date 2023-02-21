import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//baekjoon 1654 랜선 자르기 - 이진탐색
public class BOJ_1654 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int K, max;
	static long N;
	static long[] kList;
	
	public static void main(String[] args) throws IOException {
		String[] str1 = br.readLine().split(" ");
		K = Integer.parseInt(str1[0]);
		N = Long.parseLong(str1[1]);
		kList = new long[K];
		long kSum = 0;
		long max = 0;
		long start=1, end=0, mid=0;
		
		for(int i=0; i<K; i++) {
			int input = Integer.parseInt(br.readLine());
			kList[i] = input;
			kSum += input;
		}
		
		end = (int) (kSum / N);
		
		while(start <= end) {
			mid = (start + end)/2;
			if(cut(mid) >= N) {
				max = Math.max(max, mid);
				start = mid+1;
			}
			else if(cut(mid) < N) end = mid-1;
		}
		System.out.println(max);
	}

	private static int cut(long target) {
		int sum = 0;
		for(int i=0; i<K; i++) sum += kList[i] / target;
		return sum;
	}
}