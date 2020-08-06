package com.young.algorithm.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * Project:AlgorithmLearn
 * Author: yang-tan
 * Time  : 20-6-13:上午9:00
 * Description: this is ReBuildTree
 */
public class ReBuildTree extends AlgorithmImpl {
    @Override
    public void process(int[] array) {

    }


    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode head = new TreeNode(preorder[0]);
        int size = preorder.length;
        HashMap<Integer,Integer> map = new HashMap<>(size);
        // 把中序遍历存map,方便后续查找下标
        for (int i = 0;i< size; i++) {
            map.put(preorder[i],i);
        }
        return build(0,0,size -1 ,preorder,map);
    }


    public TreeNode build(int in_preroot,int in_left,int in_right,int[] preorder,HashMap<Integer,Integer> map){
        if (in_left < in_right) return null;
        // 根节点在中序中的下标
        TreeNode root = new TreeNode(preorder[in_preroot]);

        int i = map.get(preorder[in_preroot]);// 得到在中序遍历中的索引值
        // 左子树的根节点为in_preroot+1; 左边界还是in_left,右边界为i - 1;
        root.left = build(in_preroot+1,in_left,i-1,preorder,map);
        root.right = build(in_preroot + (i-in_left)+1,i+1,in_right,preorder,map);
        return root;
    }

    public void midOrder(ArrayList<TreeNode> list, TreeNode node){
        if (node != null) {

            midOrder(list,node.left);

            list.add(node);

            midOrder(list,node.right);
        }

    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
