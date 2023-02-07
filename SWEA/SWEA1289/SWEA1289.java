import java.util.Arrays;
import java.util.Scanner;

public class SWEA1289 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		sc.nextLine();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			//t : 메모리 한줄 : target
			String t = sc.nextLine();
			//수정 용이하게 배열로 관리
			int[] tarr = new int[t.length()];
			for(int i=0; i<t.length(); i++) {
				tarr[i] = t.charAt(i)-'0';
			}
			//m의 앞에서부터 초기값과 비교해서
			//다르면 수정을 실행(따로 메서드 빼서)
			//바뀐 걸로 다시 비교(재귀?)
			//그 횟수를 카운트해서 return
			
			//초기값 - 0으로 전체 초기화 됨
			int[] oarr = new int[t.length()];
			
			//바뀐 횟수 cnt
			int cnt = 0;
			
			for(int j=0; j<t.length(); j++) {
				if(tarr[j] != oarr[j]) {
					++cnt;
					switch_bit(j, oarr);
					//System.out.print("target:"+Arrays.toString(tarr));
					//System.out.println("now:"+Arrays.toString(oarr));
				}
				
			}
			System.out.println("#"+test_case+" "+cnt);
		}

	}
	
	static void switch_bit(int idx, int[] arr) {
		int toggle_num;
		if(arr[idx] == 1) toggle_num = 0;
		else toggle_num = 1;
		
		for(int i=idx; i<arr.length; i++) {
			arr[i] = toggle_num;
		}
			
	}

}