package com.fbs.dmr.universal;

import org.junit.Test;
import static org.junit.Assert.*;

import com.fbs.dmr.universal.model.party.Party;

/**
 * Unit test for simple Party
 */
public class PartyTest
{
	/*
	@Test
	public void testCompareTo()
	{
		Party p1 = null;
		Party p2 = null;

		p1 = new Party();
		assertEquals(p1.compareTo(null), -1);

		p2 = new Party();
		assertEquals(p1.compareTo(p2), 0);

		p1.setId(1);
		assertEquals(p1.compareTo(p2), -1);

		p2.setId(2);
		assertEquals(p1.compareTo(p2), -1);

		p2.setId(1);
		assertEquals(p1.compareTo(p2), 0);
	}
	*/

	/**
	 * Reflexive - It simply means that the object must be equal to itself,
	 * which it would be at any given instance; unless you intentionally
	 * override the equals method to behave otherwise.
	 * 
	 * Symmetric - It means that if object of one class is equal to another
	 * class object, the other class object must be equal to this class object.
	 * In other words, one object can not unilaterally decide whether it is
	 * equal to another object; two objects, and consequently the classes to
	 * which they belong, must bilaterally decide if they are equal or not. They
	 * BOTH must agree. Hence, it is improper and incorrect to have your own
	 * class with equals method that has comparison with an object of
	 * java.lang.String class, or with any other built-in Java class for that
	 * matter. It is very important to understand this requirement properly,
	 * because it is quite likely that a naive implementation of equals method
	 * may violate this requirement which would result in undesired
	 * consequences.
	 * 
	 * Transitive - It means that if the first object is equal to the second
	 * object and the second object is equal to the third object; then the first
	 * object is equal to the third object. In other words, if two objects agree
	 * that they are equal, and follow the symmetry principle, one of them can
	 * not decide to have a similar contract with another object of different
	 * class. All three must agree and follow symmetry principle for various
	 * permutations of these three classes. Consider this example - A, B and C
	 * are three classes. A and B both implement the equals method in such a way
	 * that it provides comparison for objects of class A and class B. Now, if
	 * author of class B decides to modify its equals method such that it would
	 * also provide equality comparison with class C; he would be violating the
	 * transitivity principle. Because, no proper equals comparison mechanism
	 * would exist for class A and class C objects.
	 * 
	 * Consistent - It means that if two objects are equal, they must remain
	 * equal as long as they are not modified. Likewise, if they are not equal,
	 * they must remain non-equal as long as they are not modified. The
	 * modification may take place in any one of them or in both of them. null
	 * comparison - It means that any instantiable class object is not equal to
	 * null, hence the equals method must return false if a null is passed to it
	 * as an argument. You have to ensure that your implementation of the equals
	 * method returns false if a null is passed to it as an argument.
	 * 
	 * Equals & Hash Code relationship - The last note from the API
	 * documentation is very important, it states the relationship requirement
	 * between these two methods. It simply means that if two objects are equal,
	 * then they must have the same hash code, however the opposite is NOT true.
	 * This is discussed in details later in this article.
	 */
	@Test
	public void testEqualsAndHashCode()
	{
		Party obj1 = null;
		Party obj2 = null;

		obj1 = new Party();
		obj2 = new Party();

		assertTrue(obj1.equals(obj1));
		assertTrue(obj1.equals(obj2));
		assertTrue(obj2.equals(obj1));
		assertEquals(obj1.hashCode(), obj2.hashCode());

		obj1.setId(1);
		assertTrue(obj1.equals(obj1));
		assertFalse(obj1.equals(obj2));
		assertFalse(obj2.equals(obj1));
		assertFalse(obj1.hashCode() == obj2.hashCode());

		obj2.setId(1);
		assertTrue(obj2.equals(obj2));
		assertTrue(obj1.equals(obj2));
		assertTrue(obj2.equals(obj1));
		assertEquals(obj1.hashCode(), obj2.hashCode());

		obj2.setId(3);
		assertTrue(obj2.equals(obj2));
		assertFalse(obj2.equals(obj1));
		assertFalse(obj1.equals(obj2));
		assertFalse(obj1.hashCode() == obj2.hashCode());
	}
}
