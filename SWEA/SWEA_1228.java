import java.util.LinkedList;
import java.util.Scanner;

public class SWEA_1228 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int tc = 1; tc <= 10; tc++)
		{
			int n = sc.nextInt(); //원본 암호문의 길이
			LinkedList<Integer> origin = new LinkedList<>();//원본 암호문
			for(int i=0; i<n; i++) origin.add(sc.nextInt());
			int o = sc.nextInt(); //명령어의 개수
			
			for(int i=0; i<o; i++) {
				String parser = sc.next();
				int x = sc.nextInt();
				int y = sc.nextInt();
				for(int j=0; j<y; j++) {
					int temp = sc.nextInt();
					origin.add(x+j, temp);
				}
			}
			System.out.print("#"+tc+" ");
			for(int i=0; i<10; i++) System.out.print(origin.get(i)+" ");
			System.out.println();
		}
	}
}