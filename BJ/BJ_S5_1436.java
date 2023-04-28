import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//baekjoon S5 1436 영화감독 숌
public class BJ_S5_1436 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int num = 666;
		int cnt = 0;
		while(true) {
			String temp = String.valueOf(num);
			if(temp.contains("666")) cnt++;
			if(cnt == N) break;
			num++;
		}
		System.out.println(num);
	}

}
