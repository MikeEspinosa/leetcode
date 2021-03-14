package com.sample.java8;

import java.util.Optional;

public class OptionalSample {
	
	// https://www.baeldung.com/java-optional
	public static void main(String[] args) {
		
		String name = "zalan";
		
		boolean isNameZalan  = Optional.of(name).filter(x -> x.equalsIgnoreCase("zalan")).isPresent();
		System.out.println("Is name Zalan : " + isNameZalan);
		
		Optional.of(name).filter(x -> x.equalsIgnoreCase("zalan")).ifPresent(System.out::println);
		//System.out.println("Is name Zalan2 : " + isNameZalan2);
		
		boolean isNameZalando  = Optional.of(name).filter(x -> x.equalsIgnoreCase("zalando")).isPresent();
		System.out.println("Is name Zalando : " + isNameZalando);
		
		String whatIsMyName  = (String) Optional.ofNullable(null).orElse("zalando");
		System.out.println("What is my name: " + whatIsMyName);
	}

}
