package com.cxz.algorithservice;

import java.util.HashMap;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2021/9/13 13:53
 */
public class test437 {
    class TreeNode {
        public int val;
        public test437.TreeNode left;
        public test437.TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, test437.TreeNode left, test437.TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
    private int count(TreeNode root, int sum, int targetSum) {
        if(root == null){
            return 0;
        }
        sum +=root.val;
        System.out.println("sum = " + sum+" targetsum ="+targetSum    );
        int ans = map.getOrDefault(sum-targetSum,0);
        System.out.println("map["+(sum-targetSum)+"] ans = " + ans);
        System.out.println("sum = " + sum+" map[sum] 1 bef ="+map.get(sum)    );
        map.put(sum,map.getOrDefault(sum,0)+1);
        System.out.println("sum = " + sum+" map[sum] 1 aft ="+map.get(sum)    );
        ans += count(root.left,sum,targetSum);
        ans += count(root.right,sum,targetSum);
        System.out.println("sum = " + sum+" map[sum] 2 bef ="+map.get(sum)    );
        map.put(sum,map.getOrDefault(sum,0)-1);
        System.out.println("sum = " + sum+" map[sum] 2 aft ="+map.get(sum)    );
        return ans;
    }



}


