package com.dy.bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//baekjoon S2 11501 주식
public class BJ_S2_11501_2 {
	static int N = 0;
	static int[] price;	//날 별 주가
	static int tmpPrice; //당시 이익
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
			tmpPrice = 0;
			st = new StringTokenizer(br.readLine());
			for(int n=0; n<N; n++) price[n] = Integer.parseInt(st.nextToken());
			//-----t별로 입력완료-----
			calcProfit();
			sb.append(maxProfit);
			sb.append("\n");
		}
		System.out.println(sb);
		br.close();
	}
	
	public static void calcProfit() {
		//끝에서 부터 체크
		for(int i=N-1; i>=0; i--) {
			if(price[i] >= tmpPrice) tmpPrice = price[i];
			else maxProfit += tmpPrice-price[i];
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
 * 쭉 구매, 구매한 값보다 비쌀 때 팔기
 * 구매한 값보다 비쌀 때 -> 앞보다 뒤의 숫자가 더 클때
 * (앞의 숫자는 여럿일 수 있음)(11312면 3,2에서 팔아야)
 * ------------------------------
 * 뒤에서 부터 세면서 하락이 시작할 때=상승세의 마지막
 * 뒤에서부터 세면서 당시 큰 값 찾기 -> 가격으로 설정
 * -> 그 이후로는 (가격-구매가)계산해서 이익에 더함
 * -> 가격보다 비싼 가격이 나오면 멈추고 이를 가격으로 재설정
 * -> 반복
 * +) 상승세의 마지막 너머 하락세인 부분은 사도 손해이니 그냥 둬도 OK
 * (..321로 끝나면 2,1은 아예 구매도 판매도 x)
 */