package com.yunlonglee.binTree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lijie
 * @version 1.0
 * @description 101. 对称二叉树
 * @date 22/2/22 5:43 下午
 */
public class SymmetricTree {

    /**
     * 1.检查两个树，两个指针，p向左移动，q向右移动，p=q,同时，p向右移动，q向左移动，p=q,否则，非镜像(非对称)树
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return check(root,root);
    }


    private boolean check(TreeNode p,TreeNode q){
        if(null == p && null == q){
            return true;
        }
        if(null==p || null==q){
            return false;
        }

        return p.val == q.val && check(p.left,q.right) && check(q.left,p.right);
    }



    /**
     * 这种算法会存在，左右子树节点相同值，但是不再镜像分支上的问题
     * @param root
     * @return
     */
    public boolean isSymmetric1(TreeNode root) {
        if(null == root){
            return false;
        }

        List<Integer> lmrs = new ArrayList<>();
        List<Integer> rmls = new ArrayList<>();
        lmrTravel(root,lmrs);
        rmlTravel(root,rmls);
        for(int i=0;i<lmrs.size();i++){
            if(!lmrs.get(i).equals(rmls.get(i))){
                return false;
            }
        }
        return true;
    }

    private void lmrTravel(TreeNode root,List<Integer> lmrs){
        if(root == null){
            return;
        }
        lmrTravel(root.left,lmrs);
        lmrs.add(root.val);
        lmrTravel(root.right,lmrs);
    }

    private void rmlTravel(TreeNode root,List<Integer> mrls){
        if(root == null){
            return;
        }
        lmrTravel(root.right,mrls);
        mrls.add(root.val);
        lmrTravel(root.left,mrls);
    }
}
