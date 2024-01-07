package com.dy.bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//baekjoon S5 11723 집합
public class BJ_S5_11723 {
	static int s = 0; //공집합 S-비트마스킹 이용
	//static int fullS = 0b111111111111111111110; //20개가 1인경우
	//static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken()); //명령어 수
		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			String order = st.nextToken();
			int x = 0;
			if(!order.equals("all") && !order.equals("empty")) x = Integer.parseInt(st.nextToken());
			calc(order, x);
		}
		System.out.println(sb);
		br.close();
	}
	
	public static void calc(String order, int x) throws IOException {
		switch(order) {
		case "add":
			s = s | (1<<x);
			break;
		case "remove":
			s = s & ~(1<<x);
			break;
		case "check":
			if((s & (1<<x)) == 0) sb.append("0");
			else sb.append("1");
			sb.append("\n");
			break;
		case "toggle":
			s = s ^ (1<<x);
			break;
		case "all":
			s=(1<<21)-1;
			break;
		case "empty":
			s=0;
			break;
		default:
			break;
		}
	}
}
/*
 * 1. int 0,1로 구성된 배열 -> 28%에서 시간초과...
 * 2. 비트마스킹
 */
