package swea1961;

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
			int N;
            N = sc.nextInt();
            
            int[][] arr = new int[N][N];
            
            //입력받기
            for (int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    int a;
                    a = sc.nextInt();
                    arr[i][j] = a;
                }
            }
            
            System.out.println("#"+test_case);
            for(int r=0; r<N; r++) {
            	String tmp = "";
            	for(int c1=1;c1<=N;c1++) {
            		tmp += arr[N-c1][0+r];
            	}
            	tmp += " ";
            	for(int c2=1; c2<=N; c2++) {
            		tmp += arr[N-r-1][N-c2];
            	}
            	tmp += " ";
            	for(int c3=0; c3<N; c3++) {
            		tmp += arr[c3][N-r-1];
            	}
            	
            	System.out.println(tmp);
            }
            /////////////////////////////////////////////////////////////////////////////////////////////
		}

	}

}
