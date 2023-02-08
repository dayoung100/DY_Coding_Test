package com.ssafy.offline03;

import java.util.Scanner;

public class BJ15650 {

	static int N, M;
	static int[] numbers;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		numbers = new int[M];
		
		comb(0, 0);
	}

	private static void comb(int cnt, int start) {
		if(cnt == M) {
			for(int x=0; x<M; x++) {
				System.out.print(numbers[x]+" ");
			}
			System.out.println();
			return;
		}
		for (int i = start; i < N; i++) {
			numbers[cnt] = i+1;
			comb(cnt+1, i+1);
		}
		
	}

}