package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Test_4 {

	public static void main(String[] args) {
			
		System.out.println ("My initial solution");
		System.out.println(solution("aaaabbbb"));
		System.out.println(solution("ccaaffddecee"));
		System.out.println(solution("eeee"));
		System.out.println(solution("example"));
		
		System.out.println("My updated solution");
		System.out.println(solution2("aaaabbbb"));
		System.out.println(solution2("ccaaffddecee"));
		System.out.println(solution2("eeee"));
		System.out.println(solution2("example"));
		
		System.out.println("Solution from internet");
		System.out.println(minCntCharDeletionsfrequency("aaaabbbb"));
		System.out.println(minCntCharDeletionsfrequency("ccaaffddecee"));
		System.out.println(minCntCharDeletionsfrequency("eeee"));
		System.out.println(minCntCharDeletionsfrequency("example"));
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
    
    
    // my updated solution for this.
    public static int solution2(String S) {
    	int answer =0;
    	int length = S.length();
    	
    	// keep track of the count of each character. Hashmap is a good way for keeping track, especially if the
    	// character placement are random.
    	Map<Character, Integer> countMap = new HashMap<Character, Integer>();  	
    	for (int i=0; i< length; i++) {
    		char c = S.charAt(i);  		
    		if (countMap.containsKey(c)) {
    			int count = countMap.get(c);
    			countMap.put(c, ++count);
    		} else {
    			countMap.put(c, 1);
    		}
    	}
    	
    	
    	// We don't need the characters once we have gotten each of their counts
    	// instead we can do this	
    	ArrayList<Integer> sortedList = new ArrayList<Integer>(countMap.values());
    	
        //sort the array list in descending order
        Collections.sort(sortedList, Collections.reverseOrder());
        
        
        // change to normal array so that we can swap values
        Integer[] counts = sortedList.toArray(new Integer[sortedList.size()]);
        for (int i =1; i <counts.length ; i ++) {
        	int lastCount = counts[i-1];
        	// if previous count is greater than or equal to counts, then reduce that count until it is less than the previous count            
        	while (lastCount <= counts[i] && counts[i] >0) {        		
        		counts[i]--;
        		
        		// answer is the number needed to be subtracted
        		answer++;
            }      		
        }
        
        return answer;
        
        
        //iterate from left to right and if count is same then reduce
       /* for (int i = 0; i <counts.length-1 ; i++) {
            for (int j = i+1; j <counts.length ; j++) {
                if (counts[i]>0 && counts[i]==counts[j]){
                    int count = counts[j];
                    counts[j] = count - 1;
                    answer++;
                }else
                    break;
            }
        } 
    	*/
    
    	
    }
    
    // Internet solutions:
    // https://algorithms.tutorialhorizon.com/minimum-deletions-to-make-the-occurrence-of-each-character-unique/
    // https://www.geeksforgeeks.org/minimum-characters-required-to-be-removed-to-make-frequency-of-each-character-unique/
    
	 // Function to find the minimum count of characters required to be deleted to make frequencies of all characters unique
	 public static int minCntCharDeletionsfrequency(String S) {
	   char[] str =  S.toCharArray();
	   int N = S.length();
	   
	   // Stores frequency of each distinct character of str
	   HashMap<Character, Integer> mp = new HashMap<>();
	  
	   // Store frequency of each distinct character such that the largest frequency is present at the top
	   PriorityQueue<Integer> pq =  new PriorityQueue<>((x, y) -> Integer.compare(y, x));
	  
	   // Stores minimum count of characters required to be deleted to make frequency of each character unique
	   int cntChar = 0;
	  
	   // Traverse the String
	   for (int i = 0; i < N; i++) 
	   {
	     // Update frequency of str[i]
	     if(mp.containsKey(str[i]))
	     {
	        mp.put(str[i], 
	        mp.get(str[i]) + 1);
	     }
	     else
	     {
	        mp.put(str[i], 1);
	     }
	   }
	  
	   // Traverse the map
	   for (Map.Entry<Character, Integer> it : mp.entrySet()) 
	   {
	     // Insert current frequency into pq
	     pq.add(it.getValue());
	   }
	  
	   // Traverse the priority_queue
	   while (!pq.isEmpty()) 
	   {
	     // Stores topmost element of pq
	     int frequent = pq.peek();
	  
	     // Pop the topmost element
	     pq.remove();
	  
	     // If pq is empty
	     if (pq.isEmpty()) {
	  
	       // Return cntChar
	       return cntChar;
	     }
	  
	     // If frequent and topmost element of pq are equal
	     if (frequent == pq.peek())
	     {
	       // If frequency of the topmost element is greater than 1
	       if (frequent > 1) 
	       {
	         // Insert the decremented value of frequent
	         pq.add(frequent - 1);
	       } 
	       // Update cntChar
	       cntChar++;
	     }
	   }
	  
	   return cntChar;
	 }
 }
