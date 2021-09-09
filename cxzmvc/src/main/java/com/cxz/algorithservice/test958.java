package com.cxz.algorithservice;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2021/9/9 11:35
 */
public class test958 {
    class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public boolean isCompleteTreeBFS(TreeNode root){
        if(root==null){
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        boolean reachnull = false;
        queue.offer(root);
        while(!queue.isEmpty()){

            TreeNode tmp = queue.poll();
            if (tmp==null){
                reachnull = true;
            }
            else {
                if(reachnull){
                    return false;
                }

                System.out.println("Poll "+tmp.val);
                TreeNode left = tmp.left;
                TreeNode right = tmp.right;
                queue.offer(left);
                queue.offer(right);
            }

        }
        return true;
    }

}
