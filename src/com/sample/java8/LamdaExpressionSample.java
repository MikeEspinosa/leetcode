package com.sample.java8;

public class LamdaExpressionSample {

	/**
	 * Lamda expression notes
	 * 
	 * Basic interface : parameter -> expression body
	 * 
	 * @param args
	 */ 
	  public static void main(String args[]) {
		  LamdaExpressionSample tester = new LamdaExpressionSample();
			
	      //with type declaration
	      MathOperation addition = (int a, int b) -> a + b;
			
	      //with out type declaration
	      MathOperation subtraction = (a, b) -> a - b;
			
	      //with return statement along with curly braces
	      MathOperation multiplication = (int a, int b) -> { return a * b; };
			
	      //without return statement and without curly braces
	      MathOperation division = (int a, int b) -> a / b;
	      
	      // note : these are like different methods!! but defined as variables! hmmm
			
	      System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
	      System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
	      System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
	      System.out.println("10 / 5 = " + tester.operate(10, 5, division));
			
	      //without parenthesis
	      GreetingService greetService1 = message -> {
		      System.out.println("Hello " + message);
		      System.out.println("Mike custom " + message);
	      };
	      			
	      //with parenthesis
	      GreetingService greetService2 = (message) ->
	      System.out.println("Hello " + message);
	      
	     GreetingService greetingService3 = new GreetingService() {
	    	 public void sayMessage(String message) {
	    		 System.out.println(message);
	    	 }
	     };
			
	      greetService1.sayMessage("Mahesh");
	      greetService2.sayMessage("Suresh");
	      
	      
	      
	   }
		
	   interface MathOperation {
	      int operation(int a, int b);
	   }
		
	   interface GreetingService {
	      void sayMessage(String message);
	   }
		
	   private int operate(int a, int b, MathOperation mathOperation) {
	      return mathOperation.operation(a, b);
	   }	   
}
