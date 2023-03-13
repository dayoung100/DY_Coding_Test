package com.dy.bj;

import java.util.Scanner;

//baekjoon B3 1085 직사각형에서 탈출
public class BJ_B3_1085 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int y = sc.nextInt();
		int w = sc.nextInt();
		int h = sc.nextInt();
		
		int min = Integer.MAX_VALUE;
		min = Math.min(x, Math.min(y, Math.min(w-x, h-y)));
		
		System.out.println(min);
	}

}
/* 직사각형의 경계선까지 가는 거리의 최솟값
 * = x,y와 경계선까지의 거리 = 점과 직선 사이의 거리
 * 네 변에 대해 다 구해보고 최솟값 고르기
 * 평행선이니까 네 변은 알 수 있음(0, 0, w, h)
 */