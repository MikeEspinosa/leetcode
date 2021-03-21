package com.leetcode;

public class CorrespondingNodeBinaryTreeTest {

    /**
     * https://leetcode.com/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree/
     * 
     * Given two binary trees original and cloned and given a reference to a node
     * target in the original tree.
     *
     * The cloned tree is a copy of the original tree.
     * 
     * Return a reference to the same node in the cloned tree.
     */

    public static void main(String[] args) {

    }

    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        
        int value = target.val;
            
        if (value == cloned.val) {
            return cloned;
        }
        
        TreeNode answer = null;
        
        if (cloned.left != null) {
            answer = getTargetCopy(original, cloned.left, target);
        }
        
        
       if (answer == null && cloned.right != null) {
            answer = getTargetCopy(original, cloned.right, target);
        }
        
        return answer;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
