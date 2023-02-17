import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//4012. [모의 SW 역량테스트] 요리사
public class SWEA_4012 {
	
	static int N, res;
	static int[] numbers;
	static List<List<Integer>> menuAList, menuBList; //요리A,B로 가능한 조합의 리스트
	static int[][] s_table;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc=1; tc<=T; tc++) {
			N = sc.nextInt(); //재료의 수
			res = Integer.MAX_VALUE;
			numbers = new int[N/2];
			menuAList = new ArrayList<>();
			menuBList = new ArrayList<>();
			s_table = new int[N][N];
			for(int r=0; r<N; r++) {
				for(int c=0; c<N;c++) s_table[r][c] = sc.nextInt();
			}//입력처리완료
			
			comb(0,0);
			for(List<Integer> m : menuAList) {
				List<Integer> temp = new ArrayList<>();
				for(int i=0; i<N; i++) {
					if(!m.contains(i)) temp.add(i);
				}
				menuBList.add(temp);
			}
			
			for(int i=0; i<menuAList.size(); i++) check(i);
			
			System.out.println("#"+tc+" "+res);
		}
		
	}

	private static void check(int idx) {
		List<Integer> menuA = menuAList.get(idx);
		List<Integer> menuB = menuBList.get(idx);
		
		int sumA = 0;
		int sumB = 0;
		
		for(int i : menuA) for(int j : menuA) sumA += s_table[i][j];
		
		
		for(int i : menuB) for(int j : menuB) sumB += s_table[i][j];
		
		res = Math.min(Math.abs(sumA-sumB), res);
	}

	private static void comb(int cnt, int start) {
		if(cnt == N/2) {
			List<Integer> temp = new ArrayList<>();
			for(int i=0; i<N/2; i++) temp.add(numbers[i]);
			menuAList.add(temp);
			return;
		}
		for(int i=start; i<N; i++) {
			numbers[cnt] = i;
			comb(cnt+1, i+1);
		}
	}

}
/*
 * n개의 식재료를 n/2개씩으로 나눔 -> 조합
 * 재료 i와 재료 j와...의 시너지 = Sij+Sji+...
 * 맛 = 시너지의 합
 * 
 * 두 요리로 나누었을 때 맛의 차이가 최소가 될때를 찾기
 * 
 * 출력: 맛의 차이의 최솟값
 */