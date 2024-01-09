package com.dy.bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//baekjoon S3 9095 1, 2, 3 더하기
public class BJ_S3_9095 {
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		//dp미리 구하기
		dp = new int[12];
		dp[1] = 1; //1
		dp[2] = dp[1]+1; //2
		dp[3] = dp[2]+dp[1]+1; //4
		for(int i=4; i<=11;i++) dp[i] = dp[i-1]+dp[i-2]+dp[i-3];
		
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			sb.append(dp[n]+"\n");
		}
		System.out.println(sb);
		br.close();
	}

}
/* n은 양수이고 11보다 작음
 * 1,2,3 중 하나 이상 사용 + 순서 달라도 1개의 경우의 수로 취급
 * dp[n] : n을 1,2,3중 하나 이상의 합으로 표현하는 경우의 수
 * dp[1] = 1 : 1
 * dp[2] = 2 : 1+1, 2
 * dp[3] = 4 : 1+1+1, 2+1, 1+2, 3
 * dp[4] = 7 : 1+1+1+1, 1+1+2, 1+2+1, 2+1+1, 2+2, 1+3, 3+1
 * dp[5] = 13: 1+1+1+1+1, 1+1+2+1, 1+2+1+1, 2+1+1+1, 2+2+1, 1+3+1, 3+1+1,
 * 			   1+1+1+2, 1+2+2, 2+1+2, 3+2
 * 			   1+1+3, 2+3
 * -------------------------
 * dp[n] = dp[n-1] + dp[n-2] + dp[n-3]
 */