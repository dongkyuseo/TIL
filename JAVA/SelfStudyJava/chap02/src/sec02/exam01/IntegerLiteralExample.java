package sec02.exam01;

public class IntegerLiteralExample {

	public static void main(String[] args) {

		int var1 = 0b1011; //0b로 시작하면 2진수로 인식
		int var2 = 013; //0으로 시작하면 8진수로 인식
		int var3 = 11; //그냥 숫자를 입력하면 10진수로 인식
		int var4 = 0xB3; //0x로 시작하면 16진수로 인식
		
		System.out.println("var1: " + var1);
		System.out.println("var2: " + var2);
		System.out.println("var3: " + var3);
		System.out.println("var4: " + var4);
		
	}

}
