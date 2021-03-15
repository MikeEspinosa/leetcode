package com.sample.java;

import java.math.BigDecimal;

import com.sample.java8.pojo.Staff;

public class PassByValueSample {

	/**
	 * Proof that java is always a pass-by-value
	 * 
	 * @param args
	 */
	public static void main (String[] args) {
		
		// create staff "mkyong"
		Staff a = new Staff("mkyong", 30, new BigDecimal(10000));
		
		// replace staff "mkyong" for "mike"
		replaceStaff(a);
		
		// staff still comes out as "mykong"
		System.out.println(a.getName());
		assert a.getName().equalsIgnoreCase("mkyong");
		
	}
	
	public static void replaceStaff(Staff a) {
		
		// the reference of a is replaced, but not the original Object a itself!
		a = new Staff("mike", 2, new BigDecimal(3000));
	}
}
