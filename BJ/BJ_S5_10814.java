import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//baekjoon S5 10814 나이순 정렬
public class BJ_S5_10814 {
	static class Person implements Comparable<Person>{
		int no;
		int age;
		String name;
		
		public Person(int no, int age, String name) {
			this.no = no;
			this.age = age;
			this.name = name;
		}
		@Override
		public int compareTo(Person o) {
			if(this.age == o.age) return this.no - o.no;
			return this.age - o.age;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Person[] people = new Person[N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			people[i] = new Person(i, Integer.parseInt(st.nextToken()), st.nextToken());
		}
		Arrays.sort(people);
		for(int i=0; i<N; i++) System.out.println(people[i].age + " "+ people[i].name);
	}
}
