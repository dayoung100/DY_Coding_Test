import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//baekjoon B1 10989 수 정렬하기 3
public class BJ_B1_10989 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[10001];
		for(int i=0; i<N; i++) {
			int n = Integer.parseInt(br.readLine());
			arr[n]++;
		}
		for(int i=0; i<arr.length; i++) {
			if(arr[i] == 0) continue; 
			int cnt = arr[i];
			for(int j=0; j<cnt; j++) {
				bw.write(String.valueOf(i));
				bw.write("\n");
			}
		}
		
		bw.flush();
		bw.close();
	}
}
