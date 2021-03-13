package getmeout;

import java.util.HashMap;
import java.util.Map;

public class Test_3 {

	public static void main(String[] args) {
		

		int[] myNum = {5,1,4,6,3,7,1};

		System.out.println(solution(myNum));
	}
//https://stackoverflow.com/questions/35209524/finding-two-non-subsequent-elements-in-array-which-sum-is-minimal
    public static int solution(int[] A) {
        // write your code in Java SE 8
    	int answer = 0;
    	
    	HashMap<String, Integer> combination = new HashMap<String, Integer>();
    	
    	for (int i =0; i < A.length ; i++) {
    		
    		for (int j=0; j < A.length; j ++) {
    			if (i+1 == j || i-1 == j || i == j ) {
    				break;
    			}
    			
    			String key = null;
    			if (i > j) {
    				key = Integer.toString(j) +  Integer.toString(i); 
    			} else if (j < i) {
    				key = Integer.toString(i) +  Integer.toString(j); 
    			}
    			
    			if (!combination.containsKey(key)) {
    				combination.put(key, A[j] + A[i]);
    			}
    			
    		}
    		
    	}
    	

       	for (Map.Entry<String, Integer> entry : combination.entrySet()) {
       		
       		int value =  entry.getValue();
       		if (answer > value || answer == 0) {
       			answer = value;
       		}
       		
       	}
    	
       	return answer;
    }
}
