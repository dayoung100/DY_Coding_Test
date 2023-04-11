import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//beakjoon B2 1978 소수 찾기
public class BJ_B2_1978 {
	
	static int N;
	static int[] numbers;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int max = 0;
		for(int i=0; i<N; i++) {
			int input = Integer.parseInt(st.nextToken());
			if(input == 1) input = 0; //1이면 소수가 될 수 없으니 0으로
			numbers[i] = input;
			max = Math.max(max, input);
		}
		int divNum = 2;
		while(divNum <= max) {
			for(int i=0; i<N; i++) {
				if(numbers[i] == 0) continue; //1과 자기 자신 외의 수로 나눠떨어진 경우
				if(numbers[i] <= divNum) continue; //소수임이 확정된 경우
				if(numbers[i] % divNum == 0) numbers[i] = 0; //나눠 떨어지면 0으로 바꿔버림
			}
			divNum++;
		}
		int cnt = 0;
		for(int i=0; i<N; i++) if(numbers[i] != 0) cnt++;
		
		System.out.println(cnt);
	}
}