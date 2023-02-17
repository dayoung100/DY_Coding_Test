import java.util.Comparator;
import java.util.Queue;
import java.util.Scanner;
import java.util.PriorityQueue;

public class BOJ_11286 {

	static Queue<Integer> h;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); //연산의 횟수
		
		 h = new PriorityQueue<>(new Comparator<Integer>() {
				public int compare(Integer i1, Integer i2) {
					if(Math.abs(i1) > Math.abs(i2)) return 1; //바꿈
					else if(Math.abs(i1) == Math.abs(i2)) return i1-i2;
					else return -1;
				}
			});
		
		for(int i=0; i<n; i++) {
			int input = sc.nextInt();
			
			if(input == 0) {
				if(h.size() == 0) System.out.println(0);
				else System.out.println(h.poll());
			}
			else h.offer(input);
		}
	}
}