package com.swea1959;
import java.io.FileInputStream;
import java.util.*;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{			
			//-----입력받기-----
			int N, M;
			N = sc.nextInt();
			M = sc.nextInt();
			
			int[] a = new int[N];
			int[] b = new int[M];
			
			for(int i=0; i<N; i++) {
				a[i] = sc.nextInt();
			}
			//System.out.println(Arrays.toString(a));
			
			for(int i=0; i<M; i++) {
				b[i] = sc.nextInt();
			}//입력완
			//System.out.println(Arrays.toString(b));
			
			//-----계산파트-----
			int gap = Math.abs(N-M);
			//System.out.println("gap: "+gap);
			int max=0;
			
			for(int k=0; k<gap+1; k++) {
				int sum = 0;
				if(N <= M) {
					for(int p=0; p<N; p++) {
						sum += a[p] * b[p+k];
						//System.out.println("a[p]("+a[p]+") * b[p+k]("+b[p+k]+") = "+sum);
					}
				}
				else{
					for(int p=0; p<M; p++) {
						sum += a[p+k]*b[p];
						//System.out.println("a["+(p+k)+"]("+a[p+k]+") * b["+p+"]("+b[p]+") = "+sum);
					}
				}
				//System.out.println("sum: "+sum);
				if(max < sum) max = sum;
			}
			System.out.println("#"+test_case+" "+max);
		}

	}

}
