package getmeout;

import java.util.HashMap;
import java.util.Map;

public class Test_4 {

	public static void main(String[] args) {
		
		//System.out.println(fibonacci(4));
	
		System.out.println(solution("aaaabbbb"));
		System.out.println(solution("ccaaffddecee"));
		System.out.println(solution("eeee"));
		System.out.println(solution("example"));
	}
	
    public static int solution(String S) {
        // write your code in Java SE 8
    	int answer =0;
    	int length = S.length();
    	Map<Character, Integer> countMap = new HashMap<Character, Integer>();
    	
    	for (int i=0; i< length; i++) {
    		char c = S.charAt(i);  		
    		if (countMap.containsKey(c)) {
    			//System.out.println("contains put " + c);
    			int count = countMap.get(c);
    			//System.out.println("contains put count before " + count);
    			countMap.put(c, ++count);
    			//System.out.println("contains put count after" + count);
    		} else {
    			countMap.put(c, 1);
    		}
    	}
    	
    	Map<Integer, String> numberMap = new HashMap<Integer, String>();
    	for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
    	
    		//System.out.println(entry.getKey() + "-" + entry.getValue());
    		int number = entry.getValue();
    		if (numberMap.containsKey(number)) {
    			//System.out.println(entry.getKey() + " contains number " + number);
	    		while (numberMap.containsKey(number) && number > 0) {
	    			number--;
	    			answer++;
	    			//System.out.println(entry.getKey() + " process number " + number);
	    			//System.out.println(entry.getKey() + " process answer " + answer);
	    		}
	    		
	    		if (!numberMap.containsKey(number) && number > 0) {
	    			numberMap.put(number, null);
	    		}
    		} else {
    			numberMap.put(number, null);
    		}
    		
    		
    	}
    
    	return answer;
    }
    
    /*public static void sortMap(Map numberMap, Map.Entry<Character, Integer> entry) {
		if (numberMap.containsKey(entry.getValue())) {
			
		} else {
			
		}
		
    } */
}
