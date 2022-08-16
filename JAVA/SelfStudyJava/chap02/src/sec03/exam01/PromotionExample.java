package sec03.exam01;

public class PromotionExample {

	public static void main(String[] args) {

		byte byteValue = 10;
		int intValue = byteValue;
		System.out.println("intValue:" + intValue);
		
		char charValue = '가';
		intValue = charValue; // 가의 유니코드형태로 저장됨
		System.out.println("intValue:" + intValue); 
		
		intValue = 30;
		long longValue = intValue;
		System.out.println("longValue:" + longValue);
		
		longValue = 100;
		float floatValue = longValue;
		System.out.println("floatValue:" + floatValue);
		
		floatValue = 100.5F;
		double doubleValue = floatValue;
		System.out.println("doubleValue:" + doubleValue);
	}

}
