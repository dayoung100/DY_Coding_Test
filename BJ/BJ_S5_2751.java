import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//baekjoon S5 2751 수 정렬하기 2
//카운팅 정렬
public class BJ_S5_2751 {
	public static void main(String[] args) throws IOException {
    
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
        
		/*
		  -1000000 ~ 1000000
		  기준점 0 = index 100000 으로 생각
		*/
		boolean[] arr = new boolean[2000001];	
        
		int N = Integer.parseInt(br.readLine());
        
		for(int i = 0; i < N; i++) {
			arr[Integer.parseInt(br.readLine()) + 1000000] = true;
		}
 
		for(int i = 0; i < arr.length; i++) {
			if(arr[i]) {
				sb.append((i - 1000000)).append('\n');
			}
		}
		System.out.print(sb);
	}
}
