import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//baekjoon G3 16637 괄호 추가하기
public class BJ_G3_16637 {

	static int N, max;
	static char[] formula;
	static int[] brackets, nums;
	static char[] opers;
	static List<Integer> nums2;
	static List<Character> opers2;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		brackets = new int[N/2];
		nums = new int[N/2+1];
		opers = new char[N/2];
		formula = new char[N];
		formula = br.readLine().toCharArray();
		//----------- 입력 완료 ---------------
		int nIdx = 0, oIdx = 0;
		for(int i=0; i<N; i++) {
			if(Character.isDigit(formula[i])) nums[nIdx++] = formula[i] - '0';
			else opers[oIdx++] = formula[i];
		}
		max = Integer.MIN_VALUE;
		dfs(0, 0);
		
		System.out.println(max);
	}

	/**
	 * dfs탐색으로 여는 괄호위치 뽑기
	 * @param idx: 괄호 위치 인덱스
	 * @param isOpen: 1방금열어서 또 열 수 없음(아직 닫히지 않음) 0열 수 있음
	 */
	private static void dfs(int idx, int isOpen) {
		if(idx == N/2) {
			calcBrackets(); //괄호위치를 보고 괄호안을 먼저 계산
			calcAll(); // 위 결과로 최종 연산
			return;
		}
		
		if(isOpen == 1) {
			brackets[idx] = 0;
			dfs(idx+1, 0);
		}else {
			brackets[idx] = 0; //괄호 없음
			dfs(idx+1, 0);
			brackets[idx] = 1; //여는 괄호
			dfs(idx+1, 1);
		}
	}

	private static void calcAll() {
		int tempSum = nums2.get(0);
		for(int i=0; i<opers2.size(); i++) {
			switch (opers2.get(i)) {
			case '+':
				tempSum += nums2.get(i+1);
				break;
			case '-':
				tempSum -= nums2.get(i+1);
				break;
			case '*':
				tempSum *= nums2.get(i+1);
				break;
			}
		}
		max = Math.max(max, tempSum);
	}

	private static void calcBrackets() {
		nums2 = new ArrayList<>();
		opers2 = new ArrayList<>();
		boolean isOpen = false;
		int temp = 0;
		for(int i=0; i<N/2; i++) { //brackets
			if(!isOpen && brackets[i] == 0) {
				nums2.add(nums[i]);
				opers2.add(opers[i]);
			}
			else if(!isOpen && brackets[i] == 1) {
				isOpen = true;
				temp += nums[i];
			}
			else if(isOpen) {
				switch (opers[i-1]) {
				case '+':
					temp += nums[i];
					break;
				case '-':
					temp -= nums[i];
					break;
				case '*':
					temp *= nums[i];
					break;
				}
				nums2.add(temp);
				opers2.add(opers[i]);
				isOpen = false;
				temp = 0;
			}
		}
		if(isOpen) {
			switch (opers[N/2-1]) {
			case '+':
				temp += nums[N/2];
				break;
			case '-':
				temp -= nums[N/2];
				break;
			case '*':
				temp *= nums[N/2];
				break;
			}
			nums2.add(temp);
		}
		else nums2.add(nums[N/2]);
	}
}
/* 괄호의 위치를 고르고 -> 완탐해서 최대값 찾기
 * 여는괄호는 숫자와 연산자 사이에 위치 -> 숫자 배열의 0번째~N-1번쨰 에 들어갈 수 있음
 * 괄호가 들어갈 수 있는 각 칸에 대해서 1.괄호 없음  / 2.여는 괄호 / 3. 닫는 괄호 -> 3종류
 * 닫는 괄호는 여는 괄호에 대응해서 따라옴 -> 2종류
 * 각 위치에 대해 dfs로 2가지 경우의 수를 다 카운트
 * -> 괄호 위치 선정이 끝나면 계산
 * 계산: 반복 한번 돌면서 괄호 내부를 먼저 계산, 그 후 순차 계산
 * 괄호배열 -> 숫자 배열 -> 연산자 배열 순서로 꺼내오면서 합치기:여는 괄호
 */