import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//swea D3 3307. 최장 증가 부분 수열
public class SWEA_D3_3307 {
	
	static int N;
	static int[] arr, lis;
	
	//binarySearch이용해보기
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			lis = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());
			//--입력완료--
			int size = 0;
			for(int i=0; i<N; i++) {
				int idx = Arrays.binarySearch(lis, 0, size, arr[i]);
				idx = Math.abs(idx) - 1; //들어갈 위치를 찾음(인덱스가 0부터 가므로 -1 해줘야)
				if(idx == size) size++; //맨 끝자리에 들어가게 되면 사이즈를 늘려줌
				lis[idx] = arr[i];
			}
			System.out.println("#"+t+" "+size);
		}
	}
}