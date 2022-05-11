package sec02.exam07;

public class FloatDoubleExample {

	public static void main(String[] args) {

		float var1 = 3.14f;
		double var2 = 3.14;
		
		
		System.out.println(var1);
		System.out.println(var2);
		
		// 정밀도 테스트
		System.out.println("-------------------------");
		
		float var4 = 0.1234567890123456789f;
		double var5 = 0.1234567890123456789f;

		System.out.println(var4);
		System.out.println(var5);
		
		double var6 = 3e6;
		float var7 = 3e6F;
		double var8 = 2e-3;
		
		System.out.println(var6);
		System.out.println(var7);
		System.out.println(var8);
		
	}

}
