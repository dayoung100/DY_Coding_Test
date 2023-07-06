import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//baekjoon S4 18110 solved.ac
public class BJ_S4_18110 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if(N == 0) {
			System.out.println(0);
			return;
		}
		int[] opinion  = new int[N];
		for(int n=0; n<N; n++) {
			opinion[n] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(opinion);
		int cutPeopleNum = Math.round((float)N*15/100);
		int sum = 0;
		for(int i=cutPeopleNum; i<N-cutPeopleNum; i++) {
			sum += opinion[i];
		}
		int avg = Math.round((float)sum/(N-cutPeopleNum*2));
		System.out.println(avg);
	}
}
