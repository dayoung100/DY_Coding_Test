import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

//baekjoon 1759 암호 만들기
public class BOJ_1759 {

	static int L, C;
	static String[] clist, numbers;
	static String[] aeiou = {"a", "e", "i", "o", "u"};
	static List<String> alist, blist;
	static Set<String> ansSet;
	static List<String> anslist;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		L = sc.nextInt();
		C = sc.nextInt();
		sc.nextLine();
		clist = sc.nextLine().split(" ");
		//--입력 완료
		
		numbers = new String[L-3]; //조합용
		alist = new ArrayList<>(); //모음
		blist = new ArrayList<>(); //자음
		ansSet = new HashSet<>(); //정답
		anslist = new ArrayList<>(); //정답
		
		Arrays.sort(clist);
		for(String c: clist) {
			blist.add(c);
			for(String a: aeiou) {
				if(c.equals(a)) {
					alist.add(c);
					blist.remove(c);
					break;
				}
			}
		}
		
		for(int a=0; a<alist.size(); a++) { //모음 중에서 하나 뽑기
			for(int b1=0; b1<blist.size()-1; b1++) {
				for(int b2=b1+1; b2<blist.size(); b2++) { //자음중에서 2개뽑기
					//나머지 중에서 뽑기
					List<String> temp = new ArrayList<>();
					temp.addAll(alist);
					temp.addAll(blist);
					temp.remove(alist.get(a));
					temp.remove(blist.get(b1));
					temp.remove(blist.get(b2));
					othersComb(0, 0, temp, new int[] {a, b1, b2});
				}
			}
		}
		for(String s: ansSet) anslist.add(s);
		
		Collections.sort(anslist);
		for(String str : anslist) System.out.println(str);
	}

	//a, b1, b2 를 뺀 나머지(temp)에서 조합을 만들어줌, ans에 더하기
	private static void othersComb(int cnt, int start, List<String> temp, int[] selected) {
		if(cnt == L-3) {
			List<String> ansTemp = new ArrayList<>();
			ansTemp.add(alist.get(selected[0]));
			ansTemp.add(blist.get(selected[1]));
			ansTemp.add(blist.get(selected[2]));
			for(int i=0; i<L-3; i++) ansTemp.add(numbers[i]);
			Collections.sort(ansTemp);
			
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<ansTemp.size(); i++) sb.append(ansTemp.get(i));
			ansSet.add(sb.toString());
			return;
		}
		for(int i=start; i<temp.size(); i++) {
			numbers[cnt] = temp.get(i);
			othersComb(cnt+1, i+1, temp, selected);
		}
	}
}
/* 
 * 암호는 서로다른 L개의 소문자들로 구성
 * - 최소 한 개의 모음 + 최소 두 개의 자음
 * - 오름차순 정렬
 * 
 * 예상 문자 종류 C가지
 * 가능성 있는 암호들을 모두 구하기
 * 
 * C개의 문자 모음과 자음으로 분리
 * 모음에서 하나 뽑기 -> 조합(그냥 i)
 * 자음에서 두개 뽑기 -> 조합 -> 14개중 2개 뽑기
 * 나머지 중에서 L-3개 뽑기 -> 조합 -> 12개중 9개 뽑기
 * 
 * 결과는 정렬해서 출력
 */