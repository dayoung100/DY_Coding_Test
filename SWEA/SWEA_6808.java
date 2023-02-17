import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//SWEA 6808 D3 규영이와 인영이의 카드게임
public class SWEA_6808 {
	static List<Integer> card_g; //규영이 덱
	static List<Integer> card_i; //인영이 덱
	static int[] numbers; //인영이 순서 순열 생성용
	static List<int[]> temp_ilist; //인영이 순서 덱들의 리스트
	static int win, lose; //각 경우의 수

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			card_g = new ArrayList<>(); //규영이 덱
			card_i = new ArrayList<>(); //인영이 덱
			temp_ilist = new ArrayList<>(); //인영이 순서 덱들의 리스트
			numbers = new int[9]; //인영이 순서 덱
			win = 0; lose = 0;
			
			for(int n=0; n<9; n++) card_g.add(sc.nextInt());
			for(int n=1; n<=18; n++) {
				if(card_g.contains(n)) continue;
				card_i.add(n);
			}//인풋처리완료
			
			permBit(0, 0);
			for(int i=0; i<temp_ilist.size(); i++) {
				game(i);
			}
			
			System.out.println("#"+tc+" "+win+" "+lose);
		}
	}

	private static void game(int idx) {
		int[] card_itemp = temp_ilist.get(idx);
		int gsum = 0, isum = 0; //각각 점수 합
		
		for(int r=0; r<9; r++) { //Round
			int gc = card_g.get(r);
			int ic = card_itemp[r];
			if(gc > ic) gsum += gc+ic;
			else isum += gc+ic;
		}
		
		if(gsum > isum) win++;
		else if(gsum < isum) lose++;
	}
	
	private static void permBit(int cnt, int flag) {
		if(cnt == 9) {
			int[] temp_i = new int[9];
			for(int i=0; i<9; i++) temp_i[i] = numbers[i];
			temp_ilist.add(temp_i);
			return;
		}
		for(int i=0; i<9; i++) {
			if((flag & (1<<i)) != 0) continue;
			numbers[cnt] = card_i.get(i);
			permBit(cnt+1, flag | 1<<i);
		}
	}

}

/*
9장 vs 9장
9라운드

서로 카드 내고 비교
-> 높은수를 낸 사람: 두 카드의 합
-> 낮은 수를 낸 사람: 점수 0점
규영이: 정수가 주어지는 순서대로 카드를 냄

결과: 점수 높으면 승, 무승부 있음

출력: 경우의 수가 총 몇가지인지 (#t 이기는경우의수 지는경우의수)

------
인영이가 낼 수 있는 모든 경우의 수에 대해 완탐 9!
1. 인영이가 낼 수 있는 경우의 수 구하기 -> 순열뽑기
2. 각각의 경우의 수에 대해 규영이의 승패 구해서 win, lose갱신

*/