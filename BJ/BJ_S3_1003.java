import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//BJ S3 1003 피보나치 함수
public class BJ_S3_1003 {
	
	static int cnt0, cnt1;
	static int[] input;
	static int[] memoNum, memo0, memo1;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		input = new int[T];
		int max = 0;
		for(int tc=0; tc<T; tc++) {
			int inputNum = Integer.parseInt(br.readLine());
			input[tc] = inputNum;
			max = Math.max(max, inputNum);
		}
		//-------input end------------
		memoNum = new int[max+1];
		memo0 = new int[max+1];
		memo1 = new int[max+1];
		Arrays.fill(memoNum, -1);
		Arrays.fill(memo0, -1);
		Arrays.fill(memo1, -1);

		for(int tc=0; tc<T; tc++) {
			cnt0 = 0;
			cnt1 = 0;
			fibbonacci(input[tc]);
			System.out.println(cnt0 + " " + cnt1);
		}
	}

	private static int fibbonacci(int n) {

		if(memoNum[n] != -1){
			cnt0 += memo0[n];
			cnt1 += memo1[n];
			return memoNum[n];
		} else {
			if(n==0) {
				cnt0++;
				return 0;
			} else if(n==1){
				cnt1++;
				return 1;
			} else {
				memoNum[n] = fibbonacci(n-1) + fibbonacci(n-2);
				memo0[n] = cnt0;
				memo1[n] = cnt1;
				return memoNum[n];
			}
		}
	}
}
/*
피보나치 재귀 -> 0과 1이 각각 몇번 출력되는가
재귀 함수 직접 만들어서 호출될 때마다 횟수 카운트 (전역)
시간 초과 -> 메모이제이션 이용
*/