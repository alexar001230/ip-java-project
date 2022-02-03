package com.yunlonglee.binTree;

import java.util.ArrayList;
import java.util.List;

import static com.yunlonglee.binTree.BuildTreeFromInAndPost.genBinTree;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 21/1/22 7:12 上午
 */
public class LeftLeafSum {
    public static int sumOfLeftLeaves(TreeNode root) {
        List<List<Integer>> allPath = new ArrayList<>();
        List<Integer> single = new ArrayList<>();
        recursiveGetAllPath(root,single,allPath);
        int sumLeft = 0;
        for(int i=0;i<allPath.size()-1;i++){
            sumLeft += allPath.get(i).get(allPath.get(i).size()-1);
        }
        return sumLeft;
    }

    private static void recursiveGetAllPath(TreeNode root,List<Integer> single,
                                      List<List<Integer>> allPath){
        single.add(root.val);
        if(null == root.left && null == root.right){
            List<Integer> copy = new ArrayList<>();
            for(int i=0;i<single.size();i++){
                copy.add(single.get(i));
            }
            allPath.add(copy);
            return;
        }
        if(null!=root.left){
            recursiveGetAllPath(root.left,single,allPath);
            single.remove(single.size()-1);
        }
        if(null!=root.right){
            recursiveGetAllPath(root.right,single,allPath);
            single.remove(single.size()-1);
        }
    }


    public static void main(String[] args) {
        TreeNode root = genBinTree();
        System.out.println(sumOfLeftLeaves(root));
    }
}
