package com.dy.bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//baekjoon G5 15989 1, 2, 3 더하기 4
public class BJ_G5_15989 {
	static int n;
	static int[][] dp; //dp배열(1부터 쓰기 위해 0포함)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		//dp 미리 세팅(값은 모든 테케에서 항상 같음)
		dp = new int[10001][4];
		dp[1][1] = dp[2][1] = dp[3][1] = dp[2][2] = dp[3][2] = dp[3][3] = 1;
		for(int i=4; i<=10000; i++) {
			dp[i][1] = dp[i-1][1];
			dp[i][2] = dp[i-2][1] + dp[i-2][2];
			dp[i][3] = dp[i-3][1] + dp[i-3][2] + dp[i-3][3];
		}
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			sb.append(dp[n][1] + dp[n][2] + dp[n][3]);
			sb.append("\n");
		}
		System.out.println(sb);
		br.close();
	}
}
/* 
 * 정수 n을 1,2,3의 합으로 나타내는 방법의 수
 * 조건1. 수를 1개 이상 사용해야
 * 조건2. 수의 순서만 다른 것은 같은 경우로 침
 * ----------------------------
 * 중복 조합
 * 눈이 1,2,3만 있는 주사위를 굴려서 나오는 경우의 수(중복 허용)와 같음
 * 그런데 눈의 수의 합이 n인 경우를 찾는 것
 * 조합 -> 재귀
 * -------->다른 접근(dp)-------
 * 3은 다시 1+2, 1+1+1로 쪼갤 수 있음
 * 그럼 3+1은 1+2+1, 1+1+1+1이 될 수 있음
 * 중복은 어떻게 찾는가 -> 3이있는 경우, 2가 있는 경우 따로 체크
 * ---------------------
 * 3이 있는 경우 : 1222333등 OK
 * 2가 있는 경우 : 11122등 OK(3있으면 안됨)
 * 1이 있는 경우 : 1...1만 OK(3,2가 있으면 안됨)
 * n = n-1 + 1 = n-2 + 2 = n-3 + 3
 * func1(n) = func1(n-1)
 * func2(n) = func2(n-2) + func1(n-2)
 * func3(n) = func3(n-3) + func2(n-3) + func1(n-3)
 * - n=6일 때 func2(4)중 하나=2+2 => 2+2에 +2하면 6이됨
 * - n=6일 때 func1(4)중 하나=2+1+1 => 2+1+1에 +2하면 6이됨
 * 이걸 dp로.. dp[n][2] = dp[n-2][2]+dp[n-2][1] 로 표현 가능
 */