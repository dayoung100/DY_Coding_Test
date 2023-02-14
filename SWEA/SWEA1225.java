import java.util.LinkedList;
import java.util.Scanner;

public class SWEA1225 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int tc = 1; tc<=10; tc++) {
			int n = sc.nextInt(); //테케 번호
			LinkedList<Integer> list = new LinkedList<>();
			for(int i=0; i<8; i++) list.add(sc.nextInt());
			
			loop:
			while(true) {
				for(int a=1; a<=5; a++) {
					Integer temp = null;
					temp = Math.max(list.get(0)-a, 0);
					list.remove(0);
					list.add(temp);
					if(temp == 0) break loop;
				}
			}
			System.out.print("#"+n+" ");
			for(int i=0; i<8; i++) System.out.print(list.get(i)+" ");
			System.out.println();
		}
	}
}