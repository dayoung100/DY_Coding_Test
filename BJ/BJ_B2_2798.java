import java.util.Scanner;

//baekjoon B2 2798 블랙잭
public class BJ_B2_2798 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); //카드 장 수
		int M = sc.nextInt(); //합 상한선
		int sum = 0; //합
		int[] inputs = new int[N];
		
		
		for(int n=0; n<N; n++) inputs[n] = sc.nextInt();
		
		for(int i=0; i<N-1; i++) {
			for(int j=i+1; j<N-1; j++) {
				for(int k=j+1; k<N; k++) {
					if(inputs[i]+inputs[j]+inputs[k] <= M) {
						sum = Math.max(sum, inputs[i]+inputs[j]+inputs[k]);
					}
				}
			}
		}
		System.out.println(sum);
	}

}