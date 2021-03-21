package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RangeSumQueryMutableTest {

    HashMap<String, Integer> hm = new HashMap<String, Integer>();

    public static void main(String[] args) {
        int[] intArray = {1,3,5,4,5,0,3};
        NumArray num = new NumArray(intArray);
    }

    // note this is only o(n)
    static class NumArray {

        private int[] nums;

        public NumArray() {
            
        }
        public NumArray(int[] nums) {
            // object to store the nums.

            this.nums = nums;

            TreeNode root = initializeTree();
            
            System.out.println(root.totalValue);
            System.out.println(root.leftIndex);
            System.out.println(root.rightIndex);
        }

        public TreeNode initializeTree() {
            // build the base
            java.util.List<TreeNode> arrTemp = new java.util.ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                arrTemp.add(new TreeNode(i, i, nums[i], null, null));
            }

            // build tree bottom - up
            boolean buildingTree=true;
            
            while (buildingTree) {
               java.util.List<TreeNode> buildTemp = new java.util.ArrayList<>();
               
               if (arrTemp.size() <= 1) break;
               int nextSize = (int) Math.ceil((double) arrTemp.size() / 2d);               
               for (int i = 0; i< nextSize ; i++) {
                   
                   TreeNode leftNode = arrTemp.get(i*2);
                   
                   TreeNode rightNode = null;
                   if (arrTemp.size() > (i*2) + 1 )  {
                       rightNode = arrTemp.get((i*2) + 1);
                   }
                   buildTemp.add( new TreeNode(leftNode.leftIndex, 
                           rightNode!=null?rightNode.rightIndex:leftNode.leftIndex, 
                           leftNode.totalValue + ((rightNode!=null)?rightNode.totalValue:0), 
                           leftNode, rightNode));
               }       
               
               arrTemp = buildTemp;
            }
            
           return arrTemp.get(0);
        }

        public void update(int index, int val) {
            // 1. find the index
            // 2. replace
            nums[index] = val;

        }

        public int sumRange(int left, int right) {
            // 1. get the sum of from the left index .... right index
            int sum = 0;
            for (int i = left; i <= right; i++) {
                sum = sum + nums[i];
            }
            return sum;
        }

        class TreeNode {
            int leftIndex;
            int rightIndex;
            int totalValue;

            TreeNode left;
            TreeNode right;

            TreeNode(int l, int r, int t, TreeNode left, TreeNode right) {
                leftIndex = l;
                rightIndex = r;
                totalValue = t;
                this.left = left;
                this.right = right;
            }
        }

    }

    class NumArray2 {

        private int[] nums;
        private java.util.HashMap<String, Integer> hm = new java.util.HashMap<String, Integer>();

        public NumArray2(int[] nums) {
            // object to store the nums.
            this.nums = nums;

            for (int i = 0; i < nums.length; i++) {
                int sum = nums[i];
                hm.put(i + "" + i, sum);
                for (int j = i + 1; j < nums.length; j++) {
                    sum += nums[j];
                    hm.put(i + "" + j, sum);
                }
            }
        }

        public void update(int index, int val) {
            // 1. find the index
            // 2. replace
            nums[index] = val;

            for (int i = 0; i < nums.length; i++) {
                int sum = nums[i];
                hm.put(i + "" + i, sum);
                // System.out.println(i + ":" + sum);
                for (int j = i + 1; j < nums.length; j++) {
                    sum += nums[j];
                    hm.put(i + "" + j, sum);
                    // System.out.println(i+","+j + " :" + sum);
                }
            }

        }

        // you can precalculate it
        public int sumRange(int left, int right) {
            // 1. get the sum of from the left index .... right index
            /*
             * int sum = 0; for (int i = left; i <= right; i++) { sum = sum + nums[i]; }
             * return sum;
             */
            // System.out.println("l,r - " + left + "," + right);
            return hm.get(left + "" + right);
        }
    }

}
