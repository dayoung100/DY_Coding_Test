import java.util.Arrays;
import java.util.Scanner;

//baekjoon B2_1244 백설 공주와 일곱 난쟁이
public class BJ_B2_3040 {
	
	static int[] dwarf = new int[9]; //input
	static int[] temp_dwarf7 = new int[7]; //numbers
	static int[] dwarf7 = new int[7]; //answer
	static int flag = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int i=0; i<9; i++){
			dwarf[i] = sc.nextInt();
		}
		
		find_7(0, 0);
		for(int i=0; i<7; i++) System.out.println(dwarf7[i]);
	}

	private static void find_7(int cnt, int start) {
		if(flag == 1) return;
		
		if(cnt == 7) {
			int temp_sum = 0;
			for(int i=0; i<7; i++) {
				temp_sum += temp_dwarf7[i];
			}
			if(temp_sum == 100) {
				//여기서 그냥 출력하면 됨 copy할 필요 x
				dwarf7 = Arrays.copyOf(temp_dwarf7, 7);
				flag = 1;
			}
			return;
		}
		for(int i=start; i<9; i++) {
			temp_dwarf7[cnt] = dwarf[i];
			find_7(cnt+1, i+1);
		}
	}
}