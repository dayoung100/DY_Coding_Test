import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//baekjoon 1920 수 찾기
public class BOJ_1920 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine());
		int[] data = new int[n];
		String[] str = br.readLine().split(" ");
		for(int i=0; i<n; i++) data[i] = Integer.parseInt(str[i]);
		
		int m = Integer.parseInt(br.readLine());
		int[] target = new int[m];
		String[] str2 = br.readLine().split(" ");
		for(int i=0; i<m; i++) target[i] = Integer.parseInt(str2[i]);
		
		Arrays.sort(data);
		
		for(int i=0; i<m; i++) {
			if(Arrays.binarySearch(data, target[i]) < 0) System.out.println(0);
			else System.out.println(1);
		}
	}
}