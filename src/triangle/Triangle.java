package triangle;

public class Triangle {
	
	/*
	 * INSTANCE VARIABLES
	 */
	private int a;
	private int b;
	private int c;
	
	/*
	 * CONSTRUCTORS
	 */
	public Triangle(){
		this(0,0,0);
	}
	
	public Triangle(int x, int y, int z) {
		a = x;
		b = y;
		c = z;
	}
	
	//parameter for use with console and file input
	public Triangle(int[] array) {
		int[] sides = new int[array.length];
		for (int j = 0; j < array.length; j++){
			sides[j] = array[j];
		}
		a = sides[0];
		b = sides[1];
		c = sides[2];
	}
		
	/*
	 * METHODS
	 */
	
	/*
	 * If the sum of any two sides is greater than the third,
	 * then you have a triangle.
	 */
	public boolean isTriangle() {
		if (a + b > c &&
			b + c > a &&
			a + c > b) {
			return true;
		}
		else 
			return false;
	}
	
	//All different length sides
	public boolean isScalene() {
		if (a != b &&
			b != c) {
			return true;
		}
		else 
			return false;
	}
	
	//Two sides of the same length, but not three
	public boolean isIsosceles() {
		if ((a == b && a != c) ||
			(a == c && a != b) ||
			(b == c && b != a)) {
			return true;
		}
		else
			return false;
	}
	
	//All three sides of the same length
	public boolean isEquilateral() {
		if (a == b &&
			b == c) {
			return true;
		}
		else 
			return false;
	}
	
	//GET
	public int getSideA() {
		return a;
	}
	
	public int getSideB() {
		return b;
	}
	
	public int getSideC() {
		return c;
	}
	
	//SET
	public void setSideA(int x) {
		x = a;
	}
	
	public void setSideB(int y) {
		y = b;
	}
	
	public void setSideC(int z) {
		z = c;
	}
	
	//toString
	public String toString() {
		return this.a + " " + this.b + " " + this.c;
	}
}
