import junit.framework.TestCase;
import org.junit.Assert;

import triangle.Triangle;

public class testTriangle extends TestCase {
	
	public final void testIsTriangle() {
		Assert.assertTrue(new Triangle(3,4,5).isTriangle());
	}
	public final void testIsNotTriangle() {
		Assert.assertFalse(new Triangle(3,4,100).isTriangle());
	}
}
