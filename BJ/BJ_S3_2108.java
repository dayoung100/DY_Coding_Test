import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

//baekjoon S3 2108 통계학
public class BJ_S3_2108 {

	static int N;
	static int[] numbers, sortedNumbers;
	static HashMap<Integer, Integer> frequency = new HashMap<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		sortedNumbers = new int[N];
		for(int i=0; i<N; i++) {
			int input = Integer.parseInt(br.readLine());
			numbers[i] = input;
			if(frequency.containsKey(input)) frequency.put(input, frequency.get(input)+1);
			else frequency.put(input, 1);
		}
		//------------input end----------
		sortedNumbers = Arrays.copyOf(numbers, N);
		Arrays.sort(sortedNumbers);
		calc1();
		calc2();
		calc3();
		calc4();
	}
	
	//범위
	private static void calc4() {
		System.out.println(sortedNumbers[N-1] - sortedNumbers[0]);
	}
	//최빈값
	private static void calc3() {
		int max = 0;
		List<Integer> ansList = new ArrayList<>();
		for(int key : frequency.keySet()) {
			if(frequency.get(key) > max) max = frequency.get(key);
		}
		for(int key : frequency.keySet()) {
			if(frequency.get(key) == max) ansList.add(key);
		}
		if(ansList.size() == 1) System.out.println(ansList.get(0));
		else {
			Collections.sort(ansList);
			System.out.println(ansList.get(1));
		}
	}
	//중앙값
	private static void calc2() {
		System.out.println(sortedNumbers[N/2]);
	}
	//산술 평균
	private static void calc1() {
		int sum = 0;
		for(int i=0; i<N; i++) sum+=numbers[i];
		double result = (double) sum / N;
		System.out.println(Math.round(result*1000.0/1000.0));
	}

}
