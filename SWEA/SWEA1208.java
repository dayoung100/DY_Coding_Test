import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;


class SWEA1208
{
	static int N = 100;
	public static void main(String args[]) throws Exception
	{
		
		Scanner sc = new Scanner(System.in);

		for(int tc = 1; tc <= 10; tc++)
		{
			int dump = sc.nextInt();
			int diff = 100;
			int[] blocks = new int[N];
			for(int i=0; i<N; i++) blocks[i] = sc.nextInt();
			
			for(int j=0; j<dump; j++) {
				Arrays.sort(blocks);
				//System.out.println(j+": "+Arrays.toString(blocks));
				
				blocks[0] = blocks[0]+1;
				blocks[N-1] = blocks[N-1]-1;
				
				//Arrays.sort(blocks);
				//System.out.println(j+": "+Arrays.toString(blocks));
				if(blocks[0] != blocks[1]) {
					int temp = 0;
					temp = blocks[0];
					blocks[0] = blocks[1];
					blocks[1] = temp;
				}
				if(blocks[N-1] != blocks[N-2]) {
					int temp = 0;
					temp = blocks[N-1];
					blocks[N-1] = blocks[N-2];
					blocks[N-2] = temp;
				}
				///System.out.println(j+": "+Arrays.toString(blocks));
				diff = blocks[N-1]-blocks[0];
				if(diff < 2) break;
			}
			
			System.out.println("#"+tc+" "+diff);
			// 5 8 3 1 5 6 9 9 2 2 4
		}
	}
}