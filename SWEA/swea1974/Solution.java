package com.swea1974;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.*;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			/////////////////////////////////////////////////////////////////////////////////////////////
			
			//스도쿠 배열
			int[][] sdk = new int[9][9]; 
			for(int i=0; i<9; i++) {
				for(int j=0; j<9; j++) {
					int n = sc.nextInt();
					sdk[i][j] = n;
				}
			}
			
			//검증
			int[] temp = new int[9];
			int answer = 1;
			while(true) {
				
				//1.가로줄 체크
				Loop1:
				for(int i=0; i<9; i++) {
					//임의의 배열 temp에 넣기
					for(int j=0; j<9; j++) {
						temp[j] = sdk[i][j];
					}
					//정렬 후 중복체크
					Arrays.sort(temp);
					int prev=0;
					for(int a=0; a<9; a++) {
						//중복 있으면 전체 break
						if (temp[a] == prev) {
							answer = 0;
							//System.out.println("i: "+i+" /a:"+a);
							break Loop1;
				        }
				        prev = temp[a];
					}
				}
				
				//2.세로줄 체크
				Loop2:
				for(int i=0; i<9; i++) {
					//임의의 배열 temp에 넣기
					for(int j=0; j<9; j++) {
						temp[j] = sdk[j][i];
					}
					//정렬 후 중복체크
					Arrays.sort(temp);
					int prev=0;
					for(int a=0; a<9; a++) {
						//중복 있으면 전체 break
						if (temp[a] == prev) {
							answer = 0;
							//System.out.println("i: "+i+" /a:"+a);
							break Loop2;
				        }
				        prev = temp[a];
					}
				}
				
				//3.네모칸 체크
				Loop3:
				for(int i=0; i<9; i+=3) {
					for(int j=0; j<9; j+=3) {
						//임의의 배열 temp에 넣기(3x3>1x9)
						int idx=0;
						for(int p=0; p<3; p++) {
							for(int q=0; q<3;q++) {
								temp[idx] = sdk[i+p][j+q];
								idx++;
							}
						}
						//System.out.println(Arrays.toString(temp));
					}
					
					//정렬 후 중복체크
					Arrays.sort(temp);
					int prev=0;
					for(int a=0; a<9; a++) {
						//중복 있으면 전체 break
						if (temp[a] == prev) {
							answer = 0;
							//System.out.println("i: "+i+" /a:"+a);
							break Loop3;
				        }
				        prev = temp[a];
					}
				}
				
				//while문 break
				break;
			}
			
			//System.out.println(Arrays.toString(temp));
			System.out.println("#"+test_case+" "+answer);
			
            /////////////////////////////////////////////////////////////////////////////////////////////
		}

	}

}
