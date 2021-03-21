package com.leetcode;

import java.util.Optional;

public class AddTwoNumbersTest {
    /**
     * You are given two non-empty linked lists representing two non-negative
     * integers. The digits are stored in reverse order, and each of their nodes
     * contains a single digit. Add the two numbers and return the sum as a linked
     * list. 
     * 
     * You may assume the two numbers do not contain any leading zero, except
     * the number 0 itself.
     * 
     * Input: l1 = [2,4,3], l2 = [5,6,4]
     * Output: [7,0,8]
     * Explanation: 342 + 465 = 807.
     */

    public static void main(String[] args) {
        
        //   352 
        // + 465
        // -----------
        //     7
        
        // edge cases: 
        
        //   333352 
        // + 000465
        // -----------
        //     7
        
        // 1. add the 1st l1 node, to the 1st l2 node -- to the nth time
        // 2. if l1 node and l2 node is greater than 10, then add 1 to next node.
        //
        //
                
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        
        printResult(addTwoNumbers(l1,l2));
        
        
        l2 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9)))))));
        l1 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9))));

        printResult(addTwoNumbers(l1,l2));
        
        
        l1 = new ListNode(0);
        l2 = new ListNode(0);
        
        printResult(addTwoNumbers(l1,l2));
    }
    
    public static void printResult(ListNode listNodeResult) {
        boolean hasNext = true;
        
        while (hasNext) {
            System.out.println(listNodeResult.val);
            if (listNodeResult.next != null) {
                listNodeResult = listNodeResult.next;
                hasNext = true;
            } else {
                hasNext = false;
            }
             
        }
    }
    
    
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2, int carryOver) {
        
        
       System.out.println(l1.val + "," + l2.val + "," + carryOver);
       
       ListNode ln = null;
       int currCarryOver = 0;
       
       int sum = l1.val + l2.val + carryOver;
       if (sum >= 10) {
          currCarryOver = 1;
          sum = sum - 10;
       }
       
      
       System.out.println("new list node created : " + sum + " carryover: " + currCarryOver);
       if (l1.next != null || l2.next != null) {
          ListNode l1next =  Optional.ofNullable(l1.next).
                   orElse(new ListNode(0));
          
          ListNode l2next =  Optional.ofNullable(l2.next).
                  orElse(new ListNode(0));
           
           ln  = new ListNode(sum, addTwoNumbers(l1next, l2next, currCarryOver));
           
       } else {
           if (currCarryOver == 0) {
               ln  = new ListNode(sum);
           } else {
               ln  = new ListNode(sum, new ListNode(currCarryOver)); 
           }
           
       }
        
       
       return Optional.ofNullable(ln).
               orElse(new ListNode(0));
    }
    
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addTwoNumbers(l1, l2, 0); 
    }

    
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
