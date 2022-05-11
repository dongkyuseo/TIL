package sec02.exam04;

public class CharExample {

	public static void main(String[] args) {
		
		char c1 = 'A'; // '' 로 붙여줘야 char type이 됨 ""는 문자열이 되어서 char타입으로 저장 불가
		char c2 = 65;
		char c3 = '\u0041'; //유니코드의 또다른 방식중 하나
		
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
		
		char c4 = '가';
		char c5 = 44032;
		char c6 = '\uAC00';
		
		System.out.println(c4);
		System.out.println(c5);
		System.out.println(c6);

	}

}
