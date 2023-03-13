import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//baekjoon B3 10250 ACM 호텔
public class BJ_B3_10250 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			String[] ch = br.readLine().split(" ");
			int H = Integer.parseInt(ch[0]);
			int W = Integer.parseInt(ch[1]);
			int N =Integer.parseInt(ch[2]);
			int floor = N%H == 0 ? H : N%H;
			int room = N%H == 0 ? N/H : N / H + 1;
			String roomNo = room >= 10 ? Integer.toString(room) : "0".concat(Integer.toString(room));
			System.out.println(Integer.toString(floor).concat(roomNo));
		}
	}

}
/* 17
1 1 1
99 99 9800
10 10 99
2 2 4
3 70 144
6 12 12
6 12 10
6 12 72
30 50 72
2 10 2
2 10 4
2 10 20
1 99 21
2 1 2
1 11 11
10 10 1
2 82 18
===>output
101
9899
910
202
348
602
402
612
1203
201
202
210
121
201
111
101
209
 */