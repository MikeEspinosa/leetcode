package getmeout;

public class Test_2 {

	public static void main(String[] args) {
		
		//System.out.println(fibonacci(4));
	
		System.out.println(solution("889901"));
	}
	
    public static int solution(String S) {
        // write your code in Java SE 8
    	
    	int length = S.length();    	
    	int largestPair = 0;
    	for (int i=0; i < (length - 1); i++) {
    		
    		String numberPair = S.substring(i, i + 2);
    		System.out.println(numberPair);
    		int numberPairInt = Integer.parseInt(numberPair);
    		
    		if (numberPairInt > largestPair) {
    			largestPair = numberPairInt;
    		}    		
    	}
    	    	
    	return largestPair;
    }
}
