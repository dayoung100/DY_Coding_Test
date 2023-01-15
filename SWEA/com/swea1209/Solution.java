package com.swea1209;

import java.util.Scanner;
import java.io.FileInputStream;
import java.util.*;

public class Solution {

	public static void main(String[] args) {
		
		final int NUM = 100;
		Scanner sc = new Scanner(System.in);
		
		for(int test_case = 1; test_case <= 10; test_case++)
		{
			/////////////////////////////////////////////////////////////////////////////////////////////
			int T;
			T=sc.nextInt();
			
			int max = 0;
			int r_max = 0;
			int c_max = 0;
			int d_max = 0;
			int[][] arr = new int[NUM][NUM];
			
			//input
			for(int i=0; i<NUM; i++) {
				for(int j=0; j<NUM; j++) {
					int input = sc.nextInt();
					arr[i][j] = input;
				}
			}
			
			//dia
			int d_sum1 = 0;
			int d_sum2 = 0;
			
			for(int i=0; i<NUM; i++) {
				//row, col
				int r_sum = 0;
				int c_sum = 0;
				for(int j=0; j<NUM; j++) {
					r_sum += arr[i][j];
					c_sum += arr[j][i];
				}
				if(r_max < r_sum) r_max = r_sum;
				if(c_max < c_sum) c_max = c_sum;
				
				//dia
				d_sum1 += arr[i][i];
				d_sum2 += arr[i][NUM-1-i];
			}
			d_max = Math.max(d_sum1, d_sum2);
			
			max = Math.max(r_max, Math.max(c_max, d_max));
			
			//System.out.println("r:"+r_max+" c: "+c_max+" d:"+d_max+" - max:"+max);
			
			System.out.println("#"+T+" "+max);
			//System.out.println(Arrays.deepToString(arr));
			/////////////////////////////////////////////////////////////////////////////////////////////

		}

	}

}
