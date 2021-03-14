package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Test_3 {

	public static void main(String[] args) {
		
		int[] myNum1 = {5,1,4,6,3,7,1};
		int[] myNum2 = {5, 2, 4, 6, 3, 7};

		System.out.println("My solution");
		System.out.println(solution(myNum1));
		System.out.println(solution(myNum2));
		
		
		System.out.println("My solution 2");
		System.out.println(solution2(myNum1));
		System.out.println(solution2(myNum2));
		
		//System.out.println("Solution from the internet");
		//System.out.println(findMinNonAdjacentPair(myNum1));
		//System.out.println(findMinNonAdjacentPair(myNum2));
	}

    public static int solution(int[] A) {
        // write your code in Java SE 8
    	int answer = 0;
    	
    	HashMap<String, Integer> combination = new HashMap<String, Integer>();
    	
    	// this is to map the key (index combination) and the value (sum)
    	for (int i =0; i < A.length ; i++) {
    		
    		// this can be improved by starting j = i + 1
    		// this will also make the notation O(n^2)
    		for (int j=0; j < A.length; j ++) {
    			// make sure that adjacent pairs are not part of the hashmap
    			if (i+1 == j || i-1 == j || i == j ) {
    				break;
    			}
    			
    			// make sure that there are no repeating pairs for the keys
        		// this is not need if we make j = i+1
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
    	
    	// once all possible combinations are there, get the lowest sum of all those combination
       	for (Map.Entry<String, Integer> entry : combination.entrySet()) {
       		
       		int value =  entry.getValue();
       		if (answer > value || answer == 0) {
       			answer = value;
       		}
       		
       	}
    	
       	return answer;
    }
    
    public static int solution2(int[] a) {
    	
    	Integer[] mins = new Integer[4];
    	
    	// collect the 4 smallest values by their indexes 
    	for (int i = 0; i < a.length; i++) {		
    		for (int j = 3; j >= 0; j--) {
    			if (mins[j] == null ||  a[i] < a[mins[j]]) {
				
    				if (j < 3) {
    					Integer shiftIndex = mins[j];
    					mins[j + 1] = shiftIndex;
    				}
    			    
    				mins[j] = i;
    			}
    		}
    	}
    	    	
    	// get the biggest result for comparison. 
    	// the last min in the array is probably the biggest number. So we multiply it by 2 and add 1 (to be sure)
    	// note we can also do integer.max
    	int result= a[mins[mins.length - 1]]*2+1;
    	
    	// iterate through the 4 length array to see which non adjacent combination is the largest
        for (int j = 0; j < mins.length; j++) { // O(1)
            for (int k = j + 1; k < mins.length; k++) {
                if (Math.abs(mins[j] - mins[k]) > 1) { // not adjacent
                    if (result    > a[mins[j]]+a[mins[k]]) {
                        result    = a[mins[j]]+a[mins[k]];
                    };
                    if (k < j + 3) return result; // cannot be improved
                    break; // exit inner loop: it cannot bring improvement
                }
            }
        }	
	
    	return result;
    	
    }
    
    
    private void testcode() {
    	// test if this is correct
		/*System.out.println("values");
    	for (int i=0; i< mins.length; i++) {
    		System.out.print(a[mins[i]]+",");
    	}
    	System.out.println("");
		System.out.println("indexes");
      	for (int i=0; i< mins.length; i++) {
    		System.out.print(mins[i]+",");
    	}
      	System.out.println("");*/
    }
    
  //https://stackoverflow.com/questions/35209524/finding-two-non-subsequent-elements-in-array-which-sum-is-minimal
    public static int findMinNonAdjacentPair(int[] a) {
       // var mins = [];
        ArrayList<Integer> mins = new ArrayList<Integer>();
        int[] indexes = new int[4];
        // quick exits:
        //if (a.length < 5) return {error: "no solution, too few elements."};
        //if (a.some(isNaN)) return {error: "non-numeric values given."};
        
        // collect 4 smallest values by their indexes    
        for (int i = 1; i < a.length - 1; i++) { // O(n)
            if (mins.size() < 4 || a[i] < a[mins.get(3)]) {
                // need to keep record of this element in sorted list of 4 elements
                for (int j = Math.min(mins.size() - 1, 2); j >= 0; j--) { // O(1)
                    if (a[i] >= a[mins.get(j)]) break;
                    System.out.println("add here 1");
                    mins.add(mins.get(j));
                }
                System.out.println("add here 2");
                mins.add(i);
            }
        }
        // mins now has the indexes to the 4 smallest values

        System.out.println("mins");
        System.out.println(mins);
        int result = a[mins.get(mins.size() -1)]*2+1;

        System.out.println("result");
        System.out.println(result);
                
        for (int j = 0; j < mins.size(); j++) { // O(1)
            for (int k = j + 1; k < mins.size(); k++) {
                if (Math.abs(mins.get(j) - mins.get(k)) > 1) { // not adjacent
                    if (result    > a[mins.get(j)]+a[mins.get(k)]) {
                        result    = a[mins.get(j)]+a[mins.get(k)];
                      //  result.index1 = mins[j];
                      //  result.index2 = mins[k];
                    };
                    if (k < j + 3) return result; // cannot be improved
                    break; // exit inner loop: it cannot bring improvement
                }
            }
        }
        return result;
    }
}


