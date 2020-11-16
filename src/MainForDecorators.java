public class MainForDecorators {
	private static void showValues(Function func, int n) {
		for (int i = 0; i <= n; i++)
			System.out.print(func.f(i) + " ");
		System.out.println();
	}
	
	public static void main(String[] args) {
		Function func = new DoubleFunction();
		Function func2 = new SumDecorator(func);
		Function func3 = new SumDecorator(func2);
		showValues(func, 4);
		// 0 2 4 6 8 
		showValues(func2, 4);
		// 0 2 6 12 20 
		showValues(func3, 4);
		// 0 2 8 20 40 
	}
}
