package com.dy.bj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//baekjoon B3 23971 ZOAC 4
public class BJ_B3_23971 {
	static int H, W, N, M;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken())+1;
		M = Integer.parseInt(st.nextToken())+1;
		//-----입력완료-----
		int a = (double)W / M > W/M ? W/M+1 : W/M ;
		int b = (double)H / N > H/N ? H/N+1 : H/N ;
		bw.write(Integer.toString(a*b));
		bw.flush();
		br.close();
		bw.close();
	}
}
/*
 * 테이블: 행마다 W개씩 H행
 * 거리두기: 세로로 N칸 또는 가로로 M칸 이상 비우고 앉아야
 * 즉 참가자 사이 세로줄 번호의 차가 N보다 크거나 가로줄 번호의 차가 M보다 큰 곳만 착석 가능
 * 최대 명 명을 수용할 수 있는가?
 * ---------------------------
 * 1. 그래프문제
 * N,M<=50,000인거보니 탐색하라는 건 아닐듯...
 * 2. 계산
 * 전체 공간을 (N+1)x(M+1)크기의 사각형으로 나누었을 때
 * 만들어지는 총 사각형의 수가 답
 * 만들어지는 사각형 중 가장 왼쪽 상단에 앉는다고 생각하면
 * 꼭 n+1 x m+1 사이즈가 아니어도 남은 조각들도 개수에 포함해야
 */