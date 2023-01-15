import java.util.Scanner;
import java.io.FileInputStream;
import java.util.*;


class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
        
        int[] counts;

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N;
            N=sc.nextInt(); //테케번호
            
            counts = new int[101];
            int max_count = 0;
            int many_num = 0;
            
            for(int i=0; i<1000; i++){
                int n;
                n = sc.nextInt();
                counts[n]++;
                if(counts[n]>max_count) max_count = counts[n];
            }
            
            for(int j=100; j>=0; j--){
                if(counts[j] == max_count){
                    many_num = j;
                    break;
                }
            }
            System.out.println("#"+N+" "+many_num);
		}
	}
}