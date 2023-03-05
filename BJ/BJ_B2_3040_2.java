import java.util.Arrays;
import java.util.Scanner;

//baekjoon B2_1244 백설 공주와 일곱 난쟁이
public class BJ_B2_3040_2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] data = new int[9];
		int sum = 0;
		
		for(int i=0; i<data.length; i++){
			data[i] = sc.nextInt();
			sum += data[i]; //9명 난쟁이에 대한 수의 합
		}
		
		Arrays.sort(data);
		
		//조합-반복문 이용
		for (int i = 0; i < 8; i++) {
			for(int j=i+1; j<9; j++) {
				if(sum - data[i] - data[j] == 100) {
					for(int k=0; k<9; k++) {
						if(k==i || k==j) continue;
						System.out.println(data[k]);
					}
					return;
				}
			}
		}
	}
}