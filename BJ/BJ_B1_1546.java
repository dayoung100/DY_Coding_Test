import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//baekjoon B1 1546 평균
public class BJ_B1_1546 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		double max = 0;
		String[] scores = br.readLine().split(" ");
		double[] scoresInt = new double[N]; 
		for(int i=0; i<N; i++) {
			scoresInt[i] = Double.parseDouble(scores[i]);
			if(scoresInt[i] > max) max = scoresInt[i];
		}
		double sum = 0;
		for(int i=0; i<N; i++) {
			double afterNum = scoresInt[i] / max * 100;
			scoresInt[i] = afterNum; 
			sum += afterNum;
		}
		double ans = sum/N;
		System.out.println(ans);
	}
	
}
