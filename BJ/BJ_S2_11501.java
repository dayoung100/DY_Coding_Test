package com.dy.bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//baekjoon S2 11501 주식
public class BJ_S2_11501 {
	static int N = 0;
	static int[] price;	//날 별 주가
	static int[] partMax; //구간 별 최대 이익 기댓값
	static int[] visited; //방문체크
	static long maxProfit; //최대 이익
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); //출력용
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int t=0; t<T; t++) {
			maxProfit = 0; //초기화
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			price = new int[N];
			partMax = new int[N];
			visited = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int n=0; n<N; n++) price[n] = Integer.parseInt(st.nextToken());
			//-----t별로 입력완료-----
			dfs(0, 0, 0, 0);
			sb.append(maxProfit);
			sb.append("\n");
		}
		System.out.println(sb);
		br.close();
	}
	
	//idx: 현재 날짜
	//nowProfit: 현재까지의 이익
	//nowExpense: 현재까지의 비용
	//nowHave: 현재 갖고 있는 주식
	public static void dfs(int idx, int nowProfit, int nowExpense, int nowHave) {
		//종료 조건
		if(idx == N) {
			//계산
			maxProfit = Math.max(maxProfit, nowProfit-nowExpense);
			return;
		}
		
		//하루에 3가지 중 하나의 액션을 취함
		for(int a=0; a<3; a++) {
			switch(a) {
			case 0: //하나 구매하기
				dfs(idx+1, nowProfit, nowExpense+price[idx], nowHave+1);
				break;
			case 1: //원하는 만큼 팔기(전부 팔기)
				dfs(idx+1, nowProfit+price[idx]*nowHave, nowExpense, 0);
				break;
			default: //아무것도 안하기
				dfs(idx+1, nowProfit, nowExpense, nowHave);
				break;
			}
		}
	}
}
/*
 * 할 수 있는 행동
 * 1. 주식 하나 사기
 * 2. 원하는 만큼  팔기
 * 3. 아무것도 안하기
 * T: 테케 수 / N: 날의 수 / N개의 날 별 주가
 * 최대 이익 구하기(부호 있는 64bit 정수형으로 표현 가능)
 * -----------------------------
 * 일 별로 1,2,3중 하나를 하기 -> dfs -> 최대이익 계산
 * N<=1,000,000이라 다 보면 너무 클듯..
 * 백트래킹 -> 이후의 기댓값 계산..... 구간합...?
 * 구간: n개의 주를 갖고 있을 때 그 이후에서 얻을 수 있는 최대이익은..
 * dfs하면서 그때마다 루트의 최대이익을 저장 -> 기댓값으로 이용
 */